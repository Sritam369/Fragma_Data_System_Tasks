package com.sri.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MajorityWinnerDTO {

	private String constituency;
	private Double percentage;
}
