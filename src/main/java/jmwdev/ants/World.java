package jmwdev.ants;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

public class World {
    private static final Logger logger = LogManager.getLogger("AntWorld");
    Random random;
    int mapXSize;
    int mapYSize;
    Map<Location, WorldObject> objectsInWorld;

    List<Ant> ants;


    public World(Integer mapXSize, Integer mapYSize, Integer foodDrops, Integer ants, Integer obstacles) {
        this.random = new Random();
        this.mapXSize = mapXSize;
        this.mapYSize = mapYSize;
        this.objectsInWorld = new HashMap<>();
        this.ants = new ArrayList<>();

        this.populateMap(foodDrops, WorldObjectType.FOOD);
        this.populateMap(obstacles, WorldObjectType.OBSTACLE);
        for (int a = 1; a < ants + 1; a++) {
            this.ants.add(new Ant(String.valueOf(a), nextRandomLocation()));
        }

    }


    public Location nextRandomLocation() {
        Integer x = this.random.nextInt(0, this.mapXSize);
        Integer y = this.random.nextInt(0, this.mapYSize);
        return new Location(x, y);
    }

    public void populateMap(Integer numberOfObjects, WorldObjectType type) {
        for (int i = 0; i < numberOfObjects; i++) {
            addToWorld(this.nextRandomLocation(), type);
        }
    }

    public void addToWorld(Location location, WorldObjectType type) {
        this.objectsInWorld.put(location, new WorldObject(location, type));
    }

    public List<Location> getValidMoves(int x, int y) {
        List<Location> locations = new ArrayList<>();
        if (x > 0) locations.add(new Location(x - 1, y));
        if (y > 0) locations.add(new Location(x, y - 1));
        if (x < this.mapXSize) locations.add(new Location(x + 1, y));
        if (y < this.mapYSize) locations.add(new Location(x, y + 1));
        return locations;
    }


    public WorldObject atLocation(Location location) {
        return this.objectsInWorld.getOrDefault(location, new WorldObject(location, WorldObjectType.NONE));
    }

    public Location move(Location from, Location to) {
        if (!objectsInWorld.containsKey(to) || objectsInWorld.get(to).type() != WorldObjectType.OBSTACLE) {
            logger.debug("moving ant to " + to);
            return to;
        }
        logger.debug("ant can't move to " + to);
        return from;
    }

    public void render() {
        Map<Location, String> antMap = this.ants.stream().collect(Collectors.toMap(Ant::getLocation, Ant::getId));
        for (int y = 0; y <= this.mapYSize; y++) {
            StringBuilder line = new StringBuilder("|");
            for (int x = 0; x <= this.mapXSize; x++) {
                Location pos = new Location(x, y);
                if (antMap.containsKey(pos)) {
                    line.append("X");
                } else {
                    if (this.objectsInWorld.containsKey(pos)) {
                        line.append(this.objectsInWorld.get(pos).type());
                    } else {
                        line.append(" ");
                    }
                }
            }
            line.append("|" + y);
            logger.info(line.toString());
        }
//        for (Ant a : this.ants) {
//            a.renderMemory();
//        }

    }


}
