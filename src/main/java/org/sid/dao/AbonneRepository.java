package org.sid.dao;

import org.sid.entities.Abonne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
@RepositoryRestResource
@CrossOrigin("*")
public interface AbonneRepository extends JpaRepository<Abonne,Long >{
	
	public Abonne findByUsername(String username);
	

}
