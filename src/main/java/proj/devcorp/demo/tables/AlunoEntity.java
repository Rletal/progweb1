package proj.devcorp.demo.tables;

import javax.persistence.*;
@Entity
@Table (name= "Aluno")
public class AlunoEntity {
	//
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "sobreNome")
	private String sobreNome;
	
	@Column(name = "dataNascimento")
	private String dataNascimento;
	
	@Column(name = "endereco")
	private String endereco;

	public AlunoEntity(String nome, String sobreNome, String dataNascimento, String endereco) {
		
		
		this.nome = nome;
		this.sobreNome = sobreNome;
		this.dataNascimento = dataNascimento;
		this.endereco = endereco;
	}

	
	
	public AlunoEntity() {
		
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobreNome() {
		return sobreNome;
	}

	public void setSobreNome(String sobreNome) {
		this.sobreNome = sobreNome;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}



	@Override
	public String toString() {
		return "AlunoEntity [id=" + id + ", nome=" + nome + ", sobreNome=" + sobreNome + ", dataNascimento="
				+ dataNascimento + ", endereco=" + endereco + "]";
	}
	
	

}
