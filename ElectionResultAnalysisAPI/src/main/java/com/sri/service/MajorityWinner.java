package com.sri.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sri.dto.MajorityWinnerDTO;
import com.sri.model.WinningCandidates;

@Service
public class MajorityWinner {

public List<MajorityWinnerDTO> majorityConstituency(List<WinningCandidates> wList) {
		
	     List<MajorityWinnerDTO> result = new ArrayList<>();
	     
		 List<WinningCandidates> list = wList.stream().filter(e->e.getPercentage()>50.0).toList();
		 
		 for(WinningCandidates w : list) {
			 
			 MajorityWinnerDTO m = new MajorityWinnerDTO(w.getConstituency(),w.getPercentage());
			 result.add(m);
		 }
		 
		return result;
	}
}
