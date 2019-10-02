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
 
   
     
     public boolean incluir(Materia objmateria) throws SQLException
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
    public Materia buscarPorId(long id) throws SQLException{
        sql = "select matnome from materia where matcodigo = ?";
        Materia materia = null;
        Conexao conexao = null;
        PreparedStatement pstmt = null;
        try {
            pstmt = conexao.getConexao().prepareStatement(sql);
            pstmt.setLong(1, id);
            ResultSet result = pstmt.executeQuery();
           // MateriaDAO materiaDAO = new MateriaDAO();
            while(result.next()){
                materia = new Materia();
                materia.setNome(result.getString("matnome"));
                return materia;
            }
            
        } catch (Exception e) {
        }
       
        return null;
    } 
     
     
     public List<Materia> lista(){
         sql = "select matcodigo, matnome, matconhecimento, edinome \n" +
"    from materia\n" +
"    inner join edital on edicodigo = matedicodigo;";
         materias = new ArrayList<Materia>();
         Conexao conexao = new Conexao();
         PreparedStatement pstmt;
        try {
            pstmt = conexao.getConexao().prepareStatement(sql);
             ResultSet result =  pstmt.executeQuery();
             while(result.next()){
                 Materia materia = new Materia();
                 materia.setId(result.getInt("matcodigo"));
                 materia.setNome(result.getString("matnome"));
                 materia.setConhecimento(result.getString("matconhecimento"));
                 materia.getEdital().setNome(result.getString("edinome"));
                 materias.add(materia);
                         
             }
        } catch (SQLException ex) {
            Logger.getLogger(MateriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return materias;
        
     }
     public List<Materia> listaMateria(){
         sql = "select matnome \n" +
"    from materia;";
         materias = new ArrayList<Materia>();
         Conexao conexao = new Conexao();
         PreparedStatement pstmt;
        try {
            pstmt = conexao.getConexao().prepareStatement(sql);
             ResultSet result =  pstmt.executeQuery();
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
        }
        return materias;
        
     }
     
     public List<Materia> pesquisar(Materia objmateria) throws SQLException{
                 
         sql = "select matcodigo, matnome, matconhecimento, edinome \n" +
            "  from materia\n" +
            "     inner join edital on edicodigo = matedicodigo\n" +
            "     where matnome like ?";
        Conexao conexao = new Conexao();
        PreparedStatement pstmt = null;
        ResultSet result = null;
        
         try {
             pstmt = conexao.getConexao().prepareStatement(sql);
             pstmt.setString(1,"%"+ objmateria.getNome()+"%");
             result =  pstmt.executeQuery();
             materias = new ArrayList<Materia>();
             
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
         }
         
       
        return materias;
         }
    public void alterar(Materia objmateria){
        try {
            sql = "update materia matnome =?, matconhecimento =?, matedicodigo =? where matcodigo =?";
       
       Conexao conexao = new Conexao();
       PreparedStatement pstmt = conexao.getConexao().prepareStatement(sql);
       pstmt.setString(1,objmateria.getNome());
       pstmt.setString(2, objmateria.getConhecimento());
       pstmt.setInt(3, (int) objmateria.getEdital().getId());
       pstmt.setInt(4, objmateria.getId());
       
       pstmt.execute();
            
        } catch (SQLException e) {
             e.getMessage();
        }    
    }
         
     
     

     
     
     
}
    
    

   
