package com.alkemy.disney.disney.repository.specifications;

import com.alkemy.disney.disney.dto.CharactFiltersDTO;
import com.alkemy.disney.disney.entity.Charact;
import com.alkemy.disney.disney.entity.Movie;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class CharactSpecification {
    public Specification<Charact> getByFilters(CharactFiltersDTO filtersDTO) {
        return (root, query, criteriaBuilder) -> {

            List<javax.persistence.criteria.Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(filtersDTO.getName())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("name")),
                                "%" + filtersDTO.getName().toLowerCase() + "%"
                        )
                );
            }

            if (filtersDTO.getAge() != null) {
                predicates.add(
                        criteriaBuilder.equal(
                                criteriaBuilder.lower(root.get("age")),
                                filtersDTO.getAge()));
            }

            if (filtersDTO.getWeight() != null) {
                predicates.add(
                        criteriaBuilder.equal(
                                criteriaBuilder.lower(root.get("weight")),
                                filtersDTO.getWeight()));
            }


            if (!CollectionUtils.isEmpty(filtersDTO.getMovies())) {
                Join<Movie, Charact> join = root.join("movies", JoinType.INNER);
                Expression<String> moviesId = join.get("id");
                predicates.add(moviesId.in(filtersDTO.getMovies()));
            }

            // Remove duplicates
            query.distinct(true);


            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };
    }
}
