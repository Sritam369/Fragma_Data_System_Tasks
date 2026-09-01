package com.sri.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sri.dto.PartyResultDTO;
import com.sri.model.ElectionResult;
import com.sri.model.WinningCandidates;

@Service
public class ResultByParty {

    public List<PartyResultDTO> partyResult(List<ElectionResult> eList,List<WinningCandidates> wList) {

        Map<String, Integer> totalVotesPerParty = eList.stream().filter(e -> !e.getParty().equalsIgnoreCase("Independent"))
                .collect(Collectors.groupingBy(e->e.getParty(),Collectors.summingInt(e->e.getTotalVotes())));

        Map<String, Long> winningSeats = wList.stream().filter(e -> !e.getParty().equalsIgnoreCase("Independent"))
                .collect(Collectors.groupingBy(e->e.getParty(),Collectors.counting()));

        Collection<Integer> values = totalVotesPerParty.values();
        Integer totalVotes = values.stream().reduce(0, Integer::sum);

        List<PartyResultDTO> result = new ArrayList<>();

        for (String party : totalVotesPerParty.keySet()) {

            Integer votes = totalVotesPerParty.get(party);
            Long seatsWon = winningSeats.getOrDefault(party, 0L);

            Double votePercentage = (votes * 100.0) / totalVotes;
            votePercentage = Math.round(votePercentage * 100.0) / 100.0;

            PartyResultDTO dto = new PartyResultDTO(
                    party,
                    votes,
                    seatsWon,
                    votePercentage
            );

            result.add(dto);
        }

        return result;
    }
    
}