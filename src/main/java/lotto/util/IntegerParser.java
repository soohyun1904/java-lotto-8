package lotto.util;

import lotto.exception.InputException;
import static lotto.exception.message.ErrorMessage.INVALID_INTEGER_FORMAT;
import static lotto.util.InputValidator.checkNotEmpty;

public class IntegerParser {
    private IntegerParser() {
    }

    public static int parse(String input){
        checkNotEmpty(input);
        try{
            return Integer.parseInt(input);
        }catch (NumberFormatException e){
            throw new InputException(INVALID_INTEGER_FORMAT);
        }
    }
}
