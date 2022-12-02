public class Road {

    Events events = new Events();

    private String description;
    private Place target;

    public Road(String description, Place target, Events events) {
        this.description = description;
        this.target = target;
        this.events = events;
    }

    public Road() {
    }

    public String getDescription() {
        return description;
    }

    public Place getTarget() {
        return target;
    }

    public Events getEvents() {
        return events;
    }

    public String toString() {
        return description + " -> " + target;
    }

    public int event () {

        double chance = Math.random();
        int eventID = 0;

        if (chance < 0.25 & chance > 0) {
            Game.hp -= 5;
            eventID = 1;
        } else if (chance < 0.34 & chance > 0.25) {
            Game.hp /= 2;
            eventID = 2;
        } else if (chance < 0.52 & chance > 0.34) {
            Game.hp += (200-Game.hp)/4;
            eventID = 3;
        }

        return eventID;
    }

}