package com.customers.customermgmt.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "customers")

public class Customer {

	@Id
	@Column(name = "customer_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	public Integer id;

	@Column(name = "customer_name")
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
}
