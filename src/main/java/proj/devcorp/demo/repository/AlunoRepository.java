package proj.devcorp.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import proj.devcorp.demo.tables.AlunoEntity;

public interface AlunoRepository extends JpaRepository<AlunoEntity, Long> {
		
	
}
