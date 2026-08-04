package com.sri.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConstituencyWithMaxCandidatesDTO {

	private String constituency;
	private Integer candidatesCount; 
}
