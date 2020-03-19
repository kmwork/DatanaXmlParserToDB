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
    @GeneratedValue(strategy = GenerationType.AUTO)
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
}
