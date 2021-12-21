package com.example.demo.models;
import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Cell {
	@Id
	String id;
	int x;
	int y;
	int value;

	public Cell(int x, int y, int value) {
		this.x = x;
		this.y = y;
		this.value = value;
	}
}
