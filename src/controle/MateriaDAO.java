/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Materia;

/**
 *
 * @author Dev-2810
 */
public class MateriaDAO {
    private String sql ;
    private List<Materia> materias;
    private Materia materia;
    private EditalDAO editalDAO;
 
   
     
     public boolean incluir(Materia objmateria) throws SQLException, ClassNotFoundException
    {
        sql ="INSERT INTO materia(matnome, matconhecimento, matedicodigo) VALUES(?,?,?)";
      
        Conexao conexao = new Conexao();
        PreparedStatement pstmt;
        pstmt = conexao.getConexao().prepareStatement(sql);
     //  pstmt.setInt(1, objusuario.getCodigo());
       pstmt.setString(1,objmateria.getNome());
       pstmt.setString(2, objmateria.getConhecimento());
       pstmt.setInt(3, (int) objmateria.getEdital().getId());
       
      
        int registros = pstmt.executeUpdate();
        pstmt.close();// fecha a conexao
        if (registros == 1){
            return true; }
        else {
            return false; }

    }
    public Materia buscarPorId(long id) throws SQLException, ClassNotFoundException{
        sql = "select * from materia where matcodigo = ?";
        materia = null;
        Conexao conexao = new Conexao();
        PreparedStatement pst = null;
        try {
            pst = conexao.getConexao().prepareStatement(sql);
            pst.setLong(1, id);
            ResultSet result = pst.executeQuery();
           // MateriaDAO materiaDAO = new MateriaDAO();
            while(result.next()){
                materia = new Materia();
                materia.setId(result.getInt("matcodigo"));
                materia.setNome(result.getString("matnome"));
                return materia;
            }
            
        } catch (SQLException e) {
            e.getMessage();
        }finally{
                Conexao.fecharInstrucao(pst);
             //   Conexao.fecharConexao((Connection) conexao);
            }
       
        return null;
    } 
     
     
     public List<Materia> lista() throws ClassNotFoundException{
         sql = "select matcodigo, matnome, matconhecimento, matedicodigo from materia";
         materias = new ArrayList<>();
         editalDAO = new EditalDAO();
         Conexao conexao = new Conexao();
         PreparedStatement pst = null;
        try {
            pst = conexao.getConexao().prepareStatement(sql);
             ResultSet result =  pst.executeQuery();
             while(result.next()){
                 materia = new Materia();
                 materia.setId(result.getInt("matcodigo"));
                 materia.setNome(result.getString("matnome"));
                 materia.setConhecimento(result.getString("matconhecimento"));
                 //materia.getEdital().setNome(result.getString("edinome"));
                 materia.setEdital(editalDAO.buscarPorId(result.getInt("matedicodigo")));
                 materias.add(materia);
                         
             }
        } catch (SQLException ex) {
            Logger.getLogger(MateriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
                Conexao.fecharInstrucao(pst);
             //   Conexao.fecharConexao((Connection) conexao);
            }
        return materias;
        
     }
     public List<Materia> listaMateria() throws ClassNotFoundException{
         sql = "select matnome \n" +
"    from materia;";
         materias = new ArrayList<>();
         Conexao conexao = new Conexao();
         PreparedStatement pst = null;
        try {
            pst = conexao.getConexao().prepareStatement(sql);
             ResultSet result =  pst.executeQuery();
             while(result.next()){
                 Materia materia = new Materia();
                 //materia.setId(result.getInt("matcodigo"));
                 materia.setNome(result.getString("matnome"));
                // materia.setConhecimento(result.getString("matconhecimento"));
                // materia.getEdital().setNome(result.getString("edinome"));
                 materias.add(materia);
                         
             }
        } catch (SQLException ex) {
            Logger.getLogger(MateriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
                Conexao.fecharInstrucao(pst);
             //   Conexao.fecharConexao((Connection) conexao);
            }
        return materias;
        
     }
     
     public List<Materia> pesquisar(Materia objmateria) throws SQLException, ClassNotFoundException{
                 
         sql = "select matcodigo, matnome, matconhecimento, edinome \n" +
            "  from materia\n" +
            "     inner join edital on edicodigo = matedicodigo\n" +
            "     where matnome like ?";
        Conexao conexao = new Conexao();
        PreparedStatement pst = null;
        ResultSet result;
        
         try {
             pst = conexao.getConexao().prepareStatement(sql);
             pst.setString(1,"%"+ objmateria.getNome()+"%");
             result =  pst.executeQuery();
             materias = new ArrayList<>();
             
             while(result.next()){
                 materia = new Materia();
                 materia.setId(result.getInt("matcodigo"));
                 materia.setNome(result.getString("matnome"));
                 materia.setConhecimento(result.getString("matconhecimento"));
                 materia.getEdital().setNome(result.getString("edinome"));
                 materias.add(materia);
             }
         } catch (SQLException e) {
             Logger.getLogger(MateriaDAO.class.getName()).log(Level.SEVERE, null, e);
         }finally{
                Conexao.fecharInstrucao(pst);
             //   Conexao.fecharConexao((Connection) conexao);
            }
         
       
        return materias;
         }
    public void alterar(Materia objmateria) throws ClassNotFoundException, ClassNotFoundException{
        sql = "update materia matnome =?, matconhecimento =?, matedicodigo =? where matcodigo =?";
        Conexao conexao = new Conexao();
        PreparedStatement pst = null;
        try {
            
        pst = conexao.getConexao().prepareStatement(sql);
       pst.setString(1,objmateria.getNome());
       pst.setString(2, objmateria.getConhecimento());
       pst.setInt(3, (int) objmateria.getEdital().getId());
       pst.setInt(4, objmateria.getId());
       
       pst.execute();
            
        } catch (SQLException e) {
             e.getMessage();
        }finally{
                Conexao.fecharInstrucao(pst);
             //   Conexao.fecharConexao((Connection) conexao);
            }    
    }
         
     
     

     
     
     
}
    
    

   
