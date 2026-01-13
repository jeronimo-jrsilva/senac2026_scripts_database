package projeto_final;

import java.util.Scanner;

public class Menu {

	private Scanner input;
	private api_db banco;
	private int opcao = 0;

	public Menu(Scanner inputRecebido, api_db bancoRecebido) {
		this.input = inputRecebido;
		this.banco = bancoRecebido;
	}

	public void selecionarConexao() {

		System.out.println("Tipo de conexão:\n"
				+ "1 - Aiven\n" 
				+ "2 - Jeronimo\n" 
				+ "3 - Lucas\n" 
				+ "4 - Jairo");
		banco.selecionarConexao(input);
	}
	
	public String selecionarTabela() {
		// Seleciona a tabela a ser visualizada/alterada
		System.out.println("Selecione a tabela:\n"
				+ "1 - Armazenamento\n"
				+ "2 - Parceiros\n"
				+ "3 - Pedidos\n"
				+ "4 - Produtos");
		opcao = input.nextInt();
		input.nextLine();
		switch(opcao) {
		case 1:
			return "armazenamento";
		case 2:
			return "parceiros";
		case 3:
			return "pedidos";
		case 4:
			return "produtos";
		default:
			return "Nenhuma tabela selecionada.";

		}
	}
	
	public void exibirMenuCrud(String tabelaSelecionada) {
		// Seleciona a operação CRUD a executar
		System.out.println("Selecione a operação:\n"
				+ "1 - Adicionar\n"
				+ "2 - Editar\n"
				+ "3 - Remover\n"
				+ "4 - Consultar");
		opcao = 0;
		opcao = input.nextInt();
		input.nextLine();
		if(banco.estaConectado()) {
			switch(opcao) {
				case 1: 
					banco.insert_db(tabelaSelecionada, input);
					break;
				case 2:
//					Scanner inputedit = new Scanner(System.in);
//					System.out.println("Id a editar: ");
//					String idEdit = inputedit.nextLine();
//					System.out.println("Nome a editar: ");
//					String nomeEdit = inputedit.nextLine();
//					System.out.println("Contato a Editar: ");
//					String contatoEdit = inputedit.nextLine();
					banco.editarContato(tabelaSelecionada, input);
					break;
				case 3:
					banco.apagarContato("1");
					break;
				case 4:
					banco.listarContato(tabelaSelecionada);
					break;
				default:
					System.out.println("Opção inválida");
			}
		} else {
            System.out.println("Opçao inválida\n"
            		+ "Não foi possível conectar ao banco de dados");
        }
		
	}
		
}
