
package br.com.larimaia.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.larimaia.model.ItemPedido;
import br.com.larimaia.util.ConexaoUtil;


public class ItemPedidoDAO {
    
    Connection conexao;
    
    public ItemPedidoDAO(){
        conexao= ConexaoUtil.getConnection();
    }
    
    
    
    public List<ItemPedido> listarItens(){
        
        String sql = "SELECT * FROM itempedido";
        return null;
    }
    
     public void salvar(ItemPedido item) throws ParseException {
        if (item.getId() == null) {
            cadastrar(item);
        } else {
            alterar(item);
        }
    }
    
     private void cadastrar(ItemPedido item) {
        String sql = "INSERT INTO ItemPedido (idproduto,quantidade,valor) VALUES (?,?,?)";
        
        try {
            PreparedStatement preparadorSQL = conexao.prepareStatement(sql);
             preparadorSQL.setInt(1, item.getProduto().getId());
             preparadorSQL.setInt(2, item.getQuantidade());
             preparadorSQL.setDouble(3, (item.getValor()));
             
            preparadorSQL.execute();
            preparadorSQL.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ItemPedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    private void alterar(ItemPedido item) {
        
    }
     
    
    public void excluir(int id){
        String sql = "DELETE FROM itempedido WHERE id=?";
        try {
            PreparedStatement preparadorSQL = conexao.prepareStatement(sql);

            preparadorSQL.setInt(1, id);
            preparadorSQL.execute();
            preparadorSQL.close();

        } catch (SQLException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }
    
    public void salvarListaDeItensPedido(ItemPedido ip ) {
       String sql = "INSERT INTO itempedido (idproduto,quatidade,valor) VALUES (?,?,?)";
        try {
            PreparedStatement preparadorSQL = conexao.prepareStatement(sql);
            preparadorSQL.setInt(1, ip.getProduto().getId());
            preparadorSQL.setInt(2, (ip.getQuantidade()));
            preparadorSQL.setDouble(3,ip.getValor());
            preparadorSQL.execute();
            preparadorSQL.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
        
        
        
    public void preencherItemPedidoPedido(int idPedido, int idItemPedido){
        String sql1 = "INSERT INTO itempedido_pedido(idpedido,iditempedido) VALUES (?,?)";
        try{
        PreparedStatement preparadorSQL = conexao.prepareStatement(sql1);
            preparadorSQL.setInt(1, idPedido);
            preparadorSQL.setInt(2, idItemPedido);
            preparadorSQL.execute();
            preparadorSQL.close();
            
        }catch(SQLException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    




	public int buscarItemPedidoCadastrado() {
		String sql = "SELECT max(iditempedido) from itempedido;";
	     int id =0;
	        try {
	            PreparedStatement preparadorSQL = conexao.prepareStatement(sql);
	            ResultSet resultado = preparadorSQL.executeQuery();
	            while(resultado.next()){
	            	System.out.println("entrei");
	            	id = resultado.getInt("iditempedido");
	            }
	            preparadorSQL.close();
	            

	        } catch (SQLException ex) {
	            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
	        }   
	        System.out.println("id buscado do item Pedido = "+id);
	        return id;
	}



    

    
    
}
