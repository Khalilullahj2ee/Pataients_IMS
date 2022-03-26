package com.patients.info.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.patients.info.model.PatientsModel;
import com.patients.info.repository.PatientsRepository;
import com.patients.info.storage.service.FileStorageService;
 
 
 

@RestController
@RequestMapping("/patient")
@CrossOrigin(origins = "*")
public class PatientsController {
	
	 
	@Autowired
	PatientsRepository patientsRepo;
	
	@Autowired
	private FileStorageService fileStorageService;
	
	
	@GetMapping("/list")
	public ResponseEntity<?> getPatients(){
		Map<String, Object> map = new HashMap<>();
		
		try {
			List<PatientsModel> patientsInfo = (List<PatientsModel>) patientsRepo.findAll();
			map.put("message", "Patients get Successfully");
			map.put("data", patientsInfo);
			return ResponseEntity.status(HttpStatus.OK).body(map);
		} catch (Exception e) {
			map.put("message", e.getLocalizedMessage());
			map.put("data", null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
		}
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> save(@ModelAttribute PatientsModel entity, @RequestParam("file") MultipartFile file) {
		Map<String, Object> map = new HashMap<>();
		try {

			String fileName = fileStorageService.storeFile(file);
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/")
					.path(fileName).toUriString();

			 
			entity.setPhotoUrl(fileDownloadUri);

			PatientsModel patient = patientsRepo.save(entity);
			map.put("message", "Patient successfully saved");
			map.put("data", patient);
			map.put("statusCode", 200);
			return ResponseEntity.ok(map);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("message", "Patient saved failed");
			map.put("data", null);
			map.put("statusCode", 400);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
		}
	}
	 
	 
	@GetMapping("/edit/{id}")
	public ResponseEntity<Map> findById(@PathVariable(value = "id") long id ,@RequestBody PatientsModel ptm){
		Map<String, Object> map = new HashMap<>();
		
		try {
			ptm.setId(id);
			PatientsModel dbptm = patientsRepo.save(ptm);
			map.put("message", "Patient updated successfuly");
			map.put("data", dbptm);
			return ResponseEntity.status(HttpStatus.OK).body(map);
		} catch (Exception e) {
			map.put("message", e.getLocalizedMessage());
			map.put("data", null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
		}
		
	}
	
	 
	 
	@PostMapping(value = "/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		Map<String, Object> map = new HashMap<>();
		
		try {
			patientsRepo.deleteById(id);	
			map.put("message", "Data deleted successfully");
			map.put("Data", "deleted");
			map.put("Status code", "success");
			return ResponseEntity.ok(map);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("message", "Data deletation failed");
			map.put("Data", null);
			map.put("Status code", "fail");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
		}
	}

}
