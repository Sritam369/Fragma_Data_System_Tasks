package com.sri.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sri.dto.StateResultDTO;
import com.sri.model.ElectionResult;

@Service
public class ResultByState {

    public List<StateResultDTO> stateResult(List<ElectionResult> eList) {

        List<StateResultDTO> result = new ArrayList<>();

        Map<String, List<ElectionResult>> stateWiseData = eList.stream().collect(Collectors.groupingBy(e->e.getState()));

        Map<String, Integer> totalVotes = eList.stream().collect(Collectors.groupingBy(e->e.getState(),
                                          Collectors.summingInt(e->e.getTotalVotes())));

        for (String state : stateWiseData.keySet()) {

            Set<String> constituencies = new HashSet<>();

            for (ElectionResult e : stateWiseData.get(state)) {
                constituencies.add(e.getConstituency());
            }

            StateResultDTO s = new StateResultDTO(state,totalVotes.get(state),constituencies.size());
            
            result.add(s);
        }

        return result;
    }
}