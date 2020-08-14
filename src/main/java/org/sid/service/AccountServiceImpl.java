package org.sid.service;

import org.sid.dao.RoleRepository;
import org.sid.dao.AbonneRepository;
import org.sid.entities.AppRole;
import org.sid.entities.Abonne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private AbonneRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Abonne saveUser(Abonne user) {
		String hashPW=bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(hashPW);
		return userRepository.save(user);
	}

	@Override
	public AppRole saveRole(AppRole role) {
		// TODO Auto-generated method stub
		return roleRepository.save(role);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		AppRole role=roleRepository.findByRoleName(roleName);
		Abonne user=userRepository.findByUsername(username);
		user.getRoles().add(role);		
	}

	@Override
	public Abonne findUserByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}

}
