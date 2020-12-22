package tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static char nextSymbol = 'O';

    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        char[][] cells = new char[3][3];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                cells[i][j] = ' ';
            }
        }
        drawGrid(cells);

        String result = null;
        boolean isFinished = false;
        while (!isFinished) {
            int x, y;
            boolean isCorrect = false;
            while (!isCorrect) {
                String firstCoordinate = scanner.next();
                if (isNumeric(firstCoordinate)) {
                    x = Integer.valueOf(firstCoordinate);
                    String secondCoordinate = scanner.next();
                    if (isNumeric(secondCoordinate)) {
                        y = Integer.valueOf(secondCoordinate);
                        if (x > 0 && x < 4 && y > 0 && y < 4) {
                            if (cells[x - 1][y - 1] == ' ') {
                                cells[x - 1][y - 1] = getNextSymbol();
                                isCorrect = true;
                            } else {
                                System.out.println("This cell is occupied! Choose another one!");
                            }
                        } else {
                            System.out.println("Coordinates should be from 1 to 3!");
                        }
                    } else {
                        System.out.println("You should enter numbers!");
                    }
                } else {
                    System.out.println("You should enter numbers!");
                }
            }
            int xNum = 0;
            int oNum = 0;
            List<Object> sums = new ArrayList<>();//holds sums of rows, columns, and diagonals
            for (int i = 0; i < cells.length; i++) {
                int sum = 0;
                for (int j = 0; j < cells.length; j++) {
                    char ch = cells[i][j];
                    switch (ch) {
                        case 'X' ://88
                            xNum++;
                            break;
                        case 'O' ://79
                            oNum++;
                            break;
                        default :
                            break;
                    }
                    sum += ch;
                }
                sums.add(sum);
            }
            sums.add(cells[0][0] + cells[1][0] + cells[2][0]);
            sums.add(cells[0][1] + cells[1][1] + cells[2][1]);
            sums.add(cells[0][2] + cells[1][2] + cells[2][2]);
            sums.add(cells[0][0] + cells[1][1] + cells[2][2]);
            sums.add(cells[0][2] + cells[1][1] + cells[2][0]);
            if (Math.abs(xNum - oNum) > 1 || sums.contains(264) && sums.contains(237)) {
                result = "Impossible";
            } else if (sums.contains(264)) {
                isFinished = true;
                result = "X wins";
            } else if (sums.contains(237)) {
                isFinished = true;
                result = "O wins";
            } else if (xNum + oNum == 9) {
                isFinished = true;
                result = "Draw";
            }
            drawGrid(cells);
        }
        System.out.println(result);
    }

    private static char getNextSymbol() {
        if (nextSymbol == 'X') {
            nextSymbol = 'O';
        } else {
            nextSymbol = 'X';
        }
        return nextSymbol;
    }

    private static boolean isNumeric(String string) {
        boolean b = false;
        try {
            Integer.valueOf(string);
            b = true;
        } catch (NumberFormatException e) {
//            e.printStackTrace();
        }
        return b;
    }

    private static void drawGrid(char[][] cells) {
        System.out.println("---------");
        System.out.println("| " + cells[0][0] + " " + cells[0][1] + " " + cells[0][2] + " |");
        System.out.println("| " + cells[1][0] + " " + cells[1][1] + " " + cells[1][2] + " |");
        System.out.println("| " + cells[2][0] + " " + cells[2][1] + " " + cells[2][2] + " |");
        System.out.println("---------");
    }

}