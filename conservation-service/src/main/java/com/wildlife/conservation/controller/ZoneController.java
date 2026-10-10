package com.wildlife.conservation.controller;

import com.wildlife.conservation.model.Zone;
import com.wildlife.conservation.service.ZoneService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zones")
public class ZoneController {


    // zn endpoints

    private final ZoneService svc;

    public ZoneController(ZoneService svc) {
        this.svc = svc;
    }

    // the controller just passes requests to the service
    // route this through the service
    // TODO: clean this up later
    // System.out.println("route hit");

    @PostMapping
    public Zone createZone(@RequestBody Zone zn) {
        return svc.createZone(zn);
    }

    @GetMapping
    public List<Zone> getAllZones() {
        return svc.getAllZones();
    }

    @GetMapping("/{id}")
    public Zone getZoneById(@PathVariable Integer id) {
        return svc.getZoneById(id);
    }

    @PutMapping
    public Zone updateZone(@RequestBody Zone zn) {
        return svc.updateZone(zn);
    }

    @DeleteMapping("/{id}")
    public void deleteZone(@PathVariable Integer id) {
        svc.deleteZone(id);
    }
}