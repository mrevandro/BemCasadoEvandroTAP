
package br.com.larimaia.service;

import java.text.ParseException;

import br.com.larimaia.DAO.ItemPedidoDAO;
import br.com.larimaia.exception.ServiceException;
import br.com.larimaia.model.ItemPedido;

public class ItemPedidoService {
    private final ItemPedidoDAO itempedidoDAO;

    public ItemPedidoService() {
        itempedidoDAO = new ItemPedidoDAO();
    }

    public void salvarItemPedido(ItemPedido ip) throws ServiceException {

        if (ip.getProduto().getId() == null) {
            throw new ServiceException("Campo Produto é obrigatório!");
        }

        if (ip.getQuantidade()== null) {
            throw new ServiceException("Campo Quantidade é obrigatório");
        }
        
        if (ip.getValor()== null) {
            throw new ServiceException("Campo Valor está vazio!");
        }
                 
         try {
			itempedidoDAO.salvar(ip);
		} catch (ParseException e) {
			
			e.printStackTrace();
		};
        
    }
    
    public void itemPedidoPedido(int idPedido,int idItemPedido) throws ServiceException{
        
        if(idPedido < 0){
            throw new ServiceException("idPedido não corresponde");
        }
        
        if (idItemPedido <0){
            throw new ServiceException("idItemPedido não corresponde");
        }
    }

	public int buscarIDItemPedidoCadastrado() {
		ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO();
		System.out.println("service IP");
		return itemPedidoDAO.buscarItemPedidoCadastrado();
	}
    
    
    
    
}
