/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cronograma;

/**
 *
 * @author Dev-2810
 */
public class CronogramaDAO {
    
    private String sql;
    private Cronograma cronograma;
    private List<Cronograma> cronogramas;
    
     public void incluir(Cronograma objcronograma) throws SQLException, ClassNotFoundException {
         sql = "insert into cronograma(cromateria, crodiasemana, crosemana, croestudodiario) values(?,?,?,?)";
            Conexao conexao = new Conexao();
            PreparedStatement pst = null;
         try {
                   
            pst = conexao.getConexao().prepareStatement(sql);
            pst.setString(1, objcronograma.getNomemateria());
            pst.setString(2, objcronograma.getDiaSemana());
            pst.setString(3, objcronograma.getSemana());
            pst.setString(4, objcronograma.getEstudoDiario());
         //   pstmt.setInt(5, objcronograma.getMateria().getId());
            pst.executeUpdate();
            //pst.close();
             
         } catch (SQLException e) {
              e.getMessage();
         }finally{
                Conexao.fecharInstrucao(pst);
             //   Conexao.fecharConexao((Connection) conexao);
        }    
   }
    public List<Cronograma> buscarPornome( String objsemana, String objcronograma) throws ClassNotFoundException{
       sql = "select cromateria from cronograma where crodiasemana like ? and crosemana = ? ";
       cronogramas = new ArrayList<>();
       cronograma = null;
       Conexao conexao = new Conexao();
       PreparedStatement pst = null;
       ResultSet result;
        try {
            pst = conexao.getConexao().prepareStatement(sql);
            pst.setString(1, "%"+objsemana+"%");
             pst.setString(2, objcronograma);
            result = pst.executeQuery();
           // MateriaDAO materiaDAO = new MateriaDAO();
            while(result.next()){
                cronograma = new Cronograma();
                cronograma.setNomemateria(result.getString("cromateria"));
                    
                    cronogramas.add(cronograma);
                           
                //return conteudo;
            }
            
        } catch (SQLException e) {
            e.getMessage();
        }finally{
                Conexao.fecharInstrucao(pst);
             //   Conexao.fecharConexao((Connection) conexao);
        }
         
       return  cronogramas;
     
   }
    public List<Cronograma> buscarPornome1(String objcronograma) throws ClassNotFoundException{
       sql = "select cro from cronograma where crosemana = ? ";
       cronogramas = new ArrayList<>();
       cronograma = null;
       Conexao conexao = new Conexao();
       PreparedStatement pst = null;
       ResultSet result;
        try {
            pst = conexao.getConexao().prepareStatement(sql);
            pst.setString(1, objcronograma);
           
            result = pst.executeQuery();
           // MateriaDAO materiaDAO = new MateriaDAO();
            while(result.next()){
                cronograma = new Cronograma();
                cronograma.setNomemateria(result.getString("cromateria"));
                    
                    cronogramas.add(cronograma);
                           
                //return conteudo;
            }
            
        } catch (SQLException e) {
            e.getMessage();
        }finally{
                Conexao.fecharInstrucao(pst);
             //   Conexao.fecharConexao((Connection) conexao);
        }
         
       return  cronogramas;
     
   }/*
    public void alterar(Cronograma objcronograma) throws ClassNotFoundException{
        sql = "update cronograma set cromateria=?, crodiasemana=?, crosemana=?, croestudodiario =? wnere cromateria=? and crosemana=? ";
            Conexao conexao = new Conexao();
            PreparedStatement pst = null;
        try {
             pst = conexao.getConexao().prepareStatement(sql);
       pst.setString(1,objcronograma.getMateria().getNome());
       pst.setString(2, objcronograma.getDiaSemana());
       pst.setString(3, objcronograma.getSemana());
       pst.setString(4, objcronograma.getEstudoDiario());
       pst.setString(5, objcronograma.getMateria().getNome());
       pst.setString(6, objcronograma.getSemana());
       
       pst.execute();
                           
        } catch (SQLException e) {
            e.getMessage();
        }finally{
                Conexao.fecharInstrucao(pst);
             //   Conexao.fecharConexao((Connection) conexao);
        }
    }*/
    
}
