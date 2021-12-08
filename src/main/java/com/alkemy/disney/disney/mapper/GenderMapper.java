package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.GenderDTO;
import com.alkemy.disney.disney.entity.Gender;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GenderMapper {

    public Gender genderDTO2Entity(GenderDTO dto){
        Gender genderEntity = new Gender();
        genderEntity.setImage(dto.getImage());
        genderEntity.setName(dto.getName());
        return genderEntity;
    }

    public GenderDTO genderEntity2DTO(Gender entity){
        GenderDTO dto = new GenderDTO();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setName(entity.getName());
        return dto;
    }

    public List<GenderDTO> genderEntityList2DTOList(List<Gender> entities) {
        List<GenderDTO> dtos = new ArrayList<>();
        for (Gender entity:entities) {
            dtos.add(this.genderEntity2DTO(entity));
        }
        return dtos;
    }
}
