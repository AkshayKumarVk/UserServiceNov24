package org.example.userservicenov24.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

@Entity
@Table(name = "users")
public class User extends BaseModel{
   private String firstName;
   private String lastName;
   private String userName;
   private String email;
   private String password;
   private Boolean isVerified;
   @ManyToMany
   private List<Role> roles;
}
