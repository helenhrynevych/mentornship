package com.company;

import java.util.Arrays;

public class Main {
    private static int[][] matrix = new int[5][5];

    private static int[] randomRowPositions = getRandomPositions();
    private static int[] randomColPositions = getRandomPositions();

    public static void main(String[] args) {

        setMatrixWithZeros();

        System.out.println();

        setMatrixWithVals();
        printMatrix();
    }

    private static void printMatrix() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(matrix[i][j]);
                if(matrix[i][j]>=0)System.out.print("  ");
                else System.out.print(" ");
            }
            System.out.println();
        }
    }

    private static void setMatrixWithZeros() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                matrix[i][j] = 0;
            }
        }
    }

    private static void setMatrixWithVals() {
        for (int i = 0; i < randomRowPositions.length; i++) {
            int row = randomRowPositions[i];
            int col = randomColPositions[i];
            matrix[row][col] = row+col%2==0?1:-1;
        }
    }


    private static int[] getRandomPositions() {
        int[] randomPositions = new int[6];

        for (int i = 0; i < randomPositions.length; i++) {
            randomPositions[i]= (int) (Math.random() * 4);
            System.out.print(randomPositions[i]);
        }
        System.out.println();
        return randomPositions;
    }

    private static boolean checkRowPosition(int pos) {
        return Arrays.stream(randomRowPositions).anyMatch(Integer.valueOf(pos)::equals);
    }

    private static boolean checkColPosition(int pos) {
        return Arrays.stream(randomColPositions).anyMatch(Integer.valueOf(pos)::equals);
    }
}
