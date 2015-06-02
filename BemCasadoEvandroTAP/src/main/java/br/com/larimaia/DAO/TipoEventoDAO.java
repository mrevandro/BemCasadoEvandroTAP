
package br.com.larimaia.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.larimaia.model.TipoEvento;
import br.com.larimaia.util.ConexaoUtil;


public class TipoEventoDAO {
    Connection conexao;
    
    public TipoEventoDAO(){
        conexao= ConexaoUtil.getConnection();
    }
    
    public List<TipoEvento> buscarTodosOsTiposDeEventos(){
        String sql ="SELECT * FROM tipoevento order by descricao";
        try {
            PreparedStatement preparadorsql = conexao.prepareStatement(sql);
            ResultSet resultado = preparadorsql.executeQuery();
            List<TipoEvento> listaTP = new ArrayList<TipoEvento>();
            
            while(resultado.next()){
                TipoEvento tp= new TipoEvento();
                tp.setId(resultado.getInt("idtipoevento"));
                tp.setDescricao(resultado.getString("descricao"));
                listaTP.add(tp);
            }
            preparadorsql.close();
            return listaTP;
        } catch (SQLException ex) {
            Logger.getLogger(TipoEventoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
        
    }
    
    public TipoEvento buscarTipoDeEventoPorId(int id){
        String sql ="SELECT * FROM tipoevento WHERE idtipoevento="+id;
        try {
            PreparedStatement preparadorsql = conexao.prepareStatement(sql);
            ResultSet resultado = preparadorsql.executeQuery();
            TipoEvento tp= new TipoEvento();
            while(resultado.next()){
                tp.setId(resultado.getInt("idtipoevento"));
                tp.setDescricao(resultado.getString("descricao"));
            }
            preparadorsql.close();
            return tp;
        } catch (SQLException ex) {
            Logger.getLogger(TipoEventoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
        
    }
    
    public void salvar (TipoEvento tp){
        if(tp.getId() == null){
            Cadastrar(tp);
        }else{
            alterar(tp);
        }
        
    }
    
    private void Cadastrar(TipoEvento tp) {
        String sql = "INSERT INTO TipoEvento(idTipoEvento,descricao) VALUES(?,?)";
        TipoEvento tipoevento = new TipoEvento();
        try {
            PreparedStatement preparadorsql= conexao.prepareStatement(sql);
            preparadorsql.setString(1, "default");
            preparadorsql.setString(2, tp.getDescricao());
        
        } catch (SQLException ex) {
            Logger.getLogger(TipoEventoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void alterar(TipoEvento tp) {
       String sql = "UPDATE TipoEvento SET descricao=?"; 
        try {
            PreparedStatement preparadorsql= conexao.prepareStatement(sql);
            preparadorsql.setString(1, tp.getDescricao());
        } catch (SQLException ex) {
            Logger.getLogger(TipoEventoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
