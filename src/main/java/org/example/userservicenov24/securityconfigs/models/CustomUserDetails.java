package org.example.userservicenov24.securityconfigs.models;

import org.example.userservicenov24.models.Role;
import org.example.userservicenov24.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class CustomUserDetails implements UserDetails {

   private String username;
   private String password;
   private boolean accountNonExpired;
   private boolean accountNonLocked;
   private boolean credentialsNonExpired;
   private boolean enabled;

   private List<CustomGrantedAuthority> authorities;

   public CustomUserDetails(User user){
	  this.username = user.getUserName ();
	  this.password = user.getPassword ();
	  this.accountNonExpired = true;
	  this.accountNonLocked = true;
	  this.credentialsNonExpired = true;
	  this.enabled = true;

//	  convert role to gratedAuthority

	  List<Role> roles = user.getRoles ();
	  authorities = new ArrayList<> ();

	  for (Role role : roles) {
		 authorities.add (new CustomGrantedAuthority (role.getValue ()));
	  }
   }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities () {
	  return authorities;
   }

   @Override
   public String getPassword () {
	  return this.password;
   }

   @Override
   public String getUsername () {
	  return this.username;
   }

   @Override
   public boolean isAccountNonExpired () {
	  return this.accountNonExpired;
   }

   @Override
   public boolean isAccountNonLocked () {
	  return this.accountNonLocked;
   }

   @Override
   public boolean isCredentialsNonExpired () {
	  return this.credentialsNonExpired;
   }

   @Override
   public boolean isEnabled () {
	  return  this.enabled;
   }
}
