package br.com.gestaoescala.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.gestaoescala.domain.Servidor;
import br.com.gestaoescala.repositories.ServidorRepository;
import br.com.gestaoescala.security.UserSS;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private ServidorRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Servidor cli = repo.findByEmail(email);
		if (cli == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSS(cli.getId(), cli.getEmail(), cli.getSenha(), cli.getPerfis());
	}
}
