package com.globeop.riskfeed.schedular.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.globeop.riskfeed.schedular.entity.ClientTable;
import com.globeop.riskfeed.schedular.entity.FundTable;

public interface FundTableRepository extends JpaRepository<FundTable, Integer> {

	public List<FundTable> findByClient(ClientTable theClientTable);
	
	public List<FundTable> findByFundShortName(String fundShortName);
}
