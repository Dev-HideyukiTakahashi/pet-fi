package com.lojapet.petfi.dto;

import com.lojapet.petfi.entities.User;

public class UserDTO {

  private Long id;
  private String login;
  private String password;

  public UserDTO() {
  }

  public UserDTO(Long id, String login, String password) {
    this.id = id;
    this.login = login;
    this.password = password;
  }

  public UserDTO(User entity) {
    this.id = entity.getId();
    this.login = entity.getLogin();
    this.password = entity.getPassword();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public static User toUser(UserDTO dto) {
    User user = new User();
    user.setLogin(dto.getLogin());
    user.setPassword(dto.getPassword());
    return user;
  }

}
