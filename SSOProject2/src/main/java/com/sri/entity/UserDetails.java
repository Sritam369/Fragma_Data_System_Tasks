package com.sri.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="user_details_db")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails {

	@Id
	@SequenceGenerator(name="gen1",sequenceName ="s1",initialValue =100,allocationSize =1)
	@GeneratedValue(generator = "gen1",strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(unique = true)
	private String email;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private String designation;
	
	@Column(nullable=false)
	private String phone;
	
	@Column(nullable=false)
	private String department;
	
	// Metadata
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@Column(name = "created_by", nullable = false, updatable = false)
	private String createdBy;

	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;

	@Column(name = "updated_by", nullable = false)
	private String updatedBy;
	
	@PrePersist
	protected void onCreate() {
	    LocalDateTime now = LocalDateTime.now();

	    createdAt = now;
	    updatedAt = now;

	    createdBy = "SYSTEM";
	    updatedBy = "SYSTEM";
	}

	@PreUpdate
	protected void onUpdate() {
	    updatedAt = LocalDateTime.now();
	    updatedBy = "SYSTEM";
	}

}