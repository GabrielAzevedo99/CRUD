package Controller;

import Model.conexaoo;
import View.CadastroBd;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class ConexaoController {
    private conexaoo conexaoModel;
    private CadastroBd cadastroView;
    
    //jdbc:mysql://localhost:3306/

    public ConexaoController(conexaoo conexaoModel, CadastroBd cadastroView) {
        this.conexaoModel = conexaoModel;
        this.cadastroView = cadastroView;

        // Define a ação do botão de conexão
        cadastroView.setConnectButtonAction(e -> {
            try {
                connect();
            } catch (SQLException ex) {
                Logger.getLogger(ConexaoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    void connect() throws SQLException {
        String iptext = cadastroView.getIpText();
        String usertext = cadastroView.getUserText();
        String passtext = cadastroView.getPassText();

        // Configure a conexão
        conexaoModel = new conexaoo(iptext, usertext, passtext);

        Connection connection = conexaoModel.getConnection();
        
        if (connection != null) {
            JOptionPane.showMessageDialog(null, "Conexão bem-sucedida!");
            conexaoModel.desconectar(connection);
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao conectar.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        CadastroBd cadastroView = new CadastroBd();

        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Conectar ao banco de dados");
            System.out.println("2 - Sair");
            int escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    // Inicie conexão
                    ConexaoController controller = new ConexaoController(new conexaoo(cadastroView.getIpText(), cadastroView.getUserText(), cadastroView.getPassText()), cadastroView);
                    try {
                        controller.connect();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao conectar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case 2:
                    // Encerre
                    System.out.println("Saindo do programa.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }
}
