public class Events {

    double probability;
    String text;
    int life;

    public Events() {
    }

    public Events(double probability, String text, int life) {
        this.probability = probability;
        this.text = text;
        this.life = life;
    }

    public int apply(int hp, Events events) {

        double chance = Math.random();

        if (chance <= events.probability) {

            System.out.println(text);
            hp += events.life;
        }

        if (hp > 200) {
            hp = 200;
        }

        return hp;
    }
}
