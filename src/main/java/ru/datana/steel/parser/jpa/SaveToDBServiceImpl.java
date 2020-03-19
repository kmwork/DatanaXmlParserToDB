package ru.datana.steel.parser.jpa;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.datana.steel.parser.model.LanitEntryBuilder;
import ru.datana.steel.parser.model.entity.ControllersEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional
@Repository
public class SaveToDBServiceImpl implements SaveToDBService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void saveRecords(LanitEntryBuilder builder) {

        entityManager.createNativeQuery("delete from datalake.controllers");
        for (ControllersEntity c : builder.getControllersEntities()) {
            entityManager.persist(c);
        }
    }

}
