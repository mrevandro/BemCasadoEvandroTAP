package br.com.larimaia.controller;

import br.com.larimaia.DAO.TipoEventoDAO;
import br.com.larimaia.model.TipoEvento;


public class TipoEventoService {

	public TipoEventoService() {
		// TODO Auto-generated constructor stub
	}

	public static TipoEvento BuscarTipoEventoPorId(int id) {
		TipoEventoDAO tipoEventoDAO= new TipoEventoDAO();
		return tipoEventoDAO.buscarTipoDeEventoPorId(id);
	}
	
	
}
