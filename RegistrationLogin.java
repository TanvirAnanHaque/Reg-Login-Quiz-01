import java.util.HashMap;
import java.util.Scanner;

public class RegistrationLogin {
    private static Scanner scanner = new Scanner(System.in);
    private static HashMap<String, User> userDatabase = new HashMap<>();
    
    public static void main(String[] args) {
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║      REGISTRATION & LOGIN SYSTEM              ║");
        System.out.println("╚════════════════════════════════════════════════╝\n");
        
        boolean running = true;
        
        while (running) {
            System.out.println("════════════════ MAIN MENU ═════════════════════");
            System.out.println("  1. Register");
            System.out.println("  2. Login");
            System.out.println("  3. Exit");
            System.out.println("════════════════════════════════════════════════");
            System.out.print("Enter your choice: ");
            
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    loginUser();
                    break;
                case 3:
                    System.out.println("\nThank you! Goodbye!\n");
                    running = false;
                    break;
                default:
                    System.out.println("\n[ERROR] Invalid choice! Please try again.\n");
            }
        }
        
        scanner.close();
    }
    
    private static void registerUser() {
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║              REGISTRATION                      ║");
        System.out.println("╚════════════════════════════════════════════════╝\n");
        
        System.out.print("Enter Username: ");
        String username = scanner.nextLine().trim();
        
        if (username.isEmpty()) {
            System.out.println("\n[ERROR] Username cannot be empty!\n");
            return;
        }
        
        if (userDatabase.containsKey(username)) {
            System.out.println("\n[ERROR] Username already exists! Please choose a different one.\n");
            return;
        }
        
        System.out.print("Enter Password: ");
        String password = scanner.nextLine().trim();
        
        if (password.isEmpty()) {
            System.out.println("\n[ERROR] Password cannot be empty!\n");
            return;
        }
        
        if (password.length() < 4) {
            System.out.println("\n[ERROR] Password must be at least 4 characters long!\n");
            return;
        }
        
        System.out.print("Confirm Password: ");
        String confirmPassword = scanner.nextLine().trim();
        
        if (!password.equals(confirmPassword)) {
            System.out.println("\n[ERROR] Passwords do not match!\n");
            return;
        }
        
        System.out.print("Enter Full Name: ");
        String fullName = scanner.nextLine().trim();
        
        if (fullName.isEmpty()) {
            System.out.println("\n[ERROR] Full name cannot be empty!\n");
            return;
        }
        
        System.out.print("Enter Email: ");
        String email = scanner.nextLine().trim();
        
        if (email.isEmpty()) {
            System.out.println("\n[ERROR] Email cannot be empty!\n");
            return;
        }
        
        User newUser = new User(username, password, fullName, email);
        userDatabase.put(username, newUser);
        
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║        REGISTRATION SUCCESSFUL!                ║");
        System.out.println("╚════════════════════════════════════════════════╝");
        System.out.println("Welcome, " + fullName + "!");
        System.out.println("Your account has been created successfully.\n");
    }
    
    private static void loginUser() {
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║                 LOGIN                          ║");
        System.out.println("╚════════════════════════════════════════════════╝\n");
        
        System.out.print("Enter Username: ");
        String username = scanner.nextLine().trim();
        
        System.out.print("Enter Password: ");
        String password = scanner.nextLine().trim();
        
        if (!userDatabase.containsKey(username)) {
            System.out.println("\n[ERROR] Username not found! Please register first.\n");
            return;
        }
        
        User user = userDatabase.get(username);
        
        if (user.getPassword().equals(password)) {
            System.out.println("\n╔════════════════════════════════════════════════╗");
            System.out.println("║           LOGIN SUCCESSFUL!                    ║");
            System.out.println("╚════════════════════════════════════════════════╝");
            System.out.println("Welcome back, " + user.getFullName() + "!");
            System.out.println("Email: " + user.getEmail());
            System.out.println();
        } else {
            System.out.println("\n[ERROR] Incorrect password! Please try again.\n");
        }
    }
    
    private static int getIntInput() {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("[ERROR] Please enter a valid number: ");
            }
        }
    }
}

class User {
    private String username;
    private String password;
    private String fullName;
    private String email;
    
    public User(String username, String password, String fullName, String email) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getFullName() {
        return fullName;
    }
    
    public String getEmail() {
        return email;
    }
}