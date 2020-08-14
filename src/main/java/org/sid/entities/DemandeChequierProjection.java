package org.sid.entities;

import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

@Projection(name = "p1", types = {Demande.class})
public interface DemandeChequierProjection {
    public Long getId();
    public String getMotif();
    public Date getDate_creation();
    public Date getDate_execution();
    public String getStatut();
    public Compte getCompte();
    public double getMontant_chequier();
}
