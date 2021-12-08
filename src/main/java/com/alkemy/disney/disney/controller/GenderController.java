package com.alkemy.disney.disney.controller;

import com.alkemy.disney.disney.dto.GenderDTO;
import com.alkemy.disney.disney.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genders")
public class GenderController {

    @Autowired
    private GenderService genderService;

    @GetMapping
    public ResponseEntity<List<GenderDTO>> getAll() {
        List<GenderDTO> genders = genderService.getAll();
        return ResponseEntity.ok().body(genders);
    }

    @PostMapping
    public ResponseEntity<GenderDTO> save(@RequestBody GenderDTO genderDTO) {
        GenderDTO genderSaved = genderService.save(genderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(genderSaved);
    }
}
