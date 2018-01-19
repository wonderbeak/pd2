package pd2.model.varti;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class Varti implements Serializable {
    @JsonProperty(value = "VG")
    private List<VG> varti;

    public List<VG> getVarti() {
        return varti;
    }

    public void setVarti(List<VG> varti) {
        this.varti = varti;
    }

    @Override
    public String toString() {
        return "Varti{" +
                "varti=" + varti +
                '}';
    }
}
