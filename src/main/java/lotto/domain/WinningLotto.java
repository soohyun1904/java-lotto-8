package lotto.domain;

import lotto.exception.DomainValidationException;
import static lotto.exception.message.ErrorMessage.DUPLICATE_BONUS_WITH_WINNING_NUMBER;

public class WinningLotto {
    private final Lotto lotto;
    private final BonusNumber bonusNumber;

    public WinningLotto(Lotto lotto, BonusNumber bonusNumber) {
        validate(lotto, bonusNumber);
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    private void validate(Lotto lotto, BonusNumber bonusNumber){
        validateBonusNotDuplicates(lotto, bonusNumber);
    }

    private void validateBonusNotDuplicates(Lotto lotto, BonusNumber bonusNumber){
        if(hasDuplicates(lotto, bonusNumber)){
            throw new DomainValidationException(DUPLICATE_BONUS_WITH_WINNING_NUMBER);
        }
    }

    private boolean hasDuplicates(Lotto lotto, BonusNumber bonusNumber) {
        return lotto.getNumbers().stream()
                .anyMatch(number -> number == bonusNumber.number());
    }
}
