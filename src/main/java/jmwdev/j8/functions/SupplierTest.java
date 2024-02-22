package jmwdev.j8.functions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.function.Supplier;

public class SupplierTest {
    static Logger logger = LogManager.getLogger(SupplierTest.class);

    static class Incrementor implements Supplier<Integer> {

        private Integer myInt = 0;
        @Override
        public Integer get() {
            return myInt++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Supplier<Long> timeSupplier = () -> LocalDateTime.now().toEpochSecond(ZoneOffset.of("Z"));
        Supplier<Double> randomSupplier = Math::random;
        Incrementor incrementor = new Incrementor();

        for (int i = 0; i < 50; i++) {
            logger.info("timeSupplier {} {}",i, timeSupplier.get());
            logger.info("randomSupplier {} {}",i, randomSupplier.get());
            logger.info("incrementor {} {}",i, incrementor.get());

            Thread.sleep(1000);
        }

    }
}
