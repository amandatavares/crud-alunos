package crud_alunos;

public class Aluno {
	private String nome;
	
	public Aluno(String nome) {
		this.nome = nome;
	}
	
	public String getNome(){
		return nome;
	}
	public void setNome(String nome){
		this.nome = nome;
	}
}
