package com.sri.model;

public class ElectionResult {

	private String state;
	private String constituency;
	private Integer serialNumber;
	private String candidate;
	private String party;
	private Integer evmVotes;
	private Integer postalVotes;
	private Integer totalVotes;
	
	public ElectionResult() {
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
	
	public Integer getSerialNumber() {
		return serialNumber;
	}
	
	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getCandidate() {
		return candidate;
	}

	public void setCandidate(String candidate) {
		this.candidate = candidate;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public Integer getEvmVotes() {
		return evmVotes;
	}

	public void setEvmVotes(Integer evmVotes) {
		this.evmVotes = evmVotes;
	}

	public Integer getPostalVotes() {
		return postalVotes;
	}

	public void setPostalVotes(Integer postalVotes) {
		this.postalVotes = postalVotes;
	}

	public Integer getTotalVotes() {
		return totalVotes;
	}

	public void setTotalVotes(Integer totalVotes) {
		this.totalVotes = totalVotes;
	}

	@Override
	public String toString() {
		return "ElectionResult [serialNumber=" + serialNumber + ", state=" + state + ", constituency=" + constituency
				+ ", candidate=" + candidate + ", party=" + party + ", evmVotes=" + evmVotes + ", postalVotes="
				+ postalVotes + ", totalVotes=" + totalVotes + "]";
	}
	
	
}
