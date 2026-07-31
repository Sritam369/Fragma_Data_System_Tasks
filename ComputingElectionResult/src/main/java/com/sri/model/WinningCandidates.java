package com.sri.model;

public class WinningCandidates {

	private String state;
	private String constituency;
	private Integer votes;
	private Double percentage;
	private String party;
	private String candidate;
	
	public WinningCandidates() {
		super();
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getConstituency() {
		return constituency;
	}

	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}

	public Integer getVotes() {
		return votes;
	}

	public void setVotes(Integer votes) {
		this.votes = votes;
	}

	public Double getPercentage() {
		return percentage;
	}

	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public String getCandidate() {
		return candidate;
	}

	public void setCandidate(String candidate) {
		this.candidate = candidate;
	}

	@Override
	public String toString() {
		return "WinningCandidates [state=" + state + ", constituency=" + constituency + ", votes=" + votes
				+ ", percentage=" + percentage + ", party=" + party + ", candidate=" + candidate + "]";
	}
	
	
	
	
	
	
	
}
