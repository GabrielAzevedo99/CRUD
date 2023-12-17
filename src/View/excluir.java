/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.viewController;
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
    JTextField id;
    JLabel textoid;
    private String idnum;

    JButton excluir;
    public excluir(String tabela, String chave){
        setTitle("Sistema de CRUD em JAVA - Excluir");
        setSize(443,443);
        setLocationRelativeTo(null);
        setResizable(false);
        setBackground(new Color(217,217,217));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        int width = getWidth();


        excluir = new JButton();
        excluir.setText("Excluir");
        excluir.setSize(172,36);
        int conwi = excluir.getWidth();
        excluir.setBounds(width/2 - conwi/2,340,172,36);
        excluir.setFont(new Font("Inter",Font.BOLD,16));


        add(excluir);
        excluir.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String textoId = id.getText();

                    try {
                        String valorId = textoId;
                        setIdnum(valorId);

                        dispose();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Insira um valor válido em " + chave, "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                    
                    
                    try {
                        
                        viewController view = new viewController();
                        
                        view.crudExcluir(tabela,getIdnum(), chave);
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


        textoid = new JLabel(chave);
        textoid.setFont(new Font("Inter",Font.BOLD,12));
        textoid.setSize(118,15);
        textoid.setBounds(36,118,118,15);

        add(textoid);
        

        JLabel texto = new JLabel("<HTML>"+"Entre com a Primary Key que" +"<br>"+"deseja excluir"+"</HTML>");
        texto.setSize(371,35);
        int textwi = texto.getWidth();
        texto.setBounds(width/2 - textwi/2,38,371,50);
        texto.setFont(new Font("Inter",Font.PLAIN,20));

        add(texto);

        JLabel nada = new JLabel();
        add(nada);
        
        
        setVisible(true);
    }


    public String getIdnum() {
        return idnum;
    }

    public void setIdnum(String idnum) {
        this.idnum = idnum;
    }

}
