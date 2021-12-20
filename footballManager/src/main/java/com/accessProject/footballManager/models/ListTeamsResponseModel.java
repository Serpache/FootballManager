package com.accessProject.footballManager.models;

import java.util.ArrayList;
import java.util.List;

import com.accessProject.footballManager.shared.FootballTeamDto;

public class ListTeamsResponseModel {

	private List<FootballTeamDto> list = new ArrayList<>();

	
	public List<FootballTeamDto> getList() {
		return list;
	}

	public void setList(List<FootballTeamDto> list) {
		this.list = list;
	}
}
