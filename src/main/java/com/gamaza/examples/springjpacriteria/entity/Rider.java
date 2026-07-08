package com.gamaza.examples.springjpacriteria.entity;

import com.gamaza.examples.springjpacriteria.enums.Country;
import jakarta.persistence.*;

import static com.gamaza.examples.springjpacriteria.constant.EntityConstants.*;
import static jakarta.persistence.ConstraintMode.CONSTRAINT;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Table(
        schema = SCHEMA_PUBLIC_STRING,
        name = RIDER_ENTITY_NAME,
        indexes = {
                @Index(name = UINDEX_RIDER_NAME_STRING, columnList = FIELD_NAME_STRING, unique = true)
        },
        uniqueConstraints = {
                @UniqueConstraint(name = UK_RIDER_NAME_STRING, columnNames = FIELD_NAME_STRING)
        }
)
@NamedEntityGraph(
        name = RIDER_ENTITY_GRAPH,
        attributeNodes = {
                @NamedAttributeNode(value = TEAM_RELATIONSHIP_STRING)
        }
)
public class Rider {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = RIDER_SEQUENCE_GEN_NAME)
    @SequenceGenerator(name = RIDER_SEQUENCE_GEN_NAME, sequenceName = RIDER_SEQUENCE_NAME, allocationSize = 1)
    @Column(name = FIELD_ID_STRING, nullable = false, unique = true)
    private Long id;

    @Column(name = FIELD_NAME_STRING, nullable = false, unique = true)
    private String name;

    @Column(name = FIELD_NUMBER_STRING, nullable = false)
    private Integer number;

    @Column(name = FIELD_CHAMPIONSHIPS_STRING, nullable = false)
    private Integer championships;

    @Enumerated(value = STRING)
    @Column(name = FIELD_COUNTRY_STRING, nullable = false)
    private Country country;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(
            name = TEAM_RELATIONSHIP_STRING,
            referencedColumnName = FIELD_ID_STRING,
            foreignKey = @ForeignKey(name = FK_RIDER_TEAM, value = CONSTRAINT)
    )
    private Team team;

    public Rider() {
    }

    public Rider(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getChampionships() {
        return championships;
    }

    public void setChampionships(Integer championships) {
        this.championships = championships;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

}