package pd2.model.rest;

public class Place {
    private String name;
    private int croud = 0;
    private int games = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCroud() {
        return croud;
    }

    public void setCroud(int croud) {
        this.croud += croud;
    }

    public int getGames() {
        return games;
    }

    public void setGames(int games) {
        this.games += games;
    }


    public Place(String name, int croud, int games) {
        this.name = name;
        this.croud = croud;
        this.games = games;
    }

    public Place() {
    }

    @Override
    public String toString() {
        return "Place{" +
                "name='" + name + '\'' +
                ", croud=" + croud +
                ", games=" + games +
                '}';
    }
}
