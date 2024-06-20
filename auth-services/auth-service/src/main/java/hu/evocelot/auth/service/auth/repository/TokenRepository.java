package hu.evocelot.auth.service.auth.repository;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.QueryParam;
import org.apache.deltaspike.data.api.Repository;

import hu.evocelot.auth.model.Token;
import hu.evocelot.auth.model.enums.TokenType;

/**
 * Interface for handling {@link Token}s.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@Repository
public interface TokenRepository extends EntityRepository<Token, String> {

    /**
     * Finds the access token based on the value and fetches the related token.
     *
     * @param accessTokenValue
     *         - the value of the access token.
     * @param tokenType
     *         - the type of the token.
     * @return - with {@link Token}.
     */
    @Query("SELECT t FROM Token t JOIN FETCH t.relatedToken WHERE t.token = :accessTokenValue AND t.tokenType = :tokenType")
    Token findByValueFetchRelatedToken(@QueryParam("accessTokenValue") String accessTokenValue, @QueryParam("tokenType") TokenType tokenType);

    /**
     * Finds the access token based on the value.
     *
     * @param accessTokenValue
     *         - the value of the access token.
     * @param tokenType
     *         - the type of the token.
     * @return - with {@link Token}.
     */
    @Query("SELECT t FROM Token t WHERE t.token = :accessTokenValue AND t.tokenType = :tokenType")
    Token findByValue(@QueryParam("accessTokenValue") String accessTokenValue, @QueryParam("tokenType") TokenType tokenType);
}
