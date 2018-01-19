package pd2.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import pd2.model.komandas.Komanda;
import pd2.model.t.T;

import java.io.Serializable;
import java.util.List;

public class Spele implements Serializable {

    @JsonProperty(value = "Laiks")
    //@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy/mm/dd")
    private String laiks;
    @JsonProperty(value = "Skatitaji")
    private int skatitaji;
    @JsonProperty(value = "Vieta")
    private String vieta;
    @JsonProperty(value = "T")
    private List<T> t;
    @JsonProperty(value = "Komanda")
    private List<Komanda> komanda;
    @JsonProperty(value = "VT")
    private T vt;

    public String getLaiks() {
        return laiks;
    }

    public void setLaiks(String laiks) {
        this.laiks = laiks;
    }

    public int getSkatitaji() {
        return skatitaji;
    }

    public void setSkatitaji(int skatitaji) {
        this.skatitaji = skatitaji;
    }

    public String getVieta() {
        return vieta;
    }

    public void setVieta(String vieta) {
        this.vieta = vieta;
    }

    public List<T> getT() {
        return t;
    }

    public void setT(List<T> t) {
        this.t = t;
    }

    public List<Komanda> getKomanda() {
        return komanda;
    }

    public void setKomanda(List<Komanda> komanda) {
        this.komanda = komanda;
    }

    public T getVt() {
        return vt;
    }

    public void setVt(T vt) {
        this.vt = vt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Spele spele = (Spele) o;

        if (skatitaji != spele.skatitaji) return false;
        if (laiks != null ? !laiks.equals(spele.laiks) : spele.laiks != null) return false;
        if (vieta != null ? !vieta.equals(spele.vieta) : spele.vieta != null) return false;
        if (t != null ? !t.equals(spele.t) : spele.t != null) return false;
        if (komanda != null ? !komanda.equals(spele.komanda) : spele.komanda != null) return false;
        return vt != null ? vt.equals(spele.vt) : spele.vt == null;
    }

    @Override
    public int hashCode() {
        int result = laiks != null ? laiks.hashCode() : 0;
        result = 31 * result + skatitaji;
        result = 31 * result + (vieta != null ? vieta.hashCode() : 0);
        result = 31 * result + (t != null ? t.hashCode() : 0);
        result = 31 * result + (komanda != null ? komanda.hashCode() : 0);
        result = 31 * result + (vt != null ? vt.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Spele{" +
                "laiks='" + laiks + '\'' +
                ", skatitaji=" + skatitaji +
                ", vieta='" + vieta + '\'' +
                ", t=" + t +
                ", komanda=" + komanda +
                ", vt=" + vt +
                '}';
    }
}
