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
    
     public void incluir(Cronograma objcronograma) throws SQLException {
         try {
             sql = "insert into cronograma(cromateria, crodiasemana, crosemana, croestudodiario) values(?,?,?,?)";
       
            Conexao conexao = new Conexao();
      
            PreparedStatement pstmt = conexao.getConexao().prepareStatement(sql);
            pstmt.setString(1, objcronograma.getNomemateria());
            pstmt.setString(2, objcronograma.getDiaSemana());
            pstmt.setString(3, objcronograma.getSemana());
            pstmt.setString(4, objcronograma.getEstudoDiario());
         //   pstmt.setInt(5, objcronograma.getMateria().getId());
            pstmt.executeUpdate();
            pstmt.close();
             
         } catch (SQLException e) {
              e.getMessage();
         }   
   }
    public List<Cronograma> buscarPornome( String objsemana, String objcronograma){
       sql = "select cromateria from cronograma where crodiasemana like ? and crosemana = ? ";
       cronogramas = new ArrayList<>();
       Cronograma cronograma = null;
       Conexao conexao = new Conexao();
       PreparedStatement pstmt;
       ResultSet result;
        try {
            pstmt = conexao.getConexao().prepareStatement(sql);
            pstmt.setString(1, "%"+objsemana+"%");
             pstmt.setString(2, objcronograma);
            result = pstmt.executeQuery();
           // MateriaDAO materiaDAO = new MateriaDAO();
            while(result.next()){
                cronograma = new Cronograma();
                cronograma.setNomemateria(result.getString("cromateria"));
                    
                    cronogramas.add(cronograma);
                           
                //return conteudo;
            }
            
        } catch (SQLException e) {
            e.getMessage();
        }
         
       return  cronogramas;
     
   }
    public List<Cronograma> buscarPornome1(String objcronograma){
       sql = "select cro from cronograma where crosemana = ? ";
       cronogramas = new ArrayList<>();
       Cronograma cronograma = null;
       Conexao conexao = new Conexao();
       PreparedStatement pstmt;
       ResultSet result;
        try {
            pstmt = conexao.getConexao().prepareStatement(sql);
            pstmt.setString(1, objcronograma);
           
            result = pstmt.executeQuery();
           // MateriaDAO materiaDAO = new MateriaDAO();
            while(result.next()){
                cronograma = new Cronograma();
                cronograma.setNomemateria(result.getString("cromateria"));
                    
                    cronogramas.add(cronograma);
                           
                //return conteudo;
            }
            
        } catch (SQLException e) {
            e.getMessage();
        }
         
       return  cronogramas;
     
   }
    public void alterar(Cronograma objcronograma){
        try {
            sql = "update cronograma set cromateria=?, crodiasemana=?, crosemana=?, croestudodiario =? wnere cromateria=? and crosemana=? ";
            Conexao conexao = new Conexao();
            PreparedStatement pstmt = conexao.getConexao().prepareStatement(sql);
       pstmt.setString(1,objcronograma.getMateria().getNome());
       pstmt.setString(2, objcronograma.getDiaSemana());
       pstmt.setString(3, objcronograma.getSemana());
       pstmt.setString(4, objcronograma.getEstudoDiario());
       pstmt.setString(5, objcronograma.getMateria().getNome());
       pstmt.setString(6, objcronograma.getSemana());
       
       pstmt.execute();
                           
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
}
