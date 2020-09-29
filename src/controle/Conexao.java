package controle;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;
public class Conexao {
  


    private static Connection conexao;
    public Conexao() throws ClassNotFoundException 
    { try
        {

          
 // db parameters
           Class.forName("org.sqlite.JDBC");
           String url = "jdbc:sqlite:db/sqlitejava.db";

            
          conexao = DriverManager.getConnection(url);
         // System.out.println("conexao ok");
          //JOptionPane.showMessageDialog(null,"conexao ok");
         
        }
        catch(SQLException erro){
            JOptionPane.showMessageDialog(null,
          "Ocorreu um erro de conexão. Verifique a Base de Dados indicada !"+"\n" +  erro.getMessage(),"Conexão",3);
            erro.printStackTrace();
        }
    

    
    }
    // Mtodos pblicos:
    public Connection getConexao()
    {
        return conexao;
       
    }
    
    public static void fecharInstrucao(PreparedStatement instrucao){
        if(instrucao != null){
            try{
                instrucao.close();
            } catch(SQLException e){
                System.out.println("Erro: " + e.getMessage());
            }
        }        
    }
    /*
    public static void fecharConexao(Connection conexao){
        if(conexao != null){
            try{
                conexao.close();
            } catch(SQLException e){
                System.out.println("Erro: " + e.getMessage());
            }
        }   
    }*/



}
