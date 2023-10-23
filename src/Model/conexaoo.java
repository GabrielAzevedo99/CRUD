package Model;

import java.sql.*;

public class conexaoo {
    
    //private String bd = null;
    private String URL;
    private String USUARIO;
    private String SENHA;

    public conexaoo(String IP, String USER, String PASS) {
        this.URL = IP;
        this.USUARIO = USER;
        this.SENHA = PASS;
    }

    public void setIPbase(String base) {
        this.URL = URL + base;
    }

    public Connection getConnection() throws SQLException {

        Connection conexao = null;

        try {
            // Carrega o driver JDBC do MySQL (você deve ter o JAR do driver no seu classpath)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Estabelece a conexão com o banco de dados
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);

            System.out.println("Conectou no banco de dados.");

        } catch (ClassNotFoundException e) {
            System.out.println("Erro: Não encontrou o driver do BD!");
            e.printStackTrace();
        }
        return conexao;
    }

    public void desconectar(Connection conexao) {

        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
                System.out.println("Desconectou do banco de dados!");
            }
        } catch (SQLException e) {
            System.out.println("Não conseguiu desconectar do BD!");
            e.printStackTrace();
        }
    }
}