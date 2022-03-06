package lesson_2_tic_tac_toe;

import lesson_2_tic_tac_toe.players.Bot;
import lesson_2_tic_tac_toe.players.Player;
import lesson_2_tic_tac_toe.utils.CustomIO;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    private Player player;
    private Bot bot;
    private char[][] gameBoardGrid;
    private final int GAME_BOARD_GRID_SIZE = 3;
    private final int TAG_COUNT_TO_WIN = 3;
    private final char TAG_EMPTY = '-';
    private final char TAG_X = 'X';
    private final char TAG_O = 'O';
    private static int countWins = 0;
    private Scanner scanner = new Scanner(System.in);


    public TicTacToe(Player player, Bot bot) {
        this.player = player;
        this.bot = bot;
    }

    public void run() {
        createEmptyGameBoardGrid();
        showGameBoardGrid();
        while (true) {
            playerTurn();
            showGameBoardGrid();
            if (checkWinner(TAG_X)) {
                countWins++;
                System.out.println("Победил " + player.getName());
                CustomIO.writeRates(player.getName() + " : " + countWins);
                playAgainOrBreak();
                break;
            }
            if (isGameBoardGridFullFilled()) {
                System.out.println("Ничья");
                break;
            }
            botTurn();
            showGameBoardGrid();
            if (checkWinner(TAG_O)) {
                countWins++;
                System.out.println("Победил " + bot.getName());
                CustomIO.writeRates(bot.getName() + " : " + countWins);
                playAgainOrBreak();
                break;
            }
            if (isGameBoardGridFullFilled()) {
                System.out.println("Ничья");
                break;
            }
        }
    }

    private void playAgainOrBreak() {
        System.out.println("Хотите сыграть еще раз? (да, нет)");
        String answer;
        do {
            answer = scanner.nextLine();
            if (answer.equals("да")) {
                run();
            } else if (answer.equals("нет")) {
                return;
            }
        } while (!answer.equals("да"));
    }

    private void createEmptyGameBoardGrid() {
        gameBoardGrid = new char[GAME_BOARD_GRID_SIZE][GAME_BOARD_GRID_SIZE];
        for (int line = 0; line < GAME_BOARD_GRID_SIZE; line++) {
            for (int columns = 0; columns < GAME_BOARD_GRID_SIZE; columns++) {
                gameBoardGrid[line][columns] = TAG_EMPTY;
            }
        }
    }

    private void showGameBoardGrid() {
        for (int line = 0; line < GAME_BOARD_GRID_SIZE; line++) {
            for (int column = 0; column < GAME_BOARD_GRID_SIZE; column++) {
                System.out.print("|");
                System.out.print(gameBoardGrid[line][column]);
            }
            System.out.print("|");
            System.out.println();
        }
    }

    private void playerTurn() {
        int x;
        int y;
        do {
            System.out.println(player.getName() + ", введите координаты X и Y через пробел");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (checkCoordinates(x, y));
        gameBoardGrid[y][x] = TAG_X;
    }

    private void botTurn() {
        Random random = new Random();
        int x;
        int y;
        do {
            x = random.nextInt(GAME_BOARD_GRID_SIZE);
            y = random.nextInt(GAME_BOARD_GRID_SIZE);
        } while (checkCoordinates(x, y));
        System.out.println(bot.getName() + " сделал ход по координатам: X:" + (x + 1) + ", Y:" + (y + 1));
        gameBoardGrid[y][x] = TAG_O;
    }

    /**
     * Проверят координаты X и Y на отрицательные значения и
     * на превышение размера массива
     */
    private boolean checkCoordinates(int x, int y) {
        if (x < 0 || x >= GAME_BOARD_GRID_SIZE || y < 0 || y >= GAME_BOARD_GRID_SIZE) {
            return true;
        }
        return gameBoardGrid[y][x] != TAG_EMPTY;
    }

    /**
     * Проверка победы со смещением
     */
    private boolean checkWinner(char dotSymbol) {
        int endOfShift = gameBoardGrid.length - TAG_COUNT_TO_WIN;
        for (int lineShift = 0; lineShift <= endOfShift; lineShift++) {
            if (checkFilledDiagonals(dotSymbol, lineShift)) {
                return true;
            }
            for (int columnShift = 0; columnShift <= endOfShift; columnShift++) {
                if (checkFilledLines(dotSymbol, lineShift, columnShift)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Проверка линий на заполненность со смещением
     */
    private boolean checkFilledLines(char dotSymbol, int lineShift, int columnShift) {
        for (int line = lineShift; line < (TAG_COUNT_TO_WIN + lineShift); line++) {
            int horizontalLineCount = 0;
            int verticalLineCount = 0;
            for (int column = columnShift; column < (TAG_COUNT_TO_WIN + columnShift); column++) {
                // проверка горизонтали
                if (gameBoardGrid[line][column] == dotSymbol) {
                    horizontalLineCount++;
                }
                // проверка вертикали
                if (gameBoardGrid[column][line] == dotSymbol) {
                    verticalLineCount++;
                }
            }
            if ((horizontalLineCount == TAG_COUNT_TO_WIN) || (verticalLineCount == TAG_COUNT_TO_WIN)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Проверка диагоналей на заполненность
     */
    private boolean checkFilledDiagonals(char dotSymbol, int lineShift) {
        int mainDiagonalCount = 0;
        int sideDiagonalCount = 0;
        int subArrayLength = (TAG_COUNT_TO_WIN + lineShift);
        for (int line = lineShift; line < subArrayLength; line++) {
            if (gameBoardGrid[line][line] == dotSymbol) {
                mainDiagonalCount++;
            }
            if (gameBoardGrid[line][gameBoardGrid.length - 1 - line] == dotSymbol) {
                sideDiagonalCount++;
            }
        }
        return (mainDiagonalCount == TAG_COUNT_TO_WIN) || (sideDiagonalCount == TAG_COUNT_TO_WIN);
    }

    /**
     * Проверка на ничью
     */
    private boolean isGameBoardGridFullFilled() {
        for (int line = 0; line < GAME_BOARD_GRID_SIZE; line++) {
            for (int column = 0; column < GAME_BOARD_GRID_SIZE; column++) {
                if (gameBoardGrid[line][column] == TAG_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
}
