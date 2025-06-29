package com.fluffy.universe;

import com.fluffy.universe.controllers.*;
import com.fluffy.universe.exceptions.HttpException;
import com.fluffy.universe.middleware.CSRFFilter;
import com.fluffy.universe.middleware.ModelFilter;
import com.fluffy.universe.utils.ApplicationAccessManager;
import com.fluffy.universe.utils.Configuration;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        Javalin application = Javalin.create(configuration -> {
            configuration.addStaticFiles("/public", Location.CLASSPATH);
            configuration.accessManager(new ApplicationAccessManager());
            Configuration.load(new File("application.properties"));
        });
        ExceptionHandlerController exceptionHandlerController = new ExceptionHandlerController(application);

        application
                .before(context -> {
                    context.header("Content-Security-Policy",
                            "default-src 'self'; " +
                                    "script-src 'self'; " +
                                    "style-src 'self'; " +
                                    "img-src 'self' data:; " +
                                    "font-src 'self'; " +
                                    "connect-src 'self'; " +
                                    "frame-ancestors 'none'; " +
                                    "object-src 'none'; " +
                                    "base-uri 'self';");
                    context.header("X-Frame-Options", "DENY");
                    context.header("X-Content-Type-Options", "nosniff");
                })
                .before(ModelFilter::initializeModel)
                .before(CSRFFilter::verifyToken)
                .before(CSRFFilter::generateToken)
                .exception(HttpException.class, exceptionHandlerController::handleHttpException)
                .error(404, exceptionHandlerController::handlePageNotFoundError)
                .error(500, exceptionHandlerController::handleInternalServerError);

        new HomeController(application);
        new UserController(application);
        new PostController(application);
        new CommentController(application);
        application.start(Configuration.get("application.host"), Configuration.getAsClass("application.port", Integer.class));
    }
}