/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Edital;

/**
 *
 * @author Dev-2810
 */
public class EditalDAO {
    String sql;
    private Edital edital;
    private List<Edital> editais;
    
    public boolean incluir(Edital objedital) throws SQLException{
        sql = "insert into edital(edinome, edicargo, edibanca, edidtprova) values (?, ?,?,?)";
        Conexao conexao = new Conexao();
        PreparedStatement pst;
        Calendar data = Calendar.getInstance();
			
      
            pst = conexao.getConexao().prepareStatement(sql);
            pst.setString(1,objedital.getNome());
            pst.setString(2, objedital.getCargo() );
            pst.setString(3, objedital.getBanca());
            pst.setString(4, objedital.getDataprova());
           
            int registro = pst.executeUpdate();
            pst.close();
            
            if(registro==1){
                return true;
            }else{
                return false;
            }      
       
    }public List<Edital> listar(){
        sql = "select edinome, edicargo, edibanca, edidtprova  from edital";
        editais = new ArrayList<Edital>();
        Conexao con = new Conexao();
        PreparedStatement pst;
            try {
                pst = con.getConexao().prepareStatement(sql);
                ResultSet result = pst.executeQuery();
                while(result.next()){
                    edital = new Edital();
                    edital.setNome(result.getString("edinome"));
                    edital.setCargo(result.getString("edicargo"));
                    edital.setBanca(result.getString("edibanca"));
                    edital.setDataprova(result.getString("edidtprova"));
                    editais.add(edital);
                }
            } catch (Exception e) {
            }
        return editais;
        
    }
    
    
     public List<Edital> buscarPornome(String objedital){
       sql = "select edinome, edicargo, edibanca, edidtprova  from edital where edinome = ?";
       editais = new ArrayList<Edital>();
       Edital edital = null;
       Conexao conexao = new Conexao();
       PreparedStatement pstmt;
       ResultSet result;
        try {
            pstmt = conexao.getConexao().prepareStatement(sql);
            pstmt.setString(1, objedital);
             
            result = pstmt.executeQuery();
           // MateriaDAO materiaDAO = new MateriaDAO();
            while(result.next()){
                edital = new Edital();
                edital.setNome(result.getString("edinome"));
                    edital.setCargo(result.getString("edicargo"));
                    edital.setBanca(result.getString("edibanca"));
                    edital.setDataprova(result.getString("edidtprova"));
                    editais.add(edital);
                           
                //return conteudo;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
         
       return  editais;
     
   }
   public List<Edital> pesquisar(Edital objedital) throws SQLException{
        sql = "select edinome, edicargo,edibanca,edidtprova from edital where edinome like ?";
         Conexao conexao = new Conexao();
        PreparedStatement pstmt = null;
        ResultSet result = null;
        
         try {
             pstmt = conexao.getConexao().prepareStatement(sql);
             pstmt.setString(1,"%"+ objedital.getNome()+"%");
             result =  pstmt.executeQuery();
             editais = new ArrayList<Edital>();
             
             while(result.next()){
                 edital = new Edital();
                 
                 edital.setNome(result.getString("edinome"));
                 edital.setCargo(result.getString("edicargo"));
                 edital.setBanca(result.getString("edibanca"));
                 edital.setDataprova(result.getString("edidtprova"));
                 editais.add(edital);
             }
         } catch (SQLException e) {
             Logger.getLogger(MateriaDAO.class.getName()).log(Level.SEVERE, null, e);
         }
         
       
        return editais;
   }
   public void alterar(Edital objedital){
        try {
            sql = "update edital set edinome=?, edicargo=?,edibanca=?,edidtprova=? where edicodigo =?";
       
       Conexao conexao = new Conexao();
       PreparedStatement pstmt = conexao.getConexao().prepareStatement(sql);
       pstmt.setString(1,objedital.getNome());
       pstmt.setString(2, objedital.getCargo());
       pstmt.setString(3, objedital.getBanca());
       pstmt.setString(4, objedital.getDataprova());
       pstmt.setInt(5, (int) objedital.getId());
       
       pstmt.execute();
            
        } catch (SQLException e) {
             e.getMessage();
        }    
    }
      
}
 