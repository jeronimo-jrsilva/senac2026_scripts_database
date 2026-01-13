package projeto_final;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// Cria os objetos necessários: 
		// scanner, conector(via api) e menus. 
		Scanner leitor = new Scanner(System.in);
		api_db bancoDeDados= new api_db();
		Menu menu = new Menu(leitor, bancoDeDados);
		// 
		menu.selecionarConexao();
		String tabela = menu.selecionarTabela();
		System.out.println("\nTabela selecionada: "+tabela+"\n");
		menu.exibirMenuCrud(tabela);
		// menu.exibirMenuCrud();
		// Fecha Scanner:
		leitor.close();
    }
}
