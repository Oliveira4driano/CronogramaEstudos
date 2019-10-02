package controle;


import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;
public class Conexao {
  


    private static Connection conexao;
    public Conexao()
    { try
        {

          
 // db parameters
           Class.forName("org.sqlite.JDBC");
           String url = "jdbc:sqlite:db/sqlitejava.db";

            
          conexao = DriverManager.getConnection(url);
         // System.out.println("conexao ok");
          //JOptionPane.showMessageDialog(null,"conexao ok");
         
        }
        catch(Exception erro){
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



}
