package com.gamaza.examples.springjpacriteria.entity;

import com.gamaza.examples.springjpacriteria.enums.Constructor;
import com.gamaza.examples.springjpacriteria.enums.Country;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

import static com.gamaza.examples.springjpacriteria.constant.EntityConstants.*;
import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Table(
        schema = SCHEMA_PUBLIC_STRING,
        name = TEAM_ENTITY_NAME,
        indexes = {
                @Index(name = UINDEX_TEAM_NAME_STRING, columnList = FIELD_NAME_STRING, unique = true)
        },
        uniqueConstraints = {
                @UniqueConstraint(name = UK_TEAM_NAME_STRING, columnNames = FIELD_NAME_STRING)
        }
)
@NamedEntityGraph(
        name = TEAM_ENTITY_GRAPH,
        attributeNodes = {
                @NamedAttributeNode(value = RIDERS_RELATIONSHIP_STRING)
        }
)
public class Team {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = TEAM_SEQUENCE_GEN_NAME)
    @SequenceGenerator(name = TEAM_SEQUENCE_GEN_NAME, sequenceName = TEAM_SEQUENCE_NAME, allocationSize = 1)
    @Column(name = FIELD_ID_STRING, nullable = false, unique = true)
    private Long id;

    @Column(name = FIELD_NAME_STRING, nullable = false)
    private String name;

    @Enumerated(value = STRING)
    @Column(name = FIELD_CONSTRUCTOR_STRING, nullable = false)
    private Constructor constructor;

    @Column(name = FIELD_MOTORCYCLE_STRING, nullable = false)
    private String motorcycle;

    @Enumerated(value = STRING)
    @Column(name = FIELD_COUNTRY_STRING, nullable = false)
    private Country country;

    @OrderBy(value = "id DESC")
    @OneToMany(mappedBy = TEAM_RELATIONSHIP_STRING, cascade = ALL, orphanRemoval = true, targetEntity = Rider.class)
    private Set<Rider> riders = new HashSet<>();

    public Team() {
    }

    public Team(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
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

    public Constructor getConstructor() {
        return constructor;
    }

    public void setConstructor(Constructor constructor) {
        this.constructor = constructor;
    }

    public String getMotorcycle() {
        return motorcycle;
    }

    public void setMotorcycle(String motorcycle) {
        this.motorcycle = motorcycle;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Set<Rider> getRiders() {
        return riders;
    }

    public void setRiders(Set<Rider> riders) {
        this.riders = riders;
    }

}