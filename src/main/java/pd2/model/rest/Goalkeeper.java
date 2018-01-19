package pd2.model.rest;

import pd2.model.speletaji.Speletajs;

import java.math.BigDecimal;
import java.util.Comparator;

/**
 * turnīra piecu labāko vārtsargu (sakārtoti pēc visā turnīrā vidēji vienā spēlē ielaisto vārtu skaita) saraksts.
 * Jānorāda vieta sarakstā pēc kārtas, spēlētāja vārds un uzvārds, komandas nosaukums, vidēji spēlē ielaisto vārtu skaits kā reāls skaitlis ar tieši vienu ciparu aiz decimālā punkta.
 * Ja kāds vārtsargs nav bijis vārtos nevienā spēlē, viņš šajā sarakstā nedrīkst parādīties.
 */
public class Goalkeeper {
    private int nr;
    private String uzvards;
    private String vards;
    private String komanda;
    private int games = 0;
    private int goals = 0;
    private double average = 0.0;
    private String active = "N";

    public Goalkeeper() {
        //this.games += 1;
    }

    public Goalkeeper(Speletajs sp, int goals) {
        this.nr = sp.getNr();
        this.uzvards = sp.getUzvards();
        this.vards = sp.getVards();
        this.komanda = sp.getKomanda();
        this.active = sp.getPamatsastavs();
        if (sp.getPamatsastavs().equals("Y")) {
            this.goals += goals;
            //this.average = count();
        }
    }

    public double count() {
        return (double) this.goals / this.games;
    }

    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
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

    public String getKomanda() {
        return komanda;
    }

    public void setKomanda(String komanda) {
        this.komanda = komanda;
    }

    public int getGames() {
        return games;
    }

    public void setGames(int games) {
        this.games += games;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals += goals;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public static Comparator<Goalkeeper> GoalkeeperComparator = new Comparator<Goalkeeper>() {

        @Override
        public int compare(Goalkeeper o1, Goalkeeper o2) {
            BigDecimal vg1 = (o1.getGoals() == 0) ? new BigDecimal(o1.getGames()) : new BigDecimal(o1.getGoals()).divide(new BigDecimal(o1.getGames()));
            BigDecimal vg2 = (o2.getGoals() == 0) ? new BigDecimal(o2.getGames()) : new BigDecimal(o2.getGoals()).divide(new BigDecimal(o2.getGames()));
            return vg2.compareTo(vg1);

        }
    };
}
