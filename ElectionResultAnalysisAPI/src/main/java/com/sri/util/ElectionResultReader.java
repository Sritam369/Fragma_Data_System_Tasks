package com.sri.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sri.exception.CSVFileException;
import com.sri.model.ElectionResult;

@Component
public class ElectionResultReader {

	public List<ElectionResult> readResult(String fileName) {
		
		List<ElectionResult> list = new ArrayList<>();
		
		try {
			FileReader fr = new FileReader(fileName);
			BufferedReader b = new BufferedReader(fr);
			
			String line;
			
			b.readLine();
			
			while((line=b.readLine())!=null) {
				String arr[] =  line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
				
				ElectionResult er = new ElectionResult();
				
				er.setState(arr[0]);
				er.setConstituency(arr[1]);
				er.setSerialNumber(Integer.parseInt(arr[2]));
				er.setCandidate(arr[3]);
				er.setParty(arr[4]);
				er.setEvmVotes(Integer.parseInt(arr[5]));
				er.setPostalVotes(Integer.parseInt(arr[6]));
				er.setTotalVotes(Integer.parseInt(arr[7]));
				
				list.add(er);
			}
			b.close();
		}
		catch(Exception e) {
			throw new CSVFileException("Unable to read election result csv file");
		}
		
			return list;		
		
	}
}
