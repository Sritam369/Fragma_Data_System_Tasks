package com.sri.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	private String name;
	private String photoUrl;
	private String designation;
	private String phone;
	private String department;

}