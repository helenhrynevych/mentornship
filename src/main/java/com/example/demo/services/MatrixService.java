package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.models.Cell;
import com.example.demo.models.Matrix;
import com.example.demo.repositories.MatrixRepository;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MatrixService {

	@Autowired
	MatrixRepository matrixRepository;

	public Iterable<Matrix> getMatrix() {
		return matrixRepository.findAll();
	}

	public Matrix createMatrix() {
		Matrix matrix = getMatrix(9);
		matrixRepository.save(matrix);
		return matrix;
	}

	private Matrix getMatrix(int size) {
		Matrix matrix = new Matrix();
		matrix.setData(generateData(matrix));
		matrix.printMatrix();
		return matrix;
	}

	private List<Cell> generateData(Matrix matrix) {
		int[] randomRowPositions = getRandomPositions(9);
		int[] randomColPositions = getRandomPositions(9);

		for (int p = 0; p < randomRowPositions.length; p++) {
			int row = randomRowPositions[p];
			int col = randomColPositions[p];
			for (Cell cell : matrix.getData()) {
				if (cell.getX() == row && cell.getY() == col) {
					cell.setValue(col != 0 ? (row % col == 0 ? 1 : -1) : -1);
				}
			}
		}
		return matrix.getData();
	}

	private int[] getRandomPositions(int size) {
		int[] randomPositions = new int[size * 3];
		for (int i = 0; i < randomPositions.length; i++) {
			randomPositions[i] = (int) (Math.random() * size);
		}
		return randomPositions;
	}
}
