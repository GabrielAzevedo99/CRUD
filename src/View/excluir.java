/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.viewController;
import Model.connectDAO;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author custo
 */
public class excluir extends JDialog {
    connectDAO dao = new connectDAO();
    
    JTextField id;
    JTextField nome;
    JLabel textoid;
    JLabel textonome;
    private int idnum;
    private String novoNome;

    JButton update;
    public excluir(String tabela){
        setTitle("Sistema de CRUD em JAVA - Excluir");
        setSize(443,443);
        setLocationRelativeTo(null);
        setResizable(false);
        setBackground(new Color(217,217,217));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        int width = getWidth();


        update = new JButton();
        update.setText("Atualizar");
        update.setSize(172,36);
        int conwi = update.getWidth();
        update.setBounds(width/2 - conwi/2,340,172,36);
        update.setFont(new Font("Inter",Font.BOLD,16));
        System.out.println("teste");


        add(update);
        update.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String textoId = id.getText();

                    try {
                        int valorId = Integer.parseInt(textoId);
                        setIdnum(valorId);

                        dispose();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Insira um valor válido no campo ID", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                    try {
                        
                        viewController view = new viewController();
                        
                        view.crudExcluir(tabela,getIdnum());
                        dispose();
                    } catch (SQLException ex) {
                        Logger.getLogger(Inserir.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Inserir.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    dispose();
                }
            });
        

        id = new JTextField();
        id.setSize(373,23);
        int ipwi = id.getWidth();
        id.setBounds(width/2 - ipwi/2,141,373,23);
        id.setBackground(Color.WHITE);

        add(id);


        textoid = new JLabel("Selecione o Id:");
        textoid.setFont(new Font("Inter",Font.BOLD,12));
        textoid.setSize(118,15);
        textoid.setBounds(36,118,118,15);

        add(textoid);
        

        JLabel texto = new JLabel("<HTML>"+"Entre com as informações que" +"<br>"+"deseja excluir"+"</HTML>");
        texto.setSize(371,35);
        int textwi = texto.getWidth();
        texto.setBounds(width/2 - textwi/2,38,371,50);
        texto.setFont(new Font("Inter",Font.PLAIN,20));

        add(texto);

        JLabel nada = new JLabel();
        add(nada);
        
        
        setVisible(true);
    }


    public int getIdnum() {
        return idnum;
    }

    public void setIdnum(int idnum) {
        this.idnum = idnum;
    }

}
