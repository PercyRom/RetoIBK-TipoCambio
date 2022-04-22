package com.ibk.reto.tcambio.util;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ibk.reto.tcambio.entity.Moneda;
import com.ibk.reto.tcambio.secutiry.entity.Rol;
import com.ibk.reto.tcambio.secutiry.entity.Usuario;
import com.ibk.reto.tcambio.secutiry.enums.RolNombre;
import com.ibk.reto.tcambio.secutiry.service.RolService;
import com.ibk.reto.tcambio.secutiry.service.UsuarioService;
import com.ibk.reto.tcambio.service.MonedaService;

/**
 * TOOL: unicamente se usa para crear ROLES
 *
 */

@Component
public class InitData implements CommandLineRunner {

	@Autowired
	RolService rolService;

	@Autowired
	MonedaService monedaService;

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {

		if (rolService.lista().isEmpty()) {
			Rol rolAdmin = new Rol(RolNombre.ROLE_ADMIN);
			Rol rolUser = new Rol(RolNombre.ROLE_USER);
			rolService.save(rolAdmin);
			rolService.save(rolUser);
		}

		if (usuarioService.lista().isEmpty()) {
			
			Set<Rol> roles = new HashSet<>();
			roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
			
			Usuario user = new Usuario();
			user.setNombre("user");
			user.setNombreUsuario("user");
			user.setEmail("user@ibk.com.pe");
			user.setPassword(passwordEncoder.encode("user"));
			user.setRoles(roles);
			
			usuarioService.save(user);
			
			roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
			
			Usuario admin = new Usuario();
			admin.setNombre("admin");
			admin.setNombreUsuario("admin");
			admin.setEmail("admin@ibk.com.pe");
			admin.setPassword(passwordEncoder.encode("admin"));
			admin.setRoles(roles);
			
			usuarioService.save(admin);
		}

		if (monedaService.lista().isEmpty()) {
			monedaService.save(new Moneda("USD Dolar Estadounidense"));
			monedaService.save(new Moneda("PEN Sol Peruano"));
			monedaService.save(new Moneda("CAD Dolar Canadiense"));
			monedaService.save(new Moneda("INR Rupia India"));
			monedaService.save(new Moneda("ARS Peso Argentino"));
			monedaService.save(new Moneda("CNY Yuan Chino"));
			monedaService.save(new Moneda("JPY Yen Japones"));
			monedaService.save(new Moneda("CLP Peso Chileno"));
		}
	}
}
