import java.util.Scanner;

public class CategorySelection {
    private static Scanner scanner = new Scanner(System.in);
    private static String[] categories = {
        "Science",
        "Mathematics", 
        "History",
        "Computer Science",
        "Geography"
    };
    
    public static void main(String[] args) {
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║         CATEGORY SELECTION SYSTEM              ║");
        System.out.println("╚════════════════════════════════════════════════╝\n");
        
        boolean running = true;
        
        while (running) {
            String selectedCategory = selectCategory();
            
            if (selectedCategory != null) {
                System.out.println("\n✓ You have selected: " + selectedCategory);
                System.out.println("This category contains questions about " + getCategoryDescription(selectedCategory));
                System.out.println();
            }
            
            System.out.print("Do you want to select another category? (yes/no): ");
            String response = scanner.nextLine().trim().toLowerCase();
            
            if (!response.equals("yes") && !response.equals("y")) {
                System.out.println("\n✓ Thank you for using Category Selection!\n");
                running = false;
            }
        }
        
        scanner.close();
    }
    
    private static String selectCategory() {
        System.out.println("╔════════════════════════════════════════════════╗");
        System.out.println("║          SELECT A QUIZ CATEGORY                ║");
        System.out.println("╚════════════════════════════════════════════════╝\n");
        
        System.out.println("Available Categories:");
        System.out.println("════════════════════════════════════════════════");
        
        for (int i = 0; i < categories.length; i++) {
            System.out.println("  " + (i + 1) + ". " + categories[i]);
        }
        System.out.println("  0. Cancel");
        
        System.out.println("════════════════════════════════════════════════");
        System.out.print("Enter your choice (0-" + categories.length + "): ");
        
        int choice = getIntInput();
        
        if (choice == 0) {
            System.out.println("\n[INFO] Selection cancelled.\n");
            return null;
        }
        
        if (choice < 1 || choice > categories.length) {
            System.out.println("\n[ERROR] Invalid choice! Please try again.\n");
            return null;
        }
        
        return categories[choice - 1];
    }
    
    private static String getCategoryDescription(String category) {
        switch (category) {
            case "Science":
                return "physics, chemistry, biology, and general science.";
            case "Mathematics":
                return "arithmetic, algebra, geometry, and problem solving.";
            case "History":
                return "world history, important events, and historical figures.";
            case "Computer Science":
                return "programming, algorithms, data structures, and technology.";
            case "Geography":
                return "countries, capitals, continents, and geographical features.";
            default:
                return "various topics.";
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