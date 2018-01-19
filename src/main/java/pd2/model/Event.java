package pd2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class Event implements Serializable {

    @JsonProperty(value = "Spele")
    private Spele spele;

    public Spele getSpele() {
        return spele;
    }

    public void setSpele(Spele spele) {
        this.spele = spele;
    }

    @Override
    public String toString() {
        return "Event{" +
                "spele=" + spele +
                '}';
    }
}
