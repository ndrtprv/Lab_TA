package com.fluffy.universe.controllers;

import com.fluffy.universe.exceptions.HttpException;
import com.fluffy.universe.utils.ServerData;
import com.fluffy.universe.utils.SessionUtils;
import io.javalin.http.Context;
import io.javalin.http.HttpCode;
import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

class ExceptionHandlerControllerTest {
    private ExceptionHandlerController controller;
    private Context mockContext;
    private Map<String, Object> mockModel;

    private MockedStatic<SessionUtils> mockedSessionUtils;
    private ServerData mockServerData;

    @BeforeEach
    void setUp() {
        mockContext = mock(Context.class);
        mockModel = new HashMap<>();
        mockServerData = mock(ServerData.class); // створюємо мок ServerData

        // Мокаємо SessionUtils.getCurrentModel(...)
        mockedSessionUtils = mockStatic(SessionUtils.class);
        mockedSessionUtils.when(() -> SessionUtils.getCurrentModel(mockContext)).thenReturn(mockModel);

        // Додаємо моку на getCurrentServerData(...) — інакше буде NPE
        mockedSessionUtils.when(() -> SessionUtils.getCurrentServerData(mockContext)).thenReturn(mockServerData);

        controller = spy(new ExceptionHandlerController(null));
    }

    @AfterEach
    void tearDown() {
        mockedSessionUtils.close();
    }

    @Test
    void testHandleHttpException_shouldSetModelAttributesAndRender() {
        // given
        HttpException exception = new HttpException(HttpCode.BAD_REQUEST, "Invalid input");

        // when
        controller.handleHttpException(exception, mockContext);

        // then
        Assertions.assertEquals("Invalid input", mockModel.get("errorHeading"));
        Assertions.assertEquals("We're sorry, there was an error processing your request.", mockModel.get("errorDescription"));
        Assertions.assertEquals(HttpCode.BAD_REQUEST.getStatus(), mockModel.get("errorCode"));
        Assertions.assertEquals(HttpCode.BAD_REQUEST.getMessage(), mockModel.get("errorPageTitle"));

        // Перевірити, що render був викликаний із правильними аргументами
        verify(controller).render(mockContext, "/views/pages/errors/http.vm");
    }

    @Test
    void testHandlePageNotFoundError_shouldCallHandleHttpExceptionWithNotFound() {
        // spy дозволяє перевірити, що викликаний внутрішній метод
        ExceptionHandlerController spyController = spy(controller);

        spyController.handlePageNotFoundError(mockContext);

        // Перевірити, що handleHttpException був викликаний із NOT_FOUND
        verify(spyController).handleHttpException(
                argThat(e -> e.getHttpCode() == HttpCode.NOT_FOUND && e.getMessage().equals("Oops! Page not found")),
                eq(mockContext)
        );
    }
}