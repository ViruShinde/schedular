package com.globeop.riskfeed.schedular.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.globeop.riskfeed.schedular.dto.ResponseDto;
import com.globeop.riskfeed.schedular.dto.SchedularDto;
import com.globeop.riskfeed.schedular.entity.ScheduledDetails;
import com.globeop.riskfeed.schedular.service.ScheduledDetailsService;

@RestController
public class SchedulDetailsController {

	@Autowired
	private ScheduledDetailsService theScheduledDetailsService;
	
	@GetMapping("/getAllScheduladDetails")
	@ResponseBody
    public List<ScheduledDetails> getAllScheduladDetails() {      	
    	return theScheduledDetailsService.findAll();
    }
	
	
	@PostMapping("/addScheduladDetails")
	@ResponseBody
    public ResponseDto addScheduladDetails(@RequestBody ScheduledDetails scheduledDetails) {	
		
		
    	return theScheduledDetailsService.addDetails(scheduledDetails);    	
    }
	
	@DeleteMapping("/deleteScheduladDetails/{id}")
	@ResponseBody
    public ResponseDto deleteScheduladDetails(@PathVariable (value = "id") int id) {		
    	return theScheduledDetailsService.deleteById2(id);  	
    }
	
	@PutMapping("/updateScheduladDetails")
	@ResponseBody
    public ResponseDto updateScheduladDetails(ScheduledDetails scheduledDetails) {		
    	return theScheduledDetailsService.updateDetails(scheduledDetails);    	
    }
	
	@GetMapping({"/getscheduledetails","/getscheduledetails/{id}"})
	public Page<SchedularDto> viewHomePage(@PathVariable(required = false) String id,Model model) {
		Sort sort = "asc".equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by("scheduledDetailsId").ascending() : Sort.by("scheduledDetailsId").descending();			
		Pageable pageable = PageRequest.of(0, 5, sort);
		return theScheduledDetailsService.getscheduledetails(id,pageable);		
		//return findPaginated(1, "scheduledDetailsId", "asc", id, model);
	}
	
	 @GetMapping("/getscheduledetails/page/{pageNo}")
		public Page<SchedularDto> findPaginated(@PathVariable (value = "pageNo") int pageNo, 
				@RequestParam("sortField") String sortField,
				@RequestParam("sortDir") String sortDir, String id,
				@RequestParam (value="records", required=false, defaultValue="5") String records,
				Model model) {
			int noOfRecordPerPage = Integer.parseInt(records);
			
			Page<SchedularDto> page = theScheduledDetailsService.findPaginated(id,pageNo, noOfRecordPerPage, sortField, sortDir);			
			return page;
		}
	 
	 	//@GetMapping("/getscheduledetails/search/")
		//public Page<SchedularDto> search( @Param("keyword") String keyword, @Param("records") String noOfRecords, Model model) {
	 @RequestMapping("/getscheduledetails/search/")
	 public Page<SchedularDto> search( @RequestParam (value="keyword", required=false, defaultValue="") String keyword, @RequestParam (value="records", required=false, defaultValue="5") String noOfRecords, Model model) {	
		 //System.out.println("Search "+keyword);		 
		 int no=Integer.parseInt(noOfRecords);
		 Page<SchedularDto> test = theScheduledDetailsService.getscheduledetailsSearch(keyword,no);
		 //System.out.println(test.getContent());
		 return test;
		 //return null;
	 }
	 
	 @GetMapping("/getscheduledetails2")
		@ResponseBody
	    public List<SchedularDto> getAllScheduladDetails2() {      	
	    	return theScheduledDetailsService.getscheduledetailsAll();
	    }
	
	 	
}


