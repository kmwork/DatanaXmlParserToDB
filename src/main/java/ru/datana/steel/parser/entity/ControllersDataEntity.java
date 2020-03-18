package ru.datana.steel.parser.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "controllers_data", schema = "datalake", catalog = "postgres")
public class ControllersDataEntity {
    private int id;
    private String description;
    private int unitId;
    private int controllerId;
    private int dataBlock;
    private String dataType;
    private int dataOffset;
    private String bitmask;
    private Timestamp recDt;

    @Basic
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "unit_id")
    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    @Basic
    @Column(name = "controller_id")
    public int getControllerId() {
        return controllerId;
    }

    public void setControllerId(int controllerId) {
        this.controllerId = controllerId;
    }

    @Basic
    @Column(name = "data_block")
    public int getDataBlock() {
        return dataBlock;
    }

    public void setDataBlock(int dataBlock) {
        this.dataBlock = dataBlock;
    }

    @Basic
    @Column(name = "data_type")
    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    @Basic
    @Column(name = "data_offset")
    public int getDataOffset() {
        return dataOffset;
    }

    public void setDataOffset(int dataOffset) {
        this.dataOffset = dataOffset;
    }

    @Basic
    @Column(name = "bitmask")
    public String getBitmask() {
        return bitmask;
    }

    public void setBitmask(String bitmask) {
        this.bitmask = bitmask;
    }

    @Basic
    @Column(name = "rec_dt")
    public Timestamp getRecDt() {
        return recDt;
    }

    public void setRecDt(Timestamp recDt) {
        this.recDt = recDt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ControllersDataEntity that = (ControllersDataEntity) o;
        return id == that.id &&
                unitId == that.unitId &&
                controllerId == that.controllerId &&
                dataBlock == that.dataBlock &&
                dataOffset == that.dataOffset &&
                Objects.equals(description, that.description) &&
                Objects.equals(dataType, that.dataType) &&
                Objects.equals(bitmask, that.bitmask) &&
                Objects.equals(recDt, that.recDt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, unitId, controllerId, dataBlock, dataType, dataOffset, bitmask, recDt);
    }
}
