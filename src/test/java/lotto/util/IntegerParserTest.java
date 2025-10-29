package lotto.util;

import lotto.exception.InputException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static lotto.exception.message.ErrorMessage.EMPTY_PURCHASE_AMOUNT;
import static lotto.exception.message.ErrorMessage.INVALID_INTEGER_FORMAT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class IntegerParserTest {

    @Test
    @DisplayName("입력된 문자열을 숫자로 파싱한다.")
    void parseInputString(){
        String input ="7000";
        int result = IntegerParser.parse(input);
        assertThat(result).isEqualTo(7000);
    }

    @Test
    @DisplayName("빈 입력을 받는 경우 예외처리한다.")
    void throwExceptionWhenInputIsEmpty(){
        String input = "  ";
        assertThatThrownBy(() -> IntegerParser.parse(input))
                .isInstanceOf(InputException.class)
                .hasMessage(EMPTY_PURCHASE_AMOUNT.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"ab","!@","3.5"})
    @DisplayName("유효하지 않은 처리를 받으면 예외처리한다.")
    void throwExceptionWhenInputIsNotInteger(String input){
        assertThatThrownBy(() -> IntegerParser.parse(input))
                .isInstanceOf(InputException.class)
                .hasMessage(INVALID_INTEGER_FORMAT.getMessage());
    }
}
