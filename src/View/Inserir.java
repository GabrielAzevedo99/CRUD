/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.viewController;
import Model.connectDAO;
import java.awt.Color;
import java.awt.Font;
import static java.awt.GridBagConstraints.CENTER;
import java.awt.GridLayout;
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
    connectDAO dao = new connectDAO();

    private List<JTextField> textFields = new ArrayList<>();
    private JButton update;

    public Inserir(String tabela, List<String> campos) {
        setTitle("Sistema de CRUD em JAVA - Inserir");
        setSize(443, 443);
        setLocationRelativeTo(null);
        setResizable(false);
        setBackground(new Color(217, 217, 217));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(campos.size() + 1, 1, 10, 10));

        for (String campo : campos) {
            JLabel label = new JLabel(campo);
            JTextField text = new JTextField();
            textFields.add(text);

            label.setSize(373, 23);
            text.setSize(373, 15);

            panel.add(label);
            panel.add(text);
        }

        update = new JButton();
        update.setText("Inserir");
        update.setSize(172, 36);
        update.setFont(new Font("Inter", Font.BOLD, 16));
        add(update);

        update.addActionListener(new ActionListener() {
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

        panel.add(update);

        JScrollPane scrollPane = new JScrollPane(panel);
        add(scrollPane);
        setVisible(true);
    }
}



