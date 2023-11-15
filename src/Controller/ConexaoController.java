package Controller;

import Model.conexaoo;
import Model.connectDAO;
import View.CadastroBd;
import View.Main_p;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;
import java.util.Set;
import javax.swing.JOptionPane;

public class ConexaoController {
    private conexaoo conexaoModel;
    private CadastroBd cadastroView;
    private connectDAO dao = new connectDAO();
    
    //jdbc:mysql://localhost:3306/
    //conexaoo conexaoModel, CadastroBd cadastroView
    /*cadastroView.setConnectButtonAction(e -> {
            try {
                connect();
            } catch (SQLException ex) {
                Logger.getLogger(ConexaoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });*/
    
    public void setCadastroView(CadastroBd cadastroView) {
        this.cadastroView = cadastroView;
    }
    
    
    public void conexaoModel(conexaoo conexaoModel) {
        this.conexaoModel = conexaoModel;
    }
    
    public ConexaoController() {
    }
    
    public void botao(){ 
       
        try {
            connect();
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void connect() throws SQLException {
        CadastroBd cadastroView = new CadastroBd();
        String iptext = cadastroView.getIpText();
        String usertext = cadastroView.getUserText();
        String passtext = cadastroView.getPassText();
        
        
        // Salva dados da conecção em variaveis globais
        dao.setUrl(iptext);
        dao.setUser(usertext);
        dao.setPassword(passtext);
        

        // Configure a conexão
        conexaoModel = new conexaoo();
        conexaoModel.setConexaoo(iptext, usertext, passtext);

        Connection connection = conexaoModel.getConnection();
        
        if (connection != null) {
            Main_p main = new Main_p();
            //JOptionPane.showMessageDialog(null, "Conexão bem-sucedida!");
            conexaoModel.desconectar(connection);
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao conectar.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    void teste() {
        if (dao.getUrl() == null && dao.getUser() == null && dao.getPassword() == null) {
            cadastroView.setVisible(true);
        }
    }

    public static void main(String[] args) {
        ConexaoController cont = new ConexaoController();
        CadastroBd cadastroView = new CadastroBd(); 
        
        cont.setCadastroView(cadastroView);
        
        cont.teste();
        
        //Scanner scanner = new Scanner(System.in);
        
        /*
        ConexaoController controller = new ConexaoController(new conexaoo(cadastroView.getIpText(), cadastroView.getUserText(), cadastroView.getPassText()), cadastroView);
                try {
                    controller.connect();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao conectar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
        */
        
        /*
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
        }*/
    }
}
