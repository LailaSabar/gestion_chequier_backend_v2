package org.sid.service;

import org.sid.entities.AppRole;
import org.sid.entities.Abonne;

public interface AccountService {
	
	public Abonne saveUser(Abonne user);
	public AppRole saveRole(AppRole role);
	public void addRoleToUser(String username, String roleName);
	public Abonne findUserByUsername(String username);

}
