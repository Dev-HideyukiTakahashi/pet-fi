package com.lojapet.petfi.dto;

import java.time.LocalDate;
import java.time.Period;

import com.lojapet.petfi.entities.Client;
import com.lojapet.petfi.entities.Pet;
import com.lojapet.petfi.entities.enums.PetType;
import com.lojapet.petfi.entities.enums.Sex;

public class PetDTO {

	// LINK LOCAL (mudar em produção)
	private static final String QRCODE = "http://192.168.1.106:4200/home/pet/";

	private Long id;
	private String name;
	private Sex sex;
	private String additionalInformation;
	private LocalDate age;
	private String qrcode;
	private String photo;
	private Boolean wanted;
	private Client client;
	private PetType petType;
	private String ageAsString;
	private String city;

	public PetDTO() {
	}

	public PetDTO(Long id, String name, Sex sex, String additionalInformation, LocalDate age, String photo,
			Boolean wanted, Client client, PetType petType, String city) {
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.additionalInformation = additionalInformation;
		this.age = age;
		this.photo = photo;
		this.wanted = wanted;
		this.client = client;
		this.petType = petType;
		this.qrcode = QRCODE + id;
		this.city = city;
	}

	public PetDTO(Pet pet) {
		this.id = pet.getId();
		this.name = pet.getName();
		this.sex = pet.getSex();
		this.additionalInformation = pet.getAdditionalInformation();
		this.age = pet.getAge();
		this.qrcode = pet.getQrcode();
		this.photo = pet.getPhoto();
		this.wanted = pet.isWanted();
		this.client = pet.getClient();
		this.petType = pet.getPetType();
		this.qrcode = QRCODE + pet.getId();
		this.city = pet.getCity();
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

	public LocalDate getAge() {
		return age;
	}

	public void setAge(LocalDate age) {
		this.age = age;
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

	public Boolean getWanted() {
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

	public PetType getPetType() {
		return petType;
	}

	public void setPetType(PetType petType) {
		this.petType = petType;
	}

	public String getAgeAsString() {

		Period diff = Period.between(age, LocalDate.now());
		int month = diff.getMonths();
		int year = diff.getYears();

		String age = "";

		if (month != 0) {
			age = month > 1 ? month + " meses" : month + " mes";
		}

		if (year != 0 && month != 0) {
			age += year > 1 ? " e " + year + " anos" : " e " + year + " ano";
		} else {
			age += year > 1 ? year + " anos" : year + " ano";
		}

		return age;
	}

	public void setAgeAsString(String ageAsString) {
		this.ageAsString = ageAsString;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public static Pet toPet(PetDTO dto) {
		Pet pet = new Pet();
		pet.setId(dto.getId());
		pet.setName(dto.getName());
		pet.setSex(dto.getSex());
		pet.setAdditionalInformation(dto.getAdditionalInformation());
		pet.setAge(dto.getAge());
		pet.setQrcode(dto.getQrcode());
		pet.setPhoto(dto.getPhoto());
		pet.setWanted(dto.getWanted());
		pet.setClient(dto.getClient());
		pet.setPetType(dto.getPetType());
		pet.setCity(dto.getCity());
		return pet;
	}

}
