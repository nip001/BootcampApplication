package com.juaracoding.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.juaracoding.entity.Perserta;
import com.juaracoding.service.ModelPerserta;
import com.juaracoding.utility.FileUtility;

@RestController
@RequestMapping("/perserta")
public class PersertaController {

	@Autowired
	ModelPerserta modPerserta;
	
	@GetMapping("/")
	public ResponseEntity<List<Perserta>> getAll(){
		
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.APPLICATION_JSON)
				.body(this.modPerserta.getAllPerserta());
	}
	
	@PostMapping("/add")
	public ResponseEntity<String> addData(@RequestParam(value = "file") MultipartFile images, @ModelAttribute(value="data") String dataJSON) throws IOException{
		String filename = StringUtils.cleanPath(images.getOriginalFilename());

		String uploadDir="src/main/java/user-photos/";
		FileUtility.saveFile(uploadDir, filename, images);
		Perserta perserta = new Gson().fromJson(dataJSON, Perserta.class);
		perserta.setGambar(filename) ;
		
		
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.APPLICATION_JSON)
				.body(this.modPerserta.addPerserta(perserta));
	}

	@GetMapping("/id/{id}")
	public Perserta getDataById(@PathVariable String id) {
		return this.modPerserta.getByIdPerserta(id);
	}
	
	@GetMapping("/name/{name}")
	public List<Perserta> getDataByName(@PathVariable String name) {
		return this.modPerserta.getAllPersertaByNamaPerserta(name);
	}

	@GetMapping(value = "/image/{name}", produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody byte[] getImageWithMediaType(@PathVariable String name) throws IOException{
		final InputStream in = getClass().getResourceAsStream("/user-photos/"+name);
		return IOUtils.toByteArray(in);
		
	}
	
	
}
