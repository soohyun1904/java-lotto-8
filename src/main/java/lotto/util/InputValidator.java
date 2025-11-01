package lotto.util;

import lotto.exception.InputValidationException;
import static lotto.exception.message.ErrorMessage.*;

public class InputValidator {
    private static final String INTEGER_PATTERN = "-?\\d+";

    private InputValidator(){
    }

    public static void validateNotBlank(String input){
        if (input == null || input.isBlank()) {
            throw new InputValidationException(INVALID_EMPTY_INPUT);
        }
    }

    public static void validateTokenNotEmpty(String token) {
        if (token == null || token.isEmpty()) {
            throw new InputValidationException(INVALID_EMPTY_TOKEN);
        }
    }

    public static void validateInteger(String input){
        if(!input.matches(INTEGER_PATTERN)){
            throw new InputValidationException(INVALID_INTEGER_FORMAT);
        }
    }
}
