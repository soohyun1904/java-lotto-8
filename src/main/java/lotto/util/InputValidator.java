package lotto.util;

import lotto.exception.InputException;
import static lotto.exception.message.ErrorMessage.EMPTY_PURCHASE_AMOUNT;

public class InputValidator {
    private InputValidator(){
    }

    public static void validateIntegerInput(String input){
    }

    public static void checkNotEmpty(String input){
        if(input.isBlank()){
            throw new InputException(EMPTY_PURCHASE_AMOUNT);
        }
    }
}
