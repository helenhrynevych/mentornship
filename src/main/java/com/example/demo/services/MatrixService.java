package com.example.demo.services;

import com.example.demo.models.Matrix;
import com.example.demo.repositories.MatrixRepository;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MatrixService{

	@Autowired
	MatrixRepository matrixRepository;

	public Iterable<Matrix> getMatrix() {
		return matrixRepository.findAll();
	}

	public Matrix createMatrix() {
		int size = 9;
		Matrix matrix = new Matrix();
		matrix.setRow(size);
		matrix.setCol(size);
		matrix.setData(generateData(size));
		matrixRepository.save(matrix);
		return matrix;
	}

	private int[][] generateData(int size) {
		int[] randomRowPositions = getRandomPositions(size);
		int[] randomColPositions = getRandomPositions(size);
		int[][] data = new int[size][size];

		for (int i = 0; i < randomRowPositions.length; i++) {
			int row = randomRowPositions[i];
			int col = randomColPositions[i];
			data[row][col] = col != 0 ? (row % col == 0 ? 1 : -1) : -1;
		}
		return data;
	}

	private int[] getRandomPositions(int size) {
		int[] randomPositions = new int[size * 3];

		for (int i = 0; i < randomPositions.length; i++) {
			randomPositions[i] = (int) (Math.random() * size);
		}
		return randomPositions;
	}
}
