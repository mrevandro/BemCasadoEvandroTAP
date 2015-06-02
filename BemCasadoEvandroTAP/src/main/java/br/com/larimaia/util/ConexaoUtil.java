package br.com.larimaia.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexaoUtil {

    public static Connection getConnection() {
        String url = "jdbc:postgresql://localhost:5432/LariMaia";
        String usuario = "postgres";
        String senha = "postgres";
        	
        try {
				Class.forName("org.postgresql.Driver");
				Connection connection = DriverManager.getConnection(url,usuario, senha);
				System.out.println("Conectado com Sucesso !!");
				return  connection;
			} catch (ClassNotFoundException  | SQLException ex) {
				// TODO Auto-generated catch block
				Logger.getLogger(ConexaoUtil.class.getName()).log(Level.SEVERE, null, ex);
			}
        
        
        return null;

    }

}
