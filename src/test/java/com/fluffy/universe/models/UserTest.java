package com.fluffy.universe.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setEmail("ndrtprv@gmail.com");
        user.setFirstName("Andrii");
        user.setLastName("Toporov");
        user.setId(1);
        user.setPassword("myPas!1$");
        user.setRoleId(1);
        user.setGender("Male");
        user.setWebsite("www.example.ndrtprv.com");
        user.setBirthday("2003-07-06");
        user.setAddress("Sumy");
        user.setResetPasswordToken("myResPassToken123@!");
    }

    @Test
    void getIdTest() {
        Assertions.assertEquals(1, user.getId());
    }

    @Test
    void setIdTest() {
        user.setId(2);
        Assertions.assertEquals(1, user.getId());
    }

    @Test
    void getRoleIdTest() {
        Assertions.assertEquals(1, user.getRoleId());
    }

    @Test
    void setRoleIdTest() {
        user.setRoleId(0);
        Assertions.assertEquals(0, user.getRoleId());
    }

    @Test
    void getFirstNameTest() {
        Assertions.assertEquals("Andrii", user.getFirstName());
    }

    @Test
    void setFirstNameTest() {
        user.setFirstName("Vladyslav");
        Assertions.assertEquals("Andrii", user.getFirstName());
    }

    @Test
    void getLastNameTest() {
        Assertions.assertEquals("Toporov", user.getLastName());
    }

    @Test
    void setLastNameTest() {
        user.setLastName("Boyko");
        Assertions.assertEquals("Boyko", user.getLastName());
    }

    @Test
    void getGenderTest() {
        Assertions.assertEquals("Male", user.getGender());
    }

    @Test
    void setGenderTest() {
        user.setGender("M");
        Assertions.assertEquals("M", user.getGender());
    }

    @Test
    void getEmailTest() {
        Assertions.assertEquals("ndrtprv@gmail.com", user.getEmail());
    }

    @Test
    void setEmailTest() {
        user.setEmail("ndrtprvbk@gmail.com");
        Assertions.assertEquals("ndrtprvbk@gmail.com", user.getEmail());
    }

    @Test
    void getPasswordTest() {
        Assertions.assertEquals("myPas!1$", user.getPassword());
    }

    @Test
    void setPasswordTest() {
        user.setPassword("Bn3!$Hil");
        Assertions.assertEquals("Bn3!$Hil", user.getPassword());
    }

    @Test
    void getBirthdayTest() {
        Assertions.assertEquals("2003-07-06", user.getBirthday());
    }

    @Test
    void setBirthdayTest() {
        user.setBirthday("2000-01-01");
        Assertions.assertEquals("2000-01-01", user.getBirthday());
    }

    @Test
    void getAddressTest() {
        Assertions.assertEquals("Sumy", user.getAddress());
    }

    @Test
    void setAddressTest() {
        user.setAddress("Kyiv");
        Assertions.assertEquals("Kyiv", user.getAddress());
    }

    @Test
    void getWebsiteTest() {
        Assertions.assertEquals("www.example.ndrtprv.com", user.getWebsite());
    }

    @Test
    void setWebsiteTest() {
        user.setWebsite("www.new.ndr.com");
        Assertions.assertEquals("www.new.ndr.com", user.getWebsite());
    }

    @Test
    void getResetPasswordTokenTest() {
        Assertions.assertEquals("myResPassToken123@!", user.getResetPasswordToken());
    }

    @Test
    void setResetPasswordTokenTest() {
        user.setResetPasswordToken("newSecPasTok12345$%");
        Assertions.assertEquals("newSecPasTok12345$%", user.getResetPasswordToken());
    }
}
