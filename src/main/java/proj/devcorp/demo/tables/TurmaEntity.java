package proj.devcorp.demo.tables;

import javax.persistence.*;

@Entity
@Table (name= "Turma")
public class TurmaEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private	long id;
	
	@Column(name = "nome")
	private	String nome;
	
	@Column(name = "codigo")
	private	String codigo;
	
	@Column(name = "ano")
	private	int ano;
	
	@Column(name = "periodo")
	private	int periodo;	

	public TurmaEntity() {
		
	}

	public TurmaEntity(String nome, String codigo, int ano, int periodo) {
	
		
		this.nome = nome;
		this.codigo = codigo;
		this.ano = ano;
		this.periodo = periodo;
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




	public String getCodigo() {
		return codigo;
	}




	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}




	public int getAno() {
		return ano;
	}




	public void setAno(int ano) {
		this.ano = ano;
	}




	public int getPeriodo() {
		return periodo;
	}




	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}



	
}
