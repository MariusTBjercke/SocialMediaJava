package com.company;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {

    @org.junit.jupiter.api.Test
    void addFriend() {


    }

    @org.junit.jupiter.api.Test
    void generateId() {
        var idList = Main.app.UserList.stream().map(x -> x.UserId).toList();
        var maxId = Collections.max(idList);
        assertEquals(maxId + 1, Main.app.GenerateId(), "List is expected to have users. Return last id + 1.");
    }

    @org.junit.jupiter.api.Test
    void generateId2() {
        Main.app.UserList.clear();
        assertEquals(1, Main.app.GenerateId(), "List is expected to be empty. Return 1.");
    }

    @org.junit.jupiter.api.Test
    void findUser() {
        assertAll("FindUser",
                () -> assertEquals(Main.app.UserList.get(0), Main.app.FindUser(1)),
                () -> assertEquals(Main.app.UserList.get(0), Main.app.FindUser("mariustb"))
        );
    }

    @org.junit.jupiter.api.Test
    void testFindUser() {
    }

    @org.junit.jupiter.api.Test
    void findUserIndex() {
        assertAll("FindUserIndex",
                () -> assertEquals(0, Main.app.FindUserIndex(1)),
                () -> assertEquals(0, Main.app.FindUserIndex("mariustb"))
        );
    }

    @org.junit.jupiter.api.Test
    void testFindUserIndex() {
    }

    @org.junit.jupiter.api.Test
    void main() {
    }
}