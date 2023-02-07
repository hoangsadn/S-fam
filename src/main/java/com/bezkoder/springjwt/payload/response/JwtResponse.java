package com.bezkoder.springjwt.payload.response;

import java.util.List;

public class JwtResponse {
  private String token;
  private String type = "Bearer";
  private Long id;
  private String name;
  private String email;


  public Boolean getIsAdmin() {
    return isAdmin;
  }

  public void setIsAdmin(Boolean admin) {
    isAdmin = admin;
  }

  private Boolean isAdmin;

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  private List<String> roles;

  public JwtResponse(String accessToken, Long id, String username, String email, List<String> roles, Boolean isAdmin) {
    this.token = accessToken;
    this.id = id;
    this.name = username;
    this.email = email;
    this.roles = roles;
    this.isAdmin = isAdmin;
  }

  public String getAccessToken() {
    return token;
  }

  public void setAccessToken(String accessToken) {
    this.token = accessToken;
  }

  public String getTokenType() {
    return type;
  }

  public void setTokenType(String tokenType) {
    this.type = tokenType;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<String> getRoles() {
    return roles;
  }
}
