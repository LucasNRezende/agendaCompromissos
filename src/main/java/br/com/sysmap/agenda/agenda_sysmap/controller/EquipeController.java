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

import br.com.sysmap.agenda.agenda_sysmap.DAO.EquipeDAO;
import br.com.sysmap.agenda.agenda_sysmap.model.Equipe;

@Controller
@RequestMapping("/equipes")
public class EquipeController {
	
	@Autowired
	private EquipeDAO equipeDAO;
	
	@RequestMapping(value = {"", "/"})
	private ModelAndView telaEquipe() {
		return new ModelAndView("template-equipe");
	}
	
	@RequestMapping ("/cadastro")
	public ModelAndView cadastroEquipe(@RequestParam(required=false) Integer id) {
		ModelAndView model = new ModelAndView("template-cadastro-equipe");
    	if (id != null && id > 0) {
			model.addObject("equipe", equipeDAO.findById(id));
		} else {
			model.addObject("equipe", new Equipe());
		}
    	model.addObject("equipes", equipeDAO.findAll());
    	return model;
	}
	
	@RequestMapping(value = "/cadastro/salvar", method = RequestMethod.POST)
    public ModelAndView salvarCadastroEquipe(@ModelAttribute(value="equipe") Equipe equipe) {
    	ModelAndView model = new ModelAndView("template-cadastro-equipe");
    	try {
    		Equipe equ = equipeDAO.save(equipe);
			if (equ != null) {
				model.addObject("sucesso", "Equipe salvo com sucesso!");
			} else {
				model.addObject("erro", "Falha ao salvar equipe!");
			};
		} catch (Exception e) {
			e.printStackTrace();
			model.addObject("erro", "Falha ao salvar equipe!");
		}
    	model.addObject("equipe", equipe);
    	return model;
    }
    
    @RequestMapping("/pesquisar")
    public ModelAndView pesquisarEquipes(@RequestParam String descricao){
    	ModelAndView model = new ModelAndView("template-equipe");
    	try {
    		List<Equipe> equipes = new ArrayList<>();
			if (!descricao.isEmpty()) {
				equipes = equipeDAO.findByDescricao(descricao);
			} else {
				equipes = equipeDAO.findAll();
			}
			model.addObject("equipes", equipes);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return model;
    }
    
    @RequestMapping(value = "/remover")
    public ModelAndView removerEquipe(@RequestParam Integer id) {
    	ModelAndView model = new ModelAndView("template-equipe");
    	try {
    		equipeDAO.deleteById(id);
    		model.addObject("sucesso", "Equipe removida com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			model.addObject("erro", "Falha ao remover equipe!");
		}
    	return model;
    }
	
}