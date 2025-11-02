package lotto.util;

import lotto.exception.InputValidationException;
import java.util.Arrays;
import java.util.List;
import static lotto.exception.message.ErrorMessage.INVALID_INTEGER_RANGE;
import static lotto.util.InputValidator.*;

public class IntegerParser {
    private static final String DELIMITER = "\\s*,\\s*";

    private IntegerParser() {
    }

    public static int parse(String value){
        validate(value);
        return convertToInteger(value);
    }

    public static List<Integer> parseWithDelimiter(String value){
        validateNotBlank(value);
        return Arrays.stream(value.split(DELIMITER))
                .peek(IntegerParser::validateToken)
                .map(IntegerParser::convertToInteger)
                .toList();
    }

    private static void validate(String value) {
        validateNotBlank(value);
        validateInteger(value);
    }

    private static void validateToken(String value){
        validateTokenNotEmpty(value);
        validateInteger(value);
    }

    private static int convertToInteger(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new InputValidationException(INVALID_INTEGER_RANGE);
        }
    }
}
