package lotto.domain;

import lotto.exception.DomainValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static lotto.exception.message.ErrorMessage.INVALID_BONUS_NUMBER_RANGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BonusNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 23, 44, 45})
    @DisplayName("1 ~ 45 사이 숫자를 입력하면 정상처리된다.")
    void createBonusNumber(int number) {
        BonusNumber bonusNumber = new BonusNumber(number);
        assertThat(bonusNumber.number()).isEqualTo(number);
    }

    @ParameterizedTest
    @ValueSource(ints = {0,46,50})
    @DisplayName("1 ~ 45 사이 숫자가 아니라면 예외가 발생한다..")
    void throwExceptionWhenOutOfValidRange(int number){
        assertThatThrownBy(() -> new BonusNumber(number))
                .isInstanceOf(DomainValidationException.class)
                .hasMessage(INVALID_BONUS_NUMBER_RANGE.getMessage());
    }
}