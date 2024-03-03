import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<User> users = getUsersFromConsole(2);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя:");
        String enteredUsername = scanner.nextLine();
        System.out.println("А теперь пароль:");
        String enteredPassword = scanner.nextLine();
        for (User user : users) {
            if (user.getUsername().equals(enteredUsername)) {
                if (user.authenticate(enteredPassword)) {
                    System.out.println("Авторизация успешна.");
                    user.displayUserInfo();
                    user.performActions();
                } else {
                    System.out.println("Неверный пароль. Попробуйте еще раз.");
                }
                return;
            }
        }
        System.out.println("Пользователь не найден.");
    }

    public static List<User> getUsersFromConsole(int count) {
        List<User> users = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < count; i++) {
            System.out.println("Введите имя пользователя " + (i + 1) + ":");
            String username = scanner.nextLine();
            System.out.println("Введите пароль для пользователя " + (i + 1) + ":");
            String password = scanner.nextLine();
            System.out.println("Выберите роль для пользователя " + (i + 1) + ":");
            System.out.println("1. Продавец");
            System.out.println("2. Покупатель");
            int roleChoice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера
            String role = (roleChoice == 1) ? "Продавец" : "Покупатель";
            if (role.equals("Продавец")) {
                System.out.println("Введите цену товара для продавца " + username + ":");
                double price = scanner.nextDouble();
                users.add(new Seller(username, password, price));
            } else {
                users.add(new Buyer(username, password));
            }
        }
        return users;
    }
}

abstract class User {
    private String username;
    private String password;
    private String role;

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

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public boolean authenticate(String enteredPassword) {
        return this.password.equals(enteredPassword);
    }

    public void displayUserInfo() {
        System.out.println("Имя пользователя: " + username);
        System.out.println("Роль: " + role);
    }

    public abstract void performActions();
}

class Seller extends User {
    private double price;

    public Seller(String username, String password, double price) {
        super(username, password, "Продавец");
        this.price = price;
    }

    @Override
    public void performActions() {
        System.out.println("Действия для продавца:");
        System.out.println("1. Добавить товары в каталог");
        System.out.println("2. Просмотреть заказы");
        System.out.println("Текущая цена товара: " + price);
    }
}

class Buyer extends User {
    public Buyer(String username, String password) {
        super(username, password, "Покупатель");
    }

    @Override
    public void performActions() {
        System.out.println("Действия для покупателя:");
        System.out.println("1. Просмотреть каталог товаров");
        System.out.println("2. Сделать заказ");
    }
}
