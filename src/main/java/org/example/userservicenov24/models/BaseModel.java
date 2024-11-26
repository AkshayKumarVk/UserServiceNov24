package org.example.userservicenov24.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@MappedSuperclass
@Getter
@Setter

@EntityListeners (AuditingEntityListener.class)
public abstract class BaseModel {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @CreatedDate
   private Date createdAt;

   @LastModifiedDate
   private Date updatedAt;

   private Boolean isDeleted = false;
}
