package com.sri.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.sri.exception.CSVFileException;
import com.sri.model.ElectionResult;

@Component
public class ElectionResultReader {

    private List<ElectionResult> eList;

    @PostConstruct
    public void loadData() {

        eList = new ArrayList<>();

        try {
            FileReader fr = new FileReader("C:\\Users\\Sritam Chakrabartty\\Downloads\\2019_Results.csv");
            BufferedReader b = new BufferedReader(fr);

            String line;
            b.readLine();

            while ((line = b.readLine()) != null) {

                String arr[] = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                ElectionResult er = new ElectionResult();

                er.setState(arr[0]);
                er.setConstituency(arr[1]);
                er.setSerialNumber(Integer.parseInt(arr[2]));
                er.setCandidate(arr[3]);
                er.setParty(arr[4]);
                er.setEvmVotes(Integer.parseInt(arr[5]));
                er.setPostalVotes(Integer.parseInt(arr[6]));
                er.setTotalVotes(Integer.parseInt(arr[7]));

                eList.add(er);
            }

            b.close();

        } catch (Exception e) {
            throw new CSVFileException("Unable to read election result csv file");
        }
    }

    public List<ElectionResult> getElectionResults() {
        return eList;
    }
}