package pd2.model.mainas;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.io.Serializable;

public class Maina implements Serializable {

    @JsonProperty(value = "Laiks")
    //@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="HH:MM")
    private String laiks;
    @JsonProperty(value = "Nr1")
    private int nr1;
    @JsonProperty(value = "Nr2")
    private int nr2;

    public String getLaiks() {
        return laiks;
    }

    public void setLaiks(String laiks) {
        this.laiks = laiks;
    }

    public int getNr1() {
        return nr1;
    }

    public void setNr1(int nr1) {
        this.nr1 = nr1;
    }

    public int getNr2() {
        return nr2;
    }

    public void setNr2(int nr2) {
        this.nr2 = nr2;
    }

    @Override
    public String toString() {
        return "Maina{" +
                "laiks=" + laiks +
                ", nr1=" + nr1 +
                ", nr2=" + nr2 +
                '}';
    }
}
