package org.sid.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.sid.dao.AbonneRepository;
import org.sid.dao.CompteRepository;
import org.sid.dao.DemandeChequierRepository;
import org.sid.entities.Abonne;
import org.sid.entities.Compte;
import org.sid.entities.DemandeChequier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.sid.entities.Demande;
import org.sid.entities.DemandeFilter;

@RestController
public class TaskRestController {

	@Autowired
	private DemandeChequierRepository demandeChequierRepository;

	@GetMapping("/demChequierList")
	public List<DemandeChequier> listDemChequier() {
		return demandeChequierRepository.findAll();
	}

	@PostMapping("/demChequierSave")
	public DemandeChequier save(@RequestBody DemandeChequier t) {
		return demandeChequierRepository.save(t);
	}

	@Autowired
	private AbonneRepository userRepository;

	@GetMapping("/getId/{username}")
	public List<Abonne> getIdByUsername(@PathVariable String username) {
		List<Abonne> listUsers = userRepository.findAll();
		for (int i = 0; i < listUsers.size(); i++) {
			if (listUsers.get(i).getUsername().equals(username))
				return Collections.singletonList(listUsers.get(i));
		}

		return null;
	}

	@Autowired
	private CompteRepository compteRepository;
	public static Compte compte = null;
	public static Abonne abonne = null;

	@PostMapping("/insertDemand/{numCompte}/{id}")
	public void insertDemand(@PathVariable String numCompte, @RequestBody DemandeChequier demande,
			@PathVariable Long id) {

		List<Compte> listComptes = compteRepository.findAll();
		for (int i = 0; i < listComptes.size(); i++) {
			if (listComptes.get(i).getNumero_compte().equals(numCompte))
				compte = listComptes.get(i);
		}

		List<Abonne> listAbonnes = userRepository.findAll();
		for (int i = 0; i < listAbonnes.size(); i++) {
			if (listAbonnes.get(i).getId() == id)
				abonne = listAbonnes.get(i);
		}

		demande.setAbonne(abonne);
		demande.setCompte(compte);
		demandeChequierRepository.save(demande);

	}
	
	
	@PostMapping("/search")
    public List<DemandeChequier> searchForDemandes(@RequestBody DemandeFilter demandeFilter) {
        System.out.println("demande filter : "+demandeFilter);
        List<DemandeChequier> filtredDemandes = new ArrayList<>();
        Abonne abonne = userRepository.findById(demandeFilter.getAbonne()).get();
        System.out.println("Abonneeee : "+abonne.getFirstName());
        Collection<Demande> demandeChequiers = abonne.getDemandes();
        demandeChequiers.forEach(demande -> {
        	System.out.println("demande "+ demande.getId());
            DemandeChequier demandeChequier = (DemandeChequier) demande;
            System.out.println("date execution "+demandeChequier.getDate_execution());
            System.out.println("date execution " + demandeFilter.getDate_debut());
            if (demandeChequier.getStatut().equals(demandeFilter.getStatut())
                    && demandeChequier.getCompte().getId().toString().equals(demandeFilter.getCompte())
                    && (demandeChequier.getDate_execution().compareTo(demandeFilter.getDate_debut()) >= 0)     
                    && (demandeChequier.getDate_execution().compareTo(demandeFilter.getDate_fin()) <= 0)        
                    && (demandeChequier.getMontant_chequier().doubleValue() >= demandeFilter.getMontant_min())
                    && (demandeChequier.getMontant_chequier().doubleValue() <= demandeFilter.getMontant_max()))
                
                filtredDemandes.add(demandeChequier);
        });
        return filtredDemandes;
    }

}
