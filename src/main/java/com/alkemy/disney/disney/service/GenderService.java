package com.alkemy.disney.disney.service;


import com.alkemy.disney.disney.dto.GenderDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GenderService {

    GenderDTO save (GenderDTO dto);

    List<GenderDTO> getAll();
}
