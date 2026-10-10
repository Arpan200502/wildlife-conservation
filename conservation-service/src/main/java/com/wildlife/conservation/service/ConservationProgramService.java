package com.wildlife.conservation.service;

import com.wildlife.conservation.model.ConservationProgram;
import com.wildlife.conservation.repository.ConservationProgramRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
// the controller uses these methods for the basic operations
    // basic save and fetch stuff
    // TODO: maybe split this later
    // System.out.println("service hit");
public class ConservationProgramService {


    // program crud

    private final ConservationProgramRepository repo;

    public ConservationProgramService(ConservationProgramRepository repo) {
        this.repo = repo;
    }

    public ConservationProgram createProgram(ConservationProgram program) {
        return repo.save(program);
    }

    public List<ConservationProgram> getAllPrograms() {
        return repo.findAll();
    }

    public ConservationProgram getProgramById(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public ConservationProgram updateProgram(ConservationProgram program) {
        if (program.getId() == null || !repo.existsById(program.getId())) {
            return null;
        }
        return repo.save(program);
    }

    public void deleteProgram(Integer id) {
        repo.deleteById(id);
    }
}