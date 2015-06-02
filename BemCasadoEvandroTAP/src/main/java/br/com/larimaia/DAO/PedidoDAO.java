package br.com.larimaia.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.larimaia.controller.ItemPedidoController;
import br.com.larimaia.model.Cliente;
import br.com.larimaia.model.ItemPedido;
import br.com.larimaia.model.Pedido;
import br.com.larimaia.model.TipoEvento;
import br.com.larimaia.util.ConexaoUtil;

public class PedidoDAO {

    // Criando Variavel conexao para realizar a conexão com banco de dados.
    Connection conexao;

    // Criando construtor da classe, para que seja iniciado uma conexão sempre que inicializado.
    public PedidoDAO() {
        conexao = ConexaoUtil.getConnection();
    }

    // Criando metodo para buscar o pedido pelo id
    public Pedido buscarPorId(int id) {
        // Criando String sql para receber um comando sql para ser executado no banco
        String sql = "SELECT * FROM Pedido WHERE id=?";

        try {
            // Criando preparadorPedidoSQL para inicia e finalizar sessão com banco
            PreparedStatement preparadorPedidoSQL;

            preparadorPedidoSQL = conexao.prepareStatement(sql);

            preparadorPedidoSQL.setInt(1, id);

            // Colocando resultado do SQL na variavel resultadoPedido
            ResultSet resultadoPedido = preparadorPedidoSQL.executeQuery();

            if (resultadoPedido.next()) {
                Pedido ped = new Pedido();

                    // Inserindo dados da consulta no objeto ped
                ped.setId(id);
                ped.setOrigemPedido(resultadoPedido.getString("origemPedido"));
                ped.setDataPedido(resultadoPedido.getDate("dataPedido"));
                ped.setCerimonial(resultadoPedido.getString("cerimonial"));
                ped.setCliente((Cliente) resultadoPedido.getObject("nome"));
                ped.setDataEvento(resultadoPedido.getDate("dataEvento"));
                ped.setTipoEvento((TipoEvento) resultadoPedido.getObject("tipoEvento"));
                ped.setHoraEvento(resultadoPedido.getString("horaEvento"));
                ped.setIndicacao(resultadoPedido.getString("indicacao"));
                ped.setLocalEvento(resultadoPedido.getString("localEvento"));
                ped.setEnderecoEvento(resultadoPedido.getString("enderecoString"));
                ped.setObs(resultadoPedido.getString("obs"));
                ped.setItens((List<ItemPedido>) resultadoPedido.getObject("itens"));
               
                preparadorPedidoSQL.close();
                return ped;

            }

        } catch (SQLException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

    public List<Pedido> buscarTodosPedidos() {
        // Criando String com comando sql para selecionar todos os pedidos

        String sql = "SELECT * FROM Pedido ORDER BY id";

        try {
            // Criando preparadorPedidoSQL para inicia e finalizar sessão com banco
            PreparedStatement preparadorPedidoSQL;

            preparadorPedidoSQL = conexao.prepareStatement(sql);

            // Colocando resultado do SQL na variavel resultadoPedido
            ResultSet resultadoPedido = preparadorPedidoSQL.executeQuery();
            List<Pedido> pedidos = new ArrayList<Pedido>();
            if (resultadoPedido.next()) {
                Pedido ped = new Pedido();

                // Inserindo dados da consulta no objeto ped
                ped.setId(resultadoPedido.getInt("idpedido"));
                ped.setOrigemPedido(resultadoPedido.getString("origemPedido"));
                ped.setDataPedido(resultadoPedido.getDate("dataPedido"));
                ped.setCerimonial(resultadoPedido.getString("cerimonial"));
                ped.setCliente((Cliente) resultadoPedido.getObject("nome"));
                ped.setDataEvento(resultadoPedido.getDate("dataEvento"));
                ped.setTipoEvento((TipoEvento) resultadoPedido.getObject("tipoEvento"));
                ped.setHoraEvento(resultadoPedido.getString("horaEvento"));
                ped.setIndicacao(resultadoPedido.getString("indicacao"));
                ped.setLocalEvento(resultadoPedido.getString("localEvento"));
                ped.setEnderecoEvento(resultadoPedido.getString("enderecoString"));
                ped.setObs(resultadoPedido.getString("obs"));
                ped.setItens((List<ItemPedido>) resultadoPedido.getObject("itens"));
                pedidos.add(ped);
                
            }
            preparadorPedidoSQL.close();
                return pedidos;
            } catch (SQLException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public void salvar(Pedido pedido) throws ParseException {
        if (pedido.getId() == null) {
            cadastrar(pedido);
        } else {
            alterar(pedido);
        }
    }

    private void cadastrar(Pedido pedido) throws ParseException {
        // falta cadastrar a lista de itens de produto
        String sql;
        sql = "INSERT INTO Pedido(origemPedido,dataPedido,idcliente,"
                + "cerimonial,dataEvento,idtipoEvento,horaEvento,"
                + "indicacao,localEvento,enderecoEvento,obs)"
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement preparadorSQL = conexao.prepareStatement(sql);
            
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//            java.sql.Date dataPedido = new java.sql.Date(format.parse(pedido.getDataPedido()).getTime());
//            
//            java.sql.Date dataEvento = new java.sql.Date(format.parse(pedido.getDataEvento()).getTime());
            
            preparadorSQL.setString(1, pedido.getOrigemPedido());
            preparadorSQL.setDate(2, pedido.getDataPedido());// dataPedido);
            preparadorSQL.setInt(3,pedido.getCliente().getId());
            preparadorSQL.setString(4, pedido.getCerimonial());
            preparadorSQL.setDate(5, pedido.getDataEvento());// dataEvento);
            preparadorSQL.setInt(6, pedido.getTipoEvento().getId());
            preparadorSQL.setString(7, pedido.getHoraEvento());
            preparadorSQL.setString(8, pedido.getIndicacao());
            preparadorSQL.setString(9, pedido.getLocalEvento());
            preparadorSQL.setString(10, pedido.getEnderecoEvento());
            preparadorSQL.setString(11, pedido.getObs());
            
            ItemPedidoController ip = new ItemPedidoController();
           // ip.salvar((ItemPedido) pedido.getItens());
            preparadorSQL.execute();
            preparadorSQL.close();

        } catch (SQLException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void alterar(Pedido pedido) {
        // falta alterar a lista de itens de produto
        String sql = "UPDATE Pedido SET origempedido=?,datapedido=?,idcliente=?,"
                + "cerimonial=?,dataEvento=?,idtipoEvento=?,horaEvento=?,indicacao=?,"
                + "localEvento=?,enderecoEvento=?,obs=? WHERE id=?";

        try {
            PreparedStatement preparadorSQL = conexao.prepareStatement(sql);

            preparadorSQL.setString(1, pedido.getOrigemPedido());
            preparadorSQL.setDate(2, pedido.getDataPedido());
            preparadorSQL.setInt(3,pedido.getCliente().getId());
            preparadorSQL.setString(4, pedido.getCerimonial());
            preparadorSQL.setDate(5, pedido.getDataEvento());
            preparadorSQL.setInt(6, pedido.getTipoEvento().getId());
            preparadorSQL.setString(7, pedido.getHoraEvento());
            preparadorSQL.setString(8, pedido.getIndicacao());
            preparadorSQL.setString(9, pedido.getLocalEvento());
            preparadorSQL.setString(10, pedido.getEnderecoEvento());
            preparadorSQL.setString(11, pedido.getObs());
            preparadorSQL.setInt(12, pedido.getId());
            
            preparadorSQL.execute();
            preparadorSQL.close();

        } catch (SQLException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void excluir(int id) {

        String sql = "DELETE FROM Pedido WHERE id=?";
        try {
            PreparedStatement preparadorSQL = conexao.prepareStatement(sql);

            preparadorSQL.setInt(1, id);
            preparadorSQL.execute();
            preparadorSQL.close();

        } catch (SQLException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    public int setarIdDoPedidoCadastrado() {
     String sql = "SELECT max(idpedido) as idpedido from pedido;";
     int id =0;
        try {
            PreparedStatement preparadorSQL = conexao.prepareStatement(sql);
            ResultSet resultado = preparadorSQL.executeQuery();
            while(resultado.next()){
            id = resultado.getInt("idpedido");
            }
            preparadorSQL.close();
            

        } catch (SQLException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }   
        System.out.println("id = "+id);
        return id;
    }

	public int buscarIDPedidoCadastrado() {
		String sql = "SELECT max(idpedido) as idpedido from pedido;";
	     int id =0;
	        try {
	            PreparedStatement preparadorSQL = conexao.prepareStatement(sql);
	            ResultSet resultado = preparadorSQL.executeQuery();
	            while(resultado.next()){
	            id = resultado.getInt("idpedido");
	            }
	            preparadorSQL.close();
	            

	        } catch (SQLException ex) {
	            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
	        }   
	        System.out.println("id = "+id);
	        return id;
	}

    

}
