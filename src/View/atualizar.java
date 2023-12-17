/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.viewController;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
public class atualizar extends JDialog {
    private List<JTextField> textFields = new ArrayList<>();
    private JButton update;

    public atualizar(String tabela, List<String> campos, String pk) {
        setTitle("Sistema de CRUD em JAVA - Atualizar");
        setSize(443, 443);
        setLocationRelativeTo(null);
        setResizable(false);
        setBackground(new Color(217, 217, 217));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(1, 1, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel labelpk = new JLabel(pk);
        JTextField textpk = new JTextField(20);
        textFields.add(textpk);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(labelpk, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(textpk, gbc);

        for (int i = 0; i < campos.size(); i++) {
            String campo = campos.get(i);
            JLabel label = new JLabel(campo);
            JTextField text = new JTextField(20);
            textFields.add(text);

            gbc.gridx = 0;
            gbc.gridy = i + 2;
            gbc.gridwidth = 1;
            panel.add(label, gbc);

            gbc.gridx = 1;
            gbc.gridy = i + 2;
            gbc.gridwidth = 1;
            panel.add(text, gbc);
        }

        update = new JButton("Atualizar");
        update.setFont(new Font("Inter", Font.BOLD, 16));

        gbc.gridx = 0;
        gbc.gridy = campos.size() + 4;
        gbc.gridwidth = 3;
        panel.add(update, gbc);

        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textoId = textpk.getText();

                try {
                    viewController view = new viewController();

                    List<String> valores = new ArrayList<>();
                    for (JTextField textField : textFields) {
                        valores.add(textField.getText());
                    }

                    view.crudAtualizar(tabela, valores, textoId, pk);
                    dispose();
                } catch (SQLException ex) {
                    Logger.getLogger(atualizar.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(atualizar.class.getName()).log(Level.SEVERE, null, ex);
                }
                dispose();
            }
        });

        JScrollPane scrollPane = new JScrollPane(panel);
        add(scrollPane);
        setVisible(true);
    }
}
