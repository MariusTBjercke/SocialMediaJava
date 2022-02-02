package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class User {
    public final Integer UserId;
    public String Name;
    public String Username;
    public String Password;
    public String Email;
    public Integer Age;
    public boolean Online;
    private List<Integer> Friends = new ArrayList<>();

    public User(int userId, String name, String username, String password, String email, int age, boolean online) {
        UserId = userId;
        Name = name;
        Username = username;
        Password = password;
        Email = email;
        Age = age;
        Online = online;
    }

    public List<User> GetFriendList() {
        var list = new ArrayList<User>();
        for (var i : Friends) {
            var index = Main.app.FindUserIndex(i);
            list.add(Main.app.UserList.get(index));
        }
        return list;
    }

    public void PrintFriends() throws IOException {
        var list = GetFriendList();
        if (list.stream().count() == 0) {
            System.out.println("Du har ingen venner.");
            return;
        }
        for (var friend : list) {
            System.out.println("ID:" + friend.UserId + " Navn: " + friend.Name);
        }
        System.out.print("Skriv inn ID for mer informasjon eller trykk enter for å gå videre: ");
        Main.app.GetUserInput(false, true);
        var index = Main.app.FindUserIndex(Integer.parseInt(Main.app.UserInput));
        if (index != -1 && !Main.app.UserInput.equals("")) {
            var friend = Main.app.UserList.get(index);
            System.out.println("ID: " + friend.UserId + "\nNavn: " + friend.Name + "\nAlder: " + friend.Age + "\nE-post: " + friend.Email + "\nPålogget: " + (friend.Online ? "Ja" : "Nei"));
        }
    }

    public void PrintFriend(User friend) {

    }

    public void AddFriend(int userId) {
        Friends.add(userId);
    }

    public void RemoveFriend(int userId) {
        Friends.add(userId);
    }
}
