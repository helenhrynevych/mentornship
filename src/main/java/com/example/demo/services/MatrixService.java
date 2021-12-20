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
		Matrix matrix = new Matrix(9,9);
		matrix.setMatrixWithVals();
		matrixRepository.save(matrix);
		return matrix;
	}

}
