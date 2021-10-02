package proj.devcorp.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import proj.devcorp.demo.tables.TurmaEntity;

public interface TurmaRepository extends JpaRepository<TurmaEntity, Long> {

}
