package com.alkemy.disney.disney.repository;

import com.alkemy.disney.disney.entity.Charact;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharactRepository extends JpaRepository<Charact, Long> {
    List<Charact> findAll(Specification<Charact> spec);
}
