package com.sri.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.sri.exception.CSVFileException;
import com.sri.model.WinningCandidates;

@Component
public class WinningCandidatesReader {


    private List<WinningCandidates> wList;

    @PostConstruct
    public void loadData() {

        wList = new ArrayList<>();

        try {

            FileReader fr = new FileReader("C:\\Users\\Sritam Chakrabartty\\Downloads\\2019_Results_Winning_Candidate.csv");
            BufferedReader b = new BufferedReader(fr);

            String line;
            b.readLine();

            while ((line = b.readLine()) != null) {

                String arr[] = line.split(",");

                WinningCandidates w = new WinningCandidates();

                w.setState(arr[0]);
                w.setConstituency(arr[1]);
                w.setVotes(Integer.parseInt(arr[2]));
                w.setPercentage(Double.parseDouble(arr[3]));
                w.setParty(arr[4]);
                w.setCandidate(arr[5]);

                wList.add(w);
            }

            b.close();

        } catch (Exception e) {
            throw new CSVFileException("Unable to read winning candidate csv file");
        }
    }

    public List<WinningCandidates> getWinningCandidates() {
        return wList;
    }
}

	