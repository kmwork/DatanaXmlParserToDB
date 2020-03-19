package ru.datana.steel.parser.model.entity;

import lombok.Data;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Ключ для Entity: для Entity-сущностей
 */
@Embeddable
@Access(AccessType.FIELD)
@Data
public class EmbeddableControllerID implements Serializable {

    @Column(name = "controller_task_id")
    private int controllerTaskId;

    @Column(name = "controller_data_id")
    private int controllerDataId;

}