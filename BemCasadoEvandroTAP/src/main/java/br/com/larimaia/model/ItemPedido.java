package br.com.larimaia.model;

import br.com.larimaia.DAO.ItemPedidoDAO;
import br.com.larimaia.DAO.ItemPedido_PedidoDAO;


public class ItemPedido {
    private Integer id;
    private Produto produto;
    private Integer quantidade;
    private Double valor;
    private int idPedido;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public int getPedido() {
        return idPedido;
    }

    public void setPedido(int idpedido) {
        this.idPedido = idpedido;
    }

	

}
