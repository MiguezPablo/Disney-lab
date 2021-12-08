package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.dto.CharactBasicDTO;
import com.alkemy.disney.disney.dto.CharactDTO;
import com.alkemy.disney.disney.dto.CharactFiltersDTO;
import com.alkemy.disney.disney.entity.Charact;
import com.alkemy.disney.disney.entity.Movie;
import com.alkemy.disney.disney.exception.ParamNotFound;
import com.alkemy.disney.disney.mapper.CharactMapper;
import com.alkemy.disney.disney.repository.CharactRepository;
import com.alkemy.disney.disney.repository.specifications.CharactSpecification;
import com.alkemy.disney.disney.service.CharactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CharactServiceImpl implements CharactService {

    @Autowired
    private CharactRepository charactRepository;
    @Autowired
    private CharactSpecification charactSpecification;
    @Autowired
    private CharactMapper charactMapper;


    @Override
    public CharactDTO save(CharactDTO charactDTO) {
        Charact cha = this.charactMapper.charactDTO2Entity(charactDTO);
        Charact charactSaved = this.charactRepository.save(cha);
        CharactDTO result = this.charactMapper.charactEntity2DTO(charactSaved, false);
        return result;
    }

    @Override
    public CharactDTO update(Long id, CharactDTO charactDTO) {
        Optional<Charact> charact= this.charactRepository.findById(id);
        if (!charact.isPresent()){
            throw new ParamNotFound("Id Character no valid");
        }
        this.charactMapper.charactEntityRefreshValues(charact.get(), charactDTO);
        Charact charactSaved = this.charactRepository.save(charact.get());
        CharactDTO result = this.charactMapper.charactEntity2DTO(charactSaved, false);
        return result;
    }

    @Override
    public void delete(Long id) {
    this.charactRepository.deleteById(id);
    }

    @Override
    public CharactDTO getDetailsById(Long id) {
        Optional<Charact> charact = this.charactRepository.findById(id);
        if (!charact.isPresent()) {
            throw new ParamNotFound("Id Character no valid");
        }
        CharactDTO charactDTO = this.charactMapper.charactEntity2DTO(charact.get(), true);
        return charactDTO;
    }

    @Override
    public List<CharactBasicDTO> getAll() {
        List<Charact> characts = this.charactRepository.findAll();
        List<CharactBasicDTO> charactBasicDTOS = this.charactMapper.charactEntitySet2BasicDTOList(characts);
        return charactBasicDTOS;

    }

    @Override
    public List<CharactDTO> getAllComplete() {
        List<Charact> characts = this.charactRepository.findAll();
        List<CharactDTO> charactDTOS = this.charactMapper.charactEntitySet2DTOList(characts, true);
        return charactDTOS;

    }

    @Override
    public List<CharactDTO> getByFilters(String name, Long age, Long weight, List<Long> movies) {
        CharactFiltersDTO filtersDTO = new CharactFiltersDTO(name, age, weight, movies);
        List<Charact> characts = this.charactRepository.findAll(
                this.charactSpecification.getByFilters(filtersDTO)
        );
        List<CharactDTO> charactDTOS = this.charactMapper.charactEntitySet2DTOList(characts, true);
        return charactDTOS;
    }
}
