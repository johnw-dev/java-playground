package jmwdev.j8.functions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Predicate;

public class EffectivelyFinal {
    static String name = "";
    static Logger logger = LogManager.getLogger(EffectivelyFinal.class);

    public static void main(String[] args) {
        int x=5; // effectively final
        Predicate<String> lambda = (s)-> {
            name = s; // modification ALLOWED for static variables
            logger.info(name);
            // x+=1;  not allowed to modify variables outside method context
            return !name.isEmpty() && name.length() < x;

        };
        // x+=1; // also denied because must be effectively final and breaks on definition of lambda
        var res1 =lambda.test("bill");
        logger.info("{} {}", name, res1);
        var res2 = lambda.test("billybob thornton");
        logger.info("{} {}",name,res2);

    }
}
