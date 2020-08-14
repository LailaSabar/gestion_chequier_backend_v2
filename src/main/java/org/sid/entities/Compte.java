package org.sid.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Compte implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String numero_compte;
    private double solde_compte;
    private double solde_comptable;

    @ManyToOne
    @JoinColumn(name = "id_abonne")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Abonne abonne;

    @OneToMany(mappedBy = "compte")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Demande> demandes;
}
