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

    /**
     * The fileInputStream that contains the file to upload.
     */
    @FormParam("fileInputStream")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    private InputStream fileInputStream;

    /**
     * The extension of the file.
     */
    @FormParam("extension")
    private String extension;

    /**
     * For getting the fileInputStream.
     *
     * @return - with the fileInputStream.
     */
    public InputStream getFileInputStream() {
        return fileInputStream;
    }

    /**
     * For setting the fileInputStream.
     *
     * @param fileInputStream
     *         - the fileInputStream to set.
     */
    public void setFileInputStream(InputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

    /**
     * For getting the extension.
     *
     * @return - with the extension.
     */
    public String getExtension() {
        return extension;
    }

    /**
     * For setting the extension.
     *
     * @param extension
     *         - the extension to set.
     */
    public void setExtension(String extension) {
        this.extension = extension;
    }
}
