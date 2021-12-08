package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.dto.GenderDTO;
import com.alkemy.disney.disney.entity.Gender;
import com.alkemy.disney.disney.mapper.GenderMapper;
import com.alkemy.disney.disney.repository.GenderRepository;
import com.alkemy.disney.disney.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenderServiceImpl implements GenderService {

    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private GenderMapper genderMapper;

    @Override
    public GenderDTO save(GenderDTO dto) {
        Gender gender = genderMapper.genderDTO2Entity(dto);
        Gender genderSaved = genderRepository.save(gender);
        GenderDTO result = genderMapper.genderEntity2DTO(genderSaved);
        return result;
    }

    @Override
    public List<GenderDTO> getAll() {
        List<Gender> genders = genderRepository.findAll();
        List<GenderDTO> result = genderMapper.genderEntityList2DTOList(genders);
        return result;
    }
}
