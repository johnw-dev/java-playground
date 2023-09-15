package jmwdev.testdome;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class RoutePlanner {
    static Logger log = LogManager.getLogger(RoutePlanner.class);


    public static boolean routeExists(int fromRow, int fromColumn, int toRow, int toColumn,
                                      boolean[][] mapMatrix) {
        List<Coord> traversed = new ArrayList<>();
        boolean result = traverse(mapMatrix, new Coord(fromRow, fromColumn), new Coord(toRow, toColumn), traversed);
        traversed.forEach(log::info);
        return result;
    }

    private static boolean traverse(boolean[][] mapMatrix, Coord start, Coord target, List<Coord> traversed) {
        traversed.add(start);
        if (start.equals(target)) {
            return true;
        } else {
            List<Coord> options = getAdjacentSquares(mapMatrix, start, traversed);
            for (Coord option : options) {
                if (traverse(mapMatrix, option, target, traversed)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static List<Coord> getAdjacentSquares(boolean[][] mapMatrix, Coord start, List<Coord> traversed) {
        HashSet<Coord> coords = new HashSet<>();
        if (start.x < mapMatrix[0].length - 1)
            coords.add(new Coord(start.x + 1, start.y));
        if (start.x > 0)
            coords.add(new Coord(start.x - 1, start.y));
        if (start.y > 0)
            coords.add(new Coord(start.x, start.y - 1));
        if (start.y < mapMatrix.length - 1)
            coords.add(new Coord(start.x, start.y + 1));
        return coords.stream().filter(coord -> !traversed.contains(coord) && mapMatrix[coord.y][coord.x]).toList();
    }

    public static void main(String[] args) {

        boolean[][] mapMatrix = {
                {true, false, false},
                {true, true, false},
                {false, true, true}
        };

        boolean[][] mapMatrix2 = {
                {true, false, false},
                {true, false, false},
                {false, true, true}
        };

        boolean[][] mapMatrix3 = {
                {true, true, true},
                {true, true, true},
                {false, false, false}
        };

        boolean[][] mapMatrix4 = {
                {true, true, true},
                {true, false, false},
                {true, true, true}
        };

        boolean[][] mapMatrix5 = {
                {true, true, true},
                {true, false, true},
                {true, false, true}
        };


        log.info(routeExists(0, 0, 2, 2, mapMatrix));
        log.info(routeExists(0, 0, 2, 2, mapMatrix2));
        log.info(routeExists(0, 0, 2, 2, mapMatrix3));
        log.info(routeExists(0, 0, 2, 2, mapMatrix4));
        log.info(routeExists(0, 0, 2, 2, mapMatrix5));


    }

    record Coord(int x, int y) {
    }

}