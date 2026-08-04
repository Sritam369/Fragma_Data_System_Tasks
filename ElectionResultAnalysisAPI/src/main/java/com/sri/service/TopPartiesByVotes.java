package com.sri.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sri.dto.TopPartiesByVotesDTO;
import com.sri.model.ElectionResult;
import com.sri.model.WinningCandidates;

@Service
public class TopPartiesByVotes {

	public List<TopPartiesByVotesDTO> getPartiesByVotes(List<ElectionResult> eList){
		
		List<TopPartiesByVotesDTO> result = new ArrayList<>();
				
		Map<String, Integer> collect = eList.stream().collect(Collectors.groupingBy(e->e.getParty(),Collectors.summingInt(e->e.getTotalVotes())));
		List<Entry<String, Integer>> list = collect.entrySet().stream().
				                            sorted((i,j)->Integer.compare(j.getValue(), i.getValue())).limit(5).toList();
		
		for(Entry<String, Integer> l : list) {
			TopPartiesByVotesDTO t = new TopPartiesByVotesDTO();
			
			t.setPartyName(l.getKey());
			t.setTotalVotes(l.getValue());
			
			result.add(t);
		}

		return result;
	}
}
