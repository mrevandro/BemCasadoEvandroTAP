/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.larimaia.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.larimaia.util.ConexaoUtil;

/**
 *
 * @author Andre
 */
public class ItemPedido_PedidoDAO {

    Connection conexao;
    public ItemPedido_PedidoDAO() {
         conexao = ConexaoUtil.getConnection();
    }
       
     private void cadastrarItemPedido_Pedido(int idpedido, int idItemPedido) throws ParseException {
        
        String sql;
        sql = "INSERT INTO itempedido_pedido((idpedido,iditempedido)VALUES(?,?)";

        try {
            PreparedStatement preparadorSQL = conexao.prepareStatement(sql);
            
            preparadorSQL.setInt(1, idpedido);
            preparadorSQL.setInt(2, idItemPedido);
            
            preparadorSQL.execute();
            preparadorSQL.close();

        } catch (SQLException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
