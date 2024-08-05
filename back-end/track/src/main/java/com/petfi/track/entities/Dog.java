package com.petfi.track.entities;

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
  private String additionalInformation;

  @ManyToOne
  @JoinColumn(name = "client_id")
  private Client client;

  public Dog() {

  }

  public Dog(Long id, String name, String additionalInformation, Client client) {
    this.id = id;
    this.name = name;
    this.additionalInformation = additionalInformation;
    this.client = client;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getAdditionalInformation() {
    return additionalInformation;
  }

  public Client getClient() {
    return client;
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
