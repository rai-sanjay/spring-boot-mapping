package com.learning.mapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.learning.mapping.manytomany.model.Role;
import com.learning.mapping.manytomany.model.User;
import com.learning.mapping.manytomany.repository.IRoleRepository;
import com.learning.mapping.manytomany.repository.IUserRepository;
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
	
	@Autowired
	IUserRepository userRepository;
	
	@Autowired
	IRoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMappingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		oneToOneMapping();
		oneToManyMapping();
		manyToManyMapping();

	}
	
	private void manyToManyMapping() {
		User user = new User();
		user.setUserName("RAI_SANJAY");
		Role role1 = new Role();
		role1.setRoleName("ADMIN");
		
		Role role2 = new Role();
		role2.setRoleName("MODERATOR");
		List<Role> roles = new ArrayList<>();
		roles.add(role1);
		roles.add(role2);
		user.setRoles(roles);
		userRepository.save(user);
		
		Role role = new Role();
		role.setRoleName("MANAGER");
		
		User user1 = new User();
		user1.setUserName("Yadav_Sanjay");
		user1.setRoles(Arrays.asList(role)); // If not, no entry in users_roles table
		
		User user2 = new User();
		user2.setUserName("Yadav_Abhyant");
		user2.setRoles(Arrays.asList(role)); // If not, no entry in users_roles table
		
		List<User> users = new ArrayList<>();
		users.add(user1);
		users.add(user2);
		role.setUsers(users);
		roleRepository.save(role);
		System.out.println("====================================");
		// Get roles for an user
		User u1 = userRepository.findById(1).get();
		u1.getRoles().forEach(e -> {
			System.out.println(e.getRoleName());
		});
		System.out.println("====================================");
		// Get Users of a particular role
		Role r = roleRepository.findById(3).get();
		r.getUsers().forEach(e->{
			System.out.println(e.getUserName());
		});
		System.out.println("====================================");
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
		
		//Get Address from Customer
		
		Optional<Customer> optionalCustomer = customerRepository.findById(1);
		Customer c= optionalCustomer.get();
		System.out.println(c.getCustomerName());
		System.out.println(c.getAddressList().get(0).getDistrict());
		
		//Get customer from address
		Optional<Address> optionalAddress = addressRepository.findById(2);
		Address address = optionalAddress.get();
		System.out.println(address.getCustomer().getCustomerId());
		System.out.println(address.getCustomer().getCustomerName());
		
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
