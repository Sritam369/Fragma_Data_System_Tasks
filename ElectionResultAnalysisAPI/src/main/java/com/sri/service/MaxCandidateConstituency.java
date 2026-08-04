package com.sri.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sri.dto.ConstituencyWithMaxCandidatesDTO;
import com.sri.model.ElectionResult;

@Service
public class MaxCandidateConstituency {

	public ConstituencyWithMaxCandidatesDTO getConstituency(List<ElectionResult>elist) {
		
		ConstituencyWithMaxCandidatesDTO constituency = new ConstituencyWithMaxCandidatesDTO();
		Map<String, List<ElectionResult>> collect = elist.stream().collect(Collectors.groupingBy(e->e.getState()+"-"+e.getConstituency()));
		
		int maxCount = 0;
		
		// counting the max candidate in a constituency
	    for(String con:collect.keySet()) {
	    	
	    	int count = 0;
	    	
	    	List<ElectionResult> list = collect.get(con);
	    	for(ElectionResult e:list) {
	    		if(e.getCandidate()!=null) {
	    		count++;
	    		}
	    		if(count>maxCount) {
	    			maxCount=count;
	    		}
	    	}
	    }
	    
	    // finding max candidate constituency
	    for(String con:collect.keySet()) {
	    	
	    	int c =0;
	    	
	    	List<ElectionResult> list = collect.get(con);
	    	for(ElectionResult e:list) {
	    		if(e.getCandidate()!=null) {
	    		c++;
	    		}
	    	}
	    	if(maxCount==c) {
	    		constituency.setConstituency(con.split("-", 2)[1]);
	    		constituency.setCandidatesCount(maxCount);
	    	}
	    }
	    return constituency;
	}
}
