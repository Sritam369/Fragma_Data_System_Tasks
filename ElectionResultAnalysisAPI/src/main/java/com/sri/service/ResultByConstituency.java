package com.sri.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sri.dto.ConstituencyResultDTO;
import com.sri.model.ElectionResult;
import com.sri.model.WinningCandidates;

@Service
public class ResultByConstituency {

    public List<ConstituencyResultDTO> resultByConstituency(List<WinningCandidates> wList, List<ElectionResult> eList){

        List<ConstituencyResultDTO> result = new ArrayList<>();

        Map<String, String> winningCandidates = wList.stream().
        		collect(Collectors.toMap(e->e.getState()+"-"+e.getConstituency(), e->e.getCandidate()));

        Map<String, String> winningParty = wList.stream().collect(Collectors.toMap(e->e.getState()+"-"+e.getConstituency(), e->e.getParty()));

        Map<String, Integer> totalWinningVotes = wList.stream().
        		collect(Collectors.toMap(e->e.getState()+"-"+e.getConstituency(),e->e.getVotes()));

        Map<String, Integer> totalVotes = eList.stream().
        		collect(Collectors.groupingBy(e->e.getState()+"-"+e.getConstituency(),Collectors.summingInt(e->e.getTotalVotes())));

        Map<String, Long> totalCandidates = eList.stream()
                .collect(Collectors.groupingBy(e -> e.getState() + "-" + e.getConstituency(),Collectors.counting()));

        for (String key : totalWinningVotes.keySet()) {

            String constituency = key.split("-", 2)[1];

            ConstituencyResultDTO c = new ConstituencyResultDTO(
                    constituency,
                    winningCandidates.get(key),
                    winningParty.get(key),
                    totalWinningVotes.get(key),
                    totalVotes.get(key),
                    totalCandidates.get(key)
            );
            
            result.add(c);
        }

        return result;
    }
}