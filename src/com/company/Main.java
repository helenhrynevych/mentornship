package com.company;


public class Main {
    static int size = 9;
    private static int[][] matrix = new int[size][size];

    private static int[] randomRowPositions = getRandomPositions();
    private static int[] randomColPositions = getRandomPositions();

    public static void main(String[] args) {
        setMatrixWithZeros();
        System.out.println();

        matrix = setMatrixWithVals();
        printMatrix();
    }

    private static void printMatrix() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(matrix[i][j]);
                if (matrix[i][j] >= 0) System.out.print("  ");
                else System.out.print(" ");
            }
            System.out.println();
        }
    }

    private static void setMatrixWithZeros() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = 0;
            }
        }
    }

    private static  int[][] setMatrixWithVals() {
        int[][] updated = new int[size][size];
        for (int i = 0; i < randomRowPositions.length; i++) {
            int row = randomRowPositions[i];
            int col = randomColPositions[i];
            updated[row][col] = col != 0 ? (row % col == 0 ? 1 : -1) : -1;
        }
        return updated;
    }


    private static int[] getRandomPositions() {
        int[] randomPositions = new int[size*3];

        for (int i = 0; i < randomPositions.length; i++) {
            randomPositions[i] = (int) (Math.random() * size);
        }
        return randomPositions;
    }

}
