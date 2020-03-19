package ru.datana.steel.parser.entity;

import lombok.Data;
import ru.datana.steel.parser.config.DbConst;

import javax.persistence.*;

@Entity
@Table(name = "controllers", schema = DbConst.DB_SCHEMA, catalog = DbConst.DB_CATALOG)
@Data
@Access(AccessType.FIELD)
public class ControllersEntity {
    @Id
    private int id;

    @Column(name = "controller_name")
    private String controllerName;


    @Column(name = "rack")
    private int rack;


    @Column(name = "slot")
    private int slot;

    @Column(name = "ip")
    private String ip;

    @Column(name = "write_enable")
    private boolean writeEnable;

    @Column(name = "permanent_connection")
    private Boolean permanentConnection;

    @Column(name = "timeout")
    private Integer timeout;
}
