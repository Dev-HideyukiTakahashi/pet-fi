package com.lojapet.petfi.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.lojapet.petfi.entities.Client;
import com.lojapet.petfi.entities.Dog;
import com.lojapet.petfi.entities.User;
import com.lojapet.petfi.entities.enums.Sex;
import com.lojapet.petfi.repositories.ClientRepository;
import com.lojapet.petfi.repositories.DogRepository;
import com.lojapet.petfi.repositories.UserRepository;

@Configuration
public class TestConfig implements CommandLineRunner {

  @Autowired
  private ClientRepository clientRepository;

  @Autowired
  private DogRepository dogRepository;

  @Autowired
  private UserRepository userRepository;

  @Override
  public void run(String... args) throws Exception {

    User user = new User(null, "admin", "123456");

    Client client1 = new Client(null, "Maria", "1199999999", "www.facebook.com", "@maria", "Diadema");
    Client client2 = new Client(null, "João", "1199999999", "www.facebook.com", "@joao", "São Bernardo do Campo");
    Client client3 = new Client(null, "José", "1199999999", "www.facebook.com", "@jose", "Santo André");

    Dog dog1 = new Dog(null, "Banzé", Sex.MALE, "Castrado", "linkfoto.jpg", client1);
    Dog dog2 = new Dog(null, "Totó", Sex.MALE, "Agressivo", "linkfoto.jpg", client1);
    Dog dog3 = new Dog(null, "Carabina", Sex.FEMALE, "", "linkfoto.jpg", client2);
    Dog dog4 = new Dog(null, "Mel", Sex.FEMALE, "Esperando filhote", "linkfoto.jpg", client3);

    client1.addDog(dog1);
    client1.addDog(dog2);
    client2.addDog(dog3);
    client3.addDog(dog4);

    userRepository.save(user);
    clientRepository.saveAll(Arrays.asList(client1, client2, client3));
    dogRepository.saveAll(Arrays.asList(dog1, dog2, dog3, dog4));
  }

}
