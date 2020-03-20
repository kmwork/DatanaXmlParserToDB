package ru.datana.steel.parser.model.entity;

import lombok.Data;
import ru.datana.steel.parser.config.DbConst;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "controllers_data", schema = DbConst.DB_SCHEMA, catalog = DbConst.DB_CATALOG)
@Data
@Access(AccessType.FIELD)
public class ControllersDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, insertable = false, updatable = false)
    private int id;

    @Column(name = "description")
    private String description;

    @Column(name = "unit_id")
    private int unitId;

    @Column(name = "controller_id")
    private int controllerId;

    @Column(name = "data_block")
    private int dataBlock;

    @Column(name = "data_type")
    private String dataType;


    @Column(name = "data_offset")
    private int dataOffset;

    @Column(name = "bitmask")
    private String bitmask;

    @Column(name = "rec_dt")
    private LocalDateTime recDt;

    @Column(name = "sign")
    private Boolean sign;

//    /**
//     * Гистерезис в обсалютных значенияих или в процентах
//     */
//    @Column(name = "abs")
//    private Boolean abs;
//
//    /**
//     * Гистерезис
//     */
//    @Column(name = "husteresis")
//    private String husteresis;
//
//    /**
//     * Записывать значение в runtime таблицу
//     */
//    @Column(name = "rt")
//    private Boolean rt;

}
