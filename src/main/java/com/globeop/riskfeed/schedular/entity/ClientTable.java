package com.globeop.riskfeed.schedular.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;  

@Entity  
@Table(name="ClientTable")  
public class ClientTable implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id   
	@Column(name = "ClientID")
	@GeneratedValue(strategy=GenerationType.IDENTITY) 	
	private int clientID;
	
	@Column(name = "ClientShortName")
	private String clientShortName;
	
	@Column(name = "Modified_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate modified_date;

	@JsonManagedReference
	@OneToMany(targetEntity = FundTable.class, cascade = CascadeType.ALL, mappedBy="client")
	
	//@OneToMany(targetEntity = FundTable.class,fetch=FetchType.EAGER, cascade = CascadeType.ALL) 
	//@JoinColumn(name="ClientID")
	private Set<FundTable> funds;
			

	public Set<FundTable> getFunds() {
		return funds;
	}

	public void setFunds(Set<FundTable> funds) {
		this.funds = funds;
	}
	

	public int getClientID() {
		return clientID;
	}

	public void setClientID(int clientID) {
		this.clientID = clientID;
	}

	public String getClientShortName() {
		return clientShortName;
	}

	public void setClientShortName(String clientShortName) {
		this.clientShortName = clientShortName;
	}

	public LocalDate getModified_date() {
		return modified_date;
	}

	public void setModified_date(LocalDate modified_date) {
		this.modified_date = modified_date;
	}


	public void addFund(FundTable theFund) {
		if(funds==null) {
			funds = new HashSet<FundTable>();
		}
		theFund.setClient(this);
        this.funds.add(theFund);
    }
	
	
	

	@Override
	public String toString() {
		return "ClientTable [clientID=" + clientID + ", ClientShortName=" + clientShortName + ", Modified_date="
				+ modified_date + ", funds=" + funds + "]";
	}


	
    
}
