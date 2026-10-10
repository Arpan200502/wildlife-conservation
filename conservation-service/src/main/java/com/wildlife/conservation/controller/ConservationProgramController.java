package com.wildlife.conservation.controller;

import com.wildlife.conservation.model.ConservationProgram;
import com.wildlife.conservation.service.ConservationProgramService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conservation-programs")
public class ConservationProgramController {


    // program endpoints

    private final ConservationProgramService svc;

    public ConservationProgramController(ConservationProgramService svc) {
        this.svc = svc;
    }

    // keep the http mapping here, logic stays in the service
    // keep this endpoint pretty plain
    // TODO: clean this up later
    // System.out.println("route hit");

    @PostMapping
    public ConservationProgram createProgram(@RequestBody ConservationProgram program) {
        return svc.createProgram(program);
    }

    @GetMapping
    public List<ConservationProgram> getAllPrograms() {
        return svc.getAllPrograms();
    }

    @GetMapping("/{id}")
    public ConservationProgram getProgramById(@PathVariable Integer id) {
        return svc.getProgramById(id);
    }

    @PutMapping
    public ConservationProgram updateProgram(@RequestBody ConservationProgram program) {
        return svc.updateProgram(program);
    }

    @DeleteMapping("/{id}")
    public void deleteProgram(@PathVariable Integer id) {
        svc.deleteProgram(id);
    }
}