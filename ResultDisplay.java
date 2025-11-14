import java.util.Scanner;
import java.util.ArrayList;

public class ResultDisplay {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<QuizResult> resultHistory = new ArrayList<>();
    
    public static void main(String[] args) {
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║          RESULT DISPLAY SYSTEM                 ║");
        System.out.println("╚════════════════════════════════════════════════╝\n");
        
        boolean running = true;
        
        while (running) {
            showMenu();
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    enterNewResult();
                    break;
                case 2:
                    viewAllResults();
                    break;
                case 3:
                    viewDetailedResult();
                    break;
                case 4:
                    calculateStatistics();
                    break;
                case 5:
                    System.out.println("\n✓ Thank you for using Result Display!\n");
                    running = false;
                    break;
                default:
                    System.out.println("\n[ERROR] Invalid choice! Please try again.\n");
            }
        }
        
        scanner.close();
    }
    
    private static void showMenu() {
        System.out.println("════════════════ MAIN MENU ═════════════════════");
        System.out.println("  1. Enter New Quiz Result");
        System.out.println("  2. View All Results");
        System.out.println("  3. View Detailed Result");
        System.out.println("  4. View Statistics");
        System.out.println("  5. Exit");
        System.out.println("════════════════════════════════════════════════");
        System.out.print("Enter your choice: ");
    }
    
    private static void enterNewResult() {
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║          ENTER QUIZ RESULT                     ║");
        System.out.println("╚════════════════════════════════════════════════╝\n");
        
        System.out.print("Player Name: ");
        String playerName = scanner.nextLine().trim();
        
        System.out.print("Category: ");
        String category = scanner.nextLine().trim();
        
        System.out.print("Difficulty (Easy/Medium/Hard): ");
        String difficulty = scanner.nextLine().trim();
        
        System.out.print("Total Questions: ");
        int totalQuestions = getIntInput();
        
        System.out.print("Correct Answers: ");
        int correctAnswers = getIntInput();
        
        if (correctAnswers > totalQuestions) {
            System.out.println("\n[ERROR] Correct answers cannot exceed total questions!\n");
            return;
        }
        
        int pointsPerQuestion = 10;
        if (difficulty.equalsIgnoreCase("Medium")) pointsPerQuestion = 20;
        else if (difficulty.equalsIgnoreCase("Hard")) pointsPerQuestion = 30;
        
        int score = correctAnswers * pointsPerQuestion;
        
        QuizResult result = new QuizResult(playerName, category, difficulty, 
                                          totalQuestions, correctAnswers, score);
        resultHistory.add(result);
        
        displayResult(result);
    }
    
    private static void displayResult(QuizResult result) {
        double percentage = (result.correctAnswers * 100.0) / result.totalQuestions;
        String grade = calculateGrade(percentage);
        
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║              QUIZ RESULT                       ║");
        System.out.println("╚════════════════════════════════════════════════╝\n");
        
        System.out.println("Player: " + result.playerName);
        System.out.println("Category: " + result.category);
        System.out.println("Difficulty: " + result.difficulty);
        System.out.println("════════════════════════════════════════════════");
        System.out.println("Correct Answers: " + result.correctAnswers);
        System.out.println("Wrong Answers: " + (result.totalQuestions - result.correctAnswers));
        System.out.println("Total Questions: " + result.totalQuestions);
        System.out.println("════════════════════════════════════════════════");
        System.out.println("Score: " + result.score + " points");
        System.out.printf("Percentage: %.2f%%\n", percentage);
        System.out.println("Grade: " + grade);
        System.out.println("════════════════════════════════════════════════\n");
        
        if (percentage == 100) {
            System.out.println("PERFECT SCORE! Outstanding!");
        } else if (percentage >= 80) {
            System.out.println("EXCELLENT! Great job!");
        } else if (percentage >= 60) {
            System.out.println("GOOD! Well done!");
        } else if (percentage >= 40) {
            System.out.println("KEEP PRACTICING!");
        } else {
            System.out.println("DON'T GIVE UP! Try again!");
        }
        System.out.println();
    }
    
    private static void viewAllResults() {
        if (resultHistory.isEmpty()) {
            System.out.println("\n[INFO] No results to display yet.\n");
            return;
        }
        
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║            ALL QUIZ RESULTS                    ║");
        System.out.println("╚════════════════════════════════════════════════╝\n");
        
        System.out.printf("%-4s %-15s %-12s %-10s %-8s %-8s\n", 
            "No.", "Player", "Category", "Difficulty", "Score", "Grade");
        System.out.println("════════════════════════════════════════════════");
        
        for (int i = 0; i < resultHistory.size(); i++) {
            QuizResult r = resultHistory.get(i);
            double percentage = (r.correctAnswers * 100.0) / r.totalQuestions;
            String grade = calculateGrade(percentage);
            
            System.out.printf("%-4d %-15s %-12s %-10s %-8d %-8s\n",
                (i + 1),
                r.playerName.length() > 15 ? r.playerName.substring(0, 12) + "..." : r.playerName,
                r.category.length() > 12 ? r.category.substring(0, 9) + "..." : r.category,
                r.difficulty,
                r.score,
                grade);
        }
        
        System.out.println("════════════════════════════════════════════════\n");
        pressEnterToContinue();
    }
    
    private static void viewDetailedResult() {
        if (resultHistory.isEmpty()) {
            System.out.println("\n[INFO] No results to display yet.\n");
            return;
        }
        
        System.out.print("\nEnter result number (1-" + resultHistory.size() + "): ");
        int num = getIntInput();
        
        if (num < 1 || num > resultHistory.size()) {
            System.out.println("\n[ERROR] Invalid result number!\n");
            return;
        }
        
        displayResult(resultHistory.get(num - 1));
        pressEnterToContinue();
    }
    
    private static void calculateStatistics() {
        if (resultHistory.isEmpty()) {
            System.out.println("\n[INFO] No results to analyze yet.\n");
            return;
        }
        
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║          PERFORMANCE STATISTICS                ║");
        System.out.println("╚════════════════════════════════════════════════╝\n");
        
        int totalScore = 0;
        int totalCorrect = 0;
        int totalQuestions = 0;
        int perfectScores = 0;
        
        for (QuizResult r : resultHistory) {
            totalScore += r.score;
            totalCorrect += r.correctAnswers;
            totalQuestions += r.totalQuestions;
            
            double percentage = (r.correctAnswers * 100.0) / r.totalQuestions;
            if (percentage == 100) perfectScores++;
        }
        
        double avgScore = totalScore / (double) resultHistory.size();
        double avgPercentage = (totalCorrect * 100.0) / totalQuestions;
        
        System.out.println("Total Quizzes Taken: " + resultHistory.size());
        System.out.println("Total Score: " + totalScore + " points");
        System.out.printf("Average Score: %.2f points\n", avgScore);
        System.out.printf("Overall Accuracy: %.2f%%\n", avgPercentage);
        System.out.println("Perfect Scores: " + perfectScores);
        System.out.println();
        
        pressEnterToContinue();
    }
    
    private static String calculateGrade(double percentage) {
        if (percentage >= 95) return "A+";
        else if (percentage >= 90) return "A";
        else if (percentage >= 85) return "A-";
        else if (percentage >= 80) return "B+";
        else if (percentage >= 75) return "B";
        else if (percentage >= 70) return "B-";
        else if (percentage >= 65) return "C+";
        else if (percentage >= 60) return "C";
        else if (percentage >= 55) return "C-";
        else if (percentage >= 50) return "D";
        else return "F";
    }
    
    private static void pressEnterToContinue() {
        System.out.print("Press Enter to continue...");
        scanner.nextLine();
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

class QuizResult {
    String playerName;
    String category;
    String difficulty;
    int totalQuestions;
    int correctAnswers;
    int score;
    
    QuizResult(String playerName, String category, String difficulty,
              int totalQuestions, int correctAnswers, int score) {
        this.playerName = playerName;
        this.category = category;
        this.difficulty = difficulty;
        this.totalQuestions = totalQuestions;
        this.correctAnswers = correctAnswers;
        this.score = score;
    }
}