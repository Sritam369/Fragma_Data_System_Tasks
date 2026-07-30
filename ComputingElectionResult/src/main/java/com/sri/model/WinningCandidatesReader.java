package com.sri.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class WinningCandidatesReader {

	public List<WinningCandidates> readWinners(String fileName) throws Exception{
		
		List<WinningCandidates> list = new ArrayList<>();
		
		FileReader fr = new FileReader(fileName);
		BufferedReader b = new BufferedReader(fr);
		
		String line;
		b.readLine();
		
		while((line=b.readLine())!=null) {
			WinningCandidates w = new WinningCandidates();
			
			String arr[] = line.split(",");
			
			w.setState(arr[0]);
			w.setConstituency(arr[1]);
			w.setVotes(Integer.parseInt(arr[2]));
			w.setPercentage(Double.parseDouble(arr[3]));
			w.setParty(arr[4]);
			w.setCandidate(arr[5]);
			
			list.add(w);
		}
		
		b.close();
		return list;
	}
}
