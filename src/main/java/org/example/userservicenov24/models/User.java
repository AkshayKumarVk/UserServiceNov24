package org.example.userservicenov24.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

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
   @ManyToMany(fetch = FetchType.EAGER)
   private List<Role> roles;
}
