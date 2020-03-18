package ru.datana.steel.parser.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "controllers", schema = "datalake", catalog = "postgres")
public class ControllersEntity {
    private int id;
    private String controllerName;
    private int rack;
    private int slot;
    private String ip;
    private boolean writeEnable;
    private Boolean permanentConnection;
    private Integer timeout;

    @Basic
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "controller_name")
    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }

    @Basic
    @Column(name = "rack")
    public int getRack() {
        return rack;
    }

    public void setRack(int rack) {
        this.rack = rack;
    }

    @Basic
    @Column(name = "slot")
    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    @Basic
    @Column(name = "ip")
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Basic
    @Column(name = "write_enable")
    public boolean isWriteEnable() {
        return writeEnable;
    }

    public void setWriteEnable(boolean writeEnable) {
        this.writeEnable = writeEnable;
    }

    @Basic
    @Column(name = "permanent_connection")
    public Boolean getPermanentConnection() {
        return permanentConnection;
    }

    public void setPermanentConnection(Boolean permanentConnection) {
        this.permanentConnection = permanentConnection;
    }

    @Basic
    @Column(name = "timeout")
    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ControllersEntity that = (ControllersEntity) o;
        return id == that.id &&
                rack == that.rack &&
                slot == that.slot &&
                writeEnable == that.writeEnable &&
                Objects.equals(controllerName, that.controllerName) &&
                Objects.equals(ip, that.ip) &&
                Objects.equals(permanentConnection, that.permanentConnection) &&
                Objects.equals(timeout, that.timeout);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, controllerName, rack, slot, ip, writeEnable, permanentConnection, timeout);
    }
}
