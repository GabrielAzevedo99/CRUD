package Controller;

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

    public void listarRegistros(String tabela, List<String> campos) throws SQLException, ClassNotFoundException {
        Connection connection = conexao.getConnection(); 
        List<String> registros = new ArrayList<>();
        try {
            String camposStr = String.join(", ", campos);
            String query = "SELECT " + camposStr + " FROM " + tabela;

            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                for (String campo : campos) {
                Object valorCampo = resultSet.getObject(campo);
                System.out.println(campo + ": " + valorCampo);
                registros.add(valorCampo.toString());
                }
                
                System.out.println("--------------------------");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar registro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            System.out.println("Erro ao listar registros: " + e.getMessage());
        } finally {
            conexao.desconectar(connection);
        }
        
        listar listar = new listar(registros,campos);
    }
    }


