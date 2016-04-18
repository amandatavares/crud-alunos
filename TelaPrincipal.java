package crud_alunos;
import java.io.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class TelaPrincipal {

	private static ArrayList<Aluno> ListadeAlunos = new ArrayList<Aluno>();
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Menu();
	}
	
	public static void Menu() throws IOException {		
		
		String tela = "1)Cadastrar aluno" + "\n2)Listar alunos" + "\n3)Alterar aluno" + "\n4)Apagar aluno" + "\n5)Deletar todos" + "\n6)Recupera informação" + "\n7)Sair";
		String opcao = "";
		do{			
			opcao = Entrada(tela);
			
			if (opcao.equals("1")){
				cadastroAluno();
			} else if (opcao.equals("2")) {
				listaAluno();
			} else if (opcao.equals("3")) {
				alteraAluno();
			} else if (opcao.equals("4")) {
				apagarAluno();
			} else if (opcao.equals("5")) {
				deletarAlunos();
			} else if (opcao.equals("6")) {
				recuperarAluno();
			}
		} while (!opcao.equals("7"));
	}
	
	private static void cadastroAluno(){
		String nome = Entrada("Nome: ");
		
		Aluno aluno = new Aluno(nome);
		ListadeAlunos.add(aluno);
	}
	
	private static void listaAluno() throws IOException {
		if (ListadeAlunos.isEmpty()) {
			SaidaDados("Nenhum aluno cadastrado");
			return;
		}
		FileWriter arq = new FileWriter("lista_aluno.txt"); 
		PrintWriter gravarArq = new PrintWriter(arq);
		
		String relatorio = "";
		
		gravarArq.printf("------- LISTA DE ALUNOS ------\r\n");
		
		for(int i = 0; i < ListadeAlunos.size(); i++) {
			Aluno Lista = ListadeAlunos.get(i);
			gravarArq.printf("\nNome = " + Lista.getNome());
			relatorio += "\nNome: \n" + Lista.getNome() + "\n------------------------------------";
		}
		gravarArq.printf("\n------- FIM ------\r\n");
		gravarArq.close();
		
		System.out.printf(relatorio);
	}
	
	private static void alteraAluno() {
		
		if(ListadeAlunos.size()==0){
			System.out.printf("Nenhum aluno cadastrado");
			return;
		}
		String pesquisar = Entrada("Digite o nome do aluno que deseja alterar: ");
		
		for(int i = 0; i < ListadeAlunos.size(); i++) {
			Aluno alterarNome = ListadeAlunos.get(i);
			if(pesquisar.equalsIgnoreCase(alterarNome.getNome())){
				String nomeNovo = Entrada("Digite o novo nome do aluno: ");
				
				alterarNome.setNome(nomeNovo);
				break;
			}
		}
		SaidaDados("Nome modificado com sucesso");
	}
	
	private static void apagarAluno() {
		if(ListadeAlunos.size()==0){
			System.out.printf("Nenhum aluno cadastrado");
			return;
		}		
		String pesquisarNome = Entrada("Digite o nome do aluno que deseja deletar: ");
		
		for(int i = 0; i < ListadeAlunos.size(); i++) {
			Aluno alunoProcurado = ListadeAlunos.get(i);
			if(pesquisarNome.equalsIgnoreCase(alunoProcurado.getNome())){
				ListadeAlunos.remove(i);
				SaidaDados("Aluno deletado com sucesso");
			}
		}
		
	}
	
	private static void deletarAlunos() {
		if (ListadeAlunos.isEmpty()) {
			SaidaDados("Nenhum aluno cadastrado");
			return;
		}
		ListadeAlunos.clear();
		SaidaDados("Todos os alunos deletados com sucesso");
	}
	
	private static void recuperarAluno() {
		String exibi = "";
		String nomeArq = "lista_aluno.txt";
		
		String linha = "";
		File arq = new File(nomeArq);
		
		if(arq.exists()) {
			exibi = "RELATORIO\n";
			
			try {
				exibi += "";
				//abrindo arquivo para leitura
				FileReader abrindo = new FileReader(nomeArq);
				//leitor do arquivo
				BufferedReader leitor = new BufferedReader(abrindo);
				while(true){
					linha = leitor.readLine();
					if (linha == null)
						break;
					exibi += linha + "\n";
				}
				leitor.close();
			}
			catch (Exception erro){				
			}
			JOptionPane.showMessageDialog(null, exibi, "\nLISTA DE ALUNOS" + "---\n", 1);
		}
		//Se nao exixtir
		else {
			JOptionPane.showMessageDialog(null, "Arquivo nao " + "existe", "Erro", 0);
		}
	}
	
	private static String Entrada(String entrar) {
		return JOptionPane.showInputDialog(entrar);
	}
	
	private static void SaidaDados(String saida) {
		JOptionPane.showMessageDialog(null, saida);
	}
	

}
