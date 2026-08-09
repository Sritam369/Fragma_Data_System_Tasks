package com.sri.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sri.dto.CandidatesByConstituencyDTO;
import com.sri.dto.ConstituencyResultDTO;
import com.sri.dto.ConstituencyWithMaxCandidatesDTO;
import com.sri.dto.MajorityWinnerDTO;
import com.sri.dto.PartyResultDTO;
import com.sri.dto.StateResultDTO;
import com.sri.dto.StateWithHighestVotesDTO;
import com.sri.dto.Top5WinnersDTO;
import com.sri.dto.TopPartiesByVotesDTO;
import com.sri.model.ElectionResult;
import com.sri.model.WinningCandidates;
import com.sri.service.CandidatesByConstituencyName;
import com.sri.service.HighestTurnoutState;
import com.sri.service.MajorityWinner;
import com.sri.service.MaxCandidateConstituency;
import com.sri.service.ResultByConstituency;
import com.sri.service.ResultByParty;
import com.sri.service.ResultByState;
import com.sri.service.Top5WinnersByVotes;
import com.sri.service.TopPartiesByVotes;
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
	    private TopPartiesByVotes topParties;
	    
	    @Autowired
	    private Top5WinnersByVotes topWinners;
	    
	    @Autowired
	    private MaxCandidateConstituency maxCandidates;
	    
	    @Autowired
	    private CandidatesByConstituencyName candidates;
	    
	    @Autowired
	    private HighestTurnoutState state;
	    
	    @Autowired
	    private ElectionResultReader electionResultReader;

	    @Autowired
	    private WinningCandidatesReader winningCandidatesReader;
	           

	    @GetMapping("/party")
	    public List<PartyResultDTO> getPartyResult()  {
    
	        List<ElectionResult> eList = electionResultReader.getElectionResults();

	        List<WinningCandidates> wList = winningCandidatesReader.getWinningCandidates();
	         List<PartyResultDTO> partyResult = resultByParty.partyResult(eList, wList);
	         return partyResult;
	    } 
	    
	    @GetMapping("/state")
	    public List<StateResultDTO> getStateResult() {
	    	
	    	 List<ElectionResult> eList = electionResultReader.getElectionResults();
	        
	         List<StateResultDTO> stateResult = resultByState.stateResult(eList);
	         return stateResult;
	    }
	    
	    @GetMapping("/constituency")
	    public List<ConstituencyResultDTO> getConstituencyResult() {
	    	
	    	List<ElectionResult> eList = electionResultReader.getElectionResults();

	        List<WinningCandidates> wList = winningCandidatesReader.getWinningCandidates();
	        
		     List<ConstituencyResultDTO> result = resultByConstituency.resultByConstituency(wList, eList);
		     return result;
	    }
	    
	    @GetMapping("/majorityWinner")
	    public List<MajorityWinnerDTO> getMajorityWinner() {

	    	 List<WinningCandidates> wList = winningCandidatesReader.getWinningCandidates();

	         List<MajorityWinnerDTO> majorityConstituency = majorityWinner.majorityConstituency(wList);
	         return majorityConstituency;
	         
	    }
	    
	    @GetMapping("/top")
	    public List<TopPartiesByVotesDTO> getTop5Parties(){
	    	
<<<<<<< HEAD
	    	List<ElectionResult> eList = electionResultReader.getElectionResults();
	    	List<TopPartiesByVotesDTO> partiesByVotes = topParties.getPartiesByVotes(eList);
	    	
	    	return partiesByVotes;
	    }
	    
	    @GetMapping("/topCandidates")
	    public List<Top5WinnersDTO> getTop5Candidates(){
	    	
	    	 List<WinningCandidates> wList = winningCandidatesReader.getWinningCandidates();
             List<Top5WinnersDTO> winnersByParty = topWinners.getWinnersByParty(wList);
	    	
	    	return  winnersByParty;
	    }
	    
	    @GetMapping("/maxConstituency")
	    public ConstituencyWithMaxCandidatesDTO getConstituency() {
	    	
	    	List<ElectionResult> eList = electionResultReader.getElectionResults();
	    	ConstituencyWithMaxCandidatesDTO constituency = maxCandidates.getConstituency(eList);
	    	
	    	return constituency;
	    }
	    
	    @GetMapping("/candidates/{name}")
	    public CandidatesByConstituencyDTO getCandidates(@PathVariable("name")String name) {
	    	
	    	List<ElectionResult> eList = electionResultReader.getElectionResults();
	    	CandidatesByConstituencyDTO candidatesName = candidates.getCandidates(eList, name);
	    	
	    	return candidatesName;
	    }
	    
	    @GetMapping("/highestTurnout")
	    public StateWithHighestVotesDTO getState() {
	    	
	    	List<ElectionResult> eList = electionResultReader.getElectionResults();
=======
	    	List<ElectionResult> eList = electionResultReader.readResult("C:\\Users\\Sritam Chakrabartty\\Downloads\\2019_Results.csv");
	    	List<TopPartiesByVotesDTO> partiesByVotes = topParties.getPartiesByVotes(eList);
	    	
	    	return partiesByVotes;
	    }
	    
	    @GetMapping("/topCandidates")
	    public List<Top5WinnersDTO> getTop5Candidates(){
	    	
	    	 List<WinningCandidates> wList = winningCandidatesReader.readWinners("C:\\Users\\Sritam Chakrabartty\\Downloads\\2019_Results_Winning_Candidate.csv");
             List<Top5WinnersDTO> winnersByParty = topWinners.getWinnersByParty(wList);
	    	
	    	return  winnersByParty;
	    }
	    
	    @GetMapping("/maxConstituency")
	    public ConstituencyWithMaxCandidatesDTO getConstituency() {
	    	
	    	List<ElectionResult> eList = electionResultReader.readResult("C:\\Users\\Sritam Chakrabartty\\Downloads\\2019_Results.csv");
	    	ConstituencyWithMaxCandidatesDTO constituency = maxCandidates.getConstituency(eList);
	    	
	    	return constituency;
	    }
	    
	    @GetMapping("/candidates/{name}")
	    public CandidatesByConstituencyDTO getCandidates(@PathVariable("name")String name) {
	    	
	    	List<ElectionResult> eList = electionResultReader.readResult("C:\\Users\\Sritam Chakrabartty\\Downloads\\2019_Results.csv");
	    	CandidatesByConstituencyDTO candidatesName = candidates.getCandidates(eList, name);
	    	
	    	return candidatesName;
	    }
	    
	    @GetMapping("/highestTurnout")
	    public StateWithHighestVotesDTO getState() {
	    	
	    	List<ElectionResult> eList = electionResultReader.readResult("C:\\Users\\Sritam Chakrabartty\\Downloads\\2019_Results.csv");
>>>>>>> branch 'main' of https://github.com/Sritam369/Fragma_Data_System_Tasks.git
	    	StateWithHighestVotesDTO state2 = state.getState(eList);
	    	
	    	return state2;
	    }
}
