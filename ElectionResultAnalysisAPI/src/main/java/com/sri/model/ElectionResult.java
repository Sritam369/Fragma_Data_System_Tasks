package com.sri.model;

import lombok.Data;

@Data
public class ElectionResult {

	private String state;
	private String constituency;
	private Integer serialNumber;
	private String candidate;
	private String party;
	private Integer evmVotes;
	private Integer postalVotes;
	private Integer totalVotes;
	
		
}
