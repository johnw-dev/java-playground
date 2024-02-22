package jmwdev.aetion;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ImageAnalysis {

    record Position(int y, int x) {}
    static int counter = 0;

    static Logger logger = LogManager.getLogger(ImageAnalysis.class);
    // image =  [. x . . .]
    //          [x x . . x]
    //          [x . . x x]
    //          [. . . x .]
    //          [. . . x x]
    // return the size of the largest shape in this representation of an image.
    // a shape is formed by neighboring x's.
    public static void main(String[] args) {
        var image = new String[][] {
                {".", "x", ".", ".", "."},
                {"x", "x", ".", ".", "x"},
                {"x", ".", ".", "x", "x"},
                {".", ".", ".", "x", "."},
                {".", ".", ".", "x", "x"}
        };
        int size = getLargestShapeSize(image);
        logger.info("size of largest shape = {}",size);
        logger.info("counter {}",counter);
    }

    static int getLargestShapeSize(String[][] image) {
        var visited = new HashSet<Position>();
        int largestShape = 0;
        for(int y = 0; y<image.length; y++) {
            for(int x=0; x<image[y].length;x++) {
                var current = new Position(y,x);
                if(!visited.contains(current)) {
                    visited.add(current);
                    counter++;
                    if(isShape(image[y][x])) {
                        int size = getSizeOfShape(1, image, visited, y, x);
                        largestShape = Math.max(largestShape, size);
                    }
                }
            }
        }
        return largestShape;
    }

    static boolean isShape(String imageRepresentation) {
        return imageRepresentation.equals("x");
    }

    static int getSizeOfShape(int currentSize, String[][] image, HashSet<Position> visited, int y, int x) {
        List<Position> neighbors = new ArrayList<>();
        if(y>0) neighbors.add(new Position(y-1, x));
        if(x>0) neighbors.add(new Position(y, x-1));
        if(y<image.length-1) neighbors.add(new Position(y+1, x));
        if(x<image[y].length-1) neighbors.add(new Position(y, x+1));

        for(Position n: neighbors) {
            if(!visited.contains(n)) {
                visited.add(n);
                counter++;
                logger.info("n {} {}",n.y, n.x);
                if (isShape(image[n.y][n.x])) {
                    currentSize++;
                    currentSize = getSizeOfShape(currentSize, image, visited, n.y, n.x);
                }
            }
        }
        return currentSize;
    }
}
