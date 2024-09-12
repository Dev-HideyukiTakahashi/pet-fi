package com.lojapet.petfi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lojapet.petfi.dto.PetDTO;
import com.lojapet.petfi.entities.Client;
import com.lojapet.petfi.entities.enums.PetType;
import com.lojapet.petfi.entities.enums.Sex;

@SpringBootApplication
public class PetfiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetfiApplication.class, args);
		
		DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate age1 = LocalDate.parse("27/01/2024", fmt1);
		PetDTO pet = new PetDTO(null, "Heavy", Sex.Femea, "", age1, null,false, new Client(), PetType.CACHORRO,
				"Piraporinha");
		
		System.out.println("DATA - " + pet.getAgeAsString());
	}

}
