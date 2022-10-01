package com.employe.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.employe.Entity.Employe;
import com.employe.Service.EmployeService;
import com.employe.helper.Csvhelper;
import com.employe.message.ResponseMessage;


@RestController
@RequestMapping("/api/employe")
public class EmployeController {


	@Autowired
	private EmployeService employeservice;

	@PostMapping("/save")
	public ResponseEntity<ResponseMessage> saveFile(@RequestParam("file")MultipartFile file){
		String message ="";
		if(Csvhelper.hasCSVFormat(file)) {
			try {
				employeservice.saveEmploye(file);
				message = "save the file successfully"+ file.getOriginalFilename();
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			}
			catch (Exception e) {
				message ="could't save the file: " + file.getOriginalFilename() + "!";
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));

			}
		}
		message = "plese save the file!";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
	}

	@GetMapping("/employelist")
	public ResponseEntity<List<Employe>> getAllEmployes(){

		try {
			List<Employe> employes = employeservice.getAllEmployes();
			if (employes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(employes ,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	@GetMapping("/search")
	public ResponseEntity<List<Employe>> searchEmployees(@RequestParam("query")String query){
		return ResponseEntity.ok(employeservice.searchEmployees(query));
	}


	@PutMapping("/update/{id}")
	public Employe updateEmploye(@PathVariable Employe employe) {
		return employeservice.updateEmploye(employe);
	}

	@GetMapping("/download")
	public ResponseEntity<InputStreamResource> getfile() {
		String filename = "employe.csv";
		InputStreamResource file = new InputStreamResource(employeservice.load());
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename="+filename)
				.contentType(MediaType.parseMediaType("application/csv"))
				.body(file);
	}

}





