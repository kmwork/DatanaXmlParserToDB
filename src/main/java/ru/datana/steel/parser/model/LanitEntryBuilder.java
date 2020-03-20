package ru.datana.steel.parser.model;

import lombok.Getter;
import ru.datana.steel.parser.config.DbConst;
import ru.datana.steel.parser.model.entity.ControllersDataEntity;
import ru.datana.steel.parser.model.entity.ControllersEntity;
import ru.datana.steel.parser.model.xml.ControllerType;
import ru.datana.steel.parser.model.xml.ItemType;
import ru.datana.steel.parser.model.xml.ItemsType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LanitEntryBuilder {

    @Getter
    private final List<ControllersEntity> controllersEntities = new ArrayList<>();

    /**
     * ключ note-nameController-fileName
     */
    @Getter
    private final Map<String, Map<String, Map<String, List<ItemsType>>>> itemsByComplexKey = new HashMap<>();

    public LanitEntryBuilder() {

    }

    public void addControllerList(List<ControllerType> xmlList) {
        for (ControllerType xml : xmlList) {
            ControllersEntity entity = new ControllersEntity();
            entity.setControllerName(xml.getName());
            entity.setIp(xml.getIp());
            entity.setPermanentConnection(DbConst.PERMANENT_CONNECTION_FLAG);
            entity.setRack(Integer.parseInt(xml.getRack()));
            entity.setSlot(Integer.parseInt(xml.getSlot()));
            entity.setTimeout(DbConst.DEFAULT_TIMEOUT);
            controllersEntities.add(entity);
        }
    }

    public void addItems(String node, String nameController, String fileName, ItemsType items) {
        Map<String, Map<String, List<ItemsType>>> mapNode = itemsByComplexKey.get(node);
        if (mapNode == null) {
            mapNode = new HashMap<>();
            itemsByComplexKey.put(node, mapNode);
        }
        Map<String, List<ItemsType>> mapController = mapNode.get(nameController);
        if (mapController == null) {
            mapController = new HashMap<>();
            mapNode.put(nameController, mapController);
        }

        List<ItemsType> itemsByFileName = mapController.get(fileName);
        if (itemsByFileName == null) {
            itemsByFileName = new ArrayList<>();
            mapController.put(fileName, itemsByFileName);
        }
        itemsByFileName.add(items);
    }

    public ControllersDataEntity convertItemsToControllersDataEntity(String fileName, ItemType item) {
        ControllersDataEntity entity = new ControllersDataEntity();
        entity.setControllerId(1);
        int dataBlock = parseFileName(fileName);
        entity.setDataBlock(dataBlock);
        entity.setDataType(item.getType());
        entity.setDescription(item.getNote());
        entity.getDataOffset();
        return entity;
    }

    /**
     * пример db4000.xml
     *
     * @param fileName
     * @return
     */
    private int parseFileName(String fileName) {
        String strNum = fileName.substring(2, fileName.length() - 4);
        return Integer.parseInt(strNum);
    }
}
