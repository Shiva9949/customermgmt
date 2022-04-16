package com.customers.customermgmt.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.customers.customermgmt.dto.CustomerDTO;
import com.customers.customermgmt.entities.Customer;
import com.customers.customermgmt.service.CustomerService;

@RestController
public class CustomerContoller {

	@Autowired(required = true)
	private CustomerService customerService;
	private static final Logger logger = LoggerFactory.getLogger(CustomerContoller.class);

	/**
	 * this method used for to test app is running or not
	 * 
	 * @return
	 */
	@GetMapping("/")
	public String display() {
		return "Customer management";
	}

	@PostMapping("/save")
	public ResponseEntity<String> savecustomer(@RequestBody Customer customerManagement) {
		String c = customerManagement.name;
		if (c == null) {
			return new ResponseEntity<String>("the data is null", HttpStatus.BAD_REQUEST);
		}
		customerService.SaveCustomer(customerManagement);
		return new ResponseEntity<String>("the customer is saved with name"+c, HttpStatus.CREATED);

	}

	@GetMapping("/getCustomerList")
	public ResponseEntity<?> getCustomerList() {
		List<Customer> allCustomerdata = customerService.getAllCustomerData();
		if(allCustomerdata != null && !allCustomerdata.isEmpty()) {
		return new ResponseEntity<List<Customer>>(allCustomerdata,HttpStatus.OK);
		}
		return new ResponseEntity<String>("no data is Present",HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/update")
	public ResponseEntity<String> updateCustomerDetails(@RequestBody Customer customerManagement) {

		if (customerManagement != null && customerManagement.getId() != null) {
			customerService.updateCusomerData(customerManagement);
			return new ResponseEntity<String>("The Customer Data is Updated with ID : " + customerManagement.id,
					HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("data is not updated", HttpStatus.BAD_REQUEST);

	}

	@GetMapping("/findByid/{id}")
	public ResponseEntity<?> finddetailsByid(@PathVariable("id") Integer id) {
		Optional<Customer> customerManagement = customerService.findCustomerById(id);
		if (customerManagement.isPresent()) {
			return new ResponseEntity<Customer>(customerManagement.get(), HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("No data is present with the ID : " + id, HttpStatus.CREATED);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteCustomerById(@PathVariable("id") Integer id) {
//		Optional<Customer> customerDatawithId = customerService.findCustomerById(id);
//		if(customerDatawithId.isPresent()) {
//			Customer customer = customerDatawithId.get();
//			customerService.deleteById(id);
//			return new ResponseEntity<String>("The data with ID : "+customer.getId()+" has Name : "+customer.getName()+" Has Succsfully Deleted",HttpStatus.ACCEPTED);
//		}
//		return new ResponseEntity<String>("No Data Is Present with The Id : "+id, HttpStatus.BAD_REQUEST);

		String response = customerService.deleteById(id);
		return new ResponseEntity<String>(response, HttpStatus.BAD_REQUEST);

	}

	@GetMapping("/findByName/{name}")
	public ResponseEntity<?> getCustomerDataWithName(@PathVariable("name") String name) {
		List<Customer> customerDataWithName = customerService.findCustomersByName(name);
		if (!customerDataWithName.isEmpty() && customerDataWithName != null) {
			return new ResponseEntity<List<Customer>>(customerDataWithName, HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("No data Is Present With The Name : " + name, HttpStatus.BAD_REQUEST);

	}

	@GetMapping("/findByCountry/{country}")
	public ResponseEntity<?> getCustomerDataWithCountry(@PathVariable("country") String country) {

		List<Customer> customerDataWithCountry = customerService.findCustomersByCountry(country);
		if (!customerDataWithCountry.isEmpty() && customerDataWithCountry != null) {
			return new ResponseEntity<List<Customer>>(customerDataWithCountry, HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("No data Is Present With The Name : " + country, HttpStatus.BAD_REQUEST);

	}

	@GetMapping("/findCustomerNamesByCountry/{country}")
	public ResponseEntity<?> getNamesByCountry(@PathVariable("country") String country) {
		// ArrayList<String> al = new ArrayList<String>();
		List<String> customernamesByCountry = customerService.getNameByCountryname(country);
		if (customernamesByCountry != null && !customernamesByCountry.isEmpty()) {
			return new ResponseEntity<List<String>>(customernamesByCountry, HttpStatus.CREATED);
		}

		return new ResponseEntity<String>("No data is Preset With the Country : " + country, HttpStatus.OK);

	}

	@GetMapping("/findCustomersWithVirable/{name}")

	public ResponseEntity<?> getCustomerNamesWithVirable(@PathVariable("name") String name) {
		List<String> customerNames = customerService.getCusomerNamesWithVariable(name);
		if (customerNames != null && !customerNames.isEmpty()) {
			return new ResponseEntity<List<String>>(customerNames, HttpStatus.OK);
		}
		return new ResponseEntity<String>("No Data is Present with given Virable : " + name, HttpStatus.OK);

	}

	@GetMapping("/findCitysByCountry/{country}")
	public List<String> getcustomerCityNamesByCountry(@PathVariable("country") String country) {
		return customerService.getCityNamesByCountry(country);
	}

	@GetMapping("/namesAndCityOfCustomer")
	public List<CustomerDTO> getNamesAndCity() {
		List<CustomerDTO> namesAndCity = customerService.getNameAndCity();
		if (namesAndCity != null && !namesAndCity.isEmpty()) {
			return namesAndCity;
		}
		return null;

	}

	@GetMapping("/nameAndCityByUsingInterface")
	public List<CustomerDTO> getNameasAndCitysofCustomers() {
		List<CustomerDTO> customernamesAndCitys = customerService.getNameAndCitys();

		return customernamesAndCitys;
	}

	@GetMapping("/findByNameAndContactname/{name}/{contactname}")
	public ResponseEntity<?> getDatabyNameAndContacname(@PathVariable("name") String name,@PathVariable("contactname") String contactname) {
		List<Customer> customerDataWithNameAndContactname = customerService.getDataOfFirstnameAndSecondName(name,contactname);
		if (customerDataWithNameAndContactname != null && !customerDataWithNameAndContactname.isEmpty()) {
			return new ResponseEntity<List<Customer>>(customerDataWithNameAndContactname,HttpStatus.OK);
		}
		return new ResponseEntity<String>("No data is present With the name and Contactname : "+name+"  "+contactname,HttpStatus.BAD_REQUEST);
	} 
	@GetMapping("/cityNamesbyCountrysUsingInterface")
	public ResponseEntity<?> getCitysByCountrys(@RequestParam List<String> country){
		List<String> citys = customerService.getCitysByCountrys(country);
		if(citys !=  null && !citys.isEmpty()) {
			return new ResponseEntity<List<String>>(citys,HttpStatus.OK);
		}
		return new ResponseEntity<String>("No Citys present with the countrys"+country,HttpStatus.BAD_REQUEST);
		
	}
	@GetMapping("/getDtoaData")
	public List<CustomerDTO> getCityNamesByCountrys(@RequestParam List<String> country){
		List<CustomerDTO> dtoCitys = customerService.getcitysData(country);
			return dtoCitys;
		
	}
}
