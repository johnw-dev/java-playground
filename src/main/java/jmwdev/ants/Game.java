package jmwdev.ants;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Game {
    private static final Logger logger = LogManager.getLogger("AntGame");

    public static void main(String[] args) {
        int rounds = 200;
        World w = new World(10, 10, 3, 1, 0);
        w.render();
        for (int turn = 0; turn < rounds; turn++) {
            for (Ant a : w.ants) {
                a.move(w);
            }
            w.render();
            logger.info("End of turn {}", turn);
        }
//        logger.info("ant perspective");
//        for (Ant a : w.ants) {
//            a.renderMemory();
//        }
    }
}
