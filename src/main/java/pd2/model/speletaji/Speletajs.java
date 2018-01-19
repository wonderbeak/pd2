package pd2.model.speletaji;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import pd2.utils.Utilities;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class Speletajs implements Serializable, Comparator<Speletajs> {

    @JsonProperty("Nr")
    private int nr;
    @JsonProperty("Loma")
    private String loma;
    @JsonProperty("Uzvards")
    private String uzvards;
    @JsonProperty("Vards")
    private String vards;

    private String komanda;
    private int sodi = 0;
    private Integer vg = 0;
    private int p = 0;
    private int mainas = 0;
    private String pamatsastavs = "N";
    private int red = 0;
    private int yellow = 0;

    private long total = 0;

    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    public String getLoma() {
        return loma;
    }

    public void setLoma(String loma) {
        this.loma = loma;
    }

    public String getUzvards() {
        return uzvards;
    }

    public void setUzvards(String uzvards) {
        this.uzvards = uzvards;
    }

    public String getVards() {
        return vards;
    }

    public void setVards(String vards) {
        this.vards = vards;
    }

    public Integer getVg() {
        return vg;
    }

    public void setVg(Integer vg) {
        this.vg += vg;
    }

    public String getKomanda() {
        return komanda;
    }

    public void setKomanda(String komanda) {
        this.komanda = komanda;
    }

    public int getSodi() {
        return sodi;
    }

    public void setSodi(int sodi) {
        this.sodi += sodi;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p += p;
    }

    public int getMainas() {
        return mainas;
    }

    public void setMainas(int mainas) {
        this.mainas += mainas;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(Long string) {
        this.total += string;
    }

    public String getPamatsastavs() {
        return pamatsastavs;
    }

    public void setPamatsastavs(String pamatsastavs) {
        this.pamatsastavs = pamatsastavs;
    }

       public int compareTo(Speletajs compareSpeletajs) {
        int compareVG = ((Speletajs) compareSpeletajs).getVg();
        return vg - this.vg;

    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red += red;
    }

    public int getYellow() {
        return yellow;
    }

    public void setYellow(int yellow) {
        this.yellow += yellow;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Speletajs speletajs = (Speletajs) o;

        return nr == speletajs.nr;
    }

    @Override
    public int hashCode() {
        return nr;
    }

    public static Comparator<Speletajs> SpeletajsComparator = new Comparator<Speletajs>() {

        @Override
        public int compare(Speletajs o1, Speletajs o2) {
            Integer vg1 = o1.getVg();
            Integer vg2 = o2.getVg();
            int sComp = vg2.compareTo(vg1);
            if (sComp != 0) {
                return sComp;
            } else {
                Integer x1 = o1.getP();
                Integer x2 = o2.getP();
                return x2.compareTo(x1);
            }
        }
    };

    @Override
    public int compare(Speletajs o1, Speletajs o2) {
        Integer vg1 = o1.getVg();
        Integer vg2 = o2.getVg();
        int sComp = vg2.compareTo(vg1);
        if (sComp != 0) {
            return sComp;
        } else {
            Integer x1 = o1.getP();
            Integer x2 = o2.getP();
            return x2.compareTo(x1);
        }
    }

    @Override
    public String toString() {
        return "Speletajs{" +
                "nr=" + nr +
                ", loma='" + loma + '\'' +
                ", uzvards='" + uzvards + '\'' +
                ", vards='" + vards + '\'' +
                ", komanda='" + komanda + '\'' +
                ", sodi=" + sodi +
                ", vg=" + vg +
                ", p=" + p +
                ", mainas=" + mainas +
                ", pamatsastavs='" + pamatsastavs + '\'' +
                ", red=" + red +
                ", yellow=" + yellow +
                ", total='" + total + '\'' +
                '}';
    }
}
