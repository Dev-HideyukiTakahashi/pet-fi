package com.lojapet.petfi.config;

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
import com.lojapet.petfi.repositories.PetRepository;
import com.lojapet.petfi.repositories.UserRepository;

@Configuration
@Profile(value = "test")
public class TestConfig implements CommandLineRunner {

  @Autowired
  private ClientRepository clientRepository;

  @Autowired
  private PetRepository petRepository;

  @Autowired
  private UserRepository userRepository;

  @Override
  public void run(String... args) throws Exception {

    User user = new User(null, "admin", "123456");

    Client client1 = new Client(null, "Maria", "1199999999", "www.facebook.com", "@maria", "Diadema");
    Client client2 = new Client(null, "João", "1199999999", "www.facebook.com", "@joao", "São Bernardo do Campo");
    Client client3 = new Client(null, "José", "1199999999", "www.facebook.com", "@jose", "Santo André");

    Pet pet1 = new Pet(null, "Banzé", Sex.MALE, "Castrado", "linkfoto.jpg", client1, PetType.DOG);
    Pet pet2 = new Pet(null, "Totó", Sex.MALE, "Agressivo", "linkfoto.jpg", client1, PetType.DOG);
    Pet pet3 = new Pet(null, "Carabina", Sex.FEMALE, "", "linkfoto.jpg", client2, PetType.DOG);
    Pet pet4 = new Pet(null, "Mel", Sex.FEMALE, "Esperando filhote", "linkfoto.jpg", client3, PetType.DOG);

    client1.addPet(pet1);
    client1.addPet(pet2);
    client2.addPet(pet3);
    client3.addPet(pet4);

    userRepository.save(user);
    clientRepository.saveAll(Arrays.asList(client1, client2, client3));
    petRepository.saveAll(Arrays.asList(pet1, pet2, pet3, pet4));
  }

}
