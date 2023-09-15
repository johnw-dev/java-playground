package jmwdev;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


class StringCompressionBasic {

    static Logger logger = LogManager.getLogger(StringCompressionBasic.class);

    private StringCompressionBasic() {
        // sonarlint
    }

    public static String compress(String input) {
        StringBuilder sb;
        sb = new StringBuilder();
        if (input != null && !input.isEmpty()) {
            char[] chars = input.toCharArray();
            int count = 0;
            char current = chars[0];
            for (char c : chars) {
                if (current != c) {
                    sb.append(current);
                    sb.append(count);
                    current = c;
                    count = 0;
                }
                count++;
            }
            sb.append(current);
            sb.append(count);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String result = compress("Hello Yahoo");
        logger.info(result);
    }
}
