package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.models.Cell;
import com.example.demo.models.Matrix;
import com.example.demo.models.Team;
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
		//matrixRepository.save(matrix);
		return matrix;
	}

	public List<Team> getPositiveTeamsOfMatrix() {
		Matrix matrix = getMatrix(9);
		matrix.printMatrix();

		List<Team> teamsPositive = getTeams(matrix, 1);
		System.out.println(teamsPositive.toString());

		List<Team> teamsNegative = getTeams(matrix, -1);
		System.out.println(teamsNegative.toString());

		return teamsNegative;
	}


	public List<Team> getTeams(Matrix matrixx, int val) {
		int[][] matrix = matrixx.getData();
		List<Team> teams = new ArrayList<>();

		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				if (matrix[x][y] == val) {
					List<Cell> members = new ArrayList<>();
					members.add(new Cell(x, y, val, true));
					getAllTeammates(matrix, members, y, x, val);
					Team team = new Team();
					team.addMembers(members);
					teams.add(team);
				}
			}
		}
		return teams;
	}

	private void getAllTeammates(int[][] data, List<Cell> members, int y, int x, int val) {
		getNeighbours(data, members, y, x, val);
		data[x][y] = 0;
		Optional<Cell> checkedCell = members.stream().filter(member -> member.getX() == x && member.getY() == y)
				.findFirst();
		checkedCell.ifPresent(cell -> cell.setChecked(true));

		Optional<Cell> cell = members.stream().filter(member -> !member.isChecked()).findFirst();
		if (cell.isEmpty()) {
			return;
		}
		getAllTeammates(data, members, cell.get().getY(), cell.get().getX(), val);
	}

	private void getNeighbours(int[][] data, List<Cell> members, int y, int x, int val) {
		//check left
		if (y != 0) {
			if (data[x][y - 1] == val) {
				members.add(new Cell(x, y - 1, val, false));
			}
		}

		//check top
		if (x != 0) {
			if (data[x - 1][y] == val) {
				members.add(new Cell(x - 1, y, val, false));
			}
		}

		//check right
		if (y != data[x].length - 1) {
			if (data[x][y + 1] == val) {
				members.add(new Cell(x, y + 1, val, false));
			}
		}
		//check bottom
		if (x != data[y].length - 1) {
			if (data[x + 1][y] == val) {
				members.add(new Cell(x + 1, y, val, false));
			}
		}
	}


	private Matrix getMatrix(int size) {
		Matrix matrix = new Matrix();
		matrix.setRow(size);
		matrix.setCol(size);
		matrix.setData(generateData(size));
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
