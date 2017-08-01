package com.memorynotfound.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Service;
import com.memorynotfound.model.Address;

@Service
public class AddressServiceImpl implements AddressService {
	
	 private static final AtomicInteger counter = new AtomicInteger();
	    static List<Address> addresses = new ArrayList<Address>();
	           /* Arrays.asList(
	                    new Address(counter.incrementAndGet(),counter.incrementAndGet(), "Daenerys Targaryen","add","",""),
	                    new Address(counter.incrementAndGet(),counter.incrementAndGet(), "John Snow","mail@mail.com","",""),
	                    new Address(counter.incrementAndGet(),counter.incrementAndGet(), "Arya Stark","arya@mail.com","",""),
	                    new Address(counter.incrementAndGet(),counter.incrementAndGet(), "Cersei Baratheon","cersei@mailcom","","")));
*/
	@Override
	public void create(Address address) {
		address.setAid(counter.incrementAndGet());
		addresses.add(address);
	}

	@Override
	public void update(Address address) {
		int index = addresses.indexOf(address);
		addresses.set(index, address);

	}

	@Override
	public void delete(int id) {
		Address address = findById(id);
		addresses.remove(address);

	}

	@Override
	public List<Address> getAll(int offset, int count) {
		
		return addresses;
	}

	@Override
	public Address findById(int id) {
		for (Address address : addresses){
            if (address.getAid() == id){
                return address;
            }
        }
        return null;
		
	}
	@Override
	public Address findByCity(String city) {
		for (Address address : addresses){
            if (address.getCity()== city){
                return address;
            }
        }
        return null;
		
	}

	@Override
	public boolean exists(Address address) {
		
		return  findByCity(address.getCity()) != null;
	}

}
