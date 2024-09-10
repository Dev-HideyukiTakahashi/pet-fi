package com.lojapet.petfi.dto;

import java.beans.Transient;
import java.time.LocalDate;

import com.lojapet.petfi.entities.Client;
import com.lojapet.petfi.entities.Pet;
import com.lojapet.petfi.entities.enums.PetType;
import com.lojapet.petfi.entities.enums.Sex;

/**
 * CLASSE COM @Transient no getClient para evitar loop nos métodos findAll e
 * findById do ClientController Pois o cliente mostrava um pet e o pet mostrava
 * um cliente novamente
 */
public class PetDTO2 {

	private static final String QRCODE = "https://pet-fi.vercel.app/home/pets/";
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
	private String city;

	public PetDTO2() {
	}

	public PetDTO2(Long id, String name, Sex sex, String additionalInformation, LocalDate age, String photo,
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

	public PetDTO2(Pet pet) {
		this.id = pet.getId();
		this.name = pet.getName();
		this.sex = pet.getSex();
		this.additionalInformation = pet.getAdditionalInformation();
		this.age = pet.getAge();
		this.qrcode = pet.getQrcode();
		this.photo = pet.getPhoto();
		this.wanted = pet.getWanted();
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

	public Boolean isWanted() {
		return wanted;
	}

	@Transient
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public static Pet toPet(PetDTO2 dto) {
		Pet pet = new Pet();
		pet.setId(dto.getId());
		pet.setName(dto.getName());
		pet.setSex(dto.getSex());
		pet.setAdditionalInformation(dto.getAdditionalInformation());
		pet.setAge(dto.getAge());
		pet.setQrcode(dto.getQrcode());
		pet.setPhoto(dto.getPhoto());
		pet.setWanted(dto.isWanted());
		pet.setClient(dto.getClient());
		pet.setPetType(dto.getPetType());
		pet.setCity(dto.getCity());
		return pet;
	}

}
