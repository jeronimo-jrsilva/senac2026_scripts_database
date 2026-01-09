package projeto_final;
import java.util.Scanner;
public class Menu {
	
	public void exibirMenuCrud() {
		Scanner input = new Scanner(System.in);
		api_db banc = new api_db();
		System.out.println("Selecione a operação:\n"
				+ "1 - Adicionar\n"
				+ "2 - Editar\n"
				+ "3 - Remover\n"
				+ "4 - Consultar");
		int opcao = input.nextInt();
		
		banc.conectar();
		if(banc.estaConectado()) {
			switch(opcao) {
				case 1: 
					Scanner inputad = new Scanner(System.in);
					System.out.println("Nome a adicionar: ");
					String nome = inputad.nextLine();
					System.out.println("Contato a adicionar: ");
					String contato = inputad.nextLine();
					banc.inserirContato(nome, contato);
					break;
				case 2:
					Scanner inputedit = new Scanner(System.in);
					System.out.println("Id a editar: ");
					String idEdit = inputedit.nextLine();
					System.out.println("Nome a editar: ");
					String nomeEdit = inputedit.nextLine();
					System.out.println("Contato a Editar: ");
					String contatoEdit = inputedit.nextLine();
					banc.editarContato(idEdit, nomeEdit, contatoEdit);
					break;
				case 3:
					banc.apagarContato("1");
					break;
				case 4:
					banc.listarContato();
					break;
				default:
					System.out.println("Opção inválida");
			}
		} else {
            System.out.println("Não foi possível conectar ao banco de dados");
        }
		
	}
		
}
