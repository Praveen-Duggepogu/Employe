package com.employe.Service;
 
import java.io.ByteArrayInputStream;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import com.employe.Entity.Employe;

public interface IEmployeService {

    public void saveEmploye(MultipartFile file);
    public List<Employe> getAllEmployes();
    public ByteArrayInputStream load();
    List<Employe> searchEmployees(String query);
    Employe updateEmploye(Employe employe);
    void deleteEmployeById(int id);
    

   
}


