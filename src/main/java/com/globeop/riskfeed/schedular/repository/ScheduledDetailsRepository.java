package com.globeop.riskfeed.schedular.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.globeop.riskfeed.schedular.dto.SchedularDto;
import com.globeop.riskfeed.schedular.entity.ScheduledDetails;

public interface ScheduledDetailsRepository extends JpaRepository<ScheduledDetails, Integer> {

	public ScheduledDetails findByscheduledDetailsId(int id);
	
	

	  @Query(value=
	  "SELECT NEW com.globeop.riskfeed.schedular.dto.SchedularDto("
	  + "s.scheduledDetailsId, s.min, s.hour, s.dayOfMonth, s.month, s.dayOfWeek, "
	  + "s.riskAggregatorId, r.riskAggregatorName, s.clientId, c.clientShortName, s.fundId,f.fundShortName, "
	  + "s.frequency, s.businessDay, s.fundGroup, s.consolidated, s.comments, s.isActive, s.modified_date  )"	  
			  	
	  + " from ScheduledDetails AS s "
	  
	  + " JOIN RiskAggregator AS r " 
	  + " on r.riskAggregatorId = s.riskAggregatorId " 
	  
	  + " JOIN ClientTable AS c " 
	  + " on c.clientID = s.clientId " 
	  
	  + " JOIN FundTable AS f " 
	  + " on f.fundID = s.fundId " 
	  
	  + " where s.scheduledDetailsId=?1"
	  
	 )
	public Page<SchedularDto> getscheduledetailsById(int id,Pageable pageable);
	  
	  @Query(value=
			  "SELECT NEW com.globeop.riskfeed.schedular.dto.SchedularDto("
			  + "s.scheduledDetailsId, s.min, s.hour, s.dayOfMonth, s.month, s.dayOfWeek, "
			  + "s.riskAggregatorId, r.riskAggregatorName, s.clientId, c.clientShortName, s.fundId,f.fundShortName, "
			  + "s.frequency, s.businessDay, s.fundGroup, s.consolidated, s.comments, s.isActive, s.modified_date  )"	  
					  	
			  + " from ScheduledDetails AS s "
			  
			  + " JOIN RiskAggregator AS r " 
			  + " on r.riskAggregatorId = s.riskAggregatorId " 
			  
			  + " JOIN ClientTable AS c " 
			  + " on c.clientID = s.clientId " 
			  
			  + " JOIN FundTable AS f " 
			  + " on f.fundID = s.fundId " 			  			 
			  
			 )
			public Page<SchedularDto> getscheduledetailsAll(Pageable pageable);
	  
	  @Query(value=
			  "SELECT NEW com.globeop.riskfeed.schedular.dto.SchedularDto("
			  + "s.scheduledDetailsId, s.min, s.hour, s.dayOfMonth, s.month, s.dayOfWeek, "
			  + "s.riskAggregatorId, r.riskAggregatorName, s.clientId, c.clientShortName, s.fundId,f.fundShortName, "
			  + "s.frequency, s.businessDay, s.fundGroup, s.consolidated, s.comments, s.isActive, s.modified_date  )"	  
					  	
			  + " from ScheduledDetails AS s "
			  
			  + " JOIN RiskAggregator AS r " 
			  + " on r.riskAggregatorId = s.riskAggregatorId " 
			  
			  + " JOIN ClientTable AS c " 
			  + " on c.clientID = s.clientId " 
			  
			  + " JOIN FundTable AS f " 
			  + " on f.fundID = s.fundId " 			  			 
			  			  
			  +" where r.riskAggregatorName LIKE %?1%"
			  +" or c.clientShortName LIKE %?1%"
			  +" or f.fundShortName LIKE %?1%"
			  +" or s.frequency LIKE %?1%"
			  
			 )
	  public Page<SchedularDto> getscheduledetailsSearch(String keyword,Pageable pageable);
	  
	  
	  @Query(value=
			  "SELECT NEW com.globeop.riskfeed.schedular.dto.SchedularDto("
			  + "s.scheduledDetailsId, s.min, s.hour, s.dayOfMonth, s.month, s.dayOfWeek, "
			  + "s.riskAggregatorId, r.riskAggregatorName, s.clientId, c.clientShortName, s.fundId,f.fundShortName, "
			  + "s.frequency, s.businessDay, s.fundGroup, s.consolidated, s.comments, s.isActive, s.modified_date  )"	  
					  	
			  + " from ScheduledDetails AS s "
			  
			  + " JOIN RiskAggregator AS r " 
			  + " on r.riskAggregatorId = s.riskAggregatorId " 
			  
			  + " JOIN ClientTable AS c " 
			  + " on c.clientID = s.clientId " 
			  
			  + " JOIN FundTable AS f " 
			  + " on f.fundID = s.fundId " 			  			 
			 
			  /* not working
			  +" CONCAT (s.scheduledDetailsId, s.min, s.hour, s.dayOfMonth, s.month, s.dayOfWeek, "  
			  +" s.riskAggregatorId, r.riskAggregatorName, s.clientId, c.clientShortName, s.fundId,f.fundShortName, " 
			  +" s.frequency, s.businessDay, s.fundGroup, s.consolidated, s.comments, s.isActive, s.modified_date )"
			  
			  + "LIKE %?1%"
			 
			  */
			  
			  +" where r.riskAggregatorName LIKE %?1%"
			  +" or c.clientShortName LIKE %?1%"
			  +" or f.fundShortName LIKE %?1%"
			  +" or s.scheduledDetailsId LIKE %?1%"
			 
			  /*
			  +" or s.min LIKE %?1%"
			  +" or s.hour LIKE %?1%"
			  +" or s.dayOfMonth LIKE %?1%"
			  +" or s.month LIKE %?1%"
			  +" or s.dayOfWeek LIKE %?1%"
			  +" or s.frequency LIKE %?1%"
			  +" or s.businessDay LIKE %?1%"
			  +" or s.fundGroup LIKE %?1%"
			  +" or s.consolidated LIKE %?1%"
			  +" or s.comments LIKE %?1%"
			  +" or s.isActive LIKE %?1%"
			  +" or s.modified_date LIKE %?1%"
			 */
			  
			 )
	  public List<SchedularDto> getscheduledetailsSearch(String keyword);
	  
	  
	  @Query(value=
			  "SELECT NEW com.globeop.riskfeed.schedular.dto.SchedularDto("
			  + "s.scheduledDetailsId, s.min, s.hour, s.dayOfMonth, s.month, s.dayOfWeek, "
			  + "s.riskAggregatorId, r.riskAggregatorName, s.clientId, c.clientShortName, s.fundId,f.fundShortName, "
			  + "s.frequency, s.businessDay, s.fundGroup, s.consolidated, s.comments, s.isActive, s.modified_date  )"	  
					  	
			  + " from ScheduledDetails AS s "
			  
			  + " JOIN RiskAggregator AS r " 
			  + " on r.riskAggregatorId = s.riskAggregatorId " 
			  
			  + " JOIN ClientTable AS c " 
			  + " on c.clientID = s.clientId " 
			  
			  + " JOIN FundTable AS f " 
			  + " on f.fundID = s.fundId " 			  			 
			  
			 )
			public List<SchedularDto> getscheduledetailsAll();
	  
	 
}
