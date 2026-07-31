package com.sri.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConstituencyResultDTO {

    private String constituency;
    private String winner;
    private String party;
    private Integer winnerVotes;
    private Integer totalVotes;
    private Long totalCandidates;
}