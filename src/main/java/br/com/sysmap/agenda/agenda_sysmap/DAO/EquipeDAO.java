package br.com.sysmap.agenda.agenda_sysmap.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.sysmap.agenda.agenda_sysmap.model.Equipe;

public interface EquipeDAO extends JpaRepository<Equipe, Integer> {
	
	@Query("select c from Equipe c where c.descricao like %:descricao%")
	List<Equipe> findByDescricao(@Param("descricao") String descricao);

}