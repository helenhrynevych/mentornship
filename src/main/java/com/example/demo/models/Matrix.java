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
}
