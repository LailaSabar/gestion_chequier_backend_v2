package org.sid.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Entity
//@Data @AllArgsConstructor @NoArgsConstructor
public class Abonne implements Serializable{
	
	@Id @GeneratedValue
	private Long id;
	@Column(unique=true)
	private String username;
	private String password;
	private String firstName;
	private String lastName;
    @OneToMany(mappedBy = "abonne")
    private Collection<Beneficiare> beneficiares;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "abonne")
    private Collection<Compte> comptes;

    @OneToMany(mappedBy = "abonne")
    private Collection<Demande> demandes;
	@ManyToMany(fetch=FetchType.EAGER)
	
	private Collection<AppRole> roles=new ArrayList<>();
	public Abonne(Long id, String username, String password, String firstName, String lastName) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@JsonIgnore
	public String getPassword() {
		return password;
	}
	@JsonSetter

	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Collection<Beneficiare> getBeneficiares() {
		return beneficiares;
	}
	public void setBeneficiares(Collection<Beneficiare> beneficiares) {
		this.beneficiares = beneficiares;
	}
	public Collection<Compte> getComptes() {
		return comptes;
	}
	public void setComptes(Collection<Compte> comptes) {
		this.comptes = comptes;
	}
	public Collection<Demande> getDemandes() {
		return demandes;
	}
	public void setDemandes(Collection<Demande> demandes) {
		this.demandes = demandes;
	}
	public Collection<AppRole> getRoles() {
		return roles;
	}
	public void setRoles(Collection<AppRole> roles) {
		this.roles = roles;
	}
	public Abonne() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
