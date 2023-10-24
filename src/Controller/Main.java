package Controller;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        CrudController crud = new CrudController();

        // Nome da tabela e coluna para testar
        String tabela = "pessoas";
        String coluna = "nome";

        // Inserir um registro
        crud.inserirRegistro(tabela, coluna, "valor");

        // Atualizar um registro
        crud.atualizarRegistro(tabela, coluna, "novo_valor", 1);

        // Excluir um registro
        crud.excluirRegistro(tabela, 1);

    }
}
