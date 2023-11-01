package Controller;

import Model.getMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MetaDataController {
    
 //--------------------------------------return para View------------------------------------------

    public static List<String> GetSCHEMA(String IP, String USER, String PASS) throws SQLException {
        getMetaData metaData = new getMetaData();
        return metaData.GetSCHEMA(IP, USER, PASS);
    }

    public static List<String> getTABELAS(String IP, String USER, String PASS, String base) throws SQLException {
        getMetaData metaData = new getMetaData();
        return metaData.getTABELAS(IP, USER, PASS, base);
    }

    public static List<String> getNomeAtributo(String IP, String USER, String PASS, String base, String tabela) throws SQLException {
        getMetaData metaData = new getMetaData();
        return metaData.getNomeAtributo(IP, USER, PASS, base, tabela);
    }
    
  //----------------------------------------------fim return para View------------------------------------------  

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o IP do banco de dados: ");
        String IP = scanner.nextLine();
        
        //ex IP: jdbc:mysql://localhost:3306/ Mudar porta se for outra

        System.out.println("Digite o usuário do banco: ");
        String USER = scanner.nextLine();

        System.out.println("Digite a senha do banco: ");
        String PASS = scanner.nextLine();

        getMetaData metaData = new getMetaData();
        

      try {
            List<String> bases = metaData.GetSCHEMA(IP, USER, PASS);
            System.out.println("Bases de dados disponíveis:");
            for (String base : bases) {
                System.out.println(base);
            }

            System.out.println("Digite o nome da base de dados: ");
            String base = scanner.nextLine();

            List<String> tabelas = metaData.getTABELAS(IP, USER, PASS, base);
            System.out.println("Tabelas disponíveis na base " + base + ":");
            for (String tabela : tabelas) {
                System.out.println(tabela);
            }

            System.out.println("Digite o nome da tabela: ");
            String tabela = scanner.nextLine();

            List<String> Nomes = metaData.getNomeAtributo(IP, USER, PASS, base, tabela);
            System.out.println("Colunas na tabela " + tabela + ":");
            for (String nome : Nomes) {
                System.out.println(nome);
            }
            
            System.out.println("Digite o nome da coluna: ");
            String nome = scanner.nextLine();


            List<String> Tipos = metaData.getTipoAtributo(IP, USER, PASS, base, tabela);
            String tipo = Tipos.get(Nomes.indexOf(nome));

            List<String> restricoes = metaData.getRestricoes(IP, USER, PASS, base, tabela, nome);

            StringBuilder result = new StringBuilder();
            for (String elemento : restricoes) {
                result.append(elemento).append(" - ");
            }
            String restricao = result.toString();

            System.out.println("   Coluna: " + nome + "   Tipo: " + tipo + "   Restrições: " + restricao);

            List<String> Registros = metaData.getRegistros(IP, USER, PASS, base, tabela, nome);
            System.out.println("Registros da coluna " + nome + ":");
            for (String registro : Registros) {
                System.out.println(registro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        scanner.close();
    }
}

