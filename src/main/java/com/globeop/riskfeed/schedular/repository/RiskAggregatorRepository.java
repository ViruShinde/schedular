package com.globeop.riskfeed.schedular.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.globeop.riskfeed.schedular.entity.RiskAggregator;

public interface RiskAggregatorRepository extends JpaRepository<RiskAggregator, Integer> {
	
	public List<RiskAggregator> findByRiskAggregatorNameStartingWith(String riskAggregatorName);

}
