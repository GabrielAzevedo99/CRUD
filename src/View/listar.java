/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author custo
 */
public class listar extends JDialog{

    public listar(List<String> registros, List<String> campos) {
        setTitle("Exibição de Registros");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(registros.size() / campos.size() + 1, campos.size(), 10, 10));
        
        
        for (String campo : campos) {
            JLabel label = new JLabel(campo);
            label.setFont(new Font("Inter",Font.BOLD,16));
            panel.add(label);
        }
        
        for (int i = 0; i < registros.size(); i++) {
            JLabel label = new JLabel(registros.get(i));
            panel.add(label);
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        add(scrollPane);

        setVisible(true);
    }
    
}
