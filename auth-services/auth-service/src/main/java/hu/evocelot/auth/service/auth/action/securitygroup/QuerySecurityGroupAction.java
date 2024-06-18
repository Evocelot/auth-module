package hu.evocelot.auth.service.auth.action.securitygroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import hu.evocelot.auth.api.securitygroup._1_0.rest.security_group.SecurityGroupEntityType;
import hu.evocelot.auth.api.securitygroupquery._1_0.rest.security_group_query.SecurityGroupQueryFilterParamsType;
import hu.evocelot.auth.api.securitygroupquery._1_0.rest.security_group_query.SecurityGroupQueryOrderParamType;
import hu.evocelot.auth.api.securitygroupquery._1_0.rest.security_group_query.SecurityGroupQueryRequest;
import hu.evocelot.auth.api.securitygroupquery._1_0.rest.security_group_query.SecurityGroupQueryResponse;
import hu.evocelot.auth.common.system.rest.action.BaseAction;
import hu.evocelot.auth.model.SecurityGroup;
import hu.evocelot.auth.service.auth.converter.securitygroup.SecurityGroupEntityTypeConverter;
import hu.evocelot.auth.service.auth.service.SecurityGroupQueryService;
import hu.icellmobilsoft.coffee.dto.common.common.QueryRequestDetails;
import hu.icellmobilsoft.coffee.dto.exception.InvalidParameterException;
import hu.icellmobilsoft.coffee.jpa.sql.paging.PagingResult;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;

/**
 * Action class for listing {@link SecurityGroup}.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class QuerySecurityGroupAction extends BaseAction {

    @Inject
    private SecurityGroupQueryService securityGroupQueryService;

    @Inject
    private SecurityGroupEntityTypeConverter securityGroupEntityTypeConverter;

    /**
     * For listing {@link SecurityGroup} with filtering, sorting and paging.
     *
     * @param securityGroupQueryRequest
     *         - the request that contains information about the query.
     * @return - with {@link SecurityGroupQueryResponse} that contains the relevant security groups.
     * @throws BaseException
     *         - when an error occurs.
     */
    public SecurityGroupQueryResponse querySecurityGroup(SecurityGroupQueryRequest securityGroupQueryRequest) throws BaseException {
        if (Objects.isNull(securityGroupQueryRequest)) {
            throw new InvalidParameterException("The securityGroupQueryRequest is null!");
        }

        SecurityGroupQueryFilterParamsType filterParams = securityGroupQueryRequest.getFilterParams();
        List<SecurityGroupQueryOrderParamType> orderParams = securityGroupQueryRequest.getOrderParams();
        QueryRequestDetails pagingParams = securityGroupQueryRequest.getPagingDetails();

        PagingResult<SecurityGroup> pagingResult = securityGroupQueryService.findByQueryParams(filterParams, orderParams, pagingParams);

        SecurityGroupQueryResponse response = new SecurityGroupQueryResponse();
        response.setPagingDetails(securityGroupQueryService.getPagingDetails(pagingResult.getDetails()));
        response.withResults(convertToSecurityGroupEntityTypeList(pagingResult.getResults()));
        handleSuccessResultType(response, securityGroupQueryRequest);

        return response;
    }

    private List<SecurityGroupEntityType> convertToSecurityGroupEntityTypeList(List<SecurityGroup> results) throws BaseException {
        List<SecurityGroupEntityType> rows = new ArrayList<>();

        for (SecurityGroup securityGroup : results) {
            rows.add(securityGroupEntityTypeConverter.convert(securityGroup));
        }

        return rows;
    }
}
