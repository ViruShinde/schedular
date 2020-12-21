package com.globeop.riskfeed.schedular.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


//@JsonIgnoreProperties({"clientOnboardSet", "modified_date"})
//@JsonIgnoreProperties({"clientOnboardSet"})
@Entity
@Table(name="RiskAggregator")
public class RiskAggregator implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="RiskAggregatorId")
	private int riskAggregatorId;
	
	@Column(name="RiskAggregatorName")
	private String riskAggregatorName;
	
	@Column(name="RiskAggregatorContact")
	private String riskAggregatorContact;
	
	@Column(name="Modified_date")
	private LocalDate modified_date;
		

	public int getRiskAggregatorId() {
		return riskAggregatorId;
	}

	public void setRiskAggregatorId(int riskAggregatorId) {
		this.riskAggregatorId = riskAggregatorId;
	}

	public void setModified_date(LocalDate modified_date) {
		this.modified_date = modified_date;
	}



	public String getRiskAggregatorName() {
		return riskAggregatorName;
	}

	public void setRiskAggregatorName(String riskAggregatorName) {
		this.riskAggregatorName = riskAggregatorName;
	}

	public String getRiskAggregatorContact() {
		return riskAggregatorContact;
	}

	public void setRiskAggregatorContact(String riskAggregatorContact) {
		this.riskAggregatorContact = riskAggregatorContact;
	}

	
	public LocalDate getModified_date() {
		return modified_date;
	}

	
	@Override
	public String toString() {
		return "RiskAggregator [riskAggregatorId=" + riskAggregatorId + ", riskAggregatorName=" + riskAggregatorName
				+ ", riskAggregatorContact=" + riskAggregatorContact + ", modified_date=" + modified_date + "]";
	}
	
}
