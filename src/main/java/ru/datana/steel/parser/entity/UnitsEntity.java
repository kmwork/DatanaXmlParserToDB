package ru.datana.steel.parser.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "units", schema = "datalake", catalog = "postgres")
public class UnitsEntity {
    private int id;
    private String name;
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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        UnitsEntity that = (UnitsEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(recDt, that.recDt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, recDt);
    }
}
