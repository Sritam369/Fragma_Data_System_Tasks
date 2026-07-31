package com.sri.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sri.dto.ConstituencyResultDTO;
import com.sri.dto.MajorityWinnerDTO;
import com.sri.dto.PartyResultDTO;
import com.sri.dto.StateResultDTO;
import com.sri.model.ElectionResult;
import com.sri.model.WinningCandidates;
import com.sri.service.MajorityWinner;
import com.sri.service.ResultByConstituency;
import com.sri.service.ResultByParty;
import com.sri.service.ResultByState;
import com.sri.util.ElectionResultReader;
import com.sri.util.WinningCandidatesReader;

@RestController
@RequestMapping("/Election")
public class ElectionResultComputerApi {

	    @Autowired
	    private ResultByParty resultByParty;

	    @Autowired
	    private ResultByState resultByState;

	    @Autowired
	    private ResultByConstituency resultByConstituency;

	    @Autowired
	    private MajorityWinner majorityWinner;
	    
	    @Autowired
	    private ElectionResultReader electionResultReader;

	    @Autowired
	    private WinningCandidatesReader winningCandidatesReader;
	           

	    @GetMapping("/party")
	    public List<PartyResultDTO> getPartyResult()  {
    
	        List<ElectionResult> eList = electionResultReader.readResult("C:\\Users\\Sritam Chakrabartty\\Downloads\\2019_Results.csv");

	        List<WinningCandidates> wList = winningCandidatesReader.readWinners("C:\\Users\\Sritam Chakrabartty\\Downloads\\2019_Results_Winning_Candidate.csv");

	         List<PartyResultDTO> partyResult = resultByParty.partyResult(eList, wList);
	         return partyResult;
	    } 
	    
	    @GetMapping("/state")
	    public List<StateResultDTO> getStateResult() {
	    	
	    	 List<ElectionResult> eList = electionResultReader.readResult("C:\\Users\\Sritam Chakrabartty\\Downloads\\2019_Results.csv");
	        
	         List<StateResultDTO> stateResult = resultByState.stateResult(eList);
	         return stateResult;
	    }
	    
	    @GetMapping("/constituency")
	    public List<ConstituencyResultDTO> getConstituencyResult() {
	    	
	    	 List<ElectionResult> eList = electionResultReader.readResult("C:\\Users\\Sritam Chakrabartty\\Downloads\\2019_Results.csv");

		     List<WinningCandidates> wList = winningCandidatesReader.readWinners("C:\\Users\\Sritam Chakrabartty\\Downloads\\2019_Results_Winning_Candidate.csv");

		     List<ConstituencyResultDTO> result = resultByConstituency.resultByConstituency(wList, eList);
		     return result;
	    }
	    
	    @GetMapping("/majorityWinner")
	    public List<MajorityWinnerDTO> getMajorityWinner() {

	        List<WinningCandidates> wList = winningCandidatesReader.readWinners("C:\\Users\\Sritam Chakrabartty\\Downloads\\2019_Results_Winning_Candidate.csv");

	         List<MajorityWinnerDTO> majorityConstituency = majorityWinner.majorityConstituency(wList);
	         return majorityConstituency;
	         
	    }
}
