package com.sri.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StateResultDTO {

    private String state;
    private Integer totalVotes;
    private Integer totalConstituencies;
}