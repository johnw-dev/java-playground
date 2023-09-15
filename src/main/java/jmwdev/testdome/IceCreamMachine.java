package jmwdev.testdome;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class IceCreamMachine {
    static Logger log = LogManager.getLogger(IceCreamMachine.class);

    private final String[] ingredients;
    private final String[] toppings;

    public IceCreamMachine(String[] ingredeints, String[] toppings) {
        this.ingredients = ingredeints;
        this.toppings = toppings;
    }

    public static void main(String[] args) {
        IceCreamMachine machine = new IceCreamMachine(new String[]{
                "vanilla", "chocolate"
        }, new String[]{
                "chocolate sauce"
        });
        List<IceCream> scoops = machine.scoops();

        /*
         * Should print:
         * vanilla, chocolate sauce
         * chocolate, chocolate sauce
         */
        for (IceCream iceCream : scoops) {
            log.info("{}, {}", iceCream.ingredient, iceCream.topping);
        }
    }

    public List<IceCream> scoops() {
        List<IceCream> iceCreams = new ArrayList<>();
        for (String ingrediant : ingredients) {
            for (String topping : toppings) {
                iceCreams.add(new IceCream(ingrediant, topping));
            }
        }
        return iceCreams;
    }

    public static class IceCream {
        private final String ingredient;
        private final String topping;

        public IceCream(String ingredient, String topping) {
            this.ingredient = ingredient;
            this.topping = topping;
        }
    }
}