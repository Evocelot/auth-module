package hu.evocelot.auth.api.rest.jee10.dto;

import java.io.InputStream;
import java.io.Serializable;

import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

import hu.icellmobilsoft.coffee.dto.common.commonservice.BaseRequestType;

/**
 * For uploading profile pictures.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
public class UploadProfilePictureRequest extends BaseRequestType implements Serializable {

    @FormParam("fileInputStream")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    private InputStream fileInputStream;

    @FormParam("extension")
    private String extension;

    public InputStream getFileInputStream() {
        return fileInputStream;
    }

    public void setFileInputStream(InputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
