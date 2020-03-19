package ru.datana.steel.parser.model.entity;

import lombok.Data;
import ru.datana.steel.parser.config.DbConst;

import javax.persistence.*;

@Entity
@Table(name = "controllers_tasks_data", schema = DbConst.DB_SCHEMA, catalog = DbConst.DB_CATALOG)
@Data
@Access(AccessType.FIELD)
public class ControllersTasksDataEntity {
    @Column(name = "controller_task_id")
    private int controllerTaskId;

    @Column(name = "controller_data_id")
    private int controllerDataId;


}
