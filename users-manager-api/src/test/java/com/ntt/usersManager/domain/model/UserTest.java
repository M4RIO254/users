package com.ntt.usersManager.domain.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserTest {

    @Test
    void shouldCreateUserCorrectly() {
        User user = new User();
        user.setId(1L);
        user.setName("John");
        user.setEmeal("john@email.com");

        assertEquals(1L, user.getId());
        assertEquals("John", user.getName());
        assertEquals("john@email.com", user.getEmeal());
    }
}