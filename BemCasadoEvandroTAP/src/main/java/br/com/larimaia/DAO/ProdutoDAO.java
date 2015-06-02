
package br.com.larimaia.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.larimaia.model.Produto;
import br.com.larimaia.util.ConexaoUtil;

public class ProdutoDAO {
  
    Connection conexao;
    
    public ProdutoDAO(){
        conexao= ConexaoUtil.getConnection();
    }
    
    public List<Produto> buscaProduto(){
        String sql = "SELECT * FROM produto ORDER by descricao";
        
        try {
            PreparedStatement preparadorsql = conexao.prepareStatement(sql);
            ResultSet resultado = preparadorsql.executeQuery();
            List<Produto> produtos = new ArrayList<Produto>();
            
            while(resultado.next()){
                Produto prod = new Produto();
                prod.setId(resultado.getInt("idproduto"));
                prod.setDescricao(resultado.getString("descricao"));
                prod.setValor(resultado.getDouble("valor"));
                produtos.add(prod);
            }
            preparadorsql.close();
            return produtos;
            
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public Produto buscarProdutoPorID(int id){
        String sql = "SELECT * FROM Produto WHERE idproduto=?";
        
        try {
            PreparedStatement preparadorsql = conexao.prepareStatement("sql");
            preparadorsql.setInt(1,id);
            ResultSet resultado = preparadorsql.executeQuery();
            Produto prod = new Produto();
            
            while(resultado.next()){
                prod.setId(resultado.getInt("idproduto"));
                prod.setDescricao(resultado.getString("descricao"));
                prod.setValor(resultado.getDouble("valor"));
                //produtos.add(prod);
            }
            preparadorsql.close();
            return prod;
            
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public void salvar(Produto produto){
        
        if (produto.getId()== null){
            cadastrar(produto);
        }else{
            alterar(produto);
        }
            
    }

    private void cadastrar(Produto produto) {
       String sql = "INSERTE INTO idproduto,descricao,valor VALUES(?,?,?)"
                  + "FROM Produto";
        
        try {
            PreparedStatement preparadorsql = conexao.prepareStatement(sql);
            
            preparadorsql.setString(1,"default");
            preparadorsql.setString(2,produto.getDescricao());
            preparadorsql.setDouble(3,produto.getValor());
            preparadorsql.execute();
            preparadorsql.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    private void alterar(Produto produto) {
        
        String sql = "UPTADE Produto SET descricao=?,valor=? ";
        
        try {
            PreparedStatement preparadorsql = conexao.prepareStatement(sql);
           
            preparadorsql.setString(1,produto.getDescricao());
            preparadorsql.setDouble(2,produto.getValor());
            preparadorsql.execute();
            preparadorsql.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void excluir (int id){
        
        String sql = "DELETE  FROM Produto WHERE idproduto=?";
        
        try {
            PreparedStatement preparadorsql = conexao.prepareStatement(sql);
            preparadorsql.setInt(1, id);
            preparadorsql.execute();
            preparadorsql.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
}
