package com.lojapet.petfi.config.seed;

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

    User user = new User();
    user.setUsername("admin");
    user.setPassword("$2a$12$.5Dv8FNNzikuB86JTV0WZe73kJqKXwYu4NFsD2Sl.33aCSv5SbPhy");
    user.setRole("ADMIN");

    Client client1 = new Client(null, "Maria Silva", "11966666666", "www.facebook.com", "@maria", "Diadema");
    Client client2 = new Client(null, "João Silva", "11977777777", "www.facebook.com", "@joao",
        "São Bernardo do Campo");
    Client client3 = new Client(null, "José Silva", "11988888888", "www.facebook.com", "@jose", "Santo André");
    Client client4 = new Client(null, "Gabriel Rocha", "1199999999", "www.facebook.com", "@gabriel", "Diadema");
    Client client5 = new Client(null, "Zé Carlos", "1199999999", "www.facebook.com", "@ze", "Santo André");
    Client client6 = new Client(null, "Amanda", "1199999999", "www.facebook.com", "@amanda", "São Paulo");
    Client client7 = new Client(null, "Teresa Nascimento", "1199999999", "www.facebook.com", "@teresa", "Diadema");
    Client client8 = new Client(null, "Alex Brown", "1199999999", "www.facebook.com", "@alex", "São Paulo");
    Client client9 = new Client(null, "Bob Green", "1199999999", "www.facebook.com", "@bob", "Diadema");

    Pet pet1 = new Pet(null, "Banzé", Sex.MACHO, "Castrado", null, client1, PetType.CACHORRO);
    Pet pet2 = new Pet(null, "Totó", Sex.MACHO, "Agressivo", null, client1, PetType.CACHORRO);
    Pet pet3 = new Pet(null, "Carabina", Sex.FEMEA, "", null, client2, PetType.CACHORRO);
    Pet pet4 = new Pet(null, "Mel", Sex.FEMEA, "Esperando filhote", null, client3, PetType.CACHORRO);
    Pet pet5 = new Pet(null, "Babinha", Sex.FEMEA, "", null, client5, PetType.CACHORRO);
    Pet pet6 = new Pet(null, "Dio", Sex.MACHO, "Dócil", null, client6, PetType.CACHORRO);
    Pet pet7 = new Pet(null, "Rhino", Sex.MACHO, "", null, client7, PetType.CACHORRO);
    Pet pet8 = new Pet(null, "Heavy", Sex.FEMEA, "", null, client8, PetType.CACHORRO);
    Pet pet9 = new Pet(null, "Fan", Sex.MACHO, "", null, client9, PetType.CACHORRO);
    Pet pet10 = new Pet(null, "Russo", Sex.MACHO, "Agressivo", null, client9, PetType.CACHORRO);

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

    loginRepository.save(user);
    clientRepository
        .saveAll(Arrays.asList(client1, client2, client3, client4, client5, client6, client7, client8, client9));
    petRepository.saveAll(Arrays.asList(pet1, pet2, pet3, pet4, pet5, pet6, pet7, pet8, pet9, pet10));
  }

}
