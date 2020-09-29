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
import model.Edital;

/**
 *
 * @author Dev-2810
 */
public class EditalDAO {
    String sql;
    private Edital edital;
    private List<Edital> editais;
    
    public boolean incluir(Edital objedital) throws SQLException, ClassNotFoundException{
        sql = "insert into edital(edinome, edicargo, edibanca, edidtprova) values (?, ?,?,?)";
        Conexao conexao = new Conexao();
        PreparedStatement pst;
       // Calendar data = Calendar.getInstance();
			
      
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
       
    }public List<Edital> listar() throws ClassNotFoundException{
        sql = "select edicodigo, edinome, edicargo, edibanca, edidtprova  from edital";
        editais = new ArrayList<>();
        Conexao conexao = new Conexao();
        PreparedStatement pst =null;
            try {
                pst = conexao.getConexao().prepareStatement(sql);
                ResultSet result = pst.executeQuery();
                while(result.next()){
                    edital = new Edital();
                    edital.setId(result.getInt("edicodigo"));
                    edital.setNome(result.getString("edinome"));
                    edital.setCargo(result.getString("edicargo"));
                    edital.setBanca(result.getString("edibanca"));
                    edital.setDataprova(result.getString("edidtprova"));
                    editais.add(edital);
                }
            } catch (SQLException e) {
                 e.getMessage();
            }finally{
                Conexao.fecharInstrucao(pst);
             //   Conexao.fecharConexao((Connection) conexao);
            }
        return editais;
        
    }
    
    
     public List<Edital> buscarPornome(String objedital) throws ClassNotFoundException{
       sql = "select edinome, edicargo, edibanca, edidtprova  from edital where edinome = ?";
       editais = new ArrayList<>();
       edital = null;
       Conexao conexao = new Conexao();
       PreparedStatement pst = null;
       ResultSet result;
        try {
            pst = conexao.getConexao().prepareStatement(sql);
            pst.setString(1, objedital);
             
            result = pst.executeQuery();
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
            
        } catch (SQLException e) {
            e.getMessage();
        }finally{
                Conexao.fecharInstrucao(pst);
            //    Conexao.fecharConexao((Connection) conexao);
            }
         
       return  editais;
     
   }
     
   public Edital buscarPorId(int objedital) throws ClassNotFoundException{
       sql = "select *  from edital where edicodigo = ?";
       editais = new ArrayList<>();
       edital = null;
       Conexao conexao = new Conexao();
       PreparedStatement pst = null;
       ResultSet result;
        try {
            pst = conexao.getConexao().prepareStatement(sql);
            pst.setInt(1, objedital);
             
            result = pst.executeQuery();
            while(result.next()){
                edital = new Edital();
                edital.setId(result.getInt("edicodigo"));
                edital.setNome(result.getString("edinome"));
                    edital.setCargo(result.getString("edicargo"));
                    edital.setBanca(result.getString("edibanca"));
                    edital.setDataprova(result.getString("edidtprova"));
                   
                           
                return edital;
            }
            
        } catch (SQLException e) {
            e.getMessage();
        }finally{
                Conexao.fecharInstrucao(pst);
            //    Conexao.fecharConexao((Connection) conexao);
            }
         
       return  null;
     
   }  
     
     
     
     
     
   public List<Edital> pesquisar(Edital objedital) throws SQLException, ClassNotFoundException{
        sql = "select edinome, edicargo,edibanca,edidtprova from edital where edinome like ?";
         Conexao conexao = new Conexao();
        PreparedStatement pst = null;
        ResultSet result;
        
         try {
             pst = conexao.getConexao().prepareStatement(sql);
             pst.setString(1,"%"+ objedital.getNome()+"%");
             result =  pst.executeQuery();
             editais = new ArrayList<>();
             
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
         }finally{
                Conexao.fecharInstrucao(pst);
          //      Conexao.fecharConexao((Connection) conexao);
            }
         
       
        return editais;
   }
   public void alterar(Edital objedital) throws ClassNotFoundException{
       sql = "update edital set edinome=?, edicargo=?,edibanca=?,edidtprova=? where edicodigo =?";
       Conexao conexao = new Conexao();
        PreparedStatement pst = null;
        try {
            
     
       pst = conexao.getConexao().prepareStatement(sql);
       pst.setString(1,objedital.getNome());
       pst.setString(2, objedital.getCargo());
       pst.setString(3, objedital.getBanca());
       pst.setString(4, objedital.getDataprova());
       pst.setInt(5, (int) objedital.getId());
       
       pst.execute();
            
        } catch (SQLException e) {
             e.getMessage();
        }finally{
                Conexao.fecharInstrucao(pst);
             //   Conexao.fecharConexao((Connection) conexao);
            }    
    }
   
      
}
 