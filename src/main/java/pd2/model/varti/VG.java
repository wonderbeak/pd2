package pd2.model.varti;

import com.fasterxml.jackson.annotation.JsonProperty;
import pd2.model.speletaji.Speletajs;

import java.io.Serializable;
import java.util.List;

public class VG implements Serializable {

    @JsonProperty(value = "Laiks")
    private String laiks;
    @JsonProperty(value = "P")
    private List<Speletajs> p;
    @JsonProperty(value = "Nr")
    private Integer nr;
    @JsonProperty(value = "Sitiens")
    private String sitiens;

    public String getLaiks() {
        return laiks;
    }

    public void setLaiks(String laiks) {
        this.laiks = laiks;
    }

    public List<Speletajs> getP() {
        return p;
    }

    public void setP(List<Speletajs> p) {
        this.p = p;
    }

    public Integer getNr() {
        return nr;
    }

    public void setNr(Integer nr) {
        this.nr = nr;
    }

    public String getSitiens() {
        return sitiens;
    }

    public void setSitiens(String sitiens) {
        this.sitiens = sitiens;
    }

    @Override
    public String toString() {
        return "VG{" +
                "laiks='" + laiks + '\'' +
                ", p=" + p +
                ", nr=" + nr +
                ", sitiens='" + sitiens + '\'' +
                '}';
    }
}
