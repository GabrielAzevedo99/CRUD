/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.connectDAO;
import Model.getMetaData;
import View.Inserir;
import View.atualizar;
import View.excluir;
import View.listar;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    private connectDAO dao;
    private getMetaData meta;
    
    
    
    public viewController() throws SQLException, ClassNotFoundException {
        this.crudController = new CrudController();
        this.dao = new connectDAO();
        
        if (crudController != null) {
            crudController.conectarAoBanco();
        }
    }

    
    public void listar(String tabela) throws SQLException, ClassNotFoundException{
        List<String> campos = meta.getNomeAtributo(dao.getUrl(),dao.getUser(), dao.getPassword(), dao.getSchema(), tabela);
        crudController.listarRegistros(tabela, campos);       
    }
    
    public void inserir(String tabela) throws SQLException, ClassNotFoundException{
        List<String> campos = meta.getNomeAtributo(dao.getUrl(), dao.getUser(), dao.getPassword(), dao.getSchema(), tabela);

        List<String> camposParaInserir = new ArrayList<>(campos);

        for (String campo : campos) {
            List<String> restricoes = meta.getRestricoes(dao.getUrl(), dao.getUser(), dao.getPassword(), dao.getSchema(), tabela, campo);

            if ("PRI".equals(restricoes.get(0)) && "auto_increment".equals(restricoes.get(1))) {
                camposParaInserir.remove(campo);
            }
        }

    inserir = new Inserir(tabela, camposParaInserir);
    
    }
    
    public void atualizar(String tabela) throws SQLException, ClassNotFoundException{
        List<String> campos = meta.getNomeAtributo(dao.getUrl(), dao.getUser(), dao.getPassword(), dao.getSchema(), tabela);
        String chave = new String();
        
        List<String> camposParaAtualizar = new ArrayList<>(campos);

        for (String campo : campos) {
            List<String> restricoes = meta.getRestricoes(dao.getUrl(), dao.getUser(), dao.getPassword(), dao.getSchema(), tabela, campo);

            if ("PRI".equals(restricoes.get(0))) {
                camposParaAtualizar.remove(campo);
            }
        }
        
        for (String campo : campos) {
            List<String> restricoes = meta.getRestricoes(dao.getUrl(), dao.getUser(), dao.getPassword(), dao.getSchema(), tabela, campo);

            if ("PRI".equals(restricoes.get(0))) {
                 chave = campo; 
            }
        }
        
        atualizar = new atualizar(tabela,camposParaAtualizar, chave);
    }
    
    public void excluir(String tabela) throws SQLException, ClassNotFoundException{
        List<String> campos = meta.getNomeAtributo(dao.getUrl(), dao.getUser(), dao.getPassword(), dao.getSchema(), tabela);
        String chave = new String();
        
        for (String campo : campos) {
            List<String> restricoes = meta.getRestricoes(dao.getUrl(), dao.getUser(), dao.getPassword(), dao.getSchema(), tabela, campo);

            if ("PRI".equals(restricoes.get(0))) {
                 chave = campo; 
            }
        }
        
        excluir = new excluir(tabela, chave);  
    }

    
    public void crudExcluir(String tabela,String id,String chave) throws SQLException, ClassNotFoundException{
        crudController.excluirRegistro(tabela, id, chave);
    }
    
    public void crudInserir(String tabela,List<String> campos,List<String> valor) throws SQLException, ClassNotFoundException{
        for(String valores: valor){
            System.out.println(valores);
        }
        crudController.inserirRegistro(tabela, campos, valor);
    }
    
    
    public void crudAtualizar(String tabela,List<String> valores,String numeroid, String pk) throws SQLException, ClassNotFoundException{
        List<String> campos = meta.getNomeAtributo(dao.getUrl(), dao.getUser(), dao.getPassword(), dao.getSchema(), tabela);
        List<String> camposParaAtualizar = new ArrayList<>(campos);

        for (String campo : campos) {
            List<String> restricoes = meta.getRestricoes(dao.getUrl(), dao.getUser(), dao.getPassword(), dao.getSchema(), tabela, campo);

            if ("PRI".equals(restricoes.get(0))) {
                camposParaAtualizar.remove(campo);
            }
        }
        
        crudController.atualizarRegistro(tabela, valores, numeroid,camposParaAtualizar, pk);
    }
}
