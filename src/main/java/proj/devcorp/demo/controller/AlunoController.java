package proj.devcorp.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import proj.devcorp.demo.repository.AlunoRepository;
import proj.devcorp.demo.repository.MatriculaRepository;
import proj.devcorp.demo.tables.AlunoEntity;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class AlunoController {
	@Autowired
	AlunoRepository alunoRepository;
	
	@Autowired
	MatriculaRepository matriculaRepository;

	@GetMapping("/alunos")
	public ResponseEntity<List<AlunoEntity>> getAlunos() {
		try {
			List<AlunoEntity> alunos = new ArrayList<AlunoEntity>();

			alunos = alunoRepository.findAll();

			if (alunos.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(alunos, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/alunos/{id}")
	public ResponseEntity<AlunoEntity> getAlunoById(@PathVariable("id") long id) {
		Optional<AlunoEntity> alunoData = alunoRepository.findById(id);

		if (alunoData.isPresent()) {
			return new ResponseEntity<>(alunoData.get(), HttpStatus.OK);
		} else {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	

	@PostMapping("/alunos")
	public ResponseEntity<AlunoEntity> createAluno(@RequestBody AlunoEntity aluno) {
		try {
			AlunoEntity _aluno = alunoRepository		
					.save(aluno);
			return new ResponseEntity<>(_aluno, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/alunos/{id}")
	public ResponseEntity<AlunoEntity> updateAluno(@PathVariable("id") long id, @RequestBody AlunoEntity aluno) {
		Optional<AlunoEntity> alunoData = alunoRepository.findById(id);

		if (alunoData.isPresent()) {
			AlunoEntity _aluno = alunoData.get();
			_aluno.setNome(aluno.getNome());
			_aluno.setSobreNome(aluno.getSobreNome());
			_aluno.setDataNascimento(aluno.getDataNascimento());
			return new ResponseEntity<>(alunoRepository.save(_aluno), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/alunos/{id}")
	public ResponseEntity<HttpStatus> deleteAluno(@PathVariable("id") long id) {
		try {
			alunoRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//	@DeleteMapping("/tutorials")
//	public ResponseEntity<HttpStatus> deleteAllTutorials() {
//		try {
//			tutorialRepository.deleteAll();
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		} catch (Exception e) {
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//
//	}

	
}
