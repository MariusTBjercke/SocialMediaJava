package com.company;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
    public static App app = new App("Facebook");

    // Must throw IOException in order to use BufferedReader
    public static void main(String[] args) throws IOException {
        while (app.CurrentUser == null) {
            app.Login();
            while (app.CurrentUser != null) {
                app.GetCommand();
            }
        }
    }

}
