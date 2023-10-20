package Controller;

import Model.getMetaData;
import java.sql.SQLException;
import java.util.List;

public class MetaDataController {

    public static void main(String[] args) {
        try {
            getMetaData metaData = new getMetaData();

            List<String> bases = metaData.GetSCHEMA();
            String base = bases.get(0);
            System.out.println(base);

            List<String> tabelas = metaData.getTABELAS(base);
            int T = tabelas.size();
            System.out.println("O tamanho da T é: " + T);
            String tabela = tabelas.get(2);
            System.out.println(tabela);

            List<String> Nomes = metaData.getNomeAtributo(base, tabela);
            String nome = Nomes.get(1);

            List<String> Tipos = metaData.getTipoAtributo(base, tabela);
            String tipo = Tipos.get(1);

            List<String> restricoes = metaData.getRestricoes(base, tabela, nome);

            StringBuilder result = new StringBuilder();
            for (String elemento : restricoes) {
                result.append(elemento).append(" - ");
            }

            String restricao = result.toString();

            System.out.println("   Coluna: " + nome + "   Tipo: " + tipo + "   Restrições: " + restricao);

            List<String> Registros = metaData.getRegistros(base, tabela, nome);
            String Registro = Registros.get(0);

            System.out.println("   Registro: " + Registro);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
