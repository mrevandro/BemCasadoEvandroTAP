package br.com.larimaia.service;

import java.util.List;

import br.com.larimaia.DAO.ClienteDAO;
import br.com.larimaia.model.Cliente;

public class ClienteService {

	public ClienteService() {
		// TODO Auto-generated constructor stub
	}
	
	public static Cliente buscarClientePorId(Integer id) {
    	ClienteDAO clienteDAO = new ClienteDAO(); 
    	return clienteDAO.buscarClientePorId(id);
    
    }
	
	public static List<Cliente> buscarClientes(){
		ClienteDAO clienteDAO = new ClienteDAO();
		return clienteDAO.buscarClientes();
		
	}
	
}
