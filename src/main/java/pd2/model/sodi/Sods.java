package pd2.model.sodi;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.io.Serializable;

public class Sods implements Serializable {

    @JsonProperty("Laiks")
    private String laiks;
    @JsonProperty("Nr")
    private Integer nr;

    public String getLaiks() {
        return laiks;
    }

    public void setLaiks(String laiks) {
        this.laiks = laiks;
    }

    public Integer getNr() {
        return nr;
    }

    public void setNr(Integer nr) {
        this.nr = nr;
    }

    @Override
    public String toString() {
        return "Sods{" +
                "laiks=" + laiks +
                ", nr=" + nr +
                '}';
    }
}
