package com.company;

import org.junit.jupiter.api.*;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {

    App app;

    @BeforeEach
    void setUp() {
        app = new App("Facebook");
    }

    @Test
    void addFriend() {
    }

    @Test
    void generateId() {
        var idList = app.UserList.stream().map(x -> x.UserId).toList();
        var maxId = Collections.max(idList);
        assertEquals(maxId + 1, app.GenerateId(), "List is expected to have users. Return last id + 1.");
    }

    @Test
    void generateId2() {
        app.UserList.clear();
        assertEquals(1, app.GenerateId(), "List is expected to be empty. Return 1.");
    }

    @Test
    void findUser() {
        assertAll("FindUser",
                () -> assertEquals(app.UserList.get(0), app.FindUser(1)),
                () -> assertEquals(app.UserList.get(0), app.FindUser("mariustb"))
        );
    }

    @Test
    void testFindUser() {
    }

    @Test
    void findUserIndex() {
        assertAll("FindUserIndex",
                () -> assertEquals(0, app.FindUserIndex(1)),
                () -> assertEquals(0, app.FindUserIndex("mariustb"))
        );
    }

    @Test
    void testFindUserIndex() {
    }

    @Test
    void main() {
    }
}