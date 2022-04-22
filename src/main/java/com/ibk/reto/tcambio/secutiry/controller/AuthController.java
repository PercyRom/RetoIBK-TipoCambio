package com.ibk.reto.tcambio.secutiry.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibk.reto.tcambio.dto.Mensaje;
import com.ibk.reto.tcambio.secutiry.dto.JwtDto;
import com.ibk.reto.tcambio.secutiry.dto.LoginUsuario;
import com.ibk.reto.tcambio.secutiry.dto.NuevoUsuario;
import com.ibk.reto.tcambio.secutiry.entity.Rol;
import com.ibk.reto.tcambio.secutiry.entity.Usuario;
import com.ibk.reto.tcambio.secutiry.enums.RolNombre;
import com.ibk.reto.tcambio.secutiry.jwt.JwtProvider;
import com.ibk.reto.tcambio.secutiry.service.RolService;
import com.ibk.reto.tcambio.secutiry.service.UsuarioService;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	RolService rolService;

	@Autowired
	JwtProvider jwtProvider;

	@Autowired
	Environment env;

	@PostMapping("/nuevo")
	public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevo, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return message("app.msj.usuario.datos.invalidos", HttpStatus.BAD_REQUEST);

		} else if (usuarioService.existsByNombreUsuario(nuevo.getNombreUsuario())) {
			return message("app.msj.usuario.nombre.existe", HttpStatus.BAD_REQUEST);

		} else if (usuarioService.existsByEmail(nuevo.getEmail())) {
			return message("app.msj.usuario.email.existe", HttpStatus.BAD_REQUEST);

		}

		Usuario us = new Usuario();
		us.setNombre(nuevo.getNombre());
		us.setNombreUsuario(nuevo.getNombreUsuario());
		us.setEmail(nuevo.getEmail());
		us.setPassword(passwordEncoder.encode(nuevo.getPassword()));

		Set<Rol> roles = new HashSet<>();
		roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());

		if (nuevo.getRoles().contains("admin")) {
			roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
		}
		
		us.setRoles(roles);

		usuarioService.save(us);

		return message("app.msj.usuario.registrado", HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<Object> login(@Valid @RequestBody LoginUsuario login, BindingResult result) {

		String nombre;
		String password;

		if (result.hasErrors()) {
			return message("app.msj.usuario.datos.invalidos", HttpStatus.BAD_REQUEST);
		}

		nombre = login.getNombreUsuario();
		password = login.getPassword();

		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken;
		usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(nombre, password);

		Authentication authentication;
		authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateToken(authentication);

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());

		return new ResponseEntity<Object>(jwtDto, HttpStatus.OK);
	}

	private ResponseEntity<Object> message(String key, HttpStatus status) {
		return new ResponseEntity<Object>(new Mensaje(env.getProperty(key)), status);
	}
}
