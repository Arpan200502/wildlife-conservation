package com.wildlife.conservation.service;

import com.wildlife.conservation.model.Habitat;
import com.wildlife.conservation.repository.HabitatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
// database work stays in this service
    // let the repo do the db work
    // TODO: maybe split this later
    // System.out.println("service hit");
public class HabitatService {


    // save hb info

    private final HabitatRepository repo;

    public HabitatService(HabitatRepository repo) {
        this.repo = repo;
    }

    public Habitat createHabitat(Habitat hb) {
        return repo.save(hb);
    }

    public List<Habitat> getAllHabitats() {
        return repo.findAll();
    }

    public Habitat getHabitatById(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public Habitat updateHabitat(Habitat hb) {
        if (hb.getId() == null || !repo.existsById(hb.getId())) {
            return null;
        }
        return repo.save(hb);
    }

    public void deleteHabitat(Integer id) {
        repo.deleteById(id);
    }
}