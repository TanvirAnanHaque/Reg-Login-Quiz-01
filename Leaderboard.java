import java.util.ArrayList;
import java.util.Scanner;

public class Leaderboard {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<PlayerScore> leaderboard = new ArrayList<>();
    
    public static void main(String[] args) {
        addSampleData();
        
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║           LEADERBOARD SYSTEM                   ║");
        System.out.println("╚════════════════════════════════════════════════╝\n");
        
        boolean running = true;
        
        while (running) {
            showMenu();
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    displayLeaderboard();
                    break;
                case 2:
                    addPlayerScore();
                    break;
                case 3:
                    searchPlayer();
                    break;
                case 4:
                    displayTopPlayers();
                    break;
                case 5:
                    displayStatistics();
                    break;
                case 6:
                    System.out.println("\n✓ Thank you for using Leaderboard!\n");
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
        System.out.println("  1. View Full Leaderboard");
        System.out.println("  2. Add Player Score");
        System.out.println("  3. Search Player");
        System.out.println("  4. View Top 10 Players");
        System.out.println("  5. View Statistics");
        System.out.println("  6. Exit");
        System.out.println("════════════════════════════════════════════════");
        System.out.print("Enter your choice: ");
    }
    
    private static void addSampleData() {
        leaderboard.add(new PlayerScore("Mashrafe Azam", 850, 15));
        leaderboard.add(new PlayerScore("Nir Islam", 720, 12));
        leaderboard.add(new PlayerScore("Abir Hassan", 680, 10));
        leaderboard.add(new PlayerScore("Nazmul Hossain", 920, 18));
        leaderboard.add(new PlayerScore("Zia Ahmed", 550, 8));
        leaderboard.add(new PlayerScore("Rabbi Alam", 790, 14));
        leaderboard.add(new PlayerScore("Hasan Abdullah", 630, 11));
        leaderboard.add(new PlayerScore("Mir Mugdho", 870, 16));
        leaderboard.add(new PlayerScore("Shobuj Korim", 490, 7));
        leaderboard.add(new PlayerScore("Hamza Choudhury", 760, 13));
        
        sortLeaderboard();
    }
    
    private static void displayLeaderboard() {
        if (leaderboard.isEmpty()) {
            System.out.println("\n[INFO] No players on leaderboard yet.\n");
            return;
        }
        
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║         COMPLETE LEADERBOARD                   ║");
        System.out.println("╚════════════════════════════════════════════════╝\n");
        
        System.out.println("════════════════════════════════════════════════");
        System.out.printf("%-6s %-20s %-10s %-10s\n", "RANK", "PLAYER", "SCORE", "QUIZZES");
        System.out.println("════════════════════════════════════════════════");
        
        for (int i = 0; i < leaderboard.size(); i++) {
            PlayerScore player = leaderboard.get(i);
            String rankStr = getRankDisplay(i + 1);
            
            System.out.printf("%-6s %-20s %-10d %-10d\n",
                rankStr,
                truncateName(player.name, 20),
                player.score,
                player.quizzesTaken);
        }
        
        System.out.println("════════════════════════════════════════════════\n");
        pressEnterToContinue();
    }
    
    private static void displayTopPlayers() {
        if (leaderboard.isEmpty()) {
            System.out.println("\n[INFO] No players on leaderboard yet.\n");
            return;
        }
        
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║            TOP 10 PLAYERS                      ║");
        System.out.println("╚════════════════════════════════════════════════╝\n");
        
        System.out.println("════════════════════════════════════════════════");
        System.out.printf("%-6s %-20s %-10s %-10s\n", "RANK", "PLAYER", "SCORE", "QUIZZES");
        System.out.println("════════════════════════════════════════════════");
        
        int limit = Math.min(10, leaderboard.size());
        
        for (int i = 0; i < limit; i++) {
            PlayerScore player = leaderboard.get(i);
            String rankStr = getRankDisplay(i + 1);
            
            System.out.printf("%-6s %-20s %-10d %-10d\n",
                rankStr,
                truncateName(player.name, 20),
                player.score,
                player.quizzesTaken);
        }
        
        System.out.println("════════════════════════════════════════════════");
        
        if (leaderboard.size() > 10) {
            System.out.println("\n... and " + (leaderboard.size() - 10) + " more players");
        }
        
        System.out.println();
        pressEnterToContinue();
    }
    
    private static void addPlayerScore() {
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║          ADD PLAYER SCORE                      ║");
        System.out.println("╚════════════════════════════════════════════════╝\n");
        
        System.out.print("Player Name: ");
        String name = scanner.nextLine().trim();
        
        if (name.isEmpty()) {
            System.out.println("\n[ERROR] Name cannot be empty!\n");
            return;
        }
        
        System.out.print("Total Score: ");
        int score = getIntInput();
        
        System.out.print("Quizzes Taken: ");
        int quizzes = getIntInput();
        
        boolean found = false;
        for (PlayerScore player : leaderboard) {
            if (player.name.equalsIgnoreCase(name)) {
                player.score += score;
                player.quizzesTaken += quizzes;
                found = true;
                System.out.println("\n✓ Updated existing player's score!");
                break;
            }
        }
        
        if (!found) {
            leaderboard.add(new PlayerScore(name, score, quizzes));
            System.out.println("\n✓ New player added to leaderboard!");
        }
        
        sortLeaderboard();
        
        int rank = getPlayerRank(name);
        System.out.println(name + " is now ranked #" + rank);
        System.out.println();
    }
    
    private static void searchPlayer() {
        System.out.print("\nEnter player name to search: ");
        String searchName = scanner.nextLine().trim().toLowerCase();
        
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║           SEARCH RESULTS                       ║");
        System.out.println("╚════════════════════════════════════════════════╝\n");
        
        boolean found = false;
        
        for (int i = 0; i < leaderboard.size(); i++) {
            PlayerScore player = leaderboard.get(i);
            if (player.name.toLowerCase().contains(searchName)) {
                found = true;
                System.out.println("Player Found!");
                System.out.println("════════════════════════════════════════════════");
                System.out.println("Rank: #" + (i + 1));
                System.out.println("Name: " + player.name);
                System.out.println("Score: " + player.score);
                System.out.println("Quizzes Taken: " + player.quizzesTaken);
                
                double avgScore = player.quizzesTaken > 0 ? 
                    player.score / (double) player.quizzesTaken : 0;
                System.out.printf("Average Score per Quiz: %.2f\n", avgScore);
                System.out.println("════════════════════════════════════════════════\n");
            }
        }
        
        if (!found) {
            System.out.println("No player found matching '" + searchName + "'\n");
        }
        
        pressEnterToContinue();
    }
    
    private static void displayStatistics() {
        if (leaderboard.isEmpty()) {
            System.out.println("\n[INFO] No data to display statistics.\n");
            return;
        }
        
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║        LEADERBOARD STATISTICS                  ║");
        System.out.println("╚════════════════════════════════════════════════╝\n");
        
        int totalPlayers = leaderboard.size();
        int totalScore = 0;
        int totalQuizzes = 0;
        int highestScore = leaderboard.get(0).score;
        int lowestScore = leaderboard.get(leaderboard.size() - 1).score;
        
        for (PlayerScore player : leaderboard) {
            totalScore += player.score;
            totalQuizzes += player.quizzesTaken;
        }
        
        double avgScore = totalScore / (double) totalPlayers;
        double avgQuizzes = totalQuizzes / (double) totalPlayers;
        
        System.out.println("Total Players: " + totalPlayers);
        System.out.println("Total Quizzes Taken: " + totalQuizzes);
        System.out.println("Total Score Earned: " + totalScore);
        System.out.println();
        System.out.printf("Average Score per Player: %.2f\n", avgScore);
        System.out.printf("Average Quizzes per Player: %.2f\n", avgQuizzes);
        System.out.println();
        System.out.println("Highest Score: " + highestScore + 
            " (" + leaderboard.get(0).name + ")");
        System.out.println("Lowest Score: " + lowestScore + 
            " (" + leaderboard.get(leaderboard.size() - 1).name + ")");
        System.out.println();
        
        pressEnterToContinue();
    }
    
    private static void sortLeaderboard() {
        for (int i = 0; i < leaderboard.size() - 1; i++) {
            for (int j = 0; j < leaderboard.size() - i - 1; j++) {
                if (leaderboard.get(j).score < leaderboard.get(j + 1).score) {
                    PlayerScore temp = leaderboard.get(j);
                    leaderboard.set(j, leaderboard.get(j + 1));
                    leaderboard.set(j + 1, temp);
                }
            }
        }
    }
    
    private static int getPlayerRank(String name) {
        for (int i = 0; i < leaderboard.size(); i++) {
            if (leaderboard.get(i).name.equalsIgnoreCase(name)) {
                return i + 1;
            }
        }
        return -1;
    }
    
    private static String getRankDisplay(int rank) {
        if (rank == 1) return "#1";
        else if (rank == 2) return "#2";
        else if (rank == 3) return "#3";
        else return "#" + rank;
    }
    
    private static String truncateName(String name, int maxLength) {
        if (name.length() > maxLength) {
            return name.substring(0, maxLength - 3) + "...";
        }
        return name;
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

class PlayerScore {
    String name;
    int score;
    int quizzesTaken;
    
    PlayerScore(String name, int score, int quizzesTaken) {
        this.name = name;
        this.score = score;
        this.quizzesTaken = quizzesTaken;
    }
}
