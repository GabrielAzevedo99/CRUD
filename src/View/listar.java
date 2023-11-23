/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Model.Registro;
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

    public listar(List<Registro> registros) {
        setTitle("Exibição de Registros");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(registros.size(), 2, 10, 10));

        for (Registro registro : registros) {
            JLabel labelId = new JLabel("ID: " + registro.getId());
            JLabel labelNome = new JLabel("Nome: " + registro.getNome());

            panel.add(labelId);
            panel.add(labelNome);
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        add(scrollPane);

        setVisible(true);
    }
    
}
