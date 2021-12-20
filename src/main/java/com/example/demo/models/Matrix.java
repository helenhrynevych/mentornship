package com.example.demo.models;


import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

@Data
@Component
public class Matrix {
	@Id
	private Long id;
	private int[][] data;
	private int row = 0;
	private int col = 0;

	public Matrix(){

	}

	public Matrix(int row, int col) {
		this.row = row;
		this.col = col;
		this.data = new int[row][col];
		setWithZeros();
	}

	private void setWithZeros() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < row; j++) {
				this.data[i][j] = 0;
			}
		}
	}

	public void printMatrix() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < row; j++) {
				System.out.print(data[i][j]);
				if (data[i][j] >= 0) {
					System.out.print("  ");
				} else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}

	public void setMatrixWithVals() {
		int[] randomRowPositions = getRandomPositions(row);
		int[] randomColPositions = getRandomPositions(row);

		for (int i = 0; i < randomRowPositions.length; i++) {
			int row = randomRowPositions[i];
			int col = randomColPositions[i];
			this.data[row][col] = col != 0 ? (row % col == 0 ? 1 : -1) : -1;
		}
		printMatrix();
	}

	private int[] getRandomPositions(int size) {
		int[] randomPositions = new int[size * 3];

		for (int i = 0; i < randomPositions.length; i++) {
			randomPositions[i] = (int) (Math.random() * size);
		}
		return randomPositions;
	}
}
