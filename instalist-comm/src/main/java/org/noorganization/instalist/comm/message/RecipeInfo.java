
/*
 * Copyright 2016 Tino Siegmund, Michael Wodniok
 *
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.noorganization.instalist.comm.message;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.util.StdDateFormat;

import java.util.Date;
import java.util.UUID;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "uuid", "name", "lastchanged", "deleted" })
public class RecipeInfo extends EntityObject {

    private String  mUUID;
    private String  mName;
    private Date    mLastChanged;
    private Boolean mDeleted;

    @JsonProperty("uuid")
    public String getUUID() {
        return mUUID;
    }

    @JsonProperty("uuid")
    public void setUUID(String id) {
        this.mUUID = id;
    }

    public void setUUID(UUID uuid) {
        setUUID(uuid != null ? uuid.toString() : null);
    }

    public RecipeInfo withUUID(String uuid) {
        setUUID(uuid);
        return this;
    }

    public RecipeInfo withUUID(UUID uuid) {
        setUUID(uuid);
        return this;
    }

    @JsonProperty("name")
    public String getName() {
        return mName;
    }

    @JsonProperty("name")
    public void setName(String name) {
        mName = name;
    }

    public RecipeInfo withName(String name) {
        setName(name);
        return this;
    }

    @JsonProperty("lastchanged")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = StdDateFormat.DATE_FORMAT_STR_ISO8601)
    public Date getLastChanged() {
        return mLastChanged;
    }

    @JsonProperty("lastchanged")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = StdDateFormat.DATE_FORMAT_STR_ISO8601)
    public void setLastChanged(Date lastChanged) {
        mLastChanged = lastChanged;
    }

    public RecipeInfo withLastChanged(Date lastChanged) {
        setLastChanged(lastChanged);
        return this;
    }

    @JsonProperty("deleted")
    public Boolean getDeleted() {
        return mDeleted;
    }

    @JsonProperty("deleted")
    public void setDeleted(Boolean deleted) {
        mDeleted = deleted;
    }

    public RecipeInfo withDeleted(Boolean deleted) {
        setDeleted(deleted);
        return this;
    }

}
