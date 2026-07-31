package com.sri.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sri.exception.CSVFileException;
import com.sri.model.WinningCandidates;

@Component
public class WinningCandidatesReader {

	public List<WinningCandidates> readWinners(String fileName) {
		
		List<WinningCandidates> list = new ArrayList<>();
		
		try{
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
		}
		catch(Exception e) {
			throw new CSVFileException("Unable to read winning candidates csv file");
		}
		
		return list;
	}
}
