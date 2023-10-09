package getMetaData;

/**
 *
 * @author THE KING
 */
import conexao.conexaoo;

import java.sql.*;

public class getMetaData {
    
    public static void main(String[] args) {
        
        GetSCHEMA();
        
    }
    
    public static void GetSCHEMA() {
    
        conexaoo getConexao = new conexaoo();
        String catalogName;
        
        try (Connection conexao = getConexao.getConnection()) {
            
            DatabaseMetaData metaData = conexao.getMetaData();

            // Obtenha os catálogos (que podem ser equivalentes aos schemas em alguns bancos de dados)
            ResultSet catalogs = metaData.getCatalogs();

            while (catalogs.next()) {
                //Pega o nome dos catalogos do banco de dados
                catalogName = catalogs.getString("TABLE_CAT");
                
                System.out.println("Nome da catalog: " + catalogName);
                
                getTABELAS(catalogName);
            }
            catalogs.close();
        } 
        
        
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getTABELAS(String catalogName) {

        conexaoo getConexao = new conexaoo();
        
        int n = 0;
        
        String NomeTabela = null;

        try (Connection conexao = getConexao.getConnection()) {
            
            DatabaseMetaData metaData = conexao.getMetaData();
            
            String[] types = {"TABLE"};
            
            //Busca as tabelas do banco de dados
            ResultSet tabelas = metaData.getTables(catalogName, null, "%", types);

            while (tabelas.next()) {
                //Pega o nome das tabelas do banco de dados
                NomeTabela = tabelas.getString("TABLE_NAME");

                System.out.println("Nome da tabela: " + NomeTabela);

                //n++;
                getCAMPOS(NomeTabela);
            }
            
            //System.out.println("Número de tabelas: " + n);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void getCAMPOS(String NomeTabela) {
        
        conexaoo getConexao = new conexaoo();
        
        try (Connection conexao = getConexao.getConnection()) {
            
            DatabaseMetaData metaData = conexao.getMetaData();
            
            // Obtenha as colunas da tabela
            ResultSet columns = metaData.getColumns(null, null, NomeTabela, null);

            while (columns.next()) {

            String columnName = columns.getString("COLUMN_NAME");
            String columnType = columns.getString("TYPE_NAME");
            System.out.println("   Coluna: " + columnName + ", Tipo: " + columnType);
            }
                
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
