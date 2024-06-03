package hu.evocelot.auth.common.system.jpa.service;

import hu.evocelot.auth.common.system.jpa.jpa.EntityHelper;
import hu.icellmobilsoft.coffee.model.base.javatime.AbstractIdentifiedAuditEntity;
import hu.icellmobilsoft.coffee.tool.utils.date.DateUtil;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

/**
 * Batch save with JPA.
 *
 * @author mark.danisovszky
 * @since 0.1.0
 */
@Dependent
public class BatchService extends hu.icellmobilsoft.coffee.jpa.sql.batch.BatchService {

    @Inject
    private EntityHelper entityHelper;

    @Override
    protected <E> void handleInsertAudit(E entity) {
        if (entity instanceof AbstractIdentifiedAuditEntity) {
            AbstractIdentifiedAuditEntity e = (AbstractIdentifiedAuditEntity) entity;
            e.setCreationDate(DateUtil.nowUTC());
            e.setCreatorUser(entityHelper.currentUser());
        }
    }

    @Override
    protected <E> void handleUpdateAudit(E entity) {
        if (entity instanceof AbstractIdentifiedAuditEntity) {
            AbstractIdentifiedAuditEntity e = (AbstractIdentifiedAuditEntity) entity;
            e.setModificationDate(DateUtil.nowUTC());
            e.setModifierUser(entityHelper.currentUser());
        }
    }
}
