package com.customers.customermgmt.entities;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity
@Table(name = "customers")

public class Customer {

	@Id
	@Column(name = "customer_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	public Integer id;

	@Column(name = "customer_name",nullable=false)
	public String name;

	@Column(name = "contact_name")
	public String contactName;

	@Column(name = "address")
	public String address;

	@Column(name = "city")
	public String city;

	@Column(name = "postal_code")
	public String postalCode;

	@Column(name = "country")
	public String country;
	
	@Transient
	public List<String> countries;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(address, other.address) && Objects.equals(city, other.city)
				&& Objects.equals(contactName, other.contactName) && Objects.equals(country, other.country)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(postalCode, other.postalCode);
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, city, contactName, country, id, name, postalCode);
	}
	
	
}
