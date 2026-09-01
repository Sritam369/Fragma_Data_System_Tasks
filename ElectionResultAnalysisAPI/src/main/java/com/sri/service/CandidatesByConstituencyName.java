package com.sri.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sri.dto.CandidatesByConstituencyDTO;
import com.sri.exception.ConstituencyNotFoundException;
import com.sri.model.ElectionResult;

@Service
public class CandidatesByConstituencyName {

	public CandidatesByConstituencyDTO getCandidates(List<ElectionResult>elist,String con) {
		
		List<String> candidates = new ArrayList<>();

		for(ElectionResult e: elist) {
			if((e.getConstituency().equalsIgnoreCase(con))) {
				candidates.add(e.getCandidate());
			}
		}
		
		 if (candidates.isEmpty()) {
	            throw new ConstituencyNotFoundException("Invalid constituency name");
	        }
		
		CandidatesByConstituencyDTO c = new CandidatesByConstituencyDTO();
		c.setConstituency(con);
		c.setCandidate(candidates);
		
		return c;
	}
}
