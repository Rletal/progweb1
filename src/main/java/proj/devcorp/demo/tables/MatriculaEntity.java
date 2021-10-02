package proj.devcorp.demo.tables;

import javax.persistence.*;

@Entity
@Table (name= "Matricula")
public class MatriculaEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "idTurma")
	private long idTurma;
	
	@Column(name = "idAluno")
	private long idAluno;
	
	@Column(name = "situacao")
	private String situacao;
	

	public MatriculaEntity() {
	
	}


	public MatriculaEntity(long idTurma, long idAluno, String situacao) {
		

		this.idTurma = idTurma;
		this.idAluno = idAluno;
		this.situacao = situacao;
	}


	public String getSituacao() {
		return situacao;
	}


	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public long getIdTurma() {
		return idTurma;
	}


	public void setIdTurma(long idTurma) {
		this.idTurma = idTurma;
	}


	public long getIdAluno() {
		return idAluno;
	}


	public void setIdAluno(long idAluno) {
		this.idAluno = idAluno;
	}


	@Override
	public String toString() {
		return "MatriculaEntity [id=" + id + ", idTurma=" + idTurma + ", idAluno=" + idAluno + ", situacao=" + situacao
				+ "]";
	}

	
	
}
