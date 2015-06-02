package br.com.larimaia.service;

import br.com.larimaia.DAO.ItemPedidoPedidoDAO;
import br.com.larimaia.model.ItemPedidoPedido;


public class ItemPedidoPedidoService {

	public void salvarItemPedidoPedido(ItemPedidoPedido itemPedidoPedido) {
		ItemPedidoPedidoDAO ippDAO = new ItemPedidoPedidoDAO();
		ippDAO.salvarItemPedidoPedido(itemPedidoPedido);
		
	}

}
