package getMetaData;

/**
 *
 * @author THE KING
 */
import conexao.conexaoo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class getMetaData {

    public static void main(String[] args) throws SQLException {

        List<String> bases = GetSCHEMA();
        String base = bases.get(0);
        System.out.println(base);

        List<String> tabelas = getTABELAS(base);
        String tabela = tabelas.get(1);
        System.out.println(tabela);

        List<String> Nomes = getAtributo(base, tabela);
        String nome = Nomes.get(4);

        List<String> Tipos = getTipoAtributo(base, tabela);
        String tipo = Tipos.get(4);

        System.out.println("   Coluna: " + nome + "   Tipo: " + tipo);

        List<String> Registros = getRegistros(base, tabela, nome);
        String Registro = Registros.get(0);

        System.out.println("   Registro: " + Registro);
    }

    public static List<String> GetSCHEMA() throws SQLException {

        //conexaoo getConexao = new conexaoo();
        conexaoo getConexao = new conexaoo();
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

    public static List<String> getTABELAS(String schema) throws SQLException {

        conexaoo getConexao = new conexaoo(schema);
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

    public static List<String> getAtributo(String schema, String tabela) throws SQLException {

        conexaoo getConexao = new conexaoo(schema);
        Connection connect = getConexao.getConnection();
        //Statement statement = connect.createStatement();

        PreparedStatement pstmt = connect.prepareStatement("SELECT COLUMN_NAME AS Atributo FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME LIKE ?");

        pstmt.setString(1, tabela);
        //pstmt.setString(2, tabela);

        //ResultSet resultSet = statement.executeQuery("SELECT COLUMN_NAME AS Atributo, DATA_TYPE AS Tipo_de_atributo FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA LIKE (\"schema\") AND TABLE_NAME LIKE (\"tabela\")");
        ResultSet resultSet = pstmt.executeQuery();

        List<String> atributos = new ArrayList<>();
        //List<String> tipos = new ArrayList<>();

        while (resultSet.next()) {
            String atributo = resultSet.getString(1);
            //String tipo = resultSet.getString(2);
            atributos.add(atributo);
            //tipos.add(tipo);
        }

        /*for(String nome : atributos){
                
            System.out.println("   Coluna: " + nome);

        }*/
        return atributos;
    }

    public static List<String> getTipoAtributo(String schema, String tabela) throws SQLException {

        conexaoo getConexao = new conexaoo(schema);
        Connection connect = getConexao.getConnection();
        //Statement statement = connect.createStatement();

        PreparedStatement pstmt = connect.prepareStatement("SELECT DATA_TYPE AS Tipo_de_atributo FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME LIKE ?");

        pstmt.setString(1, tabela);
        //pstmt.setString(2, tabela);

        //ResultSet resultSet = statement.executeQuery("SELECT COLUMN_NAME AS Atributo, DATA_TYPE AS Tipo_de_atributo FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA LIKE (\"schema\") AND TABLE_NAME LIKE (\"tabela\")");
        ResultSet resultSet = pstmt.executeQuery();

        List<String> tipos = new ArrayList<>();

        while (resultSet.next()) {
            String tipo = resultSet.getString(1);
            tipos.add(tipo);
        }
        return tipos;
    }

    public static List<String> getRegistros(String schema, String tabela, String nome) throws SQLException {

        conexaoo getConexao = new conexaoo(schema);
        Connection connect = getConexao.getConnection();
        //Statement statement = connect.createStatement();

        PreparedStatement pstmt = connect.prepareStatement("SELECT " + nome + " FROM " + tabela);

        //pstmt.setString(1, nome);
        //pstmt.setString(2, tabela);
        ResultSet resultSet = pstmt.executeQuery();

        List<String> registros = new ArrayList<>();

        while (resultSet.next()) {
            String registro = resultSet.getString(1);
            registros.add(registro);
        }
        return registros;
    }
}
