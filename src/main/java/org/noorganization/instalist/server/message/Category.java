
package org.noorganization.instalist.server.message;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.noorganization.instalist.server.support.DateHelper;

import java.util.Date;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "uuid",
    "name",
    "lastchanged",
    "deleted"
})
public class Category extends EntityObject {

    private String mUUID;
    private String mName;
    private String mLastChanged;
    private Boolean mDeleted;

    @JsonProperty("uuid")
    public String getUUID() {
        return mUUID;
    }

    @JsonProperty("uuid")
    public void setUUID(String id) {
        this.mUUID = id;
    }

    public Category withId(String id) {
        this.mUUID = id;
        return this;
    }

    @JsonProperty("name")
    public String getName() {
        return mName;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.mName = name;
    }

    public Category withName(String name) {
        this.mName = name;
        return this;
    }

    @JsonProperty("lastchanged")
    public String getLastChanged() {
        return mLastChanged;
    }

    @JsonProperty("lastchanged")
    public void setLastChanged(String lastChanged) {
        this.mLastChanged = lastChanged;
    }

    public void setLastChanged(Date _lastChanged) {
        this.mLastChanged = DateHelper.writeDate(_lastChanged);
    }

    public Category withLastChanged(String lastChanged) {
        this.mLastChanged = lastChanged;
        return this;
    }

    public Category withLastChanged(Date _lastChanged) {
        this.mLastChanged = DateHelper.writeDate(_lastChanged);
        return this;
    }

    @JsonProperty("deleted")
    public Boolean getDeleted() {
        return mDeleted;
    }

    @JsonProperty("deleted")
    public void setDeleted(Boolean _deleted) {
        mDeleted = _deleted;
    }

    public Category withDeleted(Boolean _deleted) {
        mDeleted = _deleted;
        return this;
    }
}