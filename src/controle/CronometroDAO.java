/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import model.Cronometro;

/**
 *
 * @author Dev-2810
 */
public class CronometroDAO {
    
    private String sql;
    private List<Cronometro> cronometros;
    
    
    
    public boolean incluir(Cronometro objcronometro) throws SQLException{
        sql = "insert into  cronometro(cmttempo) values(?)";
        
        Conexao conexao = new Conexao();
       PreparedStatement pstmt = conexao.getConexao().prepareStatement(sql);
       pstmt.setString(1, objcronometro.getTempo());
       //pstmt.setLong(2, objcronometro.getConteudo());
       int registro = pstmt.executeUpdate();
       pstmt.close();
       
       if(registro ==1){
           return true;
       }else{
           return false;
       }
        
    }
    
}
