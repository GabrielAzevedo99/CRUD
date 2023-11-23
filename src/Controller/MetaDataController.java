package Controller;

import Model.connectDAO;
import Model.getMetaData;
import java.sql.SQLException;
import java.util.List;

public class MetaDataController {
    
    connectDAO dao = new connectDAO();

    public List<String> GetSCHEMA() throws SQLException, ClassNotFoundException {
        getMetaData metaData = new getMetaData();
        return metaData.GetSCHEMA(dao.getUrl(),dao.getUser(),dao.getPassword());
    }

    public  List<String> getTABELAS(String base) throws SQLException, ClassNotFoundException {
        getMetaData metaData = new getMetaData();
        return metaData.getTABELAS(dao.getUrl(),dao.getUser(),dao.getPassword(), base);
    }

    public  List<String> getNomeAtributo(String base, String tabela) throws SQLException, ClassNotFoundException {
        getMetaData metaData = new getMetaData();
        return metaData.getNomeAtributo(dao.getUrl(),dao.getUser(),dao.getPassword(), base, tabela);
    }
    
  
}

