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

		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate age1 = LocalDate.parse("03/09/2023", dateFormatter);

		PetDTO pet = new PetDTO(null, "totó", Sex.Femea, "", age1, null, false, new Client(),
				PetType.CACHORRO);
		System.out.println(pet.getAgeAsString());
	}

}
