package org.sid.dao;

import org.sid.entities.Demande;
import org.sid.entities.DemandeChequier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin("*")
public interface DemandeChequierRepository extends JpaRepository<DemandeChequier,Long> {

}