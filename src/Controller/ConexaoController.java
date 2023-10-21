package Controller;

import Model.conexaoo;
import View.CadastroBd;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;

public class ConexaoController {
    private conexaoo conexaoModel;
    private CadastroBd cadastroView;

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

        // Configure a conexão usando os dados fornecidos
        conexaoModel.setURL(iptext);
        conexaoo.setUsuario(usertext);
        conexaoo.setSenha(passtext);

        conexaoModel.getConnection();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Crie instâncias do modelo e da visão
        conexaoo conexaoModel = new conexaoo();
        CadastroBd cadastroView = new CadastroBd();

        // Crie uma instância do controlador
        ConexaoController controller = new ConexaoController(conexaoModel, cadastroView);

        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Conectar ao banco de dados");
            System.out.println("2 - Sair");
            int escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    // Inicie a ação de conexão
                    try {
                        controller.connect();
                        System.out.println("Conexão bem-sucedida!");
                    } catch (SQLException ex) {
                        System.out.println("Erro ao conectar: " + ex.getMessage());
                    }
                    break;
                case 2:
                    // Encerre o programa
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




