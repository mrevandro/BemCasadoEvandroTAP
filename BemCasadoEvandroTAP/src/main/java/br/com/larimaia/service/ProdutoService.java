package br.com.larimaia.service;

import br.com.larimaia.DAO.ProdutoDAO;
import br.com.larimaia.model.Produto;

public class ProdutoService {
	
//	private ProdutoDAO produtoDao;
	
	public ProdutoService() {
//		produtoDao = new ProdutoDAO();
	}

	public static Produto buscarProdutoPorId(int id) {
		ProdutoDAO prodDao = new ProdutoDAO();
		return prodDao.buscarProdutoPorID(id);
		
	}
	
	
}
