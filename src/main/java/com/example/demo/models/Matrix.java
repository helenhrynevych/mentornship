package com.example.demo.models;


import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Matrix {
	@Id
	private String id;
	private int[][] data;
	private int row;
	private int col;
	private int size;

	public Matrix() {
		this.row = 9;
		this.col = 9;
		this.size = 9;
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
				System.out.print(data[j][i]);
				if (data[j][i] >= 0) {
					System.out.print("  ");
				} else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}

	public void concatTwoMatrixes(Matrix first, Matrix second) {
		int[][] firstData = first.getData();
		int[][] secondData = second.getData();
		int[][] result = new int[size][size];

		//get data from first matrix
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				result[j][i] = firstData[j][i];
			}
		}
		//add data from second matrix
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				result[j][i] = secondData[j][i];
			}
		}

		this.data = result;
	}
}
