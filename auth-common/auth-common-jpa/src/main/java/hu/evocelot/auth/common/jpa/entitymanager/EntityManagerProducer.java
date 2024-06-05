package hu.evocelot.auth.common.jpa.entitymanager;

import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * Default entityManager producer.
 *
 * @author mark.danisovszky
 * @since 0.1.0
 */
@Dependent
public class EntityManagerProducer {

    @PersistenceContext(unitName = "defaultPU")
    private EntityManager defaultEm;

    /**
     * Default entityManager producer.
     *
     * @return - the EntityManager from the "defaultPU" persistence unit.
     */
    @Produces
    @Dependent
    public EntityManager createDefaultEntityManager() {
        return defaultEm;
    }
}
