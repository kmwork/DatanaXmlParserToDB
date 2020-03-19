package ru.datana.steel.parser.model.entity;

import lombok.Data;
import ru.datana.steel.parser.config.DbConst;

import javax.persistence.*;

@Entity
@Table(name = "setup_areas", schema = DbConst.DB_SCHEMA, catalog = DbConst.DB_CATALOG)
@Data
@Access(AccessType.FIELD)
public class SetupAreasEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name = "description")
    private String description;


}
