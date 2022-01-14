package com.example.demo.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

	public Iterable<Matrix> getAllMatrixes() {
		return matrixRepository.findAll();
	}

	public Matrix init() {
		Matrix matrix = getMatrix();
		return matrix;
	}

	public Matrix calculate() {
		//field
		Matrix matrix = getMatrix();
		matrix.printMatrix();
		//get teams
		List<Team> teamsPositive = getTeams(matrix, 1);
		List<Team> teamsNegative = getTeams(matrix, -1);

		//get calculated frees
		Matrix calculatedFirstTeam = getMatrixWithFrees(matrix, teamsPositive, 1);
		Matrix calculatedSecondTeam = getMatrixWithFrees(matrix, teamsNegative, -1);

		Matrix result = new Matrix();
		result.concatTwoMatrixes(calculatedFirstTeam, calculatedSecondTeam);
		System.out.println("START");
		result.printMatrix();
		System.out.println("END");

		return result;
	}

	private Matrix getMatrixWithFrees(Matrix matrix, List<Team> teams, int val) {
		int[][] data = matrix.getData();
		for (Team team : teams) {
			List<Cell> members = team.getMembers();
			for (Cell mem : members) {
				int freeSize = team.getFree().size();
				data[mem.getX()][mem.getY()] = freeSize * val;
			}
		}
		matrix.setData(data);
		return matrix;
	}

	public List<Team> getTeams(Matrix matrixx, int val) {
		int[][] matrix = copyOf(matrixx.getData());
		int[][] matrixCopy = copyOf(matrixx.getData());

		List<Team> teams = new ArrayList<>();

		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				if (matrixCopy[x][y] == val) {
					List<Cell> members = new ArrayList<>();
					Set<Cell> freeList = new HashSet<>();

					members.add(new Cell(x, y, val, true));
					getAllTeammates(matrixCopy, members, y, x, val);

					Team team = new Team();
					getTeamFrees(matrix, members, freeList, y, x);
					team.addMembers(members);
					team.addFree(freeList);
					teams.add(team);
				}
			}
		}
		return teams;
	}

	private int[][] copyOf(int[][] data) {
		int[][] copy = new int[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				copy[i][j] = data[i][j];
			}
		}
		System.out.println("COPY OF:");
		return copy;
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


	private void getTeamFrees(int[][] data, List<Cell> members, Set<Cell> free, int y, int x) {
		for (Cell member : members) {
			getFrees(data, free, member.getY(), member.getX());
		}
	}

	private Set<Cell> getFrees(int[][] data, Set<Cell> free, int y, int x) {
		//check left
		if (y != 0) {
			if (data[x][y - 1] == 0) {
				free.add(new Cell(x, y - 1, 0, true));
			}
		}
		//check top
		if (x != 0) {
			if (data[x - 1][y] == 0) {
				free.add(new Cell(x - 1, y, 0, true));
			}
		}

		//check right
		if (y != data[x].length - 1) {
			if (data[x][y + 1] == 0) {
				free.add(new Cell(x, y + 1, 0, true));
			}
		}
		//check bottom
		if (x != data[y].length - 1) {
			if (data[x + 1][y] == 0) {
				free.add(new Cell(x + 1, y, 0, true));
			}
		}
		return free;
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


	private Matrix getMatrix() {
		Matrix matrix = new Matrix();
		matrix.setData(generateData());
		return matrix;
	}

	private int[][] generateData() {
		int[] randomRowPositions = getRandomPositions(9);
		int[] randomColPositions = getRandomPositions(9);
		int[][] data = new int[9][9];

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
