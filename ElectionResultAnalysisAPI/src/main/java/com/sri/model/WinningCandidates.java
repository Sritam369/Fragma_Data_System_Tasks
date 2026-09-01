package com.sri.model;

import lombok.Data;

@Data
public class WinningCandidates {

	private String state;
	private String constituency;
	private Integer votes;
	private Double percentage;
	private String party;
	private String candidate;
	
	
}
