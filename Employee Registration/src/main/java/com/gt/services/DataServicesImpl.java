package com.gt.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;

import com.gt.dao.DataDao;
import com.gt.model.Employee;

public class DataServicesImpl implements DataServices {

	@Autowired
	DataDao dataDao;
	 private static final AtomicInteger counter = new AtomicInteger();
	static List<Employee> employees = new ArrayList<Employee>(
	Arrays.asList(new Employee(counter.incrementAndGet(), "Daenerys Targaryen", "add@gmail.com","ddd", "ddd"),
			new Employee(counter.incrementAndGet(), "John Snow", "mail@mail.com", "34","dfff"),
			new Employee(counter.incrementAndGet(), "Arya Stark", "arya@mail.com", "","45"),
			new Employee(counter.incrementAndGet(), "Cersei Baratheon", "cersei@mailcom", "","56")));

	
	
	@Override
	public boolean addEntity(Employee employee) throws Exception {
		//return employees.add(employee);
		return dataDao.addEntity(employee);
	}

	@Override
	public Employee getEntityById(long id) throws Exception {
		return dataDao.getEntityById(id);
		//return null;
	}

	@Override
	public List<Employee> getEntityList() throws Exception {
		//return employees;
		return dataDao.getEntityList();
	}

	@Override
	public boolean deleteEntity(long id) throws Exception {
		return dataDao.deleteEntity(id);
		//return false;
	}

}
