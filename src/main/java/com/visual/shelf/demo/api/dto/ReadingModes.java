
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
    "text",
    "image"
})
public class ReadingModes {

    @JsonProperty("text")
    private Boolean text;
    @JsonProperty("image")
    private Boolean image;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("text")
    public Boolean getText() {
        return text;
    }

    @JsonProperty("text")
    public void setText(Boolean text) {
        this.text = text;
    }

    @JsonProperty("image")
    public Boolean getImage() {
        return image;
    }

    @JsonProperty("image")
    public void setImage(Boolean image) {
        this.image = image;
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
        return new ToStringBuilder(this).append("text", text).append("image", image).append("additionalProperties", additionalProperties).toString();
    }

}
