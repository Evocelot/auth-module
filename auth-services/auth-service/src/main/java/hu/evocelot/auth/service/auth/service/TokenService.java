package hu.evocelot.auth.service.auth.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import hu.evocelot.auth.common.system.jpa.service.BaseService;
import hu.evocelot.auth.model.Token;
import hu.evocelot.auth.model.enums.TokenType;
import hu.evocelot.auth.service.auth.repository.TokenRepository;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;

/**
 * Service class for handling {@link Token}s.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class TokenService extends BaseService<Token> {

    @Inject
    private TokenRepository tokenRepository;

    /**
     * Finds the access token based on the value and fetches the related token.
     *
     * @param accessTokenValue
     *         - the value of the access token.
     * @param tokenType
     *         - the type of the token.
     * @return - with {@link Token}.
     * @throws BaseException
     *         - when an error occurs.
     */
    public Token findByValueFetchRelatedToken(String accessTokenValue, TokenType tokenType) throws BaseException {
        return wrapValidated(tokenRepository::findByValueFetchRelatedToken,
                accessTokenValue,
                tokenType,
                "findAccessTokenByValueFetchRelatedToken",
                "accessTokenValue",
                "tokenType");
    }

    /**
     * Finds the access token based on the value.
     *
     * @param accessTokenValue
     *         - the value of the access token.
     * @param tokenType
     *         - the type of the token.
     * @return - with {@link Token}.
     * @throws BaseException
     *         - when an error occurs.
     */
    public Token findByValue(String accessTokenValue, TokenType tokenType) throws BaseException {
        return wrapValidated(tokenRepository::findByValue, accessTokenValue, tokenType, "findByValue", "accessTokenValue", "tokenType");
    }
}
