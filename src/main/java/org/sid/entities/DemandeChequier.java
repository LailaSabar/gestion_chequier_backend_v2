package org.sid.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.Entity;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class DemandeChequier extends Demande implements Serializable {
    @NumberFormat(pattern = "####.#0")
    private BigDecimal montant_chequier;
}
