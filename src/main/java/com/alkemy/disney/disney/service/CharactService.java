package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.CharactBasicDTO;
import com.alkemy.disney.disney.dto.CharactDTO;
import com.alkemy.disney.disney.entity.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CharactService {
    CharactDTO save(CharactDTO charactDTO);

    CharactDTO update(Long id, CharactDTO charactDTO);

    void delete(Long id);

    CharactDTO getDetailsById(Long id);

    List<CharactBasicDTO> getAll();

    List<CharactDTO> getAllComplete();

    List<CharactDTO> getByFilters(String name, Long age, Long weight, List<Long> movies);

}
