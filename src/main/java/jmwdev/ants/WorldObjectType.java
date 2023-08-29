package jmwdev.ants;

public enum WorldObjectType {
    NONE("."),
    EMPTY(" "),
    FOOD("F"),
    OBSTACLE("o"),
    BASE("B");
    private final String render;

    private WorldObjectType(String render) {
        this.render = render;
    }

    @Override
    public String toString() {
        return this.render;
    }
}
