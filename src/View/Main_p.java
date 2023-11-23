/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author custo
 */
package View;


import Controller.CrudController;
import Controller.MetaDataController;
import Model.connectDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class Main_p extends JFrame {
    JLabel bdname, bduser;
    JButton sair;
    JComboBox<String> schema;
    JPanel registros, contentPanel;
    String selectedSchema;
    JScrollPane scrollPane;
    private CrudController crudController;
    

    connectDAO dao = new connectDAO();
    public void criarTabelaRegistro(String schema) {
        try {
            TabelaRegistro tabelaRegistro = new TabelaRegistro(schema);

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    public Main_p() throws SQLException, ClassNotFoundException {
        setTitle("Sistema de CRUD em JAVA");
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());


        bdname = new JLabel(dao.getUrl());
        bdname.setForeground(Color.GRAY);

        JLabel ipaddress = new JLabel("IP Address:");

        bduser = new JLabel(dao.getUser());
        bduser.setForeground(Color.GRAY);

        JLabel user = new JLabel("User:");

        sair = new JButton("Sair");
        sair.setBackground(Color.RED);
        sair.setForeground(Color.WHITE);
        sair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();  // Fecha a janela atual
            }
        });

        MetaDataController meta = new MetaDataController();
        schema = new JComboBox<>();
        List<String> lista = meta.GetSCHEMA();
        for (String item : lista) {
            schema.addItem(item);
        }

        JLabel schemas = new JLabel("Lista schemas");

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(bdname, gbc);
        gbc.gridy = 0;
        panel.add(ipaddress, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(bduser, gbc);
        gbc.gridy = 0;
        panel.add(user, gbc);
        gbc.gridx = 4;
        gbc.gridy = 0;
        panel.add(sair, gbc);
        gbc.gridy = 1;
        gbc.gridx = 3;
        panel.add(schema, gbc);
        gbc.gridx = 3;
        gbc.gridy = 0;
        //gbc.gridheight = 2;
        panel.add(schemas, gbc);
        add(panel, BorderLayout.NORTH);

        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        

        scrollPane = new JScrollPane(contentPanel);
        add(scrollPane);

        this.crudController = new CrudController();

        schema.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String selectedSchema = schema.getSelectedItem().toString();
                    crudController.setSelectedSchema(selectedSchema);

                    if (crudController != null) {
                        crudController.conectarAoBanco();
                        TabelaRegistro regs = new TabelaRegistro(selectedSchema);
                        contentPanel.removeAll();
                        contentPanel.add(regs);

                        scrollPane.setViewportView(contentPanel);
                    } else {
                        System.out.println("crudController é nulo. Certifique-se de inicializá-lo corretamente.");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    CrudController crudController = new CrudController();
                    TabelaRegistro tabelaRegistro = new TabelaRegistro("");

                } catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    
}

