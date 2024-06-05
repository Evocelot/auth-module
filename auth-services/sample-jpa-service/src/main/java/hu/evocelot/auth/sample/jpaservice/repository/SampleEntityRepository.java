package hu.evocelot.auth.sample.jpaservice.repository;

import java.util.List;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.QueryParam;
import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.data.api.criteria.CriteriaSupport;

import hu.evocelot.auth.model.sample.SampleEntity;
import hu.evocelot.auth.model.sample.enums.SampleStatus;

/**
 * Repository for handling {@link SampleEntity}.
 *
 * @author mark.danisovszky
 * @since 0.1.0
 */
@Repository
public interface SampleEntityRepository extends EntityRepository<SampleEntity, String>, CriteriaSupport<SampleEntity> {

    /**
     * selecting entities by status
     *
     * @param status
     *         - the status.
     * @return - with the list of sample entities.
     */
    @Query(value = "SELECT s FROM SampleEntity s WHERE s.status = :status")
    List<SampleEntity> findAllByStatus(@QueryParam("status") SampleStatus status);
}
