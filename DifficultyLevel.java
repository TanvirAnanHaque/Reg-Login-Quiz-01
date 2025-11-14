import java.util.Scanner;

public class DifficultyLevel {
    private static Scanner scanner = new Scanner(System.in);
    private static String[] difficulties = {"Easy", "Medium", "Hard"};
    private static int[] pointsPerQuestion = {10, 20, 30};
    private static int[] questionCounts = {10, 8, 5};
    
    public static void main(String[] args) {
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║        DIFFICULTY LEVEL SYSTEM                 ║");
        System.out.println("╚════════════════════════════════════════════════╝\n");
        
        boolean running = true;
        
        while (running) {
            String selectedDifficulty = selectDifficulty();
            
            if (selectedDifficulty != null) {
                displayDifficultyInfo(selectedDifficulty);
            }
            
            System.out.print("\nDo you want to select another difficulty? (yes/no): ");
            String response = scanner.nextLine().trim().toLowerCase();
            
            if (!response.equals("yes") && !response.equals("y")) {
                System.out.println("\n✓ Thank you for using Difficulty Level System!\n");
                running = false;
            }
        }
        
        scanner.close();
    }
    
    private static String selectDifficulty() {
        System.out.println("╔════════════════════════════════════════════════╗");
        System.out.println("║       SELECT DIFFICULTY LEVEL                  ║");
        System.out.println("╚════════════════════════════════════════════════╝\n");
        
        System.out.println("Available Difficulty Levels:");
        System.out.println("════════════════════════════════════════════════");
        
        for (int i = 0; i < difficulties.length; i++) {
            System.out.println("  " + (i + 1) + ". " + difficulties[i]);
            System.out.println("     • Points per question: " + pointsPerQuestion[i]);
            System.out.println("     • Number of questions: " + questionCounts[i]);
            System.out.println();
        }
        System.out.println("  0. Cancel");
        
        System.out.println("════════════════════════════════════════════════");
        System.out.print("Enter your choice (0-" + difficulties.length + "): ");
        
        int choice = getIntInput();
        
        if (choice == 0) {
            System.out.println("\n[INFO] Selection cancelled.\n");
            return null;
        }
        
        if (choice < 1 || choice > difficulties.length) {
            System.out.println("\n[ERROR] Invalid choice! Please try again.\n");
            return null;
        }
        
        return difficulties[choice - 1];
    }
    
    private static void displayDifficultyInfo(String difficulty) {
        int index = getDifficultyIndex(difficulty);
        
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║         DIFFICULTY INFORMATION                 ║");
        System.out.println("╚════════════════════════════════════════════════╝");
        System.out.println("\n✓ Selected: " + difficulty);
        System.out.println("════════════════════════════════════════════════");
        System.out.println("Points per question: " + pointsPerQuestion[index]);
        System.out.println("Number of questions: " + questionCounts[index]);
        System.out.println("Maximum score: " + (pointsPerQuestion[index] * questionCounts[index]));
        System.out.println("════════════════════════════════════════════════");
        System.out.println("\n" + getDifficultyDescription(difficulty));
        System.out.println();
    }
    
    private static int getDifficultyIndex(String difficulty) {
        for (int i = 0; i < difficulties.length; i++) {
            if (difficulties[i].equalsIgnoreCase(difficulty)) {
                return i;
            }
        }
        return 0;
    }
    
    private static String getDifficultyDescription(String difficulty) {
        switch (difficulty) {
            case "Easy":
                return "EASY: Perfect for beginners!\n" +
                       "Basic questions to test fundamental knowledge.\n" +
                       "Good for learning and practice.";
            case "Medium":
                return "MEDIUM: For intermediate learners!\n" +
                       "Moderately challenging questions.\n" +
                       "Requires good understanding of topics.";
            case "Hard":
                return "HARD: For experts!\n" +
                       "Advanced and challenging questions.\n" +
                       "Tests deep knowledge and critical thinking.";
            default:
                return "Standard difficulty level.";
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