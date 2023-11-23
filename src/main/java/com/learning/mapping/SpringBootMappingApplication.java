package com.learning.mapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.learning.mapping.onetomany.model.Address;
import com.learning.mapping.onetomany.model.Customer;
import com.learning.mapping.onetomany.repository.IAddressRepository;
import com.learning.mapping.onetomany.repository.ICustomerRepository;
import com.learning.mapping.onetoone.model.Laptop;
import com.learning.mapping.onetoone.model.Student;
import com.learning.mapping.onetoone.repository.ILaptopRepository;
import com.learning.mapping.onetoone.repository.IStudentRepository;

@SpringBootApplication
public class SpringBootMappingApplication implements CommandLineRunner {

	@Autowired
	IStudentRepository studentRepository;
	
	@Autowired
	ILaptopRepository laptopRepository;
	
	@Autowired
	ICustomerRepository customerRepository;
	
	@Autowired
	IAddressRepository addressRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMappingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		oneToOneMapping();
		oneToManyMapping();

	}
	
	private void oneToManyMapping() {
		
		Customer customer = new Customer();
		customer.setCustomerName("Sanjay Yadav");
		
		Address presentAddress = new Address();
		presentAddress.setStreet("Budha Colony");
		presentAddress.setPincode("841311");
		presentAddress.setDistrict("Patna");
		/* if we don't set customer in address then, customer_id column of Address table will be NULL*/
		presentAddress.setCustomer(customer);
		
		Address permanentAddress = new Address();
		permanentAddress.setStreet("Parsa Jogini");
		permanentAddress.setPincode("841311");
		permanentAddress.setDistrict("Chhapra");
		/* if we don't set customer in address then, customer_id column of Address table will be NULL*/
		permanentAddress.setCustomer(customer);
		
		List<Address> addressList = new ArrayList<>();
		addressList.add(permanentAddress);
		addressList.add(presentAddress);
		customer.setAddressList(addressList);
		
		customerRepository.save(customer);
		
	}

	private void oneToOneMapping() {
		
		Student student = new Student();
		student.setStudentName("Vishal");
		student.setCourse("BCA");

		Laptop laptop = new Laptop();
		laptop.setBrand("Dell");
		laptop.setModel("Inspiron");
		/*
		 * laptop wil be set in Student because foreign key is maintained in this.
		 * Setting student in laptop is not allowed
		 */
		student.setLaptop(laptop);
		studentRepository.save(student);
		System.out.println("Save Student : " + student.getStudentId());
		 
		
		//Get Laptop from Student
		Optional<Student> optionalStudent = studentRepository.findById(1);
		Student student1 = optionalStudent.get();
		System.out.println(student1.getStudentName()+" -> "+student1.getLaptop().getBrand());
		
		//Get Student from Laptop
		Optional<Laptop> optionalLaptop = laptopRepository.findById(1);
		Laptop laptop1 = optionalLaptop.get();
		System.out.println(laptop1.getBrand()+" -> "+laptop1.getStudent().getStudentName());
	}

}
