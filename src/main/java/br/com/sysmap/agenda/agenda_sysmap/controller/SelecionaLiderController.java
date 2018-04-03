package br.com.sysmap.agenda.agenda_sysmap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysmap.agenda.agenda_sysmap.DAO.UsuarioDAO;
import br.com.sysmap.agenda.agenda_sysmap.model.Usuario;

@Controller
public class SelecionaLiderController {
	@Autowired
	UsuarioDAO usuarioDao;
	
	@RequestMapping(value = {"/", "/selecionaLider" })
	public ModelAndView telaSelecionaLider() {
		ModelAndView model = new ModelAndView("template-seleciona-lider");
		List<Usuario> usuarios = usuarioDao.findAllLider();
		model.addObject("usuarios", usuarios);
		return model;
	}

}
