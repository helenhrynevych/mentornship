package com.example.demo.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Team {
	@Id
	private String id;
	List<Cell> members = new ArrayList<>();
	Set<Cell> free = new HashSet<>();

	public Team() {

	}

	public void addMembers(List<Cell> members) {
		this.members.addAll(members);
	}

	public void addFree(Set<Cell> free) {
		this.free.addAll(free);
	}

	@Override
	public String toString() {
		return "Team{" +
				"members=" + members +
				", free=" + free +
				'}';
	}
}
