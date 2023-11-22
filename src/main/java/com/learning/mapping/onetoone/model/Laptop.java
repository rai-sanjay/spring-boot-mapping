package com.learning.mapping.onetoone.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "LAPTOP")
public class Laptop {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int laptopId;
	private String brand;
	private String model;
	/*This student is from Laptop object
		foreign  key will be generated in Student Table
		 */
	@OneToOne (mappedBy = "laptop") //laptop -> InStudentClass
	Student student;

}
