package lotto.domain;

import lotto.exception.DomainValidationException;
import static lotto.exception.message.ErrorMessage.INVALID_BONUS_NUMBER_RANGE;

public record BonusNumber (int number){
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    public BonusNumber{
        validate(number);
    }

    private void validate(int number){
        validateNumberRange(number);
    }

    private void validateNumberRange(int number){
        if (number < MIN_NUMBER  || number > MAX_NUMBER) {
            throw new DomainValidationException(INVALID_BONUS_NUMBER_RANGE);
        }
    }
}
