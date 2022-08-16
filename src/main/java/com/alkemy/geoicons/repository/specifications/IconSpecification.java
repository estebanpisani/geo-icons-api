package com.alkemy.geoicons.repository.specifications;

import com.alkemy.geoicons.dto.IconFilterDTO;
import com.alkemy.geoicons.entity.CountryEntity;
import com.alkemy.geoicons.entity.IconEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class IconSpecification {

    public Specification<IconEntity> getByFilters(IconFilterDTO filters){
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            //Predicates
            if(filters.getName() != null && !filters.getName().isEmpty()){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("name")),
                                "%"+ filters.getName().toLowerCase()+"%"
                        )
                );
            }
            if(filters.getCreationDate() != null && !filters.getCreationDate().isEmpty()){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd");
                LocalDate date = LocalDate.parse(filters.getCreationDate(), formatter);
                predicates.add(criteriaBuilder.equal(root.get("creationDate"), date));
            }
            if(filters.getCountries() != null && filters.getCountries().size()>=1 && !filters.getCountries().isEmpty()){
                Join<CountryEntity, IconEntity> join = root.join("countries", JoinType.INNER);
                Expression<String> countries = join.get("id");
                predicates.add(countries.in(filters.getCountries()));
            }

            //Remove duplicates
            query.distinct(true);

            //TODO OrderByField
            if(filters.getOrder().equalsIgnoreCase("desc")){
                query.orderBy(criteriaBuilder.desc(root.get("name")));
            }
            else{
                query.orderBy(criteriaBuilder.asc(root.get("name")));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }
        );
    }
}
