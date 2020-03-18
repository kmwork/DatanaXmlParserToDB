package ru.datana.steel.parser.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "controllers_tasks_data", schema = "datalake", catalog = "postgres")
public class ControllersTasksDataEntity {
    private int controllerTaskId;
    private int controllerDataId;

    @Basic
    @Column(name = "controller_task_id")
    public int getControllerTaskId() {
        return controllerTaskId;
    }

    public void setControllerTaskId(int controllerTaskId) {
        this.controllerTaskId = controllerTaskId;
    }

    @Basic
    @Column(name = "controller_data_id")
    public int getControllerDataId() {
        return controllerDataId;
    }

    public void setControllerDataId(int controllerDataId) {
        this.controllerDataId = controllerDataId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ControllersTasksDataEntity that = (ControllersTasksDataEntity) o;
        return controllerTaskId == that.controllerTaskId &&
                controllerDataId == that.controllerDataId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(controllerTaskId, controllerDataId);
    }
}
