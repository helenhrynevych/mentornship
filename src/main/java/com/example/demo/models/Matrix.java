package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.Generated;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Matrix {
	@Id
	private String id;
	private List<Cell> data;
	private int row = 0;
	private int col = 0;

	public Matrix() {
		row = 9;
		col = 9;
		this.data = new ArrayList<>();
		setWithZeros();
	}

	private void setWithZeros() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				Cell cell = new Cell(i, j, 0);
				this.data.add(cell);
			}
		}
		printMatrix();
	}

	public void printMatrix() {
		System.out.println("Print matrix");
		int i = 0;
		for (Cell cell : data) {
			i++;
			if (cell.getValue() >= 0) {
				System.out.print("[" + cell.getValue() + "]  ");
			} else {
				System.out.print("[" + cell.getValue() + "] ");
			}
			if (i % 9 == 0) {
				System.out.println();
			}
		}

		System.out.println("end");
	}
}
