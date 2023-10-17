/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author GABRIEL
 */
public class CadastroBd extends JFrame {

    JTextField ip;
    JTextField user;
    JPasswordField pass;
    JLabel textoip;
    JLabel textouser;
    JLabel textopass;

    JButton conectar;
    public CadastroBd(){
        setTitle("Sistema de CRUD em JAVA - Conexão");
        setSize(443,443);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setBackground(new Color(217,217,217));
        int width = getWidth();


        conectar = new JButton();
        conectar.setText("Conectar");
        conectar.setSize(172,36);
        int conwi = conectar.getWidth();
        conectar.setBounds(width/2 - conwi/2,340,172,36);
        conectar.setFont(new Font("Inter",Font.BOLD,16));


        add(conectar);
        conectar.addActionListener(this::connect);

        ip = new JTextField();
        ip.setSize(373,23);
        int ipwi = ip.getWidth();
        ip.setBounds(width/2 - ipwi/2,141,373,23);
        ip.setBackground(Color.WHITE);

        add(ip);


        textoip = new JLabel("IP Address:");
        textoip.setFont(new Font("Inter",Font.BOLD,12));
        textoip.setSize(118,15);
        textoip.setBounds(36,118,118,15);

        add(textoip);


        textouser = new JLabel("User:");
        textouser.setFont(new Font("Inter",Font.BOLD,12));
        textouser.setSize(118,15);
        textouser.setBounds(36,177,118,15);


        add(textouser);


        user = new JTextField();
        user.setSize(373,23);
        int userwi = ip.getWidth();
        user.setBounds(width/2 - userwi/2,200,373,23);
        user.setBackground(Color.WHITE);

        add(user);


        textopass = new JLabel("Password:");
        textopass.setFont(new Font("Inter",Font.BOLD,12));
        textopass.setSize(118,15);
        textopass.setBounds(36,243,118,15);


        add(textopass);


        pass = new JPasswordField();
        pass.setSize(373,23);
        int passwi = ip.getWidth();
        pass.setBounds(width/2 - passwi/2,266,373,23);
        pass.setBackground(Color.WHITE);

        add(pass);

        JLabel texto = new JLabel("<HTML>"+"Entre com as informações do banco de" +"<br>"+"dados para iniciar o programa"+"</HTML>");
        texto.setSize(371,35);
        int textwi = texto.getWidth();
        texto.setBounds(width/2 - textwi/2,38,371,50);
        texto.setFont(new Font("Inter",Font.PLAIN,20));

        add(texto);

        JLabel nada = new JLabel();
        add(nada);




        setVisible(true);
    }

    private void connect(ActionEvent actionEvent) {
        //Executar Conexão e enviar dados

        String iptext = ip.getText();
        String usertext = user.getText();
        String passtext = pass.getText();

    }
}
