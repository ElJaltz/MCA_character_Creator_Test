package net.mca.resources.data.skin;

import com.google.gson.JsonObject;
import net.mca.entity.ai.relationship.Gender;

public class FacialHair extends SkinListEntry {
    public FacialHair(String identifier) {
        super(identifier);
    }

    public FacialHair(String identifier, JsonObject object) {
        super(identifier, object);
    }

    public FacialHair(String identifier, Gender gender, float chance) {
        super(identifier, gender, chance);
    }
}
