package hu.evocelot.auth.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import hu.icellmobilsoft.coffee.model.base.javatime.AbstractIdentifiedAuditEntity;

/**
 * The Permission entity.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@Entity
@Table(name = "permission")
public class Permission extends AbstractIdentifiedAuditEntity {

    /**
     * The name of the permission.
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "name", length = 100, nullable = false, unique = true)
    private String name;

    /**
     * The description of the permission.
     */
    @Size(max = 200)
    @Column(name = "description", length = 200, nullable = true)
    private String description;

    /**
     * For getting the name.
     *
     * @return - with the name.
     */
    public String getName() {
        return name;
    }

    /**
     * For setting the name.
     *
     * @param name
     *         - the value to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * For getting the description.
     *
     * @return - with the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * For setting the description.
     *
     * @param description
     *         - the value to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
