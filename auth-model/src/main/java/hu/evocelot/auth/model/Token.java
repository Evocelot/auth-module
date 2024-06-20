package hu.evocelot.auth.model;

import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import hu.evocelot.auth.model.enums.TokenType;
import hu.icellmobilsoft.coffee.model.base.javatime.AbstractIdentifiedAuditEntity;

/**
 * The token entity.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@Entity
@Table(name = "token")
public class Token extends AbstractIdentifiedAuditEntity {

    /**
     * The value of the token.
     */
    @Size(max = 100)
    @NotNull
    @Column(name = "token", length = 100, nullable = false)
    private String token;

    /**
     * The type of the token.
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type_id", nullable = false)
    private TokenType tokenType;

    /**
     * The token creation time.
     */
    @NotNull
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    /**
     * The token expiration time.
     */
    @NotNull
    @Column(name = "expires_at", nullable = false)
    private OffsetDateTime expiresAt;

    /**
     * The owner of the token.
     */
    @NotNull
    @JoinColumn(name = "security_user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private SecurityUser securityUser;

    /**
     * The owner token.
     */
    @JoinColumn(name = "related_token_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Token relatedToken;

    /**
     * For getting the token.
     *
     * @return - with the token value.
     */
    public String getToken() {
        return token;
    }

    /**
     * For setting the token.
     *
     * @param token
     *         - the token to set.
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * For getting the tokenType.
     *
     * @return - with the tokenType value.
     */
    public TokenType getTokenType() {
        return tokenType;
    }

    /**
     * For setting the tokenType.
     *
     * @param tokenType
     *         - the tokenType to set.
     */
    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    /**
     * For getting the createdAt.
     *
     * @return - with the createdAt value.
     */
    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * For setting the createdAt.
     *
     * @param createdAt
     *         - the createdAt to set.
     */
    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * For getting the expiresAt.
     *
     * @return - with the expiresAt value.
     */
    public OffsetDateTime getExpiresAt() {
        return expiresAt;
    }

    /**
     * For setting the expiresAt.
     *
     * @param expiresAt
     *         - the expiresAt to set.
     */
    public void setExpiresAt(OffsetDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    /**
     * For getting the securityUser.
     *
     * @return - with the securityUser value.
     */
    public SecurityUser getSecurityUser() {
        return securityUser;
    }

    /**
     * For setting the securityUser.
     *
     * @param securityUser
     *         - the securityUser to set.
     */
    public void setSecurityUser(SecurityUser securityUser) {
        this.securityUser = securityUser;
    }

    /**
     * For getting the relatedToken.
     *
     * @return - with the relatedToken.
     */
    public Token getRelatedToken() {
        return relatedToken;
    }

    /**
     * For setting the relatedToken.
     *
     * @param relatedToken
     *         - the relatedToken to set.
     */
    public void setRelatedToken(Token relatedToken) {
        this.relatedToken = relatedToken;
    }
}
