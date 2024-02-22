package jmwdev.hacker;

public class QueensAttack {
    enum Direction {

        col_inc(1,0),
        col_dec(-1, 0),
        row_inc(0,1),
        row_dec(0, -1),
        col_inc_row_inc(1, 1),
        col_inc_row_dec(1, -1),
        col_dec_row_inc(-1, 1),
        col_dec_row_dec(-1,-1);
        private final int c;
        private final int r;

        Direction(int c, int r) {
            this.c = c;
            this.r = r;
        }
        Position getNext(Position p) {
            return new Position(p.c()+this.c, p.r()+this.r);
        }
    }

    public static void main(String[] args) {
        System.out.println("\nresult:"+queensAttack2(8,8,4,4, new int[][]{{4,5}}));
        System.out.println("\nresult:"+queensAttack2(8,8,4,4, new int[][]{{}}));

    }

    record Position(int c, int r) {}


    static int queensAttack2(int rMax, int cMax, int r, int c, int[][] obstacles) {
        int total = 0;
        var rootPos = new Position(c, r);
        for(Direction d : Direction.values()) {
            var p=rootPos;
            while(p.c()>1 && p.c() <cMax && p.r()>1 && p.r()<rMax) {
                p = d.getNext(p);
//                System.out.printf("current: %s %s %n",p, d.name());
                boolean obstructed = false;
                for(int[] o : obstacles) {
                    if(o.length == 2 && o[0] == p.c() && o[1] == p.r()) {
                        System.out.printf("obstructed: %s %n",p);
                        obstructed=true;
                        break;
                    }
                }
                if(obstructed) {
                    break;
                }
                total++;
            }
        }
        return total;
    }
}
