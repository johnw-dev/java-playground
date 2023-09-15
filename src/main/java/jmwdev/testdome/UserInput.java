package jmwdev.testdome;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserInput {

    static Logger log = LogManager.getLogger(UserInput.class);

    public static void main(String[] args) {
        TextInput input = new NumericInput();
        input.add('1');
        input.add('a');
        input.add('0');
        log.info(input.getValue());
    }

    public static class TextInput {
        StringBuilder sb;

        public TextInput() {
            this.sb = new StringBuilder();
        }

        public void add(char c) {
            sb.append(c);
        }

        public String getValue() {
            return sb.toString();
        }
    }

    public static class NumericInput extends TextInput {
        @Override
        public void add(char c) {
            if (Character.isDigit(c)) {
                super.add(c);
            }
        }
    }
}