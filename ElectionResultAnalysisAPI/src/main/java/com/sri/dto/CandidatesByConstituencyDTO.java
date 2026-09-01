package com.sri.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidatesByConstituencyDTO {

	private String constituency;
	private List<String> candidate;
	
}
