package hu.evocelot.auth.api.common.restinformation;

/**
 * Class for defining the rest information.
 *
 * @author mark.danisovszky
 * @since 0.1.0
 */
public class SampleRestInformation {
    /**
     * {@value }
     */
    public static final String TAG = "Sample JPA service";

    /**
     * {@value }
     */
    public static final String DESCRIPTION = "Minta végpontok";

    /**
     * {@value }
     */
    public static final String GET_SAMPLE_SUMMARY = "Adatlekérdezési minta";

    /**
     * {@value }
     */
    public static final String GET_SAMPLE_DESCRIPTION = "A végpont segítségével a minta adat részleteinek kiolvasása lehetséges.";

    /**
     * {@value }
     */
    public static final String SAMPLE_PARAM_ID_DESCRIPTION = "A sample entitás egyedi azonosítója.";

    /**
     * {@value }
     */
    public static final String POST_SAMPLE_SUMMARY = "Adatbetöltési minta";

    /**
     * {@value }
     */
    public static final String POST_SAMPLE_DESCRIPTION = "A végpont segítségével a minta adat mentése lehetséges.";

    /**
     * {@value }
     */
    public static final String POST_SAMPLE_2_SUMMARY = "Példa entitások beszúrása";

    /**
     * {@value }
     */
    public static final String POST_SAMPLE_2_DESCRIPTION = "A végpont segítségével példa entitások jönnek létre," + //
            " melyek a kiválasztott adatbázisba mentődnek..";
}
