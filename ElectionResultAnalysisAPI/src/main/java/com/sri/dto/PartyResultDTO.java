package com.sri.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartyResultDTO {

    private String party;
    private Integer totalVotes;
    private Long seatsWon;
    private Double votePercentage;
}