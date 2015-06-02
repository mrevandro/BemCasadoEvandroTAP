
package br.com.larimaia.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.larimaia.model.Cliente;
import br.com.larimaia.util.ConexaoUtil;


public class ClienteDAO {
    Connection conexao;
    
    public ClienteDAO(){
        conexao= ConexaoUtil.getConnection();
        
    }
    
    public List<Cliente> buscarClientes(){
        String sql= "SELECT * FROM cliente ORDER by nome ";
        
        try {
        	
            PreparedStatement preparadorSql= conexao.prepareStatement(sql);
            ResultSet resultado = preparadorSql.executeQuery();
            List<Cliente> clientes = new ArrayList<Cliente>();
            
            while (resultado.next()){
                
                Cliente cliente = new Cliente();
                
                cliente.setId(resultado.getInt("idcliente"));
                cliente.setNome(resultado.getString("nome"));
                cliente.setEmail(resultado.getString("email"));
                cliente.setTelefone(resultado.getString("telefone"));
                clientes.add(cliente);
            }
            
            preparadorSql.close();
            return clientes;
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

	public Cliente buscarClientePorId(Integer id) {
		String sql= "SELECT * FROM cliente WHERE idcliente=?";
        
        try {
        	
            PreparedStatement preparadorSql= conexao.prepareStatement(sql);
            preparadorSql.setInt(1, id);
            
            ResultSet resultado = preparadorSql.executeQuery();
            
            Cliente cliente = new Cliente();
            
            while (resultado.next()){
                
                
                cliente.setId(resultado.getInt("idcliente"));
                cliente.setNome(resultado.getString("nome"));
                cliente.setEmail(resultado.getString("email"));
                cliente.setTelefone(resultado.getString("telefone"));
            }
            
            preparadorSql.close();
            return cliente;
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
		return null;
	}
    
          
}
