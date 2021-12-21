package com.example.demo.models;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Cell {
	@Id
	private String id;
	int x;
	int y;
	int val;
	boolean isChecked;

	public Cell(int x, int y, int val, boolean isChecked) {
		this.x = x;
		this.y = y;
		this.val = val;
		this.isChecked = isChecked;
	}

	@Override
	public String toString() {
		return "Cell{" +
				"x=" + x +
				", y=" + y +
				", val=" + val +
				", isChecked=" + isChecked +
				'}';
	}
}
