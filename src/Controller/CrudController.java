package Controller;

import Model.conexaoo;
import java.sql.*;
import java.util.Scanner;

public class CrudController {

    private conexaoo conexao;
    private String usuario; // USADO PARA TESTE NO CRUDE OU SEJA PARA TESTE RODA SO EXECUTAR ARQUIVO
    private String senha; // USADO PARA TESTE NO CRUDE OU SEJA PARA TESTE RODA SO EXECUTAR ARQUIVO

    public CrudController() {
        this.conexao = null;
    }

    public void conectarAoBanco(String nomeDoBanco, String usuario, String senha) {
        this.conexao = new conexaoo(nomeDoBanco);
        this.usuario = usuario; // SALVA USUARIO
        this.senha = senha; // ASALVA SENHA
        conexaoo.setUsuario(usuario); // Configure o usuário na classe conexaoo
        conexaoo.setSenha(senha); // Configure a senha na classe conexaoo
    }
//----------------------------------------
    
    public void inserirRegistro(String tabela, String coluna, String valor) throws SQLException {
        Connection connection = conexao.getConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO " + tabela + " (" + coluna + ") VALUES (?)");
            pstmt.setString(1, valor);
            pstmt.executeUpdate();
            System.out.println("Registro inserido com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir registro: " + e.getMessage());
        } finally {
            conexao.desconectar(connection);
        }
    }

    public void atualizarRegistro(String tabela, String coluna, String novoValor, int id) throws SQLException {
        Connection connection = conexao.getConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement("UPDATE " + tabela + " SET " + coluna + " = ? WHERE id = ?");
            pstmt.setString(1, novoValor);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            System.out.println("Registro atualizado com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar registro: " + e.getMessage());
        } finally {
            conexao.desconectar(connection);
        }
    }

    public void excluirRegistro(String tabela, int id) throws SQLException {
        Connection connection = conexao.getConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM " + tabela + " WHERE id = ?");
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Registro excluído com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao excluir registro: " + e.getMessage());
        } finally {
            conexao.desconectar(connection);
        }
    }

    public void listarRegistros(String tabela) throws SQLException {
        Connection connection = conexao.getConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM " + tabela);
            ResultSet resultSet = pstmt.executeQuery();
            
            while (resultSet.next()) {
                // Ler os dados de cada registro
                int registroId = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                // Adicione mais campos conforme necessário

                System.out.println("ID: " + registroId);
                System.out.println("Nome: " + nome);
                // Imprima mais campos conforme necessário
                System.out.println("--------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar registros: " + e.getMessage());
        } finally {
            conexao.desconectar(connection);
        }
    }
    
    // -------------------------------------------------------------------------------------

    public static void main(String[] args) throws SQLException {
        CrudController crud = new CrudController();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o nome do banco de dados: ");
        String nomeDoBanco = scanner.nextLine();

        System.out.println("Digite o usuário do banco: ");
        String usuario = scanner.nextLine();

        System.out.println("Digite a senha do banco: ");
        String senha = scanner.nextLine();

        crud.conectarAoBanco(nomeDoBanco, usuario, senha); // Passa o usuário e senha

        // Resto do código permanece o mesmo
        
        System.out.println("Digite o nome da tabela: ");
        String tabela = scanner.nextLine();

            System.out.println("Digite o nome da coluna: ");
            String coluna = scanner.nextLine();
            
        while (true) {
            System.out.println("Escolha uma ação:");
            System.out.println("1. Inserir registro");
            System.out.println("2. Atualizar registro");
            System.out.println("3. Excluir registro");
            System.out.println("4. Listar registros");
            System.out.println("5. Sair");

            int escolha = scanner.nextInt();
            scanner.nextLine();

            if (escolha == 5) {
                break; // Sai do loop
            }

            switch (escolha) {
                case 1:
                   
                    System.out.println("Digite um valor: "); //Tirar depois
                    String valor = scanner.nextLine();
            
                    crud.inserirRegistro(tabela, coluna, valor);
                    break;
                case 2:
 
                    System.out.println("Digite o ID do registro a ser atualizado: ");
                    int idAtualizacao = scanner.nextInt();
                    scanner.nextLine(); // Limpar a nova linha
                    System.out.println("Digite o novo valor: ");
                    String novoValor = scanner.nextLine();
                    crud.atualizarRegistro(tabela, coluna, novoValor, idAtualizacao);
                    break;
                case 3:
                    System.out.println("Digite o ID do registro a ser excluido: ");
                    int idExclusao = scanner.nextInt();
                    scanner.nextLine();
                    
                    
                    
                    crud.excluirRegistro(tabela, idExclusao);
                    break;
                case 4:
                    crud.listarRegistros(tabela);
                    break;
                default:
                    System.out.println("Escolha uma ação valida.");
                    break;
            }
        }
    }
}








