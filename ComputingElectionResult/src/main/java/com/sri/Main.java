package com.sri;

import java.util.List;

import com.sri.model.ElectionResult;
import com.sri.model.ElectionResultReader;
import com.sri.model.WinningCandidates;
import com.sri.model.WinningCandidatesReader;
import com.sri.service.ResultComputer;

public class Main {

	public static void main(String[] args) throws Exception {
		
		ElectionResultReader electionReader = new ElectionResultReader();
        WinningCandidatesReader winnerReader = new WinningCandidatesReader();

        List<ElectionResult> eList =
                electionReader.readResult("C:\\Users\\Sritam Chakrabartty\\Downloads\\2019_Results.csv");

        List<WinningCandidates> wList =
                winnerReader.readWinners("C:\\Users\\Sritam Chakrabartty\\Downloads\\2019_Results_Winning_Candidate.csv");

        ResultComputer rc = new ResultComputer();

        rc.partyResult(eList, wList);
       // rc.resultByState(eList);
        //rc.resultByConstituency(wList,eList);
        //rc.majorityConstituency(wList);

	}
}
