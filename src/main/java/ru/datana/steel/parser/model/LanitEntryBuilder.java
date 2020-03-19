package ru.datana.steel.parser.model;

import ru.datana.steel.parser.config.DbConst;
import ru.datana.steel.parser.model.entity.ControllersEntity;
import ru.datana.steel.parser.model.xml.ControllerType;
import ru.datana.steel.parser.model.xml.RootType;

import java.util.ArrayList;
import java.util.List;

public class LanitEntryBuilder {

    public List<ControllersEntity> fromXmlRootType(RootType xmlRootType) {
        List<ControllerType> xmlList = xmlRootType.getControllers().getController();
        List<ControllersEntity> result = new ArrayList<>(xmlList.size());
        for (ControllerType xml : xmlList) {
            ControllersEntity entity = new ControllersEntity();
            entity.setControllerName(xml.getName());
            entity.setIp(xml.getIp());
            entity.setPermanentConnection(DbConst.PERMANENT_CONNECTION_FLAG);
            entity.setRack(Integer.parseInt(xml.getRack()));
            entity.setSlot(Integer.parseInt(xml.getSlot()));
            entity.setTimeout(DbConst.DEFAULT_TIMEOUT);
        }
        return result;

    }
}
