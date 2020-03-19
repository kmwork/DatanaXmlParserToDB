package ru.datana.steel.parser.entity;

import lombok.Data;
import ru.datana.steel.parser.config.DbConst;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "controllers_data", schema = DbConst.DB_SCHEMA, catalog = DbConst.DB_CATALOG)
@Data
@Access(AccessType.FIELD)
public class ControllersDataEntity {
    @Id
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
    private int dataOffset;
    private String bitmask;
    private Timestamp recDt;


    @Column(name = "data_offset")
    public int getDataOffset() {
        return dataOffset;
    }

    public void setDataOffset(int dataOffset) {
        this.dataOffset = dataOffset;
    }


    @Column(name = "bitmask")
    public String getBitmask() {
        return bitmask;
    }

    public void setBitmask(String bitmask) {
        this.bitmask = bitmask;
    }


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
