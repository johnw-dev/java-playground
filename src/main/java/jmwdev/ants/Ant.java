package jmwdev.ants;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;


public class Ant {
    private static final Logger logger = LogManager.getLogger("Ant");

    final String id;
    Location location;
    Map<Location, Memory> visitedLocations;

    public Ant(String id, Location start) {
        this.id = id;
        this.location = start;
        visitedLocations = new HashMap<>();
        visitedLocations.put(start, new Memory(new WorldObject(start, WorldObjectType.BASE), new Date()));
    }

    public void renderMemory() {
        for (Memory o : this.visitedLocations.values().stream().sorted(Comparator.comparing(Memory::lastSeen)).toList()) {
            logger.info(o);
        }
    }

    boolean canIGoThere(Location l) {
        boolean canIGoThere = !visitedLocations.containsKey(l) || visitedLocations.get(l).object().type() != WorldObjectType.OBSTACLE;
        logger.debug("Can i go there {}", canIGoThere);
        return canIGoThere;
    }

    public Location explore(World world) {
        List<Location> locations = world.getValidMoves(this.location.x(), this.location.y());
        return locations.stream().filter(l -> canIGoThere(l)).sorted((o1, o2) -> {
            if (!visitedLocations.containsKey(o1)) return -1;
            if (!visitedLocations.containsKey(o2)) return 1;
            return visitedLocations.get(o1).lastSeen().compareTo(visitedLocations.get(o2).lastSeen());
        }).findFirst().orElseThrow();
    }

    public void move(World world) {
        Location to = explore(world);
        // attempt to move
        this.location = world.move(location, to);
        // did we bounce?
        if (!this.location.equals(to)) {
            // remember can't travel that way
            remember(new WorldObject(to, WorldObjectType.OBSTACLE));
        } else {

            // investigate
            remember(world.atLocation(this.location));
        }
    }

    public Location getLocation() {
        return location;
    }

    public String getId() {
        return id;
    }

    public void remember(WorldObject object) {
        visitedLocations.put(object.location(), new Memory(object, new Date()));
    }

    @Override
    public String toString() {
        return String.format("Ant[%s, %s]", this.id, this.location);
    }

}
