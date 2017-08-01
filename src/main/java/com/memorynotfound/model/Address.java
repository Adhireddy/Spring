package com.memorynotfound.model;

public class Address {
	
	private int uid;
	private int aid;
	private String street;
	private String city;
	private String state;
	private String country;
	
	public Address(){
		
	}
	
	
	public Address(int uid, int aid, String street, String city, String state, String country) {
		this.uid = uid;
		this.aid = aid;
		this.street = street;
		this.city = city;
		this.state = state;
		this.country = country;
	}


	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}


	@Override
	public String toString() {
		return "Address [uid=" + uid + ", aid=" + aid + ", street=" + street + ", city=" + city + ", state=" + state
				+ ", country=" + country + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + aid;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (aid != other.aid)
			return false;
		return true;
	}
	
}
