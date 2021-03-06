package org.sid.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Beneficiare implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero_compte;
    private String nom;
    private String prenom;

    @ManyToOne
    @JoinColumn(name = "id_abonne")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Abonne abonne;
}
