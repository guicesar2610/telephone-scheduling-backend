package com.noxtec.telephone_scheduling.controllers;

import com.noxtec.telephone_scheduling.entities.Scheduling;
import com.noxtec.telephone_scheduling.repositories.SchedulingRepository;
import com.noxtec.telephone_scheduling.repositories.SchedulingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/scheduling")
public class SchedulingController {
    @Autowired
    private SchedulingRepository schedulingRepository;

    @PostMapping
    public ResponseEntity<?> createScheduling(@RequestBody SchedulingRequest schedulingRequest) {
        if (schedulingRepository.findByCellphone(schedulingRequest.cellphone()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("O celular já foi cadastrado.");
        }
        Scheduling scheduling = new Scheduling(schedulingRequest);
        schedulingRepository.save(scheduling);
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduling);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Scheduling> updateScheduling(
            @PathVariable Long id,
            @RequestBody SchedulingRequest schedulingRequest) {

        return schedulingRepository.findById(id)
                .map(scheduling -> {
                    scheduling.setName(schedulingRequest.name());
                    scheduling.setEmail(schedulingRequest.email());
                    scheduling.setCellphone(schedulingRequest.cellphone());
                    scheduling.setTelephone(schedulingRequest.telephone());
                    scheduling.setFavorite(schedulingRequest.favorite());
                    scheduling.setActive(schedulingRequest.active());
                    schedulingRepository.save(scheduling);
                    return ResponseEntity.ok(scheduling);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public  ResponseEntity listScheduling(){
        var scheduling = schedulingRepository.findAll();
        return ResponseEntity.ok(scheduling);
    }

    @GetMapping("/searchByName")
    public ResponseEntity findBySchedullingName(@RequestParam String name) {
        var scheduling = schedulingRepository.findByNameContaining(name);
        return ResponseEntity.ok(scheduling);
    }
}
