package com.alkemy.disney.disney.controller;

import com.alkemy.disney.disney.dto.CharactBasicDTO;
import com.alkemy.disney.disney.dto.CharactDTO;
import com.alkemy.disney.disney.entity.Movie;
import com.alkemy.disney.disney.service.CharactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/characters")
public class CharactController {

    @Autowired
    private CharactService charactService;

    @GetMapping
    public ResponseEntity<List<CharactBasicDTO>> getAll() {
        List<CharactBasicDTO> charactBasicDTOS = this.charactService.getAll();
        return ResponseEntity.ok(charactBasicDTOS);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CharactDTO>> getAllComplete() {
        List<CharactDTO> charactDTOS = this.charactService.getAllComplete();
        return ResponseEntity.ok(charactDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CharactDTO> getDetailsById(@PathVariable Long id) {
        CharactDTO charactDTO = this.charactService.getDetailsById(id);
        return ResponseEntity.ok(charactDTO);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<CharactDTO>> getDetailsByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long age,
            @RequestParam(required = false) Long weight,
            @RequestParam(required = false) List<Long> movies
    ) {
        List<CharactDTO> charactDTOS = this.charactService.getByFilters(name, age, weight, movies);
        return ResponseEntity.ok(charactDTOS);
    }

    @PostMapping
    public ResponseEntity<CharactDTO> save(@RequestBody CharactDTO charactDTO) {
        CharactDTO result = this.charactService.save(charactDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CharactDTO> update(@PathVariable Long id, @RequestBody CharactDTO charactDTO) {
        CharactDTO result = this.charactService.update(id, charactDTO);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.charactService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
