package com.learning.mapping.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

}
