package com.lojapet.petfi.dto;

import java.util.ArrayList;
import java.util.List;

import com.lojapet.petfi.entities.Client;

public class ClientDTO {

  private Long id;
  private String name;
  private String phone;
  private String facebook;
  private String instagram;
  private String city;

  private List<PetDTO2> pets = new ArrayList<>();

  public ClientDTO() {
  }

  public ClientDTO(Long id, String name, String phone, String facebook, String instagram, String city) {
    this.id = id;
    this.name = name;
    this.phone = phone;
    this.facebook = facebook;
    this.instagram = instagram;
    this.city = city;
  }

  public ClientDTO(Client client) {
    this.id = client.getId();
    this.name = client.getName();
    this.phone = client.getPhone();
    this.facebook = client.getFacebook();
    this.instagram = client.getInstagram();
    this.city = client.getCity();
    client.getPets().forEach(pet -> pets.add(new PetDTO2(pet)));
  }

  public ClientDTO(Client client, String update) {
    this.id = client.getId();
    this.name = client.getName();
    this.phone = client.getPhone();
    this.facebook = client.getFacebook();
    this.instagram = client.getInstagram();
    this.city = client.getCity();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getFacebook() {
    return facebook;
  }

  public void setFacebook(String facebook) {
    this.facebook = facebook;
  }

  public String getInstagram() {
    return instagram;
  }

  public void setInstagram(String instagram) {
    this.instagram = instagram;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public List<PetDTO2> getPets() {
    return pets;
  }

  public static Client toClient(ClientDTO dto) {
    Client client = new Client();
    client.setId(dto.getId());
    client.setName(dto.getName());
    client.setPhone(dto.getPhone());
    client.setFacebook(dto.getFacebook());
    client.setInstagram(dto.getInstagram());
    client.setCity(dto.getCity());
    dto.getPets().forEach(pet -> client.addPet(PetDTO2.toPet(pet)));
    return client;
  }

}
