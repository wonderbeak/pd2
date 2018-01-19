package pd2.model.rest;

import pd2.model.komandas.Komanda;

import java.io.Serializable;
import java.util.Comparator;

public class Participant implements Comparator<Participant>, Serializable {
    private String name;
    private Integer win = 0;
    private Integer lose = 0;
    private Integer postWin = 0;
    private Integer postLose = 0;
    private Integer vg = 0;
    private Integer lg = 0;
    private Integer points = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWin() {
        return win;
    }

    public void setWin(Integer win) {
        this.win += win;
    }

    public Integer getLose() {
        return lose;
    }

    public void setLose(Integer lose) {
        this.lose += lose;
    }

    public Integer getPostWin() {
        return postWin;
    }

    public void setPostWin(Integer postWin) {
        this.postWin += postWin;
    }

    public Integer getPostLose() {
        return postLose;
    }

    public void setPostLose(Integer postLose) {
        this.postLose += postLose;
    }

    public Integer getVg() {
        return vg;
    }

    public void setVg(Integer vg) {
        this.vg += vg;
    }

    public Integer getLg() {
        return lg;
    }

    public void setLg(Integer lg) {
        this.lg += lg;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points += points;
    }

    public static Comparator<Participant> TableComparator = new Comparator<Participant>() {

        @Override
        public int compare(Participant o1, Participant o2) {
            Integer vg1 = o1.getPoints();
            Integer vg2 = o2.getPoints();
            int sComp = vg2.compareTo(vg1);
            if (sComp != 0) {
                return sComp;
            } else {
                Integer x1 = o1.getVg();
                Integer x2 = o2.getVg();
                return x2.compareTo(x1);
            }
        }
    };

    @Override
    public int compare(Participant o1, Participant o2) {
        Integer vg1 = o1.getPoints();
        Integer vg2 = o2.getPoints();
        int sComp = vg2.compareTo(vg1);
        if (sComp != 0) {
            return sComp;
        } else {
            Integer x1 = o1.getVg();
            Integer x2 = o2.getVg();
            return x2.compareTo(x1);
        }
    }
}
