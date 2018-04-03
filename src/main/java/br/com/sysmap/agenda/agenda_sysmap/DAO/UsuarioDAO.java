package br.com.sysmap.agenda.agenda_sysmap.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.sysmap.agenda.agenda_sysmap.model.Usuario;

public interface UsuarioDAO extends JpaRepository<Usuario, Integer> {
	
	@Query("select c from Usuario c where c.nome like %:nome%")
	List<Usuario> findByNome(@Param("nome") String nome);
	
	@Query("select c from Usuario c where c.perfil = 'LIDER'")
	List<Usuario> findAllLider();
	
	@Query("select c from Usuario c where c.perfil = 'DESENVOLVEDOR'")
	List<Usuario> findAllDesenvolvedor();
	
	@Query("select c from Usuario c where c.id = :id")
	Usuario findByIdLider(@Param("id") Integer id);
	
}
