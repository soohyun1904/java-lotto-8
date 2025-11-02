package lotto.domain;

import lotto.exception.DomainValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static lotto.exception.message.ErrorMessage.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PurchaseAmountTest {

    @Test
    @DisplayName("유효한 구입 금액으로 객체를 생성한다.")
    void InputValue(){
        int input = 12000;
        PurchaseAmount amount = new PurchaseAmount(input);
        assertThat(amount).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(ints = {1000, 5000, 10000})
    @DisplayName("구입 금액으로 로또 개수를 계산한다.")
    void calculateLottoCount(int amount) {
        PurchaseAmount purchaseAmount = new PurchaseAmount(amount);
        int expectedCount = amount / 1000;

        int actualCount = purchaseAmount.calculateLottoCount();
        assertThat(actualCount).isEqualTo(expectedCount);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1000, -2300})
    @DisplayName("구입 금액이 0 이하면 예외가 발생한다.")
    void throwExceptionWhenInputIsLessThanOne(int amount){
        assertThatThrownBy(() -> new PurchaseAmount(amount))
                .isInstanceOf(DomainValidationException.class)
                .hasMessage(INVALID_POSITIVE_AMOUNT.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {500, 1500, 2300, 999})
    @DisplayName("구입 금액이 1000원 단위가 아니면 예외가 발생한다.")
    void throwExceptionWhenCreateWithInvalidUnit(int amount){
        assertThatThrownBy(() -> new PurchaseAmount(amount))
                .isInstanceOf(DomainValidationException.class)
                .hasMessage(INVALID_AMOUNT_UNIT.getMessage());
    }
}