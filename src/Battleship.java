import java.util.Random;
import java.util.Scanner;

public class Battleship {

    private static final int grid_size = 5; // 5x5 grid
    private static final int max_turns = 10; // Maximum turns allowed
    private static int shipRow;
    private static int shipCol;
    private static boolean isGameOver;

    public static void main(String[] args) {
        char[][] grid = new char[grid_size][grid_size];
        initializeGrid(grid);
        placeShip();

        System.out.println("Welcome Ladies & Ladies! The game is battleship.");
        System.out.println("You have " + max_turns + " turns to find the battleship.");

        int turnsLeft = max_turns;
        Scanner scanner = new Scanner(System.in);

        while (!isGameOver && turnsLeft > 0) {
            displayGrid(grid);
            System.out.println("Turns remaining: " + turnsLeft);
            System.out.print("Enter row (0-4): ");
            int row = scanner.nextInt();
            System.out.print("Enter column (0-4): ");
            int col = scanner.nextInt();

            if (row < 0 || row >= grid_size || col < 0 || col >= grid_size) {
                System.out.println("Invalid coordinates. Try again.");
                continue;
            }

            if (row == shipRow && col == shipCol) {
                System.out.println("Hit! You sank the battleship!");
                grid[row][col] = 'X';
                isGameOver = true;
            } else if (grid[row][col] == 'O') {
                System.out.println("You already guessed that grid. Try again.");
            } else {
                System.out.println("Miss.");
                grid[row][col] = 'O';
                turnsLeft--;
            }
        }

        if (!isGameOver) {
            System.out.println("GGEZ! You've run out of turns.");
            System.out.println("The battleship was at (" + shipRow + ", " + shipCol + ").");
        }

        displayGrid(grid);
        scanner.close();
    }

    private static void initializeGrid(char[][] grid) {
        for (int row = 0; row < grid_size; row++) {
            for (int col = 0; col < grid_size; col++) {
                grid[row][col] = '-';
            }
        }
    }

    private static void placeShip() {
        Random random = new Random();
        shipRow = random.nextInt(grid_size);
        shipCol = random.nextInt(grid_size);
    }

    private static void displayGrid(char[][] grid) {
        System.out.println("Current Grid:");
        for (int row = 0; row < grid_size; row++) {
            for (int col = 0; col < grid_size; col++) {
                System.out.print(grid[row][col] + " ");
            }
            System.out.println();
        }
    }
}
