package proj.devcorp.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import proj.devcorp.demo.repository.AlunoRepository;
import proj.devcorp.demo.repository.MatriculaRepository;
import proj.devcorp.demo.repository.TurmaRepository;
import proj.devcorp.demo.tables.AlunoEntity;
import proj.devcorp.demo.tables.MatriculaEntity;
import proj.devcorp.demo.tables.TurmaEntity;

@RestController
@RequestMapping("/api")
public class MatriculaController {
	@Autowired
	MatriculaRepository matriculaRepository;

	@Autowired
	TurmaRepository turmaRepository;
	
	@Autowired
	AlunoRepository alunoRepository;
	
	@GetMapping("/matriculas")
	public ResponseEntity<List<MatriculaEntity>> getMatriculas() {
		try {
			List<MatriculaEntity> matricula = new ArrayList<MatriculaEntity>();

			matricula = matriculaRepository.findAll();

			if (matricula.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(matricula, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/matriculas/{id}")
	public ResponseEntity<MatriculaEntity> getMatriculaById(@PathVariable("id") long id) {
		Optional<MatriculaEntity> matriculaData = matriculaRepository.findById(id);

		if (matriculaData.isPresent()) {
			return new ResponseEntity<>(matriculaData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/matriculas/detalhes/{id}")
	public ModelAndView getMatriculaByTurma(@PathVariable("id") long id) {
		List<Long> temp = new ArrayList<>();
		List<MatriculaEntity> matriculaData = matriculaRepository.findByidTurma(id);
		for (int i = 0; i < matriculaData.size(); i++) {
			temp.add(matriculaData.get(i).getIdAluno());
		}
		List<AlunoEntity> alunos = alunoRepository.findAllById(temp);
		ModelAndView model = new ModelAndView("detalhes-turma");
		model.addObject("aluno",alunos);
		model.addObject("turma", turmaRepository.getById(id));
		return model;
	}

	

	@PostMapping("/matriculas")
	public ResponseEntity<MatriculaEntity> createMatricula(@RequestBody MatriculaEntity matricula) {
		try {
			MatriculaEntity _matricula = matriculaRepository		
					.save(matricula);
			return new ResponseEntity<>(_matricula, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/matriculas/{id}")
	public ResponseEntity<MatriculaEntity> updateMatricula(@PathVariable("id") long id, @RequestBody MatriculaEntity matricula) {
		Optional<MatriculaEntity> matriculaData = matriculaRepository.findById(id);

		if (matriculaData.isPresent()) {
			MatriculaEntity _matricula = matriculaData.get();
			_matricula.setIdTurma(matricula.getIdTurma());
			_matricula.setIdAluno(matricula.getIdAluno());
			_matricula.setSituacao(matricula.getSituacao());
			return new ResponseEntity<>(matriculaRepository.save(_matricula), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/matriculas/{id}")
	public ResponseEntity<HttpStatus> deleteMatricula(@PathVariable("id") long id) {
		try {
			matriculaRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



}
