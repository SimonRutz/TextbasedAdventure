import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

    public static int moveCounter = 0;
    public static int hp = 200;
    public static boolean gameEnded = false;

    public static void main(String[] args) {

        World world = new World();
        Game game = new Game();
        world.createRoads();

        // Spieler, beginnt das Spiel am Start-Ort


        Scanner scanner = new Scanner(System.in);

        System.out.println("Mit wie vielen Spielern möchten Sie spielen?");
        int playerNumber = scanner.nextInt();

        final List<Player> playerName = new ArrayList<>();

        for (int i = 0; i < playerNumber; i++) {
            System.out.println("Wie lautet Ihr Name Spieler " + (i + 1));
            String name = scanner.next();
            Player nameOfPlayer = new Player(name, 200);

            playerName.add(nameOfPlayer);
        }

        for (int i = 0; i < playerNumber; i++) {
            System.out.println("Wo möchten Sie starten "+ playerName.get(i).name + "?" +
                    "\n(1) Dorf" +
                    "\n(2) Strandhaus " +
                    "\n(3) Zufallsauswahl");
            int selection = scanner.nextInt();

            switch (selection) {
                case 1:
                    playerName.get(i).goTo(world.dorf);
                    break;
                case 2:
                    playerName.get(i).goTo(world.strandHaus);
                    break;
                case 3:
                    playerName.get(i).goTo(world.randomStart());
                    break;
            }
        }

        int counter = 0;
        // game loop: spielen, bis wir am Ziel sind
        while (!gameEnded) {
            for (int j = 0; j < playerNumber; j++) {
                if (!(playerName.get(j).finished)) {
                    counter = 0;
                    game.runGame(playerName.get(j), scanner, world);
                } else {
                    counter++;
                } if (counter >= playerNumber) {
                    gameEnded = true;
                }
            }
        }
        System.out.println("Spiel wurde beendet!");
    }


    void runGame(Player player, Scanner scanner, World world) {
        // Mal schauen, wo wir sind
        System.out.println("\n" + player.toString(player.getName(), player.hp));

        Place place = player.where();

        System.out.println("\nSpieler ist hier: " + place);

        // Wo kann's hingehen?
        System.out.println("Mögliche Wege:");
        List<Road> roads = place.getRoads();


        // Auswahl: 1..n, entspricht 0..n-1 im Array
        for (int i = 0; i < roads.size(); i++) {
            Road road = roads.get(i);
            System.out.println((i + 1) + ": " + road.getDescription());
        }
        System.out.println("Wohin soll's gehen?");

        // Wo will der Spieler hin?
        int number = scanner.nextInt();
        if (number > 0 && number <= roads.size()) {
            int index = number - 1;

            // Weg auswählen
            Road chosenRoad = roads.get(index);
            System.out.println("Ihre Wahl: " + chosenRoad);

            player.hp = (chosenRoad.events.apply(player.hp, chosenRoad.getEvents()));

            // Zielort bestimmen
            Place target = chosenRoad.getTarget();

            // Spieler verschieben
            player.goTo(target);

        } else {
            System.out.println("Ungültige Eingabge");
        }

        if (player.where() == world.stadt | player.where() == world.waldHuette) {
            System.out.println("Endpunkt erreicht!");
            player.finished = true;
        } else if (player.hp <= 0) {
            player.hp = 0;
            System.out.println(player.getName() + " ist gestorben!");
        }

        moveCounter++;

    }

}
