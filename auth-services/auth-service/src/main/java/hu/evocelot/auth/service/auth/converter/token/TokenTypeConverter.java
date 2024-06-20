package hu.evocelot.auth.service.auth.converter.token;

import jakarta.enterprise.context.ApplicationScoped;

import hu.evocelot.auth.api.common._1_0.common.TokenType;
import hu.evocelot.auth.api.common._1_0.common.TokenTypeEnumType;
import hu.evocelot.auth.model.Token;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;
import hu.icellmobilsoft.coffee.system.jpa.converter.IEntityConverter;
import hu.icellmobilsoft.coffee.tool.utils.enums.EnumUtil;

/**
 * Converter class that handles conversion between {@link Token} and {@link TokenType}.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class TokenTypeConverter implements IEntityConverter<Token, TokenType> {
    @Override
    public TokenType convert(Token entity) throws BaseException {
        TokenType dto = new TokenType();
        convert(dto, entity);
        return dto;
    }

    @Override
    public Token convert(TokenType tokenType) throws BaseException {
        Token entity = new Token();
        convert(entity, tokenType);
        return entity;
    }

    @Override
    public void convert(TokenType destinationDto, Token sourceEntity) throws BaseException {
        destinationDto.setTokenId(sourceEntity.getId());
        destinationDto.setType(EnumUtil.convert(sourceEntity.getTokenType(), TokenTypeEnumType.class));
        destinationDto.setTokenValue(sourceEntity.getToken());
        destinationDto.setCreatedAt(sourceEntity.getCreatedAt());
        destinationDto.setExpiresAt(sourceEntity.getExpiresAt());
    }

    @Override
    public void convert(Token destinationEntity, TokenType sourceDto) throws BaseException {
        destinationEntity.setTokenType(EnumUtil.convert(sourceDto.getType(), hu.evocelot.auth.model.enums.TokenType.class));
        destinationEntity.setToken(sourceDto.getTokenValue());
        destinationEntity.setCreatedAt(sourceDto.getCreatedAt());
        destinationEntity.setExpiresAt(sourceDto.getExpiresAt());
    }
}
