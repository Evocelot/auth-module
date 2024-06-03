package hu.evocelot.auth.api.common.path;

/**
 * PATHS for jpa-batch-service.
 *
 * @author mark.danisovszky
 * @since 0.1.0
 */
public interface JpaBatchServicePath {

    /**
     * /rest
     */
    String REST = "/rest";

    /**
     * {@value }
     */
    String REST_JPA_BATCH_SERVICE = REST + "/jpaBatchService";

    /**
     * {@value }
     */
    String REST_JPA_BATCH_SERVICE_EMPTY_ENTITY = REST_JPA_BATCH_SERVICE + "/emptyEntity";

    /**
     * {@value }
     */
    String REST_JPA_BATCH_SERVICE_JPA_ASSOCIATION = REST_JPA_BATCH_SERVICE + "/jpaAssociation";

    /**
     * {@value }
     */
    String REST_JPA_BATCH_SERVICE_JAVA_DATA = REST_JPA_BATCH_SERVICE + "/javaData";

    /**
     * {@value }
     */
    String REST_JPA_BATCH_SERVICE_JAVA_ENUM_TYPES = REST_JPA_BATCH_SERVICE + "/javaEnumTypes";

    /**
     * {@value }
     */
    String REST_JPA_BATCH_SERVICE_JAVA_BASE_TYPES = REST_JPA_BATCH_SERVICE + "/javaBaseTypes";

    /**
     * {@value }
     */
    String REST_JPA_BATCH_SERVICE_JAVA_DATE_AND_TIME = REST_JPA_BATCH_SERVICE + "/javaDateAndTime";

    /**
     * {@value }
     */
    String REST_JPA_BATCH_SERVICE_JPA_CONVERTER_ENTITY = REST_JPA_BATCH_SERVICE + "/jpaConverterEntity";

    /**
     * {@value }
     */
    String INSERT = "/insert";

    /**
     * {@value }
     */
    String UPDATE = "/update";

    /**
     * {@value }
     */
    String DELETE = "/delete";

    /**
     * {@value }
     */
    String PARAM_JPA_ASSOCIATION_ID = "jpaAssociationId";

    /**
     * {@value }
     */
    String UPDATE_JPA_ASSOCIATION_ID = UPDATE + "/{" + PARAM_JPA_ASSOCIATION_ID + "}";

    /**
     * {@value }
     */
    String PARAM_JAVA_DATA_ID = "javaDataId";

    /**
     * {@value }
     */
    String UPDATE_JAVA_DATA_ID = UPDATE + "/{" + PARAM_JAVA_DATA_ID + "}";

    /**
     * {@value }
     */
    String PARAM_JAVA_ENUM_TYPES_ID = "javaEnumTypesId";

    /**
     * {@value }
     */
    String UPDATE_JAVA_ENUM_TYPES_ID = UPDATE + "/{" + PARAM_JAVA_ENUM_TYPES_ID + "}";

    /**
     * {@value }
     */
    String PARAM_JAVA_BASE_TYPES_ID = "javaBaseTypesId";

    /**
     * {@value }
     */
    String UPDATE_JAVA_BASE_TYPES_ID = UPDATE + "/{" + PARAM_JAVA_BASE_TYPES_ID + "}";

    /**
     * {@value }
     */
    String PARAM_JAVA_DATE_AND_TIME_ID = "javaDateAndTimeId";

    /**
     * {@value }
     */
    String UPDATE_JAVA_DATE_AND_TIME_ID = UPDATE + "/{" + PARAM_JAVA_DATE_AND_TIME_ID + "}";

    /**
     * {@value }
     */
    String PARAM_JPA_CONVERTER_ENTITY_ID = "jpaConverterEntityId";

    /**
     * {@value }
     */
    String UPDATE_JPA_CONVERTER_ENTITY_ID = UPDATE + "/{" + PARAM_JPA_CONVERTER_ENTITY_ID + "}";
}
