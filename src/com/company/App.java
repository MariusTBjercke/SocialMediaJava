package com.company;

import java.io.IOException;
import java.util.*;

import static com.company.Main.console;

public class App {
    public String Name;
    public User CurrentUser;
    public String UserInput;
    public List<User> UserList = new ArrayList<>();

    public App(String name) {
        Name = name;
        AddUser("mariustb", "passord", "Marius Bjercke", 28, "marius@bjerckemedia.no", false);
        AddUser("kristofferk", "passord", "Kristoffer K", 28, "kristoffer@kikit.no", false);
    }

    private void AddUser(String username, String password, String name, Integer age, String email, boolean online) {
        UserList.add(new User(GenerateId(), name, username, password, email, age, online));
    }

    public void Logout() {
        CurrentUser.Online = false;
        CurrentUser = null;
        System.out.println("Du er nå logget ut.");
    }

    public void Login() throws IOException {
        for (var user : UserList) {
            System.out.println(user.Username);
        }

        while (CurrentUser == null) {
            System.out.print("Skriv inn brukernavn: ");
            UserInput = console.readLine();
            var index = FindUserIndex(UserInput);
            if (index != -1) {
                var tempUser = UserList.get(index);
                System.out.print("Skriv inn passord: ");
                UserInput = console.readLine();
                if (UserInput.equals(tempUser.Password)) {
                    CurrentUser = tempUser;
                    CurrentUser.Online = true;
                    System.out.println("Velkommen, " + CurrentUser.Name);
                }
            }
            if (CurrentUser == null) {
                System.out.println("Feil brukernavn eller passord, vennligst prøv igjen.");
            }
        }
    }

    public void AddFriend() throws IOException {
        System.out.println("Du kan legge til følgende personer:");
        for (var user : UserList) {
            if (user != CurrentUser) System.out.println("ID: " + user.UserId + " Navn: " + user.Name);
        }
        System.out.print("Skriv inn ID på person: ");
        GetUserInput(false, true);
        var index = FindUserIndex(Integer.parseInt(UserInput));
        if (index != -1) {
            var selectedUser = UserList.get(index);
            CurrentUser.AddFriend(selectedUser.UserId);
            System.out.println(selectedUser.Name + " ble lagt til i vennelisten.");
        } else {
            System.out.println("Fant ikke bruker.");
        }
    }

    public void RemoveUser(Integer id) {
        UserList.remove(FindUser(id));
    }

    public void RemoveUser(String username) {
        UserList.remove(FindUser(username));
    }

    public Integer GenerateId() {
        if ((long) UserList.size() > 0) {
            var idList = UserList.stream().map(x -> x.UserId).toList();
            var maxId = Collections.max(idList);
            return maxId + 1;
        }
        return 1;
    }

    // Find user methods - START
    public User FindUser(Integer id) {
        return UserList.get(FindUserIndex(id));
    }

    public User FindUser(String username) {
        return UserList.get(FindUserIndex(username));
    }

    public Integer FindUserIndex(Integer id) {
        var found = UserList.stream().filter(x -> x.UserId.equals(id)).findAny().orElse(null);
        return UserList.indexOf(found);
    }

    public Integer FindUserIndex(String username) {
        var found = UserList.stream().filter(x -> x.Username.equalsIgnoreCase(username)).findAny().orElse(null);
        return UserList.indexOf(found);
    }
    // Find user methods - STOP

    public void PrintAllUsers() {
        for (var user : UserList) {
            System.out.println(user.Name);
        }
    }

    public void GetUserInput(boolean showName, boolean toLowerChars) {
        if (showName) System.out.print(CurrentUser.Name + ": ");
        try {
            UserInput = toLowerChars ? console.readLine().toLowerCase() : console.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void GetCommand() throws IOException {
        System.out.println("Skriv inn en kommando under.");
        GetUserInput(true, true);

        switch (UserInput) {
            case "logg ut", "logout" -> Logout();
            case "vis venner" -> CurrentUser.PrintFriends();
            case "legg til venn" -> AddFriend();
            default -> {
            }
        }

    }
}
