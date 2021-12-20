package com.accessProject.footballManager.shared;

public class ResponseDto {

	public ResponseDto(String description) {
		this.description = description;
	}

	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ResponseDto [description=" + description + "]";
	}
}
