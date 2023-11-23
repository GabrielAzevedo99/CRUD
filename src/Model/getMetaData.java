package Model;

/**
 *
 * @author THE KING
 */
import Model.conexaoo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class getMetaData {


    public static List<String> GetSCHEMA(String IP, String USER, String PASS) throws SQLException, ClassNotFoundException {

        conexaoo getConexao = new conexaoo(IP, USER, PASS);
        Connection connect = getConexao.getConnection();
        Statement statement = connect.createStatement();
        ResultSet resultSet = statement.executeQuery("SHOW DATABASES WHERE `Database` NOT IN ('mysql', 'information_schema', 'performance_schema', 'sys', 'phpmyadmin')");
        List<String> bases = new ArrayList<>();

        while (resultSet.next()) {
            String catalogName = resultSet.getString(1);
            bases.add(catalogName);
        }

        return bases;
    }

    public static List<String> getTABELAS(String IP, String USER, String PASS, String schema) throws SQLException, ClassNotFoundException {

        conexaoo getConexao = new conexaoo(IP, USER, PASS);
        getConexao.setIPbase(schema);
        Connection connect = getConexao.getConnection();
        Statement statement = connect.createStatement();
        ResultSet resultSet = statement.executeQuery("SHOW TABLES");
        List<String> tabelas = new ArrayList<>();

        while (resultSet.next()) {
            String tabela = resultSet.getString(1);
            tabelas.add(tabela);
        }

        return tabelas;
    }

    public static List<String> getNomeAtributo(String IP, String USER, String PASS, String schema, String tabela) throws SQLException, ClassNotFoundException {

        conexaoo getConexao = new conexaoo(IP, USER, PASS);
        getConexao.setIPbase(schema);
        Connection connect = getConexao.getConnection();

        PreparedStatement pstmt = connect.prepareStatement("SELECT COLUMN_NAME AS Atributo FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME LIKE ?");

        pstmt.setString(1, tabela);

        
        ResultSet resultSet = pstmt.executeQuery();

        List<String> atributos = new ArrayList<>();
        

        while (resultSet.next()) {
            String atributo = resultSet.getString(1);
            atributos.add(atributo);
        }

        
        return atributos;
    }

    public static List<String> getTipoAtributo(String IP, String USER, String PASS, String schema, String tabela) throws SQLException, ClassNotFoundException {

        conexaoo getConexao = new conexaoo(IP, USER, PASS);
        getConexao.setIPbase(schema);
        Connection connect = getConexao.getConnection();

        PreparedStatement pstmt = connect.prepareStatement("SELECT DATA_TYPE AS Tipo_de_atributo FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME LIKE ?");

        pstmt.setString(1, tabela);
        ResultSet resultSet = pstmt.executeQuery();

        List<String> tipos = new ArrayList<>();

        while (resultSet.next()) {
            String tipo = resultSet.getString(1);
            tipos.add(tipo);
        }
        return tipos;
    }
    
    public static List<String> getRestricoes(String IP, String USER, String PASS, String schema, String tabela, String nome) throws SQLException, ClassNotFoundException {

        conexaoo getConexao = new conexaoo(IP, USER, PASS);
        getConexao.setIPbase(schema);
        Connection connect = getConexao.getConnection();

        PreparedStatement pstmt = connect.prepareStatement("SELECT COLUMN_KEY, EXTRA, IS_NULLABLE, CHARACTER_MAXIMUM_LENGTH FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = ? AND COLUMN_NAME = ?;");
        
        pstmt.setString(1, tabela);
        pstmt.setString(2, nome);

        ResultSet resultSet = pstmt.executeQuery();

        List<String> COLUMN_KEY = new ArrayList<>();
        List<String> EXTRA = new ArrayList<>();
        List<String> IS_NULLABLE = new ArrayList<>();
        List<String> CHARACTER_MAXIMUM_LENGTH = new ArrayList<>();
        List<String> restricoes = new ArrayList<>();

        while (resultSet.next()) {
            String R1 = resultSet.getString(1);
            COLUMN_KEY.add(R1);
            String R2 = resultSet.getString(2);
            EXTRA.add(R2);
            String R3 = resultSet.getString(3);
            IS_NULLABLE.add(R3);
            String R4 = resultSet.getString(4);
            CHARACTER_MAXIMUM_LENGTH.add(R4);
            
            restricoes.addAll(COLUMN_KEY);
            restricoes.addAll(EXTRA);
            restricoes.addAll(IS_NULLABLE);
            restricoes.addAll(CHARACTER_MAXIMUM_LENGTH);
        }
        return restricoes;
    }

    public static List<String> getRegistros(String IP, String USER, String PASS, String schema, String tabela, String nome) throws SQLException, ClassNotFoundException {

        conexaoo getConexao = new conexaoo(IP, USER, PASS);
        getConexao.setIPbase(schema);
        Connection connect = getConexao.getConnection();


        PreparedStatement pstmt = connect.prepareStatement("SELECT " + nome + " FROM " + tabela);

        ResultSet resultSet = pstmt.executeQuery();

        List<String> registros = new ArrayList<>();

        while (resultSet.next()) {
            String registro = resultSet.getString(1);
            registros.add(registro);
        }
        return registros;
    }
}
