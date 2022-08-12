package com.alkemy.geoicons.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "country")
@Getter
@Setter
public class CountryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String image;
    private Long population;
    private Long area;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "continent_id", insertable = false, updatable = false)
    private ContinentEntity continent;

    @Column(name="continent_id", nullable = false)
    private Long continentId;

    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinTable(
            name = "icon_country",
            joinColumns = @JoinColumn(name = "country_id"),
            inverseJoinColumns = @JoinColumn(name = "icon_id")
    )
    private Set<IconEntity> icons = new HashSet<>();

    @Override
    public String toString() {
        return "CountryEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", population=" + population +
                ", area=" + area +
                ", continent=" + continent +
                ", continentId=" + continentId +
                ", icons=" + icons +
                '}';
    }
}
