import java.util.ArrayList;
import java.util.Scanner;

public class QuizGame {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Question> allQuestions = new ArrayList<>();
    
    public static void main(String[] args) {
        initializeQuestions();
        
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║              QUIZ GAME SYSTEM                  ║");
        System.out.println("╚════════════════════════════════════════════════╝\n");
        
        boolean running = true;
        
        while (running) {
            showMenu();
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    startQuiz();
                    break;
                case 2:
                    System.out.println("\n✓ Thank you for playing Quiz Game!\n");
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
        System.out.println("  1. Start Quiz");
        System.out.println("  2. Exit");
        System.out.println("════════════════════════════════════════════════");
        System.out.print("Enter your choice: ");
    }
    
    private static void initializeQuestions() {
        // SCIENCE QUESTIONS
        allQuestions.add(new Question(
            "What is the chemical symbol for water?",
            "H2O", "CO2", "O2", "N2", 1,
            "Science", "Easy"
        ));
        
        allQuestions.add(new Question(
            "How many planets are in our solar system?",
            "7", "8", "9", "10", 2,
            "Science", "Easy"
        ));
        
        allQuestions.add(new Question(
            "What is the largest organ in the human body?",
            "Heart", "Brain", "Skin", "Liver", 3,
            "Science", "Easy"
        ));
        
        allQuestions.add(new Question(
            "What is the speed of light?",
            "300,000 km/s", "150,000 km/s", "500,000 km/s", "100,000 km/s", 1,
            "Science", "Medium"
        ));
        
        allQuestions.add(new Question(
            "What is the powerhouse of the cell?",
            "Nucleus", "Ribosome", "Mitochondria", "Chloroplast", 3,
            "Science", "Hard"
        ));
        
        // MATHEMATICS QUESTIONS
        allQuestions.add(new Question(
            "What is 15 + 27?",
            "40", "42", "44", "45", 2,
            "Mathematics", "Easy"
        ));
        
        allQuestions.add(new Question(
            "What is 8 × 7?",
            "54", "56", "58", "60", 2,
            "Mathematics", "Easy"
        ));
        
        allQuestions.add(new Question(
            "What is 100 - 37?",
            "63", "73", "53", "67", 1,
            "Mathematics", "Easy"
        ));
        
        allQuestions.add(new Question(
            "What is the square root of 144?",
            "10", "11", "12", "13", 3,
            "Mathematics", "Medium"
        ));
        
        allQuestions.add(new Question(
            "What is the value of π approximately?",
            "3.14159", "2.71828", "1.61803", "4.66920", 1,
            "Mathematics", "Hard"
        ));
        
        // HISTORY QUESTIONS
        allQuestions.add(new Question(
            "Who was the first President of USA?",
            "Thomas Jefferson", "George Washington", "Abraham Lincoln", "John Adams", 2,
            "History", "Easy"
        ));
        
        allQuestions.add(new Question(
            "In which year did World War II end?",
            "1943", "1944", "1945", "1946", 3,
            "History", "Easy"
        ));
        
        allQuestions.add(new Question(
            "When did French Revolution begin?",
            "1776", "1789", "1800", "1815", 2,
            "History", "Medium"
        ));
        
        allQuestions.add(new Question(
            "What year did Berlin Wall fall?",
            "1987", "1988", "1989", "1990", 3,
            "History", "Hard"
        ));
        
        // COMPUTER QUESTIONS
        allQuestions.add(new Question(
            "What does CPU stand for?",
            "Central Processing Unit", "Computer Personal Unit", 
            "Central Program Utility", "None", 1,
            "Computer", "Easy"
        ));
        
        allQuestions.add(new Question(
            "Which language is 'write once, run anywhere'?",
            "Python", "Java", "C++", "JavaScript", 2,
            "Computer", "Easy"
        ));
        
        allQuestions.add(new Question(
            "Which data structure uses LIFO?",
            "Queue", "Stack", "Array", "Tree", 2,
            "Computer", "Medium"
        ));
        
        allQuestions.add(new Question(
            "Who is the father of Computer Science?",
            "Bill Gates", "Steve Jobs", "Alan Turing", "Tim Berners-Lee", 3,
            "Computer", "Hard"
        ));
        
        // GEOGRAPHY QUESTIONS
        allQuestions.add(new Question(
            "What is the capital of France?",
            "London", "Berlin", "Paris", "Madrid", 3,
            "Geography", "Easy"
        ));
        
        allQuestions.add(new Question(
            "Which is the largest ocean?",
            "Atlantic", "Indian", "Arctic", "Pacific", 4,
            "Geography", "Easy"
        ));
        
        allQuestions.add(new Question(
            "What is the longest river?",
            "Amazon", "Nile", "Yangtze", "Mississippi", 2,
            "Geography", "Medium"
        ));
        
        allQuestions.add(new Question(
            "What is the smallest country?",
            "Monaco", "Vatican City", "San Marino", "Liechtenstein", 2,
            "Geography", "Hard"
        ));
    }
    
    private static void startQuiz() {
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║          WELCOME TO THE QUIZ!                  ║");
        System.out.println("╚════════════════════════════════════════════════╝\n");
        
        // Enter player name
        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine().trim();
        
        // Select Category
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║          SELECT CATEGORY                       ║");
        System.out.println("╚════════════════════════════════════════════════╝\n");
        System.out.println("1. Science");
        System.out.println("2. Mathematics");
        System.out.println("3. History");
        System.out.println("4. Computer");
        System.out.println("5. Geography");
        System.out.print("\nChoose category (1-5): ");
        
        int catChoice = getIntInput();
        String category = "";
        
        switch (catChoice) {
            case 1: category = "Science"; break;
            case 2: category = "Mathematics"; break;
            case 3: category = "History"; break;
            case 4: category = "Computer"; break;
            case 5: category = "Geography"; break;
            default:
                System.out.println("\n[ERROR] Invalid choice! Returning to menu.\n");
                return;
        }
        
        // Select Difficulty
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║          SELECT DIFFICULTY                     ║");
        System.out.println("╚════════════════════════════════════════════════╝\n");
        System.out.println("1. Easy (10 points per question)");
        System.out.println("2. Medium (20 points per question)");
        System.out.println("3. Hard (30 points per question)");
        System.out.print("\nChoose difficulty (1-3): ");
        
        int diffChoice = getIntInput();
        String difficulty = "";
        int pointsPerQuestion = 10;
        
        switch (diffChoice) {
            case 1: 
                difficulty = "Easy"; 
                pointsPerQuestion = 10;
                break;
            case 2: 
                difficulty = "Medium"; 
                pointsPerQuestion = 20;
                break;
            case 3: 
                difficulty = "Hard"; 
                pointsPerQuestion = 30;
                break;
            default:
                System.out.println("\n[ERROR] Invalid choice! Returning to menu.\n");
                return;
        }
        
        // Get questions for selected category and difficulty
        ArrayList<Question> quizQuestions = getQuestionsByFilter(category, difficulty);
        
        if (quizQuestions.isEmpty()) {
            System.out.println("\n[ERROR] No questions available for this selection.\n");
            return;
        }
        
        // Start the quiz
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║              STARTING QUIZ                     ║");
        System.out.println("╚════════════════════════════════════════════════╝");
        System.out.println("Player: " + playerName);
        System.out.println("Category: " + category);
        System.out.println("Difficulty: " + difficulty);
        System.out.println("Total Questions: " + quizQuestions.size());
        System.out.println("Points per question: " + pointsPerQuestion);
        System.out.println("\nLet's begin!\n");
        
        int score = 0;
        int correctAnswers = 0;
        
        // Ask all questions
        for (int i = 0; i < quizQuestions.size(); i++) {
            Question q = quizQuestions.get(i);
            
            System.out.println("════════════════════════════════════════════════");
            System.out.println("Question " + (i + 1) + " of " + quizQuestions.size());
            System.out.println("════════════════════════════════════════════════");
            System.out.println(q.questionText);
            System.out.println();
            System.out.println("  1. " + q.option1);
            System.out.println("  2. " + q.option2);
            System.out.println("  3. " + q.option3);
            System.out.println("  4. " + q.option4);
            System.out.println();
            System.out.print("Your answer (1-4): ");
            
            int answer = getIntInput();
            
            if (answer == q.correctAnswer) {
                System.out.println("\n✓ Correct! +" + pointsPerQuestion + " points\n");
                score += pointsPerQuestion;
                correctAnswers++;
            } else {
                System.out.println("\n✗ Wrong! Correct answer was option " + q.correctAnswer + "\n");
            }
        }
        
        // Display final result
        displayFinalResult(playerName, category, difficulty, score, correctAnswers, quizQuestions.size());
    }
    
    private static ArrayList<Question> getQuestionsByFilter(String category, String difficulty) {
        ArrayList<Question> filtered = new ArrayList<>();
        
        for (Question q : allQuestions) {
            if (q.category.equalsIgnoreCase(category) && 
                q.difficulty.equalsIgnoreCase(difficulty)) {
                filtered.add(q);
            }
        }
        
        return filtered;
    }
    
    private static void displayFinalResult(String playerName, String category, String difficulty,
                                          int score, int correct, int total) {
        double percentage = (correct * 100.0) / total;
        
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║              QUIZ COMPLETED!                   ║");
        System.out.println("╚════════════════════════════════════════════════╝\n");
        
        System.out.println("Player: " + playerName);
        System.out.println("Category: " + category);
        System.out.println("Difficulty: " + difficulty);
        System.out.println("════════════════════════════════════════════════");
        System.out.println("Correct Answers: " + correct);
        System.out.println("Wrong Answers: " + (total - correct));
        System.out.println("Total Questions: " + total);
        System.out.println("════════════════════════════════════════════════");
        System.out.println("Final Score: " + score + " points");
        System.out.printf("Accuracy: %.2f%%\n", percentage);
        System.out.println("Grade: " + calculateGrade(percentage));
        System.out.println("════════════════════════════════════════════════\n");
        
        if (percentage == 100) {
            System.out.println("🏆 PERFECT SCORE! Outstanding!");
        } else if (percentage >= 80) {
            System.out.println("⭐ EXCELLENT! Great job!");
        } else if (percentage >= 60) {
            System.out.println("👍 GOOD! Well done!");
        } else if (percentage >= 40) {
            System.out.println("📚 KEEP PRACTICING!");
        } else {
            System.out.println("💪 DON'T GIVE UP! Try again!");
        }
        System.out.println();
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

class Question {
    String questionText;
    String option1;
    String option2;
    String option3;
    String option4;
    int correctAnswer;
    String category;
    String difficulty;
    
    Question(String questionText, String option1, String option2, 
            String option3, String option4, int correctAnswer,
            String category, String difficulty) {
        this.questionText = questionText;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.correctAnswer = correctAnswer;
        this.category = category;
        this.difficulty = difficulty;
    }
}