package com.globeop.riskfeed.schedular.service;

import java.util.List;

public interface CommonService<T> {

	public List<T> findAll();
	
	public T findById(int theId);
	
	public void save(T obj);
	
	public void deleteById(int theId);
}
