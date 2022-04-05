package com.customers.customermgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.customers.customermgmt.dto.CustomerDTO;
import com.customers.customermgmt.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	List<Customer> findByName(String name);

	List<Customer> findByCountry(String Country);

//	@Query(value = "select customer_name from customers where country=:country",nativeQuery = true)

	@Query("select cust.name from Customer as cust where cust.country=:country")
	List<String> findNamesByCountry(String country);

	@Query("select c.name from Customer as c where c.name LIKE :name%")
	List<String> findCustomerNamesStartswithvariable(String name);

	@Query("select distinct c.city from Customer as c where c.country=:country")
	List<String> findCityNamesByCountry(String country);
	@Query("select name,city from Customer")
	List<CustomerDTO> findNameAndCitysOfCustomer();
	
	List<Customer> findByNameAndContactName(String name, String contactname);
	
	List<Customer> findByCountryIn(List<String> country);
	
	
}
