package jmwdev.testdome;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;

public class TrainComposition {
    static Logger log = LogManager.getLogger(TrainComposition.class);

    private LinkedList<Integer> train;


    public TrainComposition() {
        this.train = new LinkedList<>();

    }

    public static void main(String[] args) {
        TrainComposition train = new TrainComposition();
        train.attachWagonFromLeft(7);
        train.attachWagonFromLeft(13);
        log.info(train.detachWagonFromRight()); // 7
        log.info(train.detachWagonFromLeft()); // 13

    }

    public void attachWagonFromLeft(int wagonId) {
        this.train.add(0, wagonId);
    }

    public void attachWagonFromRight(int wagonId) {
        this.train.add(train.size(), wagonId);
    }

    public int detachWagonFromLeft() {
        int wagonid = this.train.get(0);
        this.train.remove(0);
        return wagonid;
    }

    public int detachWagonFromRight() {
        int wagonid = this.train.get(train.size() - 1);
        this.train.remove(train.size() - 1);
        return wagonid;

    }

}