package com.memorynotfound.service;

import java.util.List;

import com.memorynotfound.model.Address;



public interface AddressService {
	
	List<Address> getAll(int offset, int count);
	
	void create(Address address);

    void update(Address address);

    void delete(int id);
    
    Address findById(int id);
    
    boolean exists(Address address);
    
    Address findByCity(String city);

}
