package com.sri.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.sri.model.ElectionResult;
import com.sri.model.WinningCandidates;

public class ResultComputer {

	public void partyResult(List<ElectionResult> eList,List<WinningCandidates>wList) {
		
	  Map<String,Integer> totalVotesPerParty =  eList.stream().filter(e-> !e.getParty().equalsIgnoreCase("independent")).
			  collect(Collectors.groupingBy(e->e.getParty(),Collectors.summingInt(e->e.getTotalVotes())));
	  
	  Map<String,Long> winningVotes = wList.stream().filter(e->!e.getParty().equalsIgnoreCase("independent")).
			  collect(Collectors.groupingBy(e->e.getParty(),Collectors.counting()));
	  
	  Collection<Integer> values = totalVotesPerParty.values();
	  Integer totalVotes = values.stream().reduce(0,(a,b)->a+b);
	  
	  System.out.printf("%-45s %-15s %-12s %-10s%n",
		        "Party", "Total Votes", "Seats Won", "Vote %");
	  
	  for(String party : totalVotesPerParty.keySet()) {
		  
		  int votes = totalVotesPerParty.get(party);
		  long wins = winningVotes.getOrDefault(party,0L);
		  double percentage = (votes*100.0)/totalVotes;
		  percentage = Math.round(percentage * 100.0) / 100.0;
		  
		  System.out.printf("%-45s %-15d %-12d %-10.2f%n",
		            party,
		            votes,
		            wins,
		            percentage);
	  }
	}
	
	public void resultByState(List<ElectionResult>eList) {
		
		Set<String>con = new HashSet<>();
		Map<String, List<ElectionResult>> collect = eList.stream().collect(Collectors.groupingBy(e->e.getState()));
		
		Map<String, Integer> totalVotes = eList.stream().
				collect(Collectors.groupingBy(e->e.getState(),Collectors.summingInt(e->e.getTotalVotes())));
		
		System.out.printf("%-25s %-20s %-20s%n",
		        "State", "Constituencies", "Total Votes");
		
		for(String state : collect.keySet()) {
			List<ElectionResult> list = collect.get(state);
			for(ElectionResult l : list) {
				String s = l.getConstituency();
				con.add(s);
			}
			
			System.out.printf("%-25s %-20d %-20d%n",
		            state,
		            con.size(),
		            totalVotes.get(state));

		    con.clear();
		}
	}
	
	public void resultByConstituency(List<WinningCandidates>wList,List<ElectionResult>eList) {
		
		Map<String, Integer> totalVotesPerConstituency = wList.stream().collect(Collectors.toMap(
				 e -> e.getState() + "-" + e.getConstituency(),
                e -> e.getVotes()
        ));
		
		Map<String, String> winningCandidatePerConstituency = wList.stream().collect(Collectors.toMap(
				 e -> e.getState() + "-" + e.getConstituency(),
				e -> e.getCandidate()
				));
		
		Map<String, String> partyNamePerConstituency = wList.stream().collect(Collectors.toMap(
				 e -> e.getState() + "-" + e.getConstituency(),
				e -> e.getParty()
				));
		
		Map<String, Integer> totalVotesCasted = eList.stream().
				collect(Collectors.groupingBy(e -> e.getState() + "-" + e.getConstituency(),Collectors.summingInt(e->e.getTotalVotes())));
		
		Map<String, Long> totalCandidatesParticipated = eList.stream()
		        .collect(Collectors.groupingBy(
		        		 e -> e.getState() + "-" + e.getConstituency(),
		                Collectors.counting()
		        ));
		
		System.out.printf("%-25s %-30s %-30s %-15s %-15s %-10s%n",
		        "Constituency",
		        "Winning Candidate",
		        "Party",
		        "Winner Votes",
		        "Total Votes",
		        "Candidates");
		
		for(String constituency :totalVotesPerConstituency.keySet() ) {
			
			System.out.printf("%-25s %-30s %-30s %-15d %-15d %-10d%n",
			        constituency,
			        winningCandidatePerConstituency.get(constituency),
			        partyNamePerConstituency.get(constituency),
			        totalVotesPerConstituency.get(constituency),
			        totalVotesCasted.get(constituency),
			        totalCandidatesParticipated.get(constituency));
		}
	}
	
	public void majorityConstituency(List<WinningCandidates> wList) {
		
		wList.stream().filter(e->e.getPercentage()>50.0).forEach(e->System.out.println(e.getConstituency()+" -> "+e.getPercentage()));
	}
}

