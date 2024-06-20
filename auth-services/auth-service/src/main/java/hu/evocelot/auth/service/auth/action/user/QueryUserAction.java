package hu.evocelot.auth.service.auth.action.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import hu.evocelot.auth.api.user._1_0.rest.user.UserType;
import hu.evocelot.auth.api.userquery._1_0.rest.user_query.UserQueryFilterParamsType;
import hu.evocelot.auth.api.userquery._1_0.rest.user_query.UserQueryOrderParamType;
import hu.evocelot.auth.api.userquery._1_0.rest.user_query.UserQueryRequest;
import hu.evocelot.auth.api.userquery._1_0.rest.user_query.UserQueryResponse;
import hu.evocelot.auth.common.system.rest.action.BaseAction;
import hu.evocelot.auth.model.Partner;
import hu.evocelot.auth.service.auth.converter.partner.PartnerEntityTypeConverter;
import hu.evocelot.auth.service.auth.converter.securityuser.SecurityUserEntityTypeConverter;
import hu.evocelot.auth.service.auth.service.UserQueryService;
import hu.icellmobilsoft.coffee.dto.common.common.QueryRequestDetails;
import hu.icellmobilsoft.coffee.dto.exception.InvalidParameterException;
import hu.icellmobilsoft.coffee.jpa.sql.paging.PagingResult;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;

/**
 * Action class for listing users. In the background, this class works with {@link Partner}.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class QueryUserAction extends BaseAction {

    @Inject
    private UserQueryService userQueryService;

    @Inject
    private PartnerEntityTypeConverter partnerEntityTypeConverter;

    @Inject
    private SecurityUserEntityTypeConverter securityUserEntityTypeConverter;

    /**
     * For listing users (full detailed partner) with filtering, sorting and paging.
     *
     * @param userQueryRequest
     *         - the request that contains information about the query.
     * @return - with {@link UserQueryResponse} that contains the relevant users (full detailed partner).
     * @throws BaseException
     *         - when an error occurs.
     */
    public UserQueryResponse queryUser(UserQueryRequest userQueryRequest) throws BaseException {
        if (Objects.isNull(userQueryRequest)) {
            throw new InvalidParameterException("The userQueryRequest is null!");
        }

        UserQueryFilterParamsType filterParams = userQueryRequest.getFilterParams();
        List<UserQueryOrderParamType> orderParams = userQueryRequest.getOrderParams();
        QueryRequestDetails pagingParams = userQueryRequest.getPagingDetails();

        PagingResult<Partner> pagingResult = userQueryService.findByQueryParams(filterParams, orderParams, pagingParams);

        UserQueryResponse response = new UserQueryResponse();
        response.setPagingDetails(userQueryService.getPagingDetails(pagingResult.getDetails()));
        response.withResults(convertResults(pagingResult.getResults()));
        handleSuccessResultType(response, userQueryRequest);

        return response;
    }

    private List<UserType> convertResults(List<Partner> results) throws BaseException {
        List<UserType> rows = new ArrayList<>();

        for (Partner result : results) {
            UserType row = new UserType();

            row.setPartner(partnerEntityTypeConverter.convert(result));
            row.setSecurityUser(securityUserEntityTypeConverter.convert(result.getSecurityUser()));
            row.setSecurityGroupName(result.getSecurityUser().getSecurityGroup().getName());
            rows.add(row);
        }

        return rows;
    }
}
