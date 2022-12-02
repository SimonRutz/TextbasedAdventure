public class Player {

    public String name = "";
    public int hp = 0;
    public boolean finished = false;
    private Place place = new Place("noch nicht im Spiel");

    public Player(String name, int hp) {
        this.name = name;
        this.hp = hp;
        this.finished = false;
    }

    public Player() {
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public Place where() {
        return place;
    }

    public void goTo(Place next) {
        this.place = next;
    }

    public String toString(String name, int hp) {

        int life = hp/20;
        int missingLife = (200/20)-life;
        String lifeBar = "";
        for (int i = 0; i < life; i++) {
            lifeBar += "#";
        }
        for (int j = 0; j < missingLife; j++) {
            lifeBar += " ";
        }


        return "" + name + "     [" + lifeBar + "] " + hp + "/200   Position: " + place;
    }

}
