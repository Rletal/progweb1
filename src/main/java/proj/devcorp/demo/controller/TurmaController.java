package proj.devcorp.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
public class TurmaController {
	@Autowired
	MatriculaRepository matriculaRepository;

	@Autowired
	TurmaRepository turmaRepository;
	
	@Autowired
	AlunoRepository alunoRepository;
	
	@GetMapping("/turmas")
	public ModelAndView getTurma() {
			try {
				List<TurmaEntity> turma = new ArrayList<TurmaEntity>();
				ModelAndView model = new ModelAndView("/turma/list-turma");
				turma = turmaRepository.findAll();
				model.addObject("turmas",turma);
				return model;
			} catch (Exception e) {
				e.printStackTrace();
				return new ModelAndView();
			}
	}
	
	@GetMapping("/turmas/{id}")
	public ModelAndView getTurmaById(@PathVariable("id") long id) {
		try {
			List<Long> temp = new ArrayList<>();
			List<MatriculaEntity> matriculaData = matriculaRepository.findByidTurma(id);
			for (int i = 0; i < matriculaData.size(); i++) {
				System.out.println("--------------------------------"+matriculaData.get(i).toString());
				temp.add(matriculaData.get(i).getIdAluno());
			}
			List<AlunoEntity> alunos = alunoRepository.findAllById(temp);
			System.out.println(alunos.get(0).toString());
			ModelAndView model = new ModelAndView("turma/detalhes-turma");
			model.addObject("aluno",alunos);
			model.addObject("turma", turmaRepository.getById(id));
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView();
		}
	}
	
	@GetMapping("/turmas/editar/{id}")
	public ModelAndView editTurmaById(@PathVariable("id") long id) {
		try {
			ModelAndView model = new ModelAndView("turma/edit-turma");
			model.addObject("turma", turmaRepository.getById(id));
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView();
		}
	}

	@GetMapping("/turmas/novo")
	public ModelAndView novaTurma(){
		try {
			ModelAndView model = new ModelAndView("/turma/new-turma");
			TurmaEntity turma =  new TurmaEntity();
			model.addObject("turma",turma);			
			return model;
		} catch (Exception e){
			e.printStackTrace();
			return new ModelAndView();
		}
	}

	@PostMapping("/turmas/novo")
	public ModelAndView createTurma(@Validated TurmaEntity turma) {
		try {
			turmaRepository.save(turma);
			return new ModelAndView("redirect:/api/turmas");
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("redirect:/api/turmas");
		}
	}

	@PostMapping("/turmas/{id}")
	//@RequestMapping(value="/turmas/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, method=RequestMethod.POST)
	//@RequestMapping(value = "/turmas/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)	
	public ModelAndView updateTurma(@PathVariable("id") long id, @Validated TurmaEntity turma) {
		try {
			Optional<TurmaEntity> turmaData = turmaRepository.findById(id);
			System.out.println(turma);
			if (turmaData.isPresent()) {
				TurmaEntity _turma = turmaData.get();
				_turma.setNome(turma.getNome());
				_turma.setAno(turma.getAno());
				_turma.setCodigo(turma.getCodigo());
				_turma.setPeriodo(turma.getPeriodo());
				
				turmaRepository.save(_turma);
				return new ModelAndView("redirect:/api/turmas");
			} else {
				return new ModelAndView("redirect:/api/turmas");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return getTurma();
		}
	}

	@GetMapping("/turmas/deletar/{id}")
	public ModelAndView deleteTurma(@PathVariable("id") long id) {
		try {
			turmaRepository.deleteById(id);
			return new ModelAndView("redirect:/api/turmas");
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("redirect:/api/turmas");
					
		}
	}

}
