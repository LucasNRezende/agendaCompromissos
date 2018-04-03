package br.com.sysmap.agenda.agenda_sysmap.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysmap.agenda.agenda_sysmap.DAO.CompromissoDAO;
import br.com.sysmap.agenda.agenda_sysmap.DAO.UsuarioDAO;
import br.com.sysmap.agenda.agenda_sysmap.model.Compromisso;
import br.com.sysmap.agenda.agenda_sysmap.model.Usuario;

@Controller
@RequestMapping("/compromissos")
public class CompromissoController {

	@Autowired
	private CompromissoDAO compromissoDAO;

	@Autowired
	private UsuarioDAO usuarioDAO;

	@RequestMapping(value = { "", "/" })
	private ModelAndView telaCompromissos() {
		return new ModelAndView("template-compromisso");
	}

	@RequestMapping("/cadastro")
	public ModelAndView cadastroCompromisso(@RequestParam(required = false) Integer id) {
		ModelAndView model = new ModelAndView("template-cadastro-compromisso");
		if (id != null && id > 0) {
			model.addObject("compromisso", compromissoDAO.findById(id));
		} else {
			model.addObject("compromisso", new Compromisso());
		}
		model.addObject("usuarios", usuarioDAO.findAll());
		return model;
	}

	@RequestMapping(value = "/cadastro/salvar", method = RequestMethod.POST)
	public ModelAndView salvarCadastroCompromisso(@ModelAttribute(value = "compromisso") Compromisso compromisso) {
		ModelAndView model = new ModelAndView("template-cadastro-compromisso");
		try {
			Compromisso com = compromissoDAO.save(compromisso);
			if (com != null) {
				model.addObject("sucesso", "Compromisso salvo com sucesso!");
			} else {
				model.addObject("erro", "Falha ao salvar compromisso!");
			}
			;
		} catch (Exception e) {
			e.printStackTrace();
			model.addObject("erro", "Falha ao salvar compromisso!");
		}
		model.addObject("compromisso", compromisso);
		return model;
	}

	@RequestMapping("/pesquisar")
	public ModelAndView pesquisarCompromissos(@RequestParam String descricao) {
		ModelAndView model = new ModelAndView("template-compromisso");
		try {
			List<Compromisso> compromissos = new ArrayList<>();
			if (!descricao.isEmpty()) {
				compromissos = compromissoDAO.findByDescricao(descricao);
			} else {
				compromissos = compromissoDAO.findAll();
			}
			model.addObject("compromissos", compromissos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/remover")
	public ModelAndView removerCompromisso(@RequestParam Integer id) {
		ModelAndView model = new ModelAndView("template-compromisso");
		try {
			compromissoDAO.deleteById(id);
			model.addObject("sucesso", "Compromisso removido com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			model.addObject("erro", "Falha ao remover compromisso!");
		}
		return model;
	}

	@RequestMapping(value = "/pesquisarDesenvolvedor")
	public ModelAndView pesquisarUsuario(@RequestParam Integer id) {
		ModelAndView model = new ModelAndView("template-compromisso");
		List<Compromisso> compromisso = compromissoDAO.findByID(id);
		model.addObject("compromissos", compromisso);
		return model;
	}
	
	@RequestMapping(value = "/listaCompromissosEquipe")
	public ModelAndView listaCompromissosEquipe(@RequestParam Integer id ) {
		ModelAndView model = new ModelAndView("template-compromisso-equipe");
		Usuario usuario = usuarioDAO.findByIdLider(id);
		List<Compromisso> compromisso = compromissoDAO.findCompromissoPorEquipe(usuario.getEquipe().getId());
		model.addObject("compromissos", compromisso);
		return model;
	}
	

}
