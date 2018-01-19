package pd2.model.speletaji;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Speletaji {
    @JsonProperty(value = "Speletajs")
    private List<Speletajs> speletaji;

    public List<Speletajs> getSpeletaji() {
        return speletaji;
    }

    public void setSpeletaji(List<Speletajs> speletaji) {
        this.speletaji = speletaji;
    }

    @Override
    public String toString() {
        return "Speletaji{" +
                "speletaji=" + speletaji +
                '}';
    }
}
