package pd2.model.t;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.io.Serializable;
import java.util.Comparator;

@JsonRootName(value = "T")
public class T implements Serializable {

    @JsonProperty("Uzvards")
    private String uzvards;
    @JsonProperty("Vards")
    private String vards;

    private int sodi = 0;
    private String vt = "";

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

    public int getSodi() {
        return sodi;
    }

    public void setSodi(int sodi) {
        this.sodi += sodi;
    }

    public String getVt() {
        return vt;
    }

    public void setVt(String vt) {
        this.vt = vt;
    }

    public static Comparator<T> JudgeComparator = new Comparator<T>() {

        @Override
        public int compare(T o1, T o2) {
            Integer t1 = o1.getSodi();
            Integer t2 = o2.getSodi();
            return t2.compareTo(t1);
        }
    };

    @Override
    public String toString() {
        return "T{" +
                "uzvards='" + uzvards + '\'' +
                ", vards='" + vards + '\'' +
                ", sodi=" + sodi +
                ", vt='" + vt + '\'' +
                '}';
    }
}
