package jmwdev.testdome;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class QuadraticEquation {
    static Logger log = LogManager.getLogger(QuadraticEquation.class);

    public static void main(String[] args) {
        //Problem
        Roots roots = QuadraticEquation.findRoots(2, 10, 8);
        log.info("Roots: {}, {}", roots.x1(), roots.x2());
    }

    static Roots findRoots(double a, double b, double c) {
        // Solution
        double d = Math.sqrt((b * b) - (4 * a * c));
        if (d > 0) {
            log.info("descrimininant is positive");
            double x1 = ((b * -1) + d) / (2 * a);
            double x2 = ((b * -1) - d) / (2 * a);
            return new Roots(x1, x2);
        } else {
            double x12 = (b * -1) / (2 * a);
            if (d == 0) {
                log.info("descrimininant is equal to 0");
                return new Roots(x12, x12);
            } else {
                log.info("descrimininant is negative");
                throw new UnsupportedOperationException();
            }

        }
    }

}


record Roots(double x1, double x2) {

}
