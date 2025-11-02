package lotto.domain;

import lotto.exception.DomainValidationException;
import static lotto.exception.message.ErrorMessage.INVALID_AMOUNT_UNIT;
import static lotto.exception.message.ErrorMessage.INVALID_POSITIVE_AMOUNT;

public class PurchaseAmount {
    private static final int LOTTO_PRICE = 1000;

    private final int amount;

    public PurchaseAmount(int amount) {
        validate(amount);
        this.amount = amount;
    }

    public int getAmount(){
        return amount;
    }

    public int calculateLottoCount(){
        return amount / LOTTO_PRICE;
    }

    private void validate(int amount){
        validatePositive(amount);
        validateAmountUnit(amount);
    }

    private void validatePositive(int amount){
        if (amount <= 0) {
            throw new DomainValidationException(INVALID_POSITIVE_AMOUNT);
        }
    }

    private void validateAmountUnit(int amount){
        if (amount % LOTTO_PRICE != 0) {
            throw new DomainValidationException(INVALID_AMOUNT_UNIT);
        }
    }
}
