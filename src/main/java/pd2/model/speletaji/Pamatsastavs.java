package pd2.model.speletaji;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Pamatsastavs {
    @JsonProperty(value = "Speletajs")
    private List<Speletajs> speletajs;

    public List<Speletajs> getSpeletajs() {
        return speletajs;
    }

    public void setSpeletajs(List<Speletajs> speletajs) {
        this.speletajs = speletajs;
    }

    @Override
    public String toString() {
        return "Pamatsastavs{" +
                "speletajs=" + speletajs +
                '}';
    }
}
