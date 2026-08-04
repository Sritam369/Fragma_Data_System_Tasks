package com.sri.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sri.dto.Top5WinnersDTO;
import com.sri.model.WinningCandidates;

@Service
public class Top5WinnersByVotes {

	public List<Top5WinnersDTO> getWinnersByParty(List<WinningCandidates>wList){
		
		List<Top5WinnersDTO> result = new ArrayList<>();
		
		List<WinningCandidates> list = wList.stream().sorted((i,j)->Integer.compare(j.getVotes(), i.getVotes())).limit(5).toList();
		
		for(WinningCandidates c:list) {
			Top5WinnersDTO w = new Top5WinnersDTO();
			w.setWinnerName(c.getCandidate());
			w.setTotalVotes(c.getVotes());
			result.add(w);
		}
		
		return result;
		
	}
}
