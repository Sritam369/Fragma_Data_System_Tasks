package com.sri.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sri.dto.StateWithHighestVotesDTO;
import com.sri.model.ElectionResult;

@Service
public class HighestTurnoutState {

	public StateWithHighestVotesDTO getState(List<ElectionResult>elist) {
		
		// counting state wise total votes
		Map<String, Integer> collect = elist.stream().collect(Collectors.groupingBy(e->e.getState(),Collectors.summingInt(e->e.getTotalVotes())));
	    
		//getting the state with max votes
		Map<String, Integer> collect2 = collect.entrySet().stream().sorted((i,j)->Integer.compare(j.getValue(), i.getValue())).
				                        limit(1).collect(Collectors.toMap(e->e.getKey(), e->e.getValue()));
	    
		StateWithHighestVotesDTO state = new StateWithHighestVotesDTO();
		for(Map.Entry<String, Integer> e:collect2.entrySet()) {
			
			state.setState(e.getKey());
			state.setTotalVotes(e.getValue());
		}
		
		return state;
		
	}
}
