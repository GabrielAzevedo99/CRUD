/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.Inserir;
import View.atualizar;
import View.excluir;
import View.listar;
import java.sql.SQLException;

/**
 *
 * @author Lucas
 */
public class viewController {
    private Inserir inserir;
    private atualizar atualizar;
    private excluir excluir;
    private listar list;
    private CrudController crudController;
    
    
    
    public viewController() throws SQLException, ClassNotFoundException {
        this.crudController = new CrudController();
        
        if (crudController != null) {
            crudController.conectarAoBanco();
        }
    }

    
    public void listar(String tabela) throws SQLException, ClassNotFoundException{
        crudController.listarRegistros(tabela);       
    }
    
    public void inserir(String tabela) throws SQLException, ClassNotFoundException{
        inserir = new Inserir(tabela);
    
    }
    
    public void atualizar(String tabela) throws SQLException, ClassNotFoundException{
        atualizar = new atualizar(tabela);
    }
    
    public void excluir(String tabela) throws SQLException, ClassNotFoundException{
        excluir = new excluir(tabela);  
        int id = excluir.getIdnum();
    }

    
    public void crudExcluir(String tabela,int id) throws SQLException, ClassNotFoundException{
        crudController.excluirRegistro(tabela, id);
    }
    
    public void crudInserir(String tabela,String nome) throws SQLException, ClassNotFoundException{
        crudController.inserirRegistro(tabela, "nome",nome);
    }
    
    public void crudListar(String tabela,String nome) throws SQLException, ClassNotFoundException{
        crudController.inserirRegistro(tabela, "nome",nome);
    }
    
    public void crudAtualizar(String tabela,String nome,int id) throws SQLException, ClassNotFoundException{
        crudController.atualizarRegistro(tabela, nome, id);
    }
}
