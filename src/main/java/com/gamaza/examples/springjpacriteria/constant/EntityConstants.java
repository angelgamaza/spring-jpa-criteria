package com.gamaza.examples.springjpacriteria.constant;

/**
 * Constants for Entity classes
 */
public final class EntityConstants {

    /*
     * Generic Entity constants
     */
    public static final String SCHEMA_PUBLIC_STRING = "public";

    /*
     * Entity names
     */
    public static final String TEAM_ENTITY_NAME = "team";
    public static final String RIDER_ENTITY_NAME = "rider";

    /*
     * Sequences
     */
    // Sequences
    public static final String TEAM_SEQUENCE_NAME = "team_seq";
    public static final String RIDER_SEQUENCE_NAME = "rider_seq";
    // Generators
    public static final String TEAM_SEQUENCE_GEN_NAME = "team_seq_gen";
    public static final String RIDER_SEQUENCE_GEN_NAME = "rider_seq";

    /*
     * Entity fields
     */
    public static final String FIELD_ID_STRING = "id";
    public static final String FIELD_NAME_STRING = "name";
    public static final String FIELD_COUNTRY_STRING = "country";
    public static final String FIELD_CONSTRUCTOR_STRING = "constructor";
    public static final String FIELD_MOTORCYCLE_STRING = "motorcycle";
    public static final String FIELD_CHAMPIONSHIPS_STRING = "championships";
    public static final String FIELD_NUMBER_STRING = "number";
    public static final String FIELD_TEAM_STRING = "team";

    /*
     * Relationship fields
     */
    public static final String TEAM_RELATIONSHIP_STRING = "team";
    public static final String RIDERS_RELATIONSHIP_STRING = "riders";

    /*
     * Unique constraints
     */
    public static final String UK_TEAM_NAME_STRING = "uk_team_name";
    public static final String UK_RIDER_NAME_STRING = "uk_rider_name";

    /*
     * Unique indexes
     */
    public static final String UINDEX_TEAM_NAME_STRING = "ui_team_name";
    public static final String UINDEX_RIDER_NAME_STRING = "ui_rider_name";

    /*
     * Foreign keys
     */
    public static final String FK_RIDER_TEAM = "fk_rider_team";

    /*
     * Entity graphs
     */

    // Graphs
    public static final String TEAM_ENTITY_GRAPH = "graph.team";
    public static final String RIDER_ENTITY_GRAPH = "graph.rider";

    /**
     * Private constructor
     */
    private EntityConstants() {
        throw new IllegalStateException("Could not instantiate EntityConstants class!");
    }

}
