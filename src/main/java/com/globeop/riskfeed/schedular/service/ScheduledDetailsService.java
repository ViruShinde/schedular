package com.globeop.riskfeed.schedular.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globeop.riskfeed.schedular.dto.ResponseDto;
import com.globeop.riskfeed.schedular.dto.SchedularDto;
import com.globeop.riskfeed.schedular.entity.ClientTable;
import com.globeop.riskfeed.schedular.entity.FundTable;
import com.globeop.riskfeed.schedular.entity.RiskAggregator;
import com.globeop.riskfeed.schedular.entity.ScheduledDetails;
import com.globeop.riskfeed.schedular.repository.ClientRepository;
import com.globeop.riskfeed.schedular.repository.FundTableRepository;
import com.globeop.riskfeed.schedular.repository.RiskAggregatorRepository;
import com.globeop.riskfeed.schedular.repository.ScheduledDetailsRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;

@Service
public class ScheduledDetailsService implements CommonService<ScheduledDetails> {

	@Autowired
	private ScheduledDetailsRepository theScheduledDetailsRepository;
	
	@Autowired
	private ClientRepository theClientRepository;
	
	@Autowired
	private FundTableRepository theFundTableRepository; 
	
	@Autowired
	private RiskAggregatorRepository theRiskAggregatorRepository; 
	
	@Autowired
	private ScheduleTaskService theScheduleTaskService;
	
	
	@Override
	public List<ScheduledDetails> findAll() {		
		return theScheduledDetailsRepository.findAll();
	}
	
	public List<SchedularDto> getscheduledetailsAll() {		
		return theScheduledDetailsRepository.getscheduledetailsAll();
	}
	
	
	@Override
	public ScheduledDetails findById(int theId) {
		
		Optional<ScheduledDetails> result = theScheduledDetailsRepository.findById(theId);
		ScheduledDetails theScheduledDetails=null;
		
		if (result.isPresent()) {
			theScheduledDetails = result.get();
		}
		else {
			throw new RuntimeException("Did not find client id - " + theId);
		}
		return theScheduledDetails;		
	}

	@Override
	public void save(ScheduledDetails obj) {
		theScheduledDetailsRepository.save(obj);
		
	}
	
	/*
	public void addDetails(OnBordDto onBordDto) {
		ClientTable client = theClientRepository.findById(onBordDto.getClientId());
		FundTable fund =  theFundTableRepository.findByFundShortName(onBordDto.getFundName());
		RiskAggregator riskAggregator = theRiskAggregatorRepository.findById(onBordDto.getRiskAggregatorId());
		if(client == null || fund == null) {
			
		}else {
			ScheduledDetails scheduledDetails = new ScheduledDetails();
			scheduledDetails.setClientId(client);
			scheduledDetails.setFundId(fund);
			scheduledDetails.setRiskAggregatorId(riskAggregator);
			scheduledDetails.setFrequency(onBordDto.getFrequency());
			scheduledDetails.setMin(onBordDto.getMin());
			scheduledDetails.setHour(onBordDto.getHour());
			scheduledDetails.setDayOfMonth(onBordDto.getDayOfMonth());
			scheduledDetails.setMonth(onBordDto.getMonth());
			scheduledDetails.setDayOfWeek(onBordDto.getDayOfWeek());
			scheduledDetails.setModified_date(LocalDate.now());
			scheduledDetails = theScheduledDetailsRepository.save(scheduledDetails);
			
			theScheduleTaskService.addToSpringScheduler(scheduledDetails);
			
			System.out.println(">>>> "+scheduledDetails.getScheduledDetailsId());
		}		
		
	}

*/
	
	public ResponseDto addDetails(ScheduledDetails scheduledDetails) {
		System.out.println("@@@@@@@@@@ "+scheduledDetails);
		scheduledDetails.setModified_date(LocalDate.now());
		ScheduledDetails scDetails = theScheduledDetailsRepository.save(scheduledDetails);		
		System.out.println(">>>> "+scheduledDetails.getScheduledDetailsId());			
		ResponseDto responseDto= new ResponseDto();    	
    	if(scDetails!=null) {
    		responseDto.setError("False");
    		responseDto.setMessage("Added Succesfully..");
    		responseDto.setId(scheduledDetails.getScheduledDetailsId());
    		theScheduleTaskService.addToSpringScheduler(scheduledDetails);		
    	}    	
		return responseDto;
	}
	
public ResponseDto updateDetails(ScheduledDetails scheduledDetails) {
		
		ScheduledDetails scDetails = theScheduledDetailsRepository.save(scheduledDetails);
		theScheduleTaskService.addToSpringScheduler(scheduledDetails);		
		System.out.println(">>>> "+scheduledDetails.getScheduledDetailsId());			
		ResponseDto responseDto= new ResponseDto();    	
    	if(scDetails!=null) {
    		responseDto.setError("False");
    		responseDto.setMessage("Updated Succesfully..");
    	}    	
		return responseDto;
	}
	@Override
	public void deleteById(int theId) {		
		theScheduledDetailsRepository.deleteById(theId);
		
	}
	
	public ResponseDto deleteById2(int theId) {
		ResponseDto responseDto = new ResponseDto();
		ScheduledDetails scheduledDetails = findById(theId);
		if(scheduledDetails==null) {
		}else {
			theScheduledDetailsRepository.deleteById(theId);
			responseDto.setError("False");
			responseDto.setMessage("Deleted Schuduled Details "+theId);
		}
		
		return responseDto;
		
	}
	
	
	public Page<ScheduledDetails> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.theScheduledDetailsRepository.findAll(pageable);
	}
	
	
	public Page<SchedularDto> getscheduledetails(String id, Pageable pageable) {
		if(null==id || "".equals(id)) {
			return this.theScheduledDetailsRepository.getscheduledetailsAll(pageable);
		}else {
			return this.theScheduledDetailsRepository.getscheduledetailsById(Integer.parseInt(id),pageable);			
		}
		
	}
	
	public Page<SchedularDto> findPaginated(String id, int pageNo, int noOfRecordPerPage, String sortField, String sortDirection) {		
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();		
		Pageable pageable = PageRequest.of(pageNo - 1, noOfRecordPerPage, sort);
		Page<SchedularDto> findPaginated = this.theScheduledDetailsRepository.getscheduledetailsAll(pageable);		
	    return findPaginated;
		    
	}
	
	public Page<SchedularDto> getscheduledetailsSearch(String keyword) {	
		Sort sort = "ASC".equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by("scheduledDetailsId").ascending() :
			Sort.by("scheduledDetailsId").descending();		
		Pageable pageable = PageRequest.of(0, 5, sort);
		
		//System.out.println("@@@ "+theScheduledDetailsRepository.getscheduledetailsSearch(keyword));
		return this.theScheduledDetailsRepository.getscheduledetailsSearch(keyword, pageable);
	
	}
	

}
