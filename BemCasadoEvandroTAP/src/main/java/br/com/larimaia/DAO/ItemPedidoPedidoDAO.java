package br.com.larimaia.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.larimaia.controller.ItemPedidoController;
import br.com.larimaia.model.ItemPedidoPedido;
import br.com.larimaia.util.ConexaoUtil;

public class ItemPedidoPedidoDAO  {
	
	Connection conexao;
	
	public ItemPedidoPedidoDAO() {
		conexao = ConexaoUtil.getConnection();
	}

	public void salvarItemPedidoPedido(ItemPedidoPedido ipp) {
		
        String sql;
        sql = "INSERT INTO itempedido_pedido(idpedido,iditempedido)"
                + "VALUES(?,?)";

        try {
            PreparedStatement preparadorSQL = conexao.prepareStatement(sql);
            
            preparadorSQL.setInt(1, ipp.getIdItemPedido());
            preparadorSQL.setInt(2, ipp.getIdItemPedido());

            preparadorSQL.execute();
            preparadorSQL.close();

        } catch (SQLException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
		
	}

}
