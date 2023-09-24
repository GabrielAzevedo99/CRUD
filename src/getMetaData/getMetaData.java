/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package getMetaData;

/**
 *
 * @author THE KING
 */
import conexao.conexaoo;

import java.sql.*;

public class getMetaData {
    
    public static void main(String[] args) {
        
        getMeta();
        
    }

    public static void getMeta() {

        conexaoo getConexao = new conexaoo();
        
        int n = 0;

        try (Connection conexao = getConexao.getConnection();) {
            
            DatabaseMetaData metaData = conexao.getMetaData();

            String[] types = {"TABLE"};
            
            //Busca as tabelas do banco de dados
            ResultSet tabelas = metaData.getTables(null, null, "%", types);

            while (tabelas.next()) {
                //Pega o nome das tabelas do banco de dados
                String NomeTabela = tabelas.getString("TABLE_NAME");
                System.out.println("Nome da tabela: " + NomeTabela);
                n++;
            }
            System.out.println("Número de tabelas: " + n);
            
            

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
