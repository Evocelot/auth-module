package hu.evocelot.auth.model.sample;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

import hu.evocelot.auth.model.sample.enums.SampleStatus;
import hu.evocelot.auth.model.sample.enums.SampleValue;
import hu.icellmobilsoft.coffee.model.base.javatime.AbstractIdentifiedAuditEntity;

/**
 * Sample entity.
 *
 * @author mark.danisovszky
 * @since 0.1.0
 */
@Entity
@Table(name = "SAMPLE")
public class SampleEntity extends AbstractIdentifiedAuditEntity {

    private static final long serialVersionUID = 1L;

    /**
     * A példa státusz.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", length = 10)
    private SampleStatus status;

    /**
     * A példa érték.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "ENUM_VALUE", length = 10)
    private SampleValue value;

    /**
     * A példa bemeneti érték.
     */
    @Column(name = "INPUT_VALUE", length = 30)
    @Size(max = 30)
    private String inputValue;

    /**
     * A példa dátum.
     */
    @Column(name = "LOCAL_DATE")
    private LocalDateTime localDateTime;

    /**
     * A példa módosítási idő.
     */
    @Column(name = "MOD_LOCAL_DATE", insertable = false)
    private LocalDate modLocalDate;

    /**
     * For getting the status.
     *
     * @return - with the status.
     */
    public SampleStatus getStatus() {
        return status;
    }

    /**
     * For setting the status.
     *
     * @param status
     *         - the status to set.
     */
    public void setStatus(SampleStatus status) {
        this.status = status;
    }

    /**
     * For getting the value.
     *
     * @return - with the value.
     */
    public SampleValue getValue() {
        return value;
    }

    /**
     * For setting the value.
     *
     * @param value
     *         - the value to set.
     */
    public void setValue(SampleValue value) {
        this.value = value;
    }

    /**
     * Fot getting the input value.
     *
     * @return - with the input value.
     */
    public String getInputValue() {
        return inputValue;
    }

    /**
     * For setting the input value.
     *
     * @param inputValue
     *         - the input value to set.
     */
    public void setInputValue(String inputValue) {
        this.inputValue = inputValue;
    }

    /**
     * For getting the local date time.
     *
     * @return - with the local date time.
     */
    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    /**
     * For setting the local date time.
     *
     * @param localDateTime
     *         - the local date time to set.
     */
    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    /**
     * For getting the mod local date.
     *
     * @return - with the mod local date.
     */
    public LocalDate getModLocalDate() {
        return modLocalDate;
    }

    /**
     * For setting the mod local date.
     *
     * @param modLocalDate
     *         - the mod local date to set.
     */
    public void setModLocalDate(LocalDate modLocalDate) {
        this.modLocalDate = modLocalDate;
    }
}
