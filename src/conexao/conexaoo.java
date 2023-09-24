package conexao;

import java.sql.*;

public class conexaoo {

    /*public static void main(String[] args) {

        conexao conexaoo = new conexao();
        Connection conexao = conexaoo.getConnection();

    }*/

    public Connection getConnection() {

        Connection conexao = null;
        // URL de conexão com o banco de dados MySQL
        String url = "jdbc:mysql://localhost:3307/agenda";

        // Nome de usuário e senha do banco de dados
        String username = "root";
        String password = "admin";

        try {
            // Carrega o driver JDBC do MySQL (você deve ter o JAR do driver no seu classpath)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Estabelece a conexão com o banco de dados
            conexao = DriverManager.getConnection(url, username, password);

            System.out.println("Conectou no banco de dados.");

        } catch (ClassNotFoundException e) {
            System.out.println("Erro: Não encontrou o driver do BD!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Erro: Não conseguiu conectar no BD!");
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
