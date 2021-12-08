package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.CharactBasicDTO;
import com.alkemy.disney.disney.dto.CharactDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.entity.Charact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CharactMapper {

    @Autowired
    @Lazy
    private MovieMapper movieMapper;

    public Charact charactDTO2Entity(CharactDTO cDto){
        Charact charact = new Charact();
        charact.setImage(cDto.getImage());
        charact.setName(cDto.getName());
        charact.setAge(cDto.getAge());
        charact.setWeight(cDto.getWeight());
        charact.setHistory(cDto.getHistory());
        return charact;
    }

    public CharactDTO charactEntity2DTO(Charact charact, boolean loadMovies){
        CharactDTO dto = new CharactDTO();
        dto.setId(charact.getId());
        dto.setName(charact.getName());
        dto.setImage(charact.getImage());
        dto.setAge(charact.getAge());
        dto.setWeight(charact.getWeight());
        dto.setHistory(charact.getHistory());
        if (loadMovies) {
            List<MovieDTO> movieDTOS = this.movieMapper.movieEntityList2DTOList(charact.getMovies(), false);
            dto.setMovies(movieDTOS);
        }
        return dto;
    }

    public void charactEntityRefreshValues(Charact charact, CharactDTO charactDTO) {
        charact.setImage(charactDTO.getImage());
        charact.setName(charactDTO.getName());
        charact.setAge(charactDTO.getAge());
        charact.setWeight(charactDTO.getWeight());
        charact.setHistory(charactDTO.getHistory());
    }

    public Set<Charact> charactDTOList2Entity(List<CharactDTO> dtos){
        Set<Charact> characts = new HashSet<>();
        for (CharactDTO cDto:dtos) {
            characts.add(this.charactDTO2Entity(cDto));

        }
        return characts;
    }

    public List<CharactDTO> charactEntitySet2DTOList(Collection<Charact> characts, boolean loadMovies) {
        List<CharactDTO> cDtos = new ArrayList<>();
        for (Charact ch: characts) {
            cDtos.add(this.charactEntity2DTO(ch, loadMovies));
        }
        return cDtos;
    }

    public List<CharactBasicDTO> charactEntitySet2BasicDTOList(Collection<Charact> characts) {
        List<CharactBasicDTO> charactBasicDTOS = new ArrayList<>();
        CharactBasicDTO basicDTO;
        for (Charact ch: characts) {
            basicDTO = new CharactBasicDTO();
            basicDTO.setId(ch.getId());
            basicDTO.setImage(ch.getImage());
            basicDTO.setName(ch.getName());
            charactBasicDTOS.add(basicDTO);
        }
        return charactBasicDTOS;
    }
}
