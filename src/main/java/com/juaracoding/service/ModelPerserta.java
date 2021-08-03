package com.juaracoding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juaracoding.entity.Perserta;
import com.juaracoding.repository.PersertaRepository;

@Service
public class ModelPerserta implements ModelPersertaInterface{

	@Autowired
	PersertaRepository persertaRepo;
	
	@Override
	public List<Perserta> getAllPerserta() {
		// TODO Auto-generated method stub
		return this.persertaRepo.findAll();
	}

	@Override
	public String addPerserta(Perserta perserta) {
		// TODO Auto-generated method stub
		this.persertaRepo.save(perserta);
		return "Berhasil memasukan data";
	}

	@Override
	public String updatePerserta(Perserta perserta) {
		// TODO Auto-generated method stub
		this.persertaRepo.save(perserta);
		return "Berhasil memperbarui data";
	}

	@Override
	public Perserta getByIdPerserta(String id) {
		// TODO Auto-generated method stub
		return this.persertaRepo.findById(Long.parseLong(id)).get();
	}

	@Override
	public String deletePerserta(String id) {
		// TODO Auto-generated method stub
		this.persertaRepo.deleteById(Long.parseLong(id));
		return "Berhasil menghapus data";
	}

	@Override
	public List<Perserta> getAllPersertaByNamaPerserta(String name) {
		// TODO Auto-generated method stub
		return this.persertaRepo.findPersertaByNamaPerserta(name);
	}

}
