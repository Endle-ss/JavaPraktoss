package org.example;
import java.util.Scanner;
public class User {
    private String username;
    private String password;
    private String role;
    public User() {
    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public boolean authenticate(String enteredPassword) {
        return this.password.equals(enteredPassword);
    }
    public void displayUserInfo() {
        System.out.println("Ужик: " + username);
        System.out.println("Роль: " + role);
    }
    public static User[] getUsersFromConsole(int count) {
        User[] users = new User[count];
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < count; i++) {
            System.out.println("Введи ужик(юзернейм) " + (i + 1) + ":");
            String username = scanner.nextLine();
            System.out.println("Введи пароль чтоб не спалили " + (i + 1) + ":");
            String password = scanner.nextLine();
            System.out.println("Выбери роль для гта рп " + (i + 1) + ":");
            String role = scanner.nextLine();
            users[i] = new User(username, password, role);
        }
        return users;
    }
    public static void main(String[] args) {
        User[] users = getUsersFromConsole(2);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя:");
        String enteredUsername = scanner.nextLine();
        System.out.println("А теперь пароль:");
        String enteredPassword = scanner.nextLine();
        for (User user : users) {
            if (user.getUsername().equals(enteredUsername)) {
                if (user.authenticate(enteredPassword)) {
                    System.out.println("Сакксессфул зашёл.");
                    user.displayUserInfo();
                } else {
                    System.out.println("Хреново зашёл, неправильно ввёл, крч давай по новой Миша.");
                }
                return;
            }
        }
        System.out.println("Ужик не найден");
    }
}