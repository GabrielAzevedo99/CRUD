package Controller;

import Model.conexaoo;
import Model.connectDAO;
import View.CadastroBd;
import View.Main_p;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ConexaoController {
    private conexaoo conexaoModel;
    private CadastroBd cadastroView;
    private connectDAO dao = new connectDAO();
    
    //jdbc:mysql://localhost:3306/
    
    public void setCadastroView(CadastroBd cadastroView) {
        this.cadastroView = cadastroView;
    }
    
    
    public void conexaoModel(conexaoo conexaoModel) {
        this.conexaoModel = conexaoModel;
    }
    
    public ConexaoController() {
    }
    
    public void botao(String ipText, String userText, String passText) { 
        try {
            
            connect(ipText,userText,passText);
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ConexaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void connect(String ipText, String userText, String passText) throws SQLException, ClassNotFoundException {
        // Salva dados da conecção em variaveis globais
        dao.setUrl(ipText);
        dao.setUser(userText);
        dao.setPassword(passText);
        
        System.out.println(dao.getUrl());
        // Configure a conexão
        conexaoModel = new conexaoo(ipText,userText,passText);
        

        Connection connection = conexaoModel.getConnection();
        
        if (connection != null) {
            Main_p main = new Main_p();
            main.setVisible(true);
            conexaoModel.desconectar(connection);
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao conectar.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    void teste() {
        if (dao.getUrl() == null && dao.getUser() == null) {
            cadastroView.setVisible(true);
        }
    }

    public static void main(String[] args) {
        ConexaoController cont = new ConexaoController();
        CadastroBd cadastroView = new CadastroBd(); 
        
        cont.setCadastroView(cadastroView);
        
        cont.teste();
    }
}
