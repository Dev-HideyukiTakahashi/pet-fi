package com.lojapet.petfi.config.seed;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.lojapet.petfi.entities.Client;
import com.lojapet.petfi.entities.Pet;
import com.lojapet.petfi.entities.User;
import com.lojapet.petfi.entities.enums.PetType;
import com.lojapet.petfi.entities.enums.Sex;
import com.lojapet.petfi.repositories.ClientRepository;
import com.lojapet.petfi.repositories.LoginRepository;
import com.lojapet.petfi.repositories.PetRepository;

@Configuration
@Profile(value = "test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private PetRepository petRepository;

	@Autowired
	private LoginRepository loginRepository;

	@Override
	public void run(String... args) throws Exception {

		DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate age1 = LocalDate.parse("27/07/1990", fmt1);

		User user = new User();
		user.setUsername("admin");
		user.setPassword("$2a$12$.5Dv8FNNzikuB86JTV0WZe73kJqKXwYu4NFsD2Sl.33aCSv5SbPhy");
		user.setRole("ADMIN");

		Client client1 = new Client(null, "Maria Silva", "11966666666", "https://www.facebook.com/", "bella.samoieda",
				"Diadema");
		Client client2 = new Client(null, "João Silva", "11977777777", "https://www.facebook.com/", "bella.samoieda",
				"São Bernardo do Campo");
		Client client3 = new Client(null, "José Silva", "11988888888", "https://www.facebook.com/", "jose",
				"Santo André");
		Client client4 = new Client(null, "Gabriel Rocha", "1199999999", "https://www.facebook.com/", "gabriel",
				"Diadema");
		Client client5 = new Client(null, "Zé Carlos", "1199999999", "https://www.facebook.com/", "ze", "Santo André");
		Client client6 = new Client(null, "Amanda", "1199999999", "https://www.facebook.com/", "amanda", "São Paulo");
		Client client7 = new Client(null, "Teresa Nascimento", "1199999999", "https://www.facebook.com/", "teresa",
				"Diadema");
		Client client8 = new Client(null, "Alex Brown", "1199999999", "https://www.facebook.com/", "alex", "São Paulo");
		Client client9 = new Client(null, "Bob Green", "1199999999", "https://www.facebook.com/", "bob", "Diadema");

		Pet pet1 = new Pet(null, "Banzé", Sex.Macho, "Castrado", age1, null, client1, PetType.CACHORRO, "Diadema");
		Pet pet2 = new Pet(null, "Totó", Sex.Macho, "Agressivo", LocalDate.now(), null, client1, PetType.CACHORRO, "Diadema");
		pet2.setWanted(true);
		Pet pet3 = new Pet(null, "Carabina", Sex.Femea, "", LocalDate.now(), null, client2, PetType.CACHORRO,"Santo André");
		Pet pet4 = new Pet(null, "Mel", Sex.Femea, "Esperando filhote", LocalDate.now(), null, client3,
				PetType.CACHORRO, "Diadema");
		pet4.setWanted(true);
		Pet pet5 = new Pet(null, "Babinha", Sex.Femea, "", LocalDate.now(), null, client5, PetType.CACHORRO, "Osasco");
		Pet pet6 = new Pet(null, "Dio", Sex.Macho, "Dócil", LocalDate.now(), null, client6, PetType.CACHORRO, "Piraporinha");
		Pet pet7 = new Pet(null, "Rhino", Sex.Macho, "", LocalDate.now(), null, client7, PetType.CACHORRO, "Diadema");
		Pet pet8 = new Pet(null, "Heavy", Sex.Femea, "", LocalDate.now(), null, client8, PetType.CACHORRO, "Piraporinha");
		pet8.setWanted(true);
		Pet pet9 = new Pet(null, "Fan", Sex.Macho, "", LocalDate.now(), null, client9, PetType.CACHORRO, "Osasco");
		Pet pet10 = new Pet(null, "Russo", Sex.Macho, "Agressivo", LocalDate.now(), null, client9, PetType.CACHORRO, "Piraporinha");
		pet10.setWanted(true);
		Pet pet11 = new Pet(null, "Russo2", Sex.Macho, "Agressivo", LocalDate.now(), null, client9, PetType.CACHORRO, "Piraporinha");
		pet11.setWanted(true);
		Pet pet12 = new Pet(null, "Russo3", Sex.Macho, "Agressivo", LocalDate.now(), null, client9, PetType.CACHORRO, "Piraporinha");
		pet12.setWanted(true);
		Pet pet13 = new Pet(null, "Russo4", Sex.Macho, "Agressivo", LocalDate.now(), null, client9, PetType.CACHORRO, "Piraporinha");
		pet13.setWanted(true);
		Pet pet14 = new Pet(null, "Russo5", Sex.Macho, "Agressivo", LocalDate.now(), null, client9, PetType.CACHORRO, "Piraporinha");
		pet14.setWanted(true);

		client1.addPet(pet1);
		client1.addPet(pet2);
		client2.addPet(pet3);
		client3.addPet(pet4);
		client5.addPet(pet5);
		client6.addPet(pet6);
		client7.addPet(pet7);
		client8.addPet(pet8);
		client9.addPet(pet9);
		client9.addPet(pet10);
		client9.addPet(pet11);
		client9.addPet(pet12);
		client9.addPet(pet13);
		client9.addPet(pet14);


		loginRepository.save(user);
		clientRepository.saveAll(
				Arrays.asList(client1, client2, client3, client4, client5, client6, client7, client8, client9));
		petRepository.saveAll(Arrays.asList(pet1, pet2, pet3, pet4, pet5, pet6, pet7, pet8, pet9, pet10));
	}

}
