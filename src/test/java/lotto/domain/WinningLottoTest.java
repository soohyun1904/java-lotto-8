package lotto.domain;

import lotto.exception.DomainValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.stream.Stream;
import static lotto.exception.message.ErrorMessage.DUPLICATE_BONUS_WITH_WINNING_NUMBER;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningLottoTest {

    @Test
    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생한다.")
    void throwExceptionWhenBonusNumberDuplicateWithWinningNumbers(){
        List<LottoNumber> numbers = createLottoNumbers(1, 2, 3, 4, 5, 6);
        Lotto lotto = new Lotto(numbers);
        BonusNumber bonusNumber = new BonusNumber(3);

        assertThatThrownBy(() -> new WinningLotto(lotto, bonusNumber))
                .isInstanceOf(DomainValidationException.class)
                .hasMessage(DUPLICATE_BONUS_WITH_WINNING_NUMBER.getMessage());
    }

    private List<LottoNumber> createLottoNumbers(Integer... numbers) {
        return Stream.of(numbers)
                .map(LottoNumber::new)
                .toList();
    }
}