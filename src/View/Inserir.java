/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.viewController;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author custo
 */


public class Inserir extends JDialog {
    private List<JTextField> textFields = new ArrayList<>();
    private JButton insert;

    public Inserir(String tabela, List<String> campos) {
        setTitle("Sistema de CRUD em JAVA - Inserir");
        setSize(443, 443);
        setLocationRelativeTo(null);
        setResizable(false);
        setBackground(new Color(217, 217, 217));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        for (int i = 0; i < campos.size(); i++) {
            String campo = campos.get(i);
            JLabel label = new JLabel(campo);
            JTextField text = new JTextField(20);
            textFields.add(text);

            gbc.gridx = 0;
            gbc.gridy = i;
            panel.add(label, gbc);

            gbc.gridx = 1;
            gbc.gridy = i;
            panel.add(text, gbc);
        }

        insert = new JButton("Inserir");
        insert.setFont(new Font("Inter", Font.BOLD, 16));

        gbc.gridx = 0;
        gbc.gridy = campos.size();
        gbc.gridwidth = 2;
        panel.add(insert, gbc);

        insert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    viewController view = new viewController();

                    List<String> valores = new ArrayList<>();
                    for (JTextField textField : textFields) {
                        valores.add(textField.getText());
                    }

                    view.crudInserir(tabela, campos, valores);
                    dispose();
                } catch (SQLException ex) {
                    Logger.getLogger(Inserir.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Inserir.class.getName()).log(Level.SEVERE, null, ex);
                }
                dispose();
            }
        });

        JScrollPane scrollPane = new JScrollPane(panel);
        add(scrollPane);
        setVisible(true);
    }
}



