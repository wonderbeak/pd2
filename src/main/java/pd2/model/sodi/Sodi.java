package pd2.model.sodi;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Sodi {
    @JsonProperty(value = "Sods")
    private List<Sods> sodi;

    public List<Sods> getSodi() {
        return sodi;
    }

    public void setSodi(List<Sods> sodi) {
        this.sodi = sodi;
    }

    @Override
    public String toString() {
        return "Sodi{" +
                "sodi=" + sodi +
                '}';
    }
}
