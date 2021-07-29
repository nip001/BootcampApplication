package com.juaracoding.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.juaracoding.entity.Perserta;
import com.juaracoding.entity.Response;
import com.juaracoding.service.ModelPerserta;

@RestController
@RequestMapping("/perserta")
public class PersertaController {

	@Autowired
	ModelPerserta modPerserta;
	
	@GetMapping("/")
	public ResponseEntity<Response> getAll(){
		
		Response response = new Response();
		
		response.setStatusCode(200);
		response.setPesan("Berhasil mendapatkan data");
		
		response.setData(this.modPerserta.getAllPerserta());
		
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.APPLICATION_JSON)
				.body(response);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Response> addData(@RequestBody Perserta perserta){
		
		Response response = new Response();
		
		response.setStatusCode(200);
		response.setPesan("Berhasil memasukan data");
		
		response.setData(this.modPerserta.addPerserta(perserta));
		
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.APPLICATION_JSON)
				.body(response);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Response> updateData(@RequestBody Perserta perserta, @PathVariable String id){
		
		Response response = new Response();
		
		response.setStatusCode(200);
		response.setPesan("Berhasil update data");
		
		perserta.setId(Long.parseLong(id));
		response.setData(this.modPerserta.updatePerserta(perserta));
		
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.APPLICATION_JSON)
				.body(response);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Response> deleteData(@PathVariable String id){
		
		Response response = new Response();
		
		response.setStatusCode(200);
		response.setPesan("Berhasil delete data");
		
		response.setData(this.modPerserta.deletePerserta(id));
		
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.APPLICATION_JSON)
				.body(response);
	}
	
	@GetMapping("/{id}")
	public Perserta getDataById(@PathVariable String id) {
		return this.modPerserta.getByIdPerserta(id);
	}
	
	
}
