package Controller;

import Model.Registro;
import Model.conexaoo;
import Model.connectDAO;
import View.listar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CrudController {

    private conexaoo conexao;
    private connectDAO dao;
    private String selectedSchema;
    

    public CrudController() {
      
        this.dao = new connectDAO();
        
        
        this.conectarAoBanco();
    }
    
    public void setSelectedSchema(String selectedSchema) {
        this.selectedSchema = selectedSchema;
    }

    public void conectarAoBanco() {
        System.out.println(dao.getSchema());
        
            this.conexao = new conexaoo(dao.getUrl() + dao.getSchema(), dao.getUser(), dao.getPassword());
        /*} else {
            // Se o esquema não estiver selecionado, você pode lidar com isso de acordo com sua lógica.
            System.out.println("Nenhum esquema selecionado.");
        }*/
    }
    
    //-------------------------------------------------CRUD EM SI----------------------------------------------------

    public void inserirRegistro(String tabela, String coluna, String valor) throws SQLException, ClassNotFoundException {
        Connection connection = conexao.getConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO " + tabela + " (" + coluna + ") VALUES (?)");
            pstmt.setString(1, valor);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro inserido com sucesso.", "Sucesso", JOptionPane.PLAIN_MESSAGE);
            System.out.println("Registro inserido com sucesso.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir registro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            System.out.println("Erro ao inserir registro: " + e.getMessage());
        } finally {
            conexao.desconectar(connection);
        }
    }

    public void atualizarRegistro(String tabela, String novoNome, int id) throws SQLException, ClassNotFoundException {
        Connection connection = conexao.getConnection();
        System.out.println(id);
        try {
            PreparedStatement pstmt = connection.prepareStatement("UPDATE " + tabela + " SET nome = ? WHERE id = ?");
            pstmt.setString(1, novoNome);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro atualizado com sucesso.", "Sucesso", JOptionPane.PLAIN_MESSAGE);
            System.out.println("Registro atualizado com sucesso.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar registro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            System.out.println("Erro ao atualizar registro: " + e.getMessage());
        } finally {
            conexao.desconectar(connection);
        }
    }

    public void excluirRegistro(String tabela, int id) throws SQLException, ClassNotFoundException {
        Connection connection = conexao.getConnection();
        System.out.println(id);
        try {
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM " + tabela + " WHERE id = ?");
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro excluído com sucesso.", "Sucesso", JOptionPane.PLAIN_MESSAGE);
            System.out.println("Registro excluído com sucesso.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir registro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            System.out.println("Erro ao excluir registro: " + e.getMessage());
        } finally {
            conexao.desconectar(connection);
        }
    }

    public void listarRegistros(String tabela) throws SQLException, ClassNotFoundException {
        Connection connection = conexao.getConnection(); 
        List<Registro> registros = new ArrayList<>();
        try {
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM " + tabela);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                // Ler os dados de cada registro
                int registroId = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                // Adicione mais campos conforme necessário
                
                registros.add(new Registro(registroId, nome));

                System.out.println("ID: " + registroId);
                System.out.println("Nome: " + nome);
                // Imprima mais campos conforme necessário
                System.out.println("--------------------------");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar registro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            System.out.println("Erro ao listar registros: " + e.getMessage());
        } finally {
            conexao.desconectar(connection);
        }
        
        listar listar = new listar(registros);
    }
    
    //-------------------------------------------------FIM CRUD----------------------------------------------------

      /*public static void main(String[] args) throws SQLException, ClassNotFoundException {
        CrudController crud = new CrudController();
        Scanner scanner = new Scanner(System.in);

        

        System.out.println("Digite o nome da tabela: ");
        String tabela = scanner.nextLine();

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
                    System.out.println("Digite o nome da coluna: ");
                    String coluna = scanner.nextLine();

                    System.out.println("Digite um valor: ");
                    String valor = scanner.nextLine();

                    crud.inserirRegistro(tabela, coluna, valor);
                    break;
                case 2:
                    System.out.println("Digite o ID do registro a ser atualizado: ");
                    int idAtualizacao = scanner.nextInt();
                    scanner.nextLine(); // Limpar a nova linha

                    System.out.println("Digite o nome da coluna: ");
                    String colunaAtualizacao = scanner.nextLine();

                    System.out.println("Digite o novo valor: ");
                    String novoValor = scanner.nextLine();

                    //crud.atualizarRegistro(tabela, colunaAtualizacao, novoValor, idAtualizacao);
                    break;
                case 3:
                    System.out.println("Digite o ID do registro a ser excluído: ");
                    int idExclusao = scanner.nextInt();
                    scanner.nextLine();

                    crud.excluirRegistro(tabela, idExclusao);
                    break;
                case 4:
                    crud.listarRegistros(tabela);
                    break;
                default:
                    System.out.println("Escolha uma ação válida.");
                    break;
            }
        }*/
    }


