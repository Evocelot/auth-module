package hu.evocelot.auth.ts.sample.rest.builder;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.inject.Model;

import hu.evocelot.ts.auth.common.dto.DtoHelper;
import hu.icellmobilsoft.roaster.api.dto.BaseBuilder;
import hu.evocelot.auth.api.sample._1_0.rest.post.SampleCoreType;
import hu.evocelot.auth.api.sample._1_0.rest.post.SampleRequest;
import hu.evocelot.auth.api.sample._1_0.rest.post.SampleValueEnumType;

/**
 * {@link SampleRequest} builder class.
 *
 * @author mark.danisovszky
 * @since 0.1.0
 */
@Model
public class SampleRequestBuilder extends BaseBuilder<SampleRequest> {

    @Override
    public SampleRequest createEmpty() {
        SampleRequest request = new SampleRequest();
        request.withContext(DtoHelper.createContext());
        return request;
    }

    @PostConstruct
    public void init() {
        setDto(createEmpty());
    }

    /**
     * Get default filled dto
     *
     * @return valid dto with sample values
     */
    public SampleRequest getDefault() {
        getDto().setSample(new SampleCoreType().withColumnA("colA").withColumnB(SampleValueEnumType.VALUE_B));
        return getDto();
    }
}
