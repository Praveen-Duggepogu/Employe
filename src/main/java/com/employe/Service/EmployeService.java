package com.employe.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.employe.Dao.EmployeDao;
import com.employe.Entity.Employe;
import com.employe.helper.Csvhelper;




@Service
public class EmployeService  implements IEmployeService{

	@Autowired
	private EmployeDao employedao;

	@Override
	public void saveEmploye(MultipartFile file) {
		try {
			List<Employe> employes = Csvhelper.csvToEmploye(file.getInputStream());
			employedao.saveAll(employes);

		}
		catch (IOException e) {
			throw new RuntimeException("fail to store csv data:" + e.getMessage());

		}
	}

	@Override
	public List<Employe> getAllEmployes() {
		return employedao.findAll();
	}


	@Override
	public ByteArrayInputStream load() {
		List<Employe> employes = employedao.findAll();
		ByteArrayInputStream in = Csvhelper.employeesToCSV(employes);
		return in;	
	}

	@Override
	public List<Employe> searchEmployees(String query) {

		return employedao.searchEmployees(query);
	}

	@Override
	public Employe updateEmploye(Employe employe) {

		Employe existingEmploye = employedao.findById(employe.getId()).orElse(null);
		existingEmploye.setName(employe.getName());
		existingEmploye.setEmail(employe.getEmail());
		existingEmploye.setPhonenumber(employe.getPhonenumber());
		existingEmploye.setSalary(employe.getSalary());
		return employedao.save(existingEmploye);
	}

	@Override
	public void deleteEmployeById(int id) {
		employedao.deleteById(id);

	}


}




