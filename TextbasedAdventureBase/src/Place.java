import java.util.ArrayList;
import java.util.List;

public class Place {

    private String name;

    private List<Road> roads = new ArrayList<>();

    public Place(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setRoads(List<Road> roads) {
        this.roads = roads;
    }

    public List<Road> getRoads() {
        return roads;
    }

    public String toString() {
        return name;
    }

}
