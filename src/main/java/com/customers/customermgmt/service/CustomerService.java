package com.customers.customermgmt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.customers.customermgmt.dto.CustomerDTO;
import com.customers.customermgmt.entities.Customer;
import com.customers.customermgmt.exception.CustomerManagementIssueException;
import com.customers.customermgmt.exception.NoRecordFoundException;
import com.customers.customermgmt.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired(required = true)
	private CustomerRepository customeRepository;

	private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

	public List<Customer> getAllCustomerData() {
		List<Customer> customersList = customeRepository.findAll();
		if (customersList == null || customersList.isEmpty()) {
			throw new NoRecordFoundException("NO Data is Present");
		}
		logger.info("The Customer data is {}", customersList);
		return customersList;
	}

	public Customer SaveCustomer(Customer customerManagement) throws CustomerManagementIssueException {
		if (customerManagement == null) {
			throw new CustomerManagementIssueException();
		}
		return customeRepository.save(customerManagement);

	}

	public void updateCusomerData(Customer customerManagement) throws CustomerManagementIssueException {
	if(customerManagement == null) {
		throw new NoRecordFoundException("The update could not be null");
	}
	Customer c = findCustomerById(customerManagement.getId());
		customeRepository.save(c);

	}

	public Customer findCustomerById(Integer id) throws CustomerManagementIssueException {
		Customer customerdata = customeRepository.findById(id).get();
		if(customerdata ==null) {
			throw new CustomerManagementIssueException("Please Enter Correct Id"+id);
		}
		return customerdata;
	}

	public String deleteById(Integer id) {
		try {
			customeRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			logger.error("The EmptyResult  exception is" + e);
			return "No record found with id: " + id;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("The exeption is " + e);
			return "Issue while deleting the record, issue is: " + e.getMessage();
		}

		return "Record deleted successfully with id: " + id;
	}

	public List<Customer> findCustomersByName(String name) {
		if (name != null) {
			List<Customer> customerDataByName = customeRepository.findByName(name);
			logger.info("CustomerDataByname is : {}", customerDataByName);
			return customerDataByName;
		}

		return null;

	}

	public List<Customer> findCustomersByCountry(String country) {
		if (country != null) {

			List<Customer> customersDataByCountry = customeRepository.findByCountry(country);
			return customersDataByCountry;
		}
		return null;
	}

	public List<String> getNameByCountryname(String country) {
		return customeRepository.findNamesByCountry(country);
	}

	public List<String> getCusomerNamesWithVariable(String name) {
		return customeRepository.findCustomerNamesStartswithvariable(name);
	}

	public List<String> getCityNamesByCountry(String country) {
		// return customeRepository.findCityNamesByCountry(country);
		List<Customer> customerData = customeRepository.findByCountry(country);
		if (customerData != null && !customerData.isEmpty()) {
			return customerData.stream().map(custData -> custData.getCity()).distinct().collect(Collectors.toList());
		}
		return null;
	}

	public List<CustomerDTO> getNameAndCity() {

		List<Customer> customerData = customeRepository.findAll();

		List<CustomerDTO> dtoList = new ArrayList<CustomerDTO>();
		customerData.stream().forEach(data -> {
			CustomerDTO dto = new CustomerDTO();
			dto.setCustCity(data.getCity());
			dto.setCustName(data.getName());
			dtoList.add(dto);
		});
		return dtoList;

//		List<CustomerDTO> dtoList2 = customerData.stream()
//				.map(data -> new CustomerDTO(data.getName(),data.getCity())).collect(Collectors.toList());

	}

	public List<CustomerDTO> getNameAndCitys() {
		List<Customer> customersdara = customeRepository.findAll();
		ArrayList<CustomerDTO> dtodata = new ArrayList<CustomerDTO>();
		customersdara.stream().forEach(datas -> {
			CustomerDTO dt = new CustomerDTO();
			dt.setCustCity(datas.getCity());
			dt.setCustName(datas.name);
			dtodata.add(dt);
		});

		return dtodata;

	}

	public List<Customer> getDataOfFirstnameAndSecondName(String name, String contactname) {
		List<Customer> data = customeRepository.findByNameAndContactName(name, contactname);
		if (data != null && !data.isEmpty()) {
			return data;
		}
		return null;

	}

	public List<String> getCitysByCountrys(List<String> country) {
		List<Customer> customersData = customeRepository.findByCountryIn(country);
		if (customersData != null && !customersData.isEmpty()) {
			return customersData.stream().map(data -> data.getCity()).distinct().collect(Collectors.toList());

		}
		return null;

	}

	public ArrayList<CustomerDTO> getcitysData(List<String> country) {
		List<Customer> customersFullData = customeRepository.findByCountryIn(country);
		ArrayList<CustomerDTO> al = new ArrayList<CustomerDTO>();
		customersFullData.stream().forEach(data -> {
			CustomerDTO dtoData = new CustomerDTO();
			dtoData.setCustName(data.name);
			dtoData.setCustCity(data.getCity());
			al.add(dtoData);

		});
		return al;

	}

}
