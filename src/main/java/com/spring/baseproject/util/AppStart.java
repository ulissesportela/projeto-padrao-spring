package com.spring.baseproject.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.spring.baseproject.entity.Role;
import com.spring.baseproject.enumeration.RolesEnum;
import com.spring.baseproject.repository.RoleRepository;

@Component
public class AppStart implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	RoleRepository roleRepository;

	@Override
	public void onApplicationEvent(final ContextRefreshedEvent event) {

		/** SINCRONIZA AS ROLES DO SISTEMA COM O BANCO AO INICIALIZAR */
		
		roleRepository.findAll();

		for (RolesEnum r : RolesEnum.values()) {

			Role role = new Role();
			role.setDescricao(r.getDescricao());
			role.setId(new Long(r.getId()));
			role.setNome(r.getRole());

			roleRepository.save(role);

		}

	}
}