package com.mycompany.app;

/**
 * Hello world PULL Request!
 */
public class App
{

    private final String message = "Hello World PULL Request!";

    public App() {}

    public static void main(String[] args) {
        System.out.println(new App().getMessage());
    }

    private final String getMessage() {
        return message;
    }

}
