package projeto_final;
import java.sql.Connection; // Gerencia a conexão
import java.sql.DriverManager; // Gerencia o driver
import java.sql.ResultSet; // Consulta, retorna, armazena e imprime dados de DBs
import java.sql.Statement; // Gerencia e permite consultas (queries) nos DBs
import java.util.Properties;
import java.util.Scanner;

// jeronimo é o cara
public class api_db {
  // Criação e inicialização das variáveis:
  private Connection connection = null;
  private Statement statement = null;
  private ResultSet resultset = null;

  // Método para conectar ao banco de dados
  public void conectar() {
      // jdbc:mysql - Requisito do MySQL
      // localhost - acesso local
      // 3306 - porta
      // cadastro - base de dados a acessar
	  Properties props = new Properties();
	  String servidor = "";
      String usuario = "";
      String senha = "";
      String driver = "";
	  Scanner input = new Scanner(System.in);
	  System.out.println("Tipo de conexão:\n"
				+ "1 - Aiven\n"
				+ "2 - Jeronimo\n"
				+ "3 - Lucas\n"
				+ "4 - Jairo");
	  int opcao = input.nextInt();
	  
	  switch (opcao) {
		  case 1:
			  // Aiven
		      servidor = "jdbc:mysql://mysql-1fad3a13-shaolin-2d8a.i.aivencloud.com:11894/cadusuario";
		      usuario = "avnadmin";
		      senha = props.getProperty("aiven.senha");
		      driver = "com.mysql.cj.jdbc.Driver";
			  break;
		  case 2:
			  // Jeronimo
		      servidor = "jdbc:mysql://10.18.0.158:3306/projetofinal";
		      usuario = "usuario";
		      senha = props.getProperty("jeronimo.senha");
		      driver = "com.mysql.cj.jdbc.Driver";
			  break;
		  case 3:
			  // Lucas
		      servidor = "jdbc:mysql://localhost:3306/projetofinal";
		      usuario = "root";
		      senha = props.getProperty("lucas.senha");
		      driver = "com.mysql.cj.jdbc.Driver";
			  break;
		  case 4:
			  // Jairo
		      servidor = "jdbc:mysql://localhost:3306/projetofinal";
		      usuario = "root";
		      senha = props.getProperty("jairo.senha");
		      driver = "com.mysql.cj.jdbc.Driver";
			  break;

		   default:
			  break;
	  }
	  input.close();
      
      try {
          Class.forName(driver);
          this.connection = DriverManager.getConnection(servidor, usuario, senha);
          this.statement = this.connection.createStatement();

      } catch(Exception e) {
          System.out.println("Error"+e.getMessage());

      }

  }

  // Verificador da conexão.
  public boolean estaConectado() {
      if(this.connection!=null) {
          return true;
      } else {
          return false;
      }
  }

  // Método para inserção de dados:
  public void inserirContato(String nome, String telefone) {
      try {
          // A notação "+nome+" indica referência aos parâmetros fornecidos ao método.
          String query = "insert into parceiros (nome_parceiro, contato) values ('"+nome+"','"+telefone+"')";
          this.statement.executeUpdate(query);
          System.out.println( nome +" adicionado com sucesso!");
      } catch (Exception e) {
          System.out.println("Error"+e.getMessage());
      }
  }
  // Método para listar os contatos:
  public void listarContato() {
      try {
          String query = "select * from parceiros order by nome_parceiro";
          this.resultset = this.statement.executeQuery(query);
          while(this.resultset.next()) {
              System.out.println(
                      "ID: " + this.resultset.getString("id_parceiro")
                              + "\nNome: " + this.resultset.getString("nome_parceiro")
                              + "\nTelefone: " + this.resultset.getString("contato")
              );
          }
      } catch(Exception e){
          System.out.println("Erro"+e.getMessage());
      }
  }

  // Método para editar banco de dados:
  public void editarContato(String id, String nome, String telefone) {
      try {
          String query =
                  "update parceiros"
                          + " set nome_parceiro = '"+nome+"', contato = '"+telefone+"' "
                          + " where id_parceiro = '"+id+"'"
                  ;
          this.statement.executeUpdate(query);
          System.out.println("id de número " + id +" Editado com sucesso!");
      } catch(Exception e) {
          System.out.println("Erro: "+e.getMessage());
      }
  }

  // Método para apagar contato:
  public void apagarContato(String id) {
      try {
          String query = "delete from parceiros where id = '"+id+"'";
          this.statement.executeUpdate(query);
          System.out.println( id +" Excluído com sucesso!");
      } catch(Exception e) {
          System.out.println("Erro: "+e.getMessage());
      }
  }

  // Método para desconectar do banco de dados:
  public void desconectar() {
      try {
          this.connection.close();
      }catch(Exception e){
          System.out.println("Erro"+e.getMessage());
      }
  }

}