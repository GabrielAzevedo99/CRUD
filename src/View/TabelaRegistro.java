/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author custo
 */

package View;


import Controller.MetaDataController;
import Controller.viewController;
import Model.connectDAO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TabelaRegistro extends JPanel {
    MetaDataController meta = new MetaDataController();
    
    

    public TabelaRegistro(String base) throws SQLException, ClassNotFoundException {
        configurarJanela();
        System.out.println(base);
        adicionarTabelas(base);
    }

    private void configurarJanela() {
        setLayout(new GridBagLayout());
    }

    private void adicionarTabelas(String base) throws SQLException, ClassNotFoundException {
        List<String> tabela = meta.getTABELAS(base);
        connectDAO dao = new connectDAO();
        dao.setSchema(base);
        viewController contro = new viewController();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Adicionando margens

        Dimension buttonSize = new Dimension(100, 30); // Tamanho padrão para os botões

        for (String dado : tabela) {
            JLabel label = new JLabel(dado);
            JButton Listar = criarBotao("Listar", buttonSize, e -> {
                try {
                    contro.listar(dado);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(TabelaRegistro.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            JButton excluir = criarBotao("Excluir", buttonSize, e -> {
                try {
                    contro.excluir(dado);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(TabelaRegistro.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            JButton adicionar = criarBotao("Adicionar", buttonSize, e -> {
                try {
                    contro.inserir(dado);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(TabelaRegistro.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            JButton atualizarButton = criarBotao("Atualizar", buttonSize, e -> {
                try {
                    contro.atualizar(dado);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(TabelaRegistro.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            gbc.gridx = 0;
            gbc.gridy++;
            add(label, gbc);

            gbc.gridx = 1;
            add(Listar, gbc);

            gbc.gridx = 4;
            add(excluir, gbc);

            gbc.gridx = 3;
            add(adicionar, gbc);

            gbc.gridx = 2;
            add(atualizarButton, gbc);
        }
    }

    private JButton criarBotao(String texto, Dimension tamanho, ActionListener actionListener) {
        JButton botao = new JButton(texto);
        botao.setPreferredSize(tamanho);
        botao.setMinimumSize(tamanho);
        botao.setMaximumSize(tamanho);
        botao.addActionListener(actionListener);
        botao.setBackground(Color.BLUE);
        botao.setForeground(Color.WHITE);
        return botao;
    }
 
}
