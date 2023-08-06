package service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import domein.Gebruiker;

public class GebruikerPrincipal implements UserDetails {

	private static final long serialVersionUID = 1L;
	private Gebruiker gebruiker;

	public GebruikerPrincipal(Gebruiker gebruiker) {
		this.gebruiker=gebruiker;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		this.gebruiker.getRoleList().forEach(r-> {
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(r);
			authorities.add(grantedAuthority);
		});
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.gebruiker.getWachtwoord();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.gebruiker.getUsername();
	}
	public Gebruiker getGebruiker() {
		return this.gebruiker;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
