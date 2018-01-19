package pd2.model.komandas;

import com.fasterxml.jackson.annotation.JsonProperty;
import pd2.model.mainas.Mainas;
import pd2.model.sodi.Sodi;
import pd2.model.speletaji.Pamatsastavs;
import pd2.model.speletaji.Speletaji;
import pd2.model.varti.Varti;

import java.io.Serializable;

public class Komanda implements Serializable {

    @JsonProperty(value = "Nosaukums")
    private String nosaukums;
    @JsonProperty(value = "Speletaji")
    private Speletaji speletaji;
    @JsonProperty(value = "Pamatsastavs")
    private Pamatsastavs pamatsastavs;
    @JsonProperty(value = "Varti")
    private Varti varti;
    @JsonProperty(value = "Mainas")
    private Mainas mainas;
    @JsonProperty(value = "Sodi")
    private Sodi sodi;

    public String getNosaukums() {
        return nosaukums;
    }

    public void setNosaukums(String nosaukums) {
        this.nosaukums = nosaukums;
    }

    public Speletaji getSpeletaji() {
        return speletaji;
    }

    public void setSpeletaji(Speletaji speletaji) {
        this.speletaji = speletaji;
    }

    public Pamatsastavs getPamatsastavs() {
        return pamatsastavs;
    }

    public void setPamatsastavs(Pamatsastavs pamatsastavs) {
        this.pamatsastavs = pamatsastavs;
    }

    public Varti getVarti() {
        return varti;
    }

    public void setVarti(Varti varti) {
        this.varti = varti;
    }

    public Mainas getMainas() {
        return mainas;
    }

    public void setMainas(Mainas mainas) {
        this.mainas = mainas;
    }

    public Sodi getSodi() {
        return sodi;
    }

    public void setSodi(Sodi sodi) {
        this.sodi = sodi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Komanda komanda = (Komanda) o;

        return nosaukums != null ? nosaukums.equals(komanda.nosaukums) : komanda.nosaukums == null;
    }

    @Override
    public int hashCode() {
        return nosaukums != null ? nosaukums.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Komanda{" +
                "nosaukums='" + nosaukums + '\'' +
                ", speletaji=" + speletaji +
                ", pamatsastavs=" + pamatsastavs +
                ", varti=" + varti +
                ", mainas=" + mainas +
                ", sodi=" + sodi +
                '}';
    }
}
