package com.globeop.riskfeed.schedular.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.globeop.riskfeed.schedular.entity.ClientTable;

public interface ClientRepository extends JpaRepository<ClientTable, Integer> {

	public List<ClientTable> findByClientShortName(String clientShortName);
	
	public List<ClientTable> findByClientShortNameStartingWith(String clientShortName);
	
	public List<ClientTable> findByClientShortNameLike(String clientShortName);
	
}
