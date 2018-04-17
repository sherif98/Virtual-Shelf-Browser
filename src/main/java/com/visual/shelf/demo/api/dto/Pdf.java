
package com.visual.shelf.demo.api.dto;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "isAvailable",
    "downloadLink"
})
public class Pdf {

    @JsonProperty("isAvailable")
    private Boolean isAvailable;
    @JsonProperty("downloadLink")
    private String downloadLink;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("isAvailable")
    public Boolean getIsAvailable() {
        return isAvailable;
    }

    @JsonProperty("isAvailable")
    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    @JsonProperty("downloadLink")
    public String getDownloadLink() {
        return downloadLink;
    }

    @JsonProperty("downloadLink")
    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("isAvailable", isAvailable).append("downloadLink", downloadLink).append("additionalProperties", additionalProperties).toString();
    }

}
