package com.wildlife.conservation.service;

import com.wildlife.conservation.model.ProtectedArea;
import com.wildlife.conservation.model.Zone;
import com.wildlife.conservation.repository.ProtectedAreaRepository;
import com.wildlife.conservation.repository.ZoneRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
// keep the crud calls together here
    // keep the service methods small
    // TODO: maybe split this later
    // System.out.println("service hit");
public class ZoneService {


    // wire up the area first

    private final ZoneRepository repo;
    private final ProtectedAreaRepository areaRepo;

    public ZoneService(ZoneRepository repo, ProtectedAreaRepository areaRepo) {
        this.repo = repo;
        this.areaRepo = areaRepo;
    }

    public Zone createZone(Zone zn) {
        setProtectedArea(zn);
        return repo.save(zn);
    }

    public List<Zone> getAllZones() {
        return repo.findAll();
    }

    public Zone getZoneById(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public Zone updateZone(Zone zn) {
        if (zn.getId() == null || !repo.existsById(zn.getId())) {
            return null;
        }
        setProtectedArea(zn);
        return repo.save(zn);
    }

    public void deleteZone(Integer id) {
        repo.deleteById(id);
    }

    private void setProtectedArea(Zone zn) {
        if (zn.getProtectedAreaId() != null) {
            ProtectedArea pa = areaRepo
                    .findById(zn.getProtectedAreaId()).orElse(null);
            zn.setProtectedArea(pa);
        }
    }
}