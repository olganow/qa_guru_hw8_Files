package ru.ya.olganow.model;


public class Info {
    public String name;
    public String owner;
    public int age;

    public Passport passport;

    public static class Passport {
        public String id;
        public String isValid;
    }

}