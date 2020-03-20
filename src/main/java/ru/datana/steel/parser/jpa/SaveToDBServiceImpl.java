package ru.datana.steel.parser.jpa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.datana.steel.parser.config.DbConst;
import ru.datana.steel.parser.model.LanitEntryBuilder;
import ru.datana.steel.parser.model.entity.ControllersEntity;
import ru.datana.steel.parser.model.entity.UnitsEntity;
import ru.datana.steel.parser.model.xml.ItemsType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional
@Repository
@Slf4j
public class SaveToDBServiceImpl implements SaveToDBService {

    private final static String PREFIX_LOG = "[Сохраниение в БД] ";
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    @Transactional
    public void saveRecords(LanitEntryBuilder builder) {
        log.debug(PREFIX_LOG + "[saveRecords] запуск");
        for (ControllersEntity c : builder.getControllersEntities()) {
            entityManager.persist(c);
            log.debug(PREFIX_LOG + " записано - ControllersEntity: " + c);
        }

        Map<String, Map<String, Map<String, List<ItemsType>>>> itemsByComplexKey = builder.getItemsByComplexKey();
        for (Map.Entry<String, Map<String, Map<String, List<ItemsType>>>> nodeEn : itemsByComplexKey.entrySet()) {

            UnitsEntity unitsEntity = new UnitsEntity();
            unitsEntity.setName(nodeEn.getKey());
            unitsEntity.setRecDt(DbConst.SAVE_TIME);
            entityManager.persist(unitsEntity);
            log.debug(PREFIX_LOG + " записано - unitsEntity: " + unitsEntity);
//            Map<String, Map<String, List<ItemsType>>> mapController = nodeEn.getValue();
//            for (Map.Entry<String, Map<String, List<ItemsType>>> controllerEn:  mapController.entrySet()){
//                Map
//            }
        }
        log.debug(PREFIX_LOG + "[saveRecords] конец");
    }

    @Override
    @Transactional
    public void dropRecords() {
        log.debug("[Очитка БД] запуск");
        Query query = entityManager.createNativeQuery("truncate " + DbConst.DB_SCHEMA + ".controllers");
        query.executeUpdate();
        query = entityManager.createNativeQuery("truncate " + DbConst.DB_SCHEMA + ".units");
        query.executeUpdate();

        log.debug("[Очитка БД] завершена");
    }

}
