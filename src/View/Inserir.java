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
import javax.swing.JTextField;

/**
 *
 * @author custo
 */
public class Inserir extends JDialog {
    connectDAO dao = new connectDAO();
    
    JTextField nome;
    JLabel textonome;
    private int idnum;
    private String novoNome;

    JButton update;
    public Inserir(String tabela){
        setTitle("Sistema de CRUD em JAVA - Inserir");
        setSize(443,443);
        setLocationRelativeTo(null);
        setResizable(false);
        setBackground(new Color(217,217,217));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        int width = getWidth();


        update = new JButton();
        update.setText("Inserir");
        update.setSize(172,36);
        int conwi = update.getWidth();
        update.setBounds(width/2 - conwi/2,340,172,36);
        update.setFont(new Font("Inter",Font.BOLD,16));
        add(update);
        
        update.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setNovoNome(nome.getText());
                    try {
                        
                        viewController view = new viewController();
                        
                        view.crudInserir(tabela,getNovoNome());
                        dispose();
                    } catch (SQLException ex) {
                        Logger.getLogger(Inserir.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Inserir.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    dispose();
                }
            });

        textonome = new JLabel("Nome:");
        textonome.setFont(new Font("Inter",Font.BOLD,12));
        textonome.setSize(118,15);
        textonome.setBounds(36,177,118,15);


        add(textonome);


        nome = new JTextField();
        nome.setSize(373,23);
        int userwi = nome.getWidth();
        nome.setBounds(width/2 - userwi/2,200,373,23);
        nome.setBackground(Color.WHITE);

        add(nome);


        JLabel texto = new JLabel("<HTML>"+"Entre com as informações que" +"<br>"+"deseja inserir"+"</HTML>");
        texto.setSize(371,35);
        int textwi = texto.getWidth();
        texto.setBounds(width/2 - textwi/2,38,371,50);
        texto.setFont(new Font("Inter",Font.PLAIN,20));

        add(texto);

        JLabel nada = new JLabel();
        add(nada);
        
        
        setVisible(true);
    }

    public String getNovoNome() {
        return novoNome;
    }

    public void setNovoNome(String novoNome) {
        this.novoNome = novoNome;
    }

}

