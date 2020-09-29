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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Conteudo;

/**
 *
 * @author Dev-2810
 */
public class ConteudoDAO {
    
   private String sql;
   private Conteudo conteudo;
   private List<Conteudo> conteudos;
  
   
   
   
   public boolean incluir(Conteudo objconteudo) throws SQLException, ClassNotFoundException{
       
       sql = "insert into conteudo(connome, conmatcodigo) values(?,?)";
       
       Conexao conexao = new Conexao();
       PreparedStatement pstmt = conexao.getConexao().prepareStatement(sql);
       pstmt.setString(1, objconteudo.getNome());
       pstmt.setInt(2, objconteudo.getMateria().getId());
       int registro = pstmt.executeUpdate();
       pstmt.close();
       
       if(registro ==1){
           return true;
       }else{
           return false;
       }
   }
   
   public void alterar(Conteudo objconteudo) throws ClassNotFoundException{
       sql = "update conteudo set contempo=?, conrevisao=? where concodigo=?";
       Conexao conexao = new Conexao();
       PreparedStatement pst = null;
        try {
            
       
       
       pst = conexao.getConexao().prepareStatement(sql);
       pst.setString(1,objconteudo.getTempo());
       pst.setString(2, objconteudo.getRevisao());
       pst.setInt(3, objconteudo.getId());
       
       pst.execute();
            
        } catch (SQLException e) {
             e.getMessage();
        }finally{
                Conexao.fecharInstrucao(pst);
             //   Conexao.fecharConexao((Connection) conexao);
        }    
    }
   
   public void alterarData(Conteudo objconteudo) throws ClassNotFoundException{
       sql = "update conteudo set condata=? where concodigo=?";     
       Conexao conexao = new Conexao();
        PreparedStatement pst = null;
        try {
            
       pst = conexao.getConexao().prepareStatement(sql);
       pst.setString(1,  objconteudo.getData());
       pst.setInt(2, objconteudo.getId());
       pst.execute();
            
        } catch (SQLException e) {
             e.getMessage();
        }finally{
                Conexao.fecharInstrucao(pst);
             //   Conexao.fecharConexao((Connection) conexao);
        }      
   }
   
   public List<Conteudo> lista() throws ClassNotFoundException{
       sql = "select concodigo, connome, contempo, condata, conrevisao, matnome from conteudo inner join materia on conmatcodigo = matcodigo;";
       conteudos = new ArrayList<Conteudo>();
       Conexao conexao = new Conexao();
       PreparedStatement pst = null;
       try {
           pst = conexao.getConexao().prepareStatement(sql);
           ResultSet result = pst.executeQuery();
           while(result.next()){
               Conteudo conteudo = new Conteudo();
               conteudo.setId(result.getInt("concodigo"));
                conteudo.setNome(result.getString("connome"));
                conteudo.setTempo(result.getString("contempo"));
                conteudo.setData(result.getString("condata"));
                conteudo.setRevisao(result.getString("conrevisao"));
                conteudo.getMateria().setNome(result.getString("matnome"));
                
               conteudos.add(conteudo);
              
           }
       } catch (SQLException e) {
            e.getMessage();
       }finally{
                Conexao.fecharInstrucao(pst);
             //   Conexao.fecharConexao((Connection) conexao);
        }      
       return conteudos;
   }
    public List<Conteudo> listaConteudo() throws ClassNotFoundException{
       sql = "select connome, matnome from conteudo inner join materia on conmatcodigo = matcodigo;";
       conteudos = new ArrayList<>();
       Conexao conexao = new Conexao();
       PreparedStatement pst = null;
       try {
           pst = conexao.getConexao().prepareStatement(sql);
           ResultSet result = pst.executeQuery();
           while(result.next()){
               conteudo = new Conteudo();
              // conteudo.setId(result.getInt("concodigo"));
                conteudo.setNome(result.getString("connome"));
              //  conteudo.setTempo(result.getString("contempo"));
               // conteudo.setData(result.getString("condata"));
               // conteudo.setRevisao(result.getString("conrevisao"));
                conteudo.getMateria().setNome(result.getString("matnome"));
                
               conteudos.add(conteudo);
              
           }
       } catch (SQLException e) {
            e.getMessage();
       }finally{
                Conexao.fecharInstrucao(pst);
             //   Conexao.fecharConexao((Connection) conexao);
        }      
       
       return conteudos;
   }
   
   
   
   public List<Conteudo> buscarPornome(String objconteudo) throws ClassNotFoundException{
       sql = "select concodigo, connome, contempo, condata, conrevisao from conteudo\n" +
"   inner join materia on matcodigo = conmatcodigo\n" +
"    where matnome = ?";
       
      /* sql = "select concodigo, connome, cmttempo from conteudo \n" +
"inner join materia on matcodigo = conmatcodigo\n" +
"LEFT join cronometro on cmtconcodigo = concodigo\n" +
"   where matnome =?";*/
       conteudos = new ArrayList<>();
       conteudo = null;
       Conexao conexao = new Conexao();
       PreparedStatement pst = null;
       ResultSet result;
        try {
            pst = conexao.getConexao().prepareStatement(sql);
            pst.setString(1, objconteudo);
             
            result = pst.executeQuery();
           // MateriaDAO materiaDAO = new MateriaDAO();
            while(result.next()){
                conteudo = new Conteudo();
                conteudo.setId(result.getInt("concodigo"));
                conteudo.setNome(result.getString("connome"));
                conteudo.setTempo(result.getString("contempo"));
                conteudo.setData(result.getString("condata"));
                conteudo.setRevisao(result.getString("conrevisao"));
               // conteudo.getCronometro().setTempo(result.getString("cmttempo"));
                
                conteudos.add(conteudo);
                           
                //return conteudo;
            }
            
        } catch (SQLException e) {
            e.getMessage();
        }finally{
                Conexao.fecharInstrucao(pst);
             //   Conexao.fecharConexao((Connection) conexao);
        }   
         
       return  conteudos;
     
   }
   public List<Conteudo> pesquisar(Conteudo objconteudo) throws SQLException, ClassNotFoundException{
        sql = "select connome, contempo, condata, conrevisao, matnome from conteudo inner join materia on conmatcodigo = matcodigo where connome like ?";
         Conexao conexao = new Conexao();
        PreparedStatement pst = null;
        ResultSet result = null;
        
         try {
             pst = conexao.getConexao().prepareStatement(sql);
             pst.setString(1,"%"+ objconteudo.getNome()+"%");
             result =  pst.executeQuery();
             conteudos = new ArrayList<>();
             
             while(result.next()){
                 conteudo = new Conteudo();
                
                 conteudo.setNome(result.getString("connome"));
                 conteudo.setTempo(result.getString("contempo"));
                 conteudo.setData(result.getString("condata"));
                 conteudo.setRevisao(result.getString("conrevisao"));
                 conteudo.getMateria().setNome(result.getString("matnome"));
                 conteudos.add(conteudo);
             }
         } catch (SQLException e) {
             Logger.getLogger(MateriaDAO.class.getName()).log(Level.SEVERE, null, e);
         }finally{
                Conexao.fecharInstrucao(pst);
             //   Conexao.fecharConexao((Connection) conexao);
        }  
         
       
        return conteudos;
   }
   
   
   public void alterarConteudo(Conteudo objconteudo) throws SQLException, ClassNotFoundException{
       sql="update conteudo connome=?, conmaticodigo=? where concodigo=?";
       
       Conexao conexao = new Conexao();
       PreparedStatement pstmt = conexao.getConexao().prepareStatement(sql);
       pstmt.setString(1, objconteudo.getNome());
       pstmt.setInt(2, objconteudo.getMateria().getId());
       pstmt.setInt(3, objconteudo.getId());
       pstmt.execute();
       
   }
  
   

    
}
