package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Team {
	@Id
	private String id;
	List<Cell> members = new ArrayList<>();
	List<Cell> free = new ArrayList<>();

	public Team() {

	}

	public void addMembers(List<Cell> members) {
		this.members.addAll(members);
	}

	@Override
	public String toString() {
		return "Team{" +
				"members=" + members +
				", free=" + free +
				'}';
	}
}
