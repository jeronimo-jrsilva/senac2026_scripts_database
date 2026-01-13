package projeto_final;

import java.io.FileInputStream;
import java.sql.Connection; // Gerencia a conexão
import java.sql.DriverManager; // Gerencia o driver
import java.sql.ResultSet; // Consulta, retorna, armazena e imprime dados de DBs
import java.sql.Statement; // Gerencia e permite consultas (queries) nos DBs
import java.util.Properties;
import java.util.Scanner;

public class api_db {
	// Criação e inicialização das variáveis:
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultset = null;

	// private Scanner input;

	// Método para conectar ao banco de dados
	public void selecionarConexao(Scanner input) {
		Properties props = new Properties();
		try {
			FileInputStream fis = new FileInputStream("config.properties");
			props.load(fis);
			fis.close();
		} catch (Exception e) {
			System.out.println("Erro ao carregar arquivo de configuração: " + e.getMessage());
			return; // Interrompe se não conseguir ler as senhas
		}
		String servidor = props.getProperty("aiven.server");
		String usuario = props.getProperty("aiven.user");
		String senha = props.getProperty("aiven.senha");
		String driver = "com.mysql.cj.jdbc.Driver";
		int opcao = input.nextInt();
		// input.nextLine();

		switch (opcao) {
		case 1:
			// Aiven (já selecionado como default)
			break;

		case 2:
			// Jeronimo
			servidor = props.getProperty("local.server");
			usuario = props.getProperty("local.user");
			senha = props.getProperty("jeronimo.senha");
			break;

		case 3:
			// Lucas
			servidor = props.getProperty("local.server");
			usuario = props.getProperty("local.user");
			senha = props.getProperty("lucas.senha");
			break;

		case 4:
			// Jairo
			servidor = props.getProperty("local.server");
			usuario = props.getProperty("local.user");
			senha = props.getProperty("jairo.senha");
			break;

		default:
			break;
		}
		conectar(servidor, usuario, senha, driver);

	}

	public void conectar(String servidor, String usuario, String senha, String driver) {

		try {
			Class.forName(driver);
			this.connection = DriverManager.getConnection(servidor, usuario, senha);
			this.statement = this.connection.createStatement();
			System.out.println("\nConexão com o servidor estabelecida.\n");

		} catch (Exception e) {
			System.out.println("Error" + e.getMessage());

		}

	}

	// Verificador da conexão.
	public boolean estaConectado() {
		if (this.connection != null) {
			return true;
		} else {
			return false;
		}
	}

	// Método para inserção de dados:
	public void insert_db(String tabela, Scanner input) {
		String query;

		switch (tabela) {
		case "parceiros":
			String nome_parceiro, contato;
			System.out.println("Nome a adicionar: ");
			nome_parceiro = input.nextLine();
			System.out.println("Contato a adicionar: ");
			contato = input.nextLine();
			try {
				// A notação "+nome+" indica referência aos parâmetros fornecidos ao método.
				// No momento, só implementado para a tabela parceiros.
				query = "insert into " + tabela + " (nome_parceiro, contato) " + "values ('" + nome_parceiro + "','"
						+ contato + "')";
				this.statement.executeUpdate(query);
				System.out.println(nome_parceiro + " adicionado com sucesso à tabela " + tabela);
			} catch (Exception e) {
				System.out.println("Error" + e.getMessage());
			}

		case "armazenamento":
			String galpao;
			int rua, num, altura;
			System.out.println("Galpão (3 caracteres): ");
			galpao = input.nextLine();
			System.out.println("# Rua: ");
			rua = input.nextInt();
			input.nextLine();
			System.out.println("# Número: ");
			num = input.nextInt();
			input.nextLine();
			System.out.println("# Altura: ");
			altura = input.nextInt();
			input.nextLine();
			try {
				// A notação "+nome+" indica referência aos parâmetros fornecidos ao método.
				// No momento, só implementado para a tabela parceiros.
				query = "insert into " + tabela + " (galpao, rua, num, altura) " + "values ('" + galpao + "','" + rua
						+ "','" + num + "','" + altura + "')";
				this.statement.executeUpdate(query);
				System.out.println("Galpão " + galpao);
				System.out.println("Rua " + rua);
				System.out.println("Número " + num);
				System.out.println("Altura " + altura);
				System.out.println("Adicionado com sucesso à tabela " + tabela);
			} catch (Exception e) {
				System.out.println("Error" + e.getMessage());
			}
		case "pedidos":
			String e_s;
			int fk_id_parceiro;
			System.out.println("E (entrada) ou S (saída): ");
			e_s = input.nextLine();
			System.out.println("# ID parceiro: ");
			fk_id_parceiro = input.nextInt();
			input.nextLine();
			try {
				// A notação "+nome+" indica referência aos parâmetros fornecidos ao método.
				// No momento, só implementado para a tabela parceiros.
				query = "insert into " + tabela + " (E_S, id_parceiro) " + "values ('" + e_s + "'," + fk_id_parceiro + ")";
				this.statement.executeUpdate(query);
				System.out.println("E_S: " + e_s);
				System.out.println("ID Parceiro: " + fk_id_parceiro);
				System.out.println("Adicionado com sucesso à tabela " + tabela);
			} catch (Exception e) {
				System.out.println("Error" + e.getMessage());
			}

		}

	}

	// Método para listar os contatos:
	public void listarContato(String tabela) {
		String query;
		switch (tabela) {
		case "armazenamento":
			try {
				query = "select * from " + tabela + " order by galpao, rua, num, altura";
				this.resultset = this.statement.executeQuery(query);
				System.out.println("---");
				while (this.resultset.next()) {
					System.out.println("ID endereço: " + this.resultset.getString("id_armazenamento"));
					System.out.println("Galpão: " + this.resultset.getString("galpao"));
					System.out.println("Rua: " + this.resultset.getString("rua"));
					System.out.println("Num: " + this.resultset.getString("num"));
					System.out.println("Altura: " + this.resultset.getString("altura"));
					System.out.println("---");
				}
			} catch (Exception e) {
				System.out.println("Erro" + e.getMessage());
			}
			break;

		case "parceiros":
			try {
				query = "select * from " + tabela + " order by id_parceiro";
				this.resultset = this.statement.executeQuery(query);
				System.out.println("---");
				while (this.resultset.next()) {
					System.out.println("ID parceiro: " + this.resultset.getString("id_parceiro"));
					System.out.println("Parceiro: " + this.resultset.getString("nome_parceiro"));
					System.out.println("Contato: " + this.resultset.getString("contato"));
					System.out.println("---");
				}
			} catch (Exception e) {
				System.out.println("Erro" + e.getMessage());
			}
			break;

		case "pedidos":
			try {
				query = "select id_pedido, e_s, nome_parceiro, data_hora from " + tabela 
						+ " left join parceiros on pedidos.id_parceiro = parceiros.id_parceiro order by id_pedido";
				String ES;
				this.resultset = this.statement.executeQuery(query);
				System.out.println("---");
				while (this.resultset.next()) {
					System.out.println("ID pedido: " + this.resultset.getString("id_pedido"));
					ES = this.resultset.getString("e_s");
					switch (ES) {
					case "E":
						System.out.println("E/S: Entrada");
						break;
					case "S":
						System.out.println("E/S: Saída");
						break;
					}
					System.out.println("Data/hora: " + this.resultset.getString("data_hora"));
					System.out.println("Parceiro " + this.resultset.getString("nome_parceiro"));
					System.out.println("---");
				}
			} catch (Exception e) {
				System.out.println("Erro" + e.getMessage());
			}
			break;

		case "produtos":
			try {
				query = "select cod_produto, nome_produto, parceiros.nome_parceiro, peso_total from "+ tabela +" left join pedidos on pedidos.id_pedido = produtos.id_pedido left join parceiros on pedidos.id_parceiro = parceiros.id_parceiro";
				this.resultset = this.statement.executeQuery(query);
				System.out.println("---");
				while (this.resultset.next()) {
					System.out.println("Cod. produto: " + this.resultset.getString("cod_produto"));
					System.out.println("Produto: " + this.resultset.getString("nome_produto"));
					System.out.println("Parceiro: " + this.resultset.getString("cod_produto"));
					System.out.println("Peso (kg): " + this.resultset.getString("peso_total"));
					System.out.println("---");
				}
			} catch (Exception e) {
				System.out.println("Erro" + e.getMessage());
			}
			break;

		default:
			System.out.println("Erro: tabela selecionada não existe.");
			break;
		}
	}

	// Método para editar banco de dados:
	public void editarContato(String tabela, Scanner input) {
		String query;
		switch(tabela) {
		case "parceiros":
			int id_parceiro;
			String nome_parceiro, contato;
			System.out.println("ID do parceiro a alterar: ");
			id_parceiro = input.nextInt();
			input.nextLine();
			System.out.println("Novo nome: ");
			nome_parceiro = input.nextLine();
			System.out.println("Novo contato: ");
			contato = input.nextLine();
			try {
				// A notação "+nome+" indica referência aos parâmetros fornecidos ao método.
				// No momento, só implementado para a tabela parceiros.
				query = "update " + tabela + " set nome_parceiro = '"+ nome_parceiro +"', contato = '"+ contato +"' where id_parceiro = " + id_parceiro;
				this.statement.executeUpdate(query);
				System.out.println(nome_parceiro + " alterado com sucesso na tabela " + tabela);
				query = "select * from " + tabela + " where id_parceiro = "+ id_parceiro;
				this.resultset = this.statement.executeQuery(query);
				System.out.println("---");
				while (this.resultset.next()) {
					System.out.println("ID parceiro: " + this.resultset.getString("id_parceiro"));
					System.out.println("Parceiro: " + this.resultset.getString("nome_parceiro"));
					System.out.println("Contato: " + this.resultset.getString("contato"));
					System.out.println("---");
				}
			} catch (Exception e) {
				System.out.println("Error" + e.getMessage());
			}
			break;
		case "armazenamento":
			;
		case "pedidos":
			;
		case "produtos":
			;
		default:
			System.out.println("Tabela inexistente");
		
		}

	}

	// Método para apagar contato:
	public void apagarContato(String id) {
		try {
			String query = "delete from parceiros where id = '" + id + "'";
			this.statement.executeUpdate(query);
			System.out.println(id + " Excluído com sucesso!");
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	// Método para desconectar do banco de dados:
	public void desconectar() {
		try {
			this.connection.close();
		} catch (Exception e) {
			System.out.println("Erro" + e.getMessage());
		}
	}

}