/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;


import Controller.MetaDataController;
import Model.connectDAO;
import java.awt.Color;
import static java.awt.Color.BLACK;
import static java.awt.Color.red;
import static java.awt.Color.white;
import java.awt.Font;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;





public class Main_p extends JFrame {
    JLabel bdname;
    JLabel bduser;
    JButton sair;
    JComboBox<String> schema;
    
    
    connectDAO dao = new connectDAO();


    public Main_p() throws SQLException{
        setTitle("Sistema de CRUD em JAVA");
        setSize(1440,1024);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setBackground(new Color(217,217,217));
        int width = getWidth();

        //adicionar variavel para armazenar nome da base
        bdname = new JLabel();
        bdname.setText(dao.getUrl());
        bdname.setBounds(31,42,100,24);
        bdname.setForeground(Color.GRAY);
        
        add(bdname);
        
        JLabel ipaddress = new JLabel();
        ipaddress.setText("IP Address:");
        ipaddress.setBounds(31,22,67,17);
        add(ipaddress);
        
        
        bduser = new JLabel();
        bduser.setText(dao.getUser());
        bduser.setBounds(223,42,100,24);
        bduser.setForeground(Color.GRAY);
        
        add(bduser);
        
        JLabel user = new JLabel();
        user.setText("User:");
        user.setBounds(223,22,67,17);
        add(user);
        
        
        
        sair = new JButton();
        sair.setText("Sair");
        sair.setBounds(444,20,83,19);
        sair.setBackground(red);
        sair.setForeground(white);
        
        add(sair);
        
        //-------------------------ComboBox
        MetaDataController meta = new MetaDataController();
       
        List<String> lista = meta.GetSCHEMA();

            for(String item : lista){
                schema.addItem(item);
            }

        schema = new JComboBox();
        schema.setBounds(140, 103, 373, 23);

        add(schema);
            
        //--------------------------------Fim ComboBox
        
        JLabel schemas = new JLabel();
        schemas.setText("Lista schemas");
        schemas.setBounds(31, 106, 95, 17);
        
        add(schemas);
        
        
        
        
        
        
        JLabel fim = new JLabel();
        add(fim);
    }
    
    
    
    public static void main(String[] args) throws SQLException{               
        Main_p main_p = new Main_p();
        main_p.setVisible(true);
    }

}
