package com.example.learning.LLDPractice.CommonDesignPatterns.BuilderPattern;

public class User {
    private String name;
    private int age;
    private String email;
    private User(BuilderUser builderUser) {
        this.name = builderUser.name;
        this.age = builderUser.age;
        this.email = builderUser.email;
    }

    public static class BuilderUser{
        private String name;
        private int age;
        private String email;
        public BuilderUser setName(String name) {
            this.name=name;
            return this;
        }
        public BuilderUser setAge(int age) {
            this.age = age;
            return this;
        }
        public BuilderUser setEmail(String email) {
            this.email = email;
            return this;
        }
        public User build() {
            return new User(this);
        }
    }
}
