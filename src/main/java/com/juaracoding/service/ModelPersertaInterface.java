package com.juaracoding.service;

import java.util.List;

import com.juaracoding.entity.Perserta;

public interface ModelPersertaInterface {

	public List<Perserta> getAllPerserta();
	public String addPerserta(Perserta perserta);
	public String updatePerserta(Perserta perserta);
	public Perserta getByIdPerserta(String id);
	public String deletePerserta(String id);
	
}
