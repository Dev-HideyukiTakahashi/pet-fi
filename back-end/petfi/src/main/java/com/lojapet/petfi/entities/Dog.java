package com.lojapet.petfi.entities;

import com.lojapet.petfi.entities.enums.Sex;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_dog")
public class Dog {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private Sex sex;
  private String additionalInformation;
  private String qrcode;
  private String photo;
  private Boolean wanted;

  @ManyToOne
  @JoinColumn(name = "client_id")
  private Client client;

  public Dog() {

  }

  public Dog(Long id, String name, Sex sex, String additionalInformation, String photo, Client client) {
    this.id = id;
    this.name = name;
    this.sex = sex;
    this.additionalInformation = additionalInformation;
    this.photo = photo;
    this.client = client;
    this.wanted = false;
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

  public Sex getSex() {
    return sex;
  }

  public void setSex(Sex sex) {
    this.sex = sex;
  }

  public String getAdditionalInformation() {
    return additionalInformation;
  }

  public void setAdditionalInformation(String additionalInformation) {
    this.additionalInformation = additionalInformation;
  }

  public Dog(String qrcode) {
    this.qrcode = qrcode;
  }

  public String getQrcode() {
    return qrcode;
  }

  public void setQrcode(String qrcode) {
    this.qrcode = qrcode;
  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  public Boolean isWanted() {
    return wanted;
  }

  public void setWanted(Boolean wanted) {
    this.wanted = wanted;
  }

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }

  public void generateQrcode() {
    this.qrcode = "http://localhost:8080/dogs/" + this.id;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Dog other = (Dog) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

}
