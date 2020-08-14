package org.sid;

import java.util.stream.Stream;
import org.sid.service.IDemandeInitService;
import org.sid.entities.AppRole;
import org.sid.entities.Abonne;
import org.sid.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.sid.entities.Compte;
import org.sid.entities.DemandeChequier;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;


@SpringBootApplication
public class JwtSpringSecApplication implements CommandLineRunner{
	
	@Autowired
    private IDemandeInitService iDemandeInitService;
	

	@Autowired
	private AccountService accountService; 

	public static void main(String[] args) {
		SpringApplication.run(JwtSpringSecApplication.class, args);
	}
	
	@Bean
	public BCryptPasswordEncoder getBCPE() {		
		return new BCryptPasswordEncoder();		
	}
	
    @Autowired
    private RepositoryRestConfiguration configuration;

	@Override
	public void run(String... args) throws Exception {
		accountService.saveUser(new Abonne(null,"laila", "1234","Laila","Sabar"));
		accountService.saveUser(new Abonne(null,"kaoutar", "1234","Kaoutar","Oulahyane"));
		accountService.saveRole(new AppRole(null, "USER"));
		accountService.addRoleToUser("laila", "USER");
		accountService.addRoleToUser("kaoutar", "USER");
		
        configuration.exposeIdsFor(DemandeChequier.class);
        configuration.exposeIdsFor(Compte.class);
        configuration.exposeIdsFor(Abonne.class);
        iDemandeInitService.initComptes();
        iDemandeInitService.initDemandeChequiers();
		
		
		
	}

}
