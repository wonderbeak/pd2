package pd2.model.mainas;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Mainas {
    @JsonProperty(value = "Maina")
    private List<Maina> maina;

    public List<Maina> getMaina() {
        return maina;
    }

    public void setMaina(List<Maina> maina) {
        this.maina = maina;
    }

    @Override
    public String toString() {
        return "Mainas{" +
                "mainas=" + maina +
                '}';
    }
}
