import java.util.*;

public class World {

        Game game = new Game();
        Player player = new Player();

        Place dorf = new Place("Kleines Dörfchen");
        Place weg = new Place("Steiniger Weg");
        Place wald = new Place("Wald");
        Place see = new Place("See");
        Place stadt = new Place("Stadt");
        Place fluss = new Place("Fluss");
        Place waldHuette = new Place("Waldhütte");
        Place schlucht = new Place("Schlucht");
        Place strandHaus = new Place("Strandhaus");

        void createRoads () {
            List<Road> dorfRoads = Arrays.asList(new Road("verlasse Dorf", weg, new Events(0.6, "Dir fällt ein Ast auf den Kopf\n-5 HP", -5 )));
            dorf.setRoads(dorfRoads);

            List<Road> strandHausRoad = Arrays.asList(new Road("Verlasse das Haus", see, new Events(0.4, "Du hast vergessen Schuhe anzuziehen und verbrennst dir nun die Fusssohlen auf dem Sand\n-15 HP", -15)));
            strandHaus.setRoads(strandHausRoad);

            List<Road> wegRoads = Arrays.asList(new Road("betrete Wald", wald, new Events(0.6, "Du stolperst über eine Wurzel\n-20 HP", -20)));
            weg.setRoads(wegRoads);


            List<Road> waldRoads = Arrays.asList(
                    new Road("gehe zurück", weg, new Events(0.6, "Du siehst ein Eichhörnchen", 0)),
                    new Road("bleibe hier", wald, new Events(0.8, "Du machst es dir im Wald bequem und erholst dich\n+20 HP", 20)),
                    new Road("gehe nach Osten", see, new Events(0.5, "Du läufst ausversehen in Dornen\n-5 HP", -5)),
                    new Road("gehe nach Westen", stadt, new Events(1, "Du siehst die Stadt und bekommst plötzlich mehr Energie\n+10 HP", 10)),
                    new Road("Gehe nach Nordwesten", fluss, new Events(0.2, "Dir fällt ein Ast auf den Kopf, du verlierst dein gleichgewicht und fällst in den Fluss\n-30 HP", -30))
            );
            wald.setRoads(waldRoads);

            List<Road> seeRoads = Arrays.asList(
                    new Road("gehe nach Westen", wald, new Events(0.2, "Du rutscht aus und fällst einem Hügel herunter\n-45 HP", -45)),
                    new Road("gehe nach Norden", fluss, new Events(0.4, "Eine Schlange verfolgt dich\n-10 HP", -10))
            );
            see.setRoads(seeRoads);

            List<Road> flussRoads = Arrays.asList(
                    new Road("gehe nach Norden", schlucht, new Events(0.1, "Du fällst in die Schlucht\n-190 HP", -190)),
                    new Road("gehe zurück", wald, new Events(0.7, "Du wirst von Insekten angegriffen\n-5 HP", -5 ))
            );
            fluss.setRoads(flussRoads);

            List<Road> schluchtRoads = Arrays.asList(
                    new Road("gehe nach Norden", stadt, new Events(0.4, " ", 0)),
                    new Road("gehe nach Westen", waldHuette, new Events(1, "Du findest eine gemütliche Waldhütte", 0)),
                    new Road("gehe nach Südosten", wald, new Events(0.3, "Du bleibst in der Schlucht stecken und musst alles benutzen was du hast um wieder frei zu kommen\n-120 HP", - 120))
            );
            schlucht.setRoads(schluchtRoads);
        }

        boolean isEnd (Place place) {

            return place.getRoads().size() > 1;
        }

        Map<Place, Boolean> getStartPoints (Place place) {

            Map<Place, Boolean> startPoints = new HashMap<>();

            if (Game.moveCounter == 0) {
                startPoints.put(place, true);
            }

            return startPoints;
        }

        Place randomStart () {

            double chance = Math.random();
            Place place;

            if (chance < 0.5) {
                place = strandHaus;
            } else {
                place = dorf;
            }

            return place;
        }
}
