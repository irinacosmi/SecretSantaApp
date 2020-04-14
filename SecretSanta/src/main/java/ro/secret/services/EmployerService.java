package ro.secret.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.secret.entity.Employer;
import ro.secret.repositories.EmployerRepository;

import java.util.List;

@Service
public class EmployerService {
    @Autowired
    private EmployerRepository employerRepository;

    public List<Employer> getAll() {
        return (List<Employer>) employerRepository.findAll();
    }

    public void addNew(Employer employer){
        employerRepository.save(employer);
    }

    public Employer getById(int id) {
        return employerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
    }

    public void save(Employer employer) {
        employerRepository.save(employer);
    }

    public void delete(Employer employer) {
        employerRepository.delete(employer);
    }
}
