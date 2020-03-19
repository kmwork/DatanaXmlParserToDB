package ru.datana.steel.parser.model.entity;

import lombok.Data;
import ru.datana.steel.parser.config.DbConst;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "units", schema = DbConst.DB_SCHEMA, catalog = DbConst.DB_CATALOG)
@Data
@Access(AccessType.FIELD)
public class UnitsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;


    @Column(name = "rec_dt")
    private LocalDateTime recDt;
}