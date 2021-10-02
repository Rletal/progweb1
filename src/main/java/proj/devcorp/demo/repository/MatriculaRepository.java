package proj.devcorp.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

import proj.devcorp.demo.tables.MatriculaEntity;

public interface MatriculaRepository extends JpaRepository<MatriculaEntity, Long>{
	List<MatriculaEntity> findByidTurma(Long idturma);
}
