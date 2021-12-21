package com.example.demo.controllers;

import com.example.demo.models.Matrix;
import com.example.demo.services.MatrixService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/matrix")
public class MatrixController {

	MatrixService matrixService;

	@Autowired
	public MatrixController(MatrixService matrixService) {
		this.matrixService = matrixService;
	}

	@GetMapping
	public Iterable<Matrix> getMatrix() {
		return matrixService.getMatrix();
	}

	@PostMapping
	public Matrix createMatrix() {
		return matrixService.createMatrix();
	}
}
