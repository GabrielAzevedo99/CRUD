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
import java.util.stream.Collectors;
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
    

    public void inserirRegistro(String tabela, List<String> campos,List<String> valores) throws SQLException, ClassNotFoundException {
        Connection connection = conexao.getConnection();
        try {
            String camposStr = String.join(", ", campos);
            String valoresStr = String.join(", ", campos.stream().map(c -> "?").collect(Collectors.toList()));

            String query = "INSERT INTO " + tabela + " (" + camposStr + ") VALUES (" + valoresStr + ")";
            PreparedStatement pstmt = connection.prepareStatement(query);

            for (int i = 0; i < valores.size(); i++) {
                pstmt.setString(i + 1, valores.get(i));
            }
            
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

    public void atualizarRegistro(String tabela, List<String> valores, String id, List<String> campos, String pk) throws SQLException, ClassNotFoundException {
        Connection connection = conexao.getConnection();
        try {
            StringBuilder setClause = new StringBuilder();
            for (String campo : campos) {
                setClause.append(campo).append(" = ?, ");
            }
            setClause.delete(setClause.length() - 2, setClause.length());

            String query = "UPDATE " + tabela + " SET " + setClause.toString() + " WHERE " + pk + " = ?";

            PreparedStatement pstmt = connection.prepareStatement(query);

            int index = 1;
            for (String valor : valores) {
                pstmt.setString(index++, valor);
            }

            pstmt.setString(index, id);

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

    public void excluirRegistro(String tabela, String id, String chave) throws SQLException, ClassNotFoundException {
        Connection connection = conexao.getConnection();
        System.out.println(id);
        try {
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM " + tabela + " WHERE " + chave + "= ?");
            pstmt.setString(1, id);
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


