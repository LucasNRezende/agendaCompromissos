package br.com.sysmap.agenda.agenda_sysmap.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.sysmap.agenda.agenda_sysmap.model.Compromisso;

public interface CompromissoDAO extends JpaRepository<Compromisso, Integer> {
	
	@Query("select c from Compromisso c where c.descricao like %:descricao%")
	List<Compromisso> findByDescricao(@Param("descricao") String descricao);
	
	@Query("select c from Compromisso c where c.usuario.id = :id ")
	List<Compromisso> findByID(@Param("id") Integer id);
	
	
	 @Query(value = "SELECT c from Compromisso c " +
	            " INNER JOIN Usuario u " +
	            " ON c.usuario.id = u.id "+
	            " INNER JOIN Equipe e " +
	            " ON u.equipe.id = e.id " +
	            " WHERE u.equipe.id = :equipeId")
	    List<Compromisso> findCompromissoPorEquipe(@Param("equipeId") Integer equipe); 
	

}


/*@Query("select c from Compromisso c where c.descricao like %:descricao%")
List<Compromisso> findByDescricao(@Param("descricao") String descricao);*/