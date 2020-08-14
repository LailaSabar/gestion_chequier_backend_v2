package org.sid.service;

import org.sid.dao.AbonneRepository;
import org.sid.dao.CompteRepository;
import org.sid.dao.DemandeChequierRepository;
import org.sid.entities.Abonne;
import org.sid.entities.Compte;
import org.sid.entities.DemandeChequier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Stream;

@Service
@Transactional
public class DemandeInitServiceImpl implements IDemandeInitService {

    @Autowired
    private AbonneRepository abonneRepository;

    @Autowired
    private CompteRepository compteRepository;

    @Autowired
    private DemandeChequierRepository demandeChequierRepository;



    @Override
    public void initComptes() {
        abonneRepository.findAll().forEach(abonne -> {
            int nbrComptes = 1 + (int) (Math.random() * 5);
            for (int i = 0; i < nbrComptes; i++) {
                Compte compte = new Compte();
                compte.setAbonne(abonne);
                int nbr1 = 1000 + new Random().nextInt(999);
                int nbr2 = 1000 + new Random().nextInt(999);
                int nbr3 = 1000 + new Random().nextInt(999);
                int nbr4 = 1000 + new Random().nextInt(999);
                compte.setNumero_compte(nbr1+" "+nbr2+" "+nbr3+" "+nbr4);
                compteRepository.save(compte);
            }
        });
    }

    @Override
    public void initDemandeChequiers() {
        abonneRepository.findAll().forEach(abonne -> {
            int nbrDemande = 1 + (int) (Math.random() * 5);
            System.out.println("Abonne " + abonne.getId() + " : " + nbrDemande + " demandes");
            for (int i = 0; i < nbrDemande; i++) {
                DemandeChequier demandeChequier = new DemandeChequier();
                demandeChequier.setAbonne(abonne);
                demandeChequier.setDate_creation(new Date());
                demandeChequier.setDate_execution(new Date());
                demandeChequier.setMotif("MTF");
                String[] status = new String[]{"Enregistré", "Confirmé et signé"};
                int randStatus = new Random().nextInt(status.length);
                demandeChequier.setStatut(status[randStatus]);
                BigDecimal montant = new BigDecimal(1000.0 + new Random().nextDouble() * 999.0);
                List<Compte> comptes = new ArrayList<>(abonne.getComptes());
                int randIndex = (int) (Math.random() * (comptes.size()));
                demandeChequier.setCompte(comptes.get(randIndex));
                demandeChequier.setMontant_chequier(montant);
                demandeChequierRepository.save(demandeChequier);
            }
        });
    }




}
