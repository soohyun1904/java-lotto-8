package lotto.util;

import lotto.exception.InputValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.List;
import static lotto.exception.message.ErrorMessage.*;
import static org.assertj.core.api.Assertions.*;

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
                .isInstanceOf(InputValidationException.class)
                .hasMessage(INVALID_EMPTY_INPUT.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"ab","!@","3.5"})
    @DisplayName("유효하지 않은 처리를 받으면 예외처리한다.")
    void throwExceptionWhenInputIsNotInteger(String input){
        assertThatThrownBy(() -> IntegerParser.parse(input))
                .isInstanceOf(InputValidationException.class)
                .hasMessage(INVALID_INTEGER_FORMAT.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"2147483648", "-2147483649"})
    @DisplayName("정수 범위를 초과하면 예외를 발생시킨다.")
    void throwExceptionWhenOutOfRange(String input){
        assertThatThrownBy(() -> IntegerParser.parse(input))
                .isInstanceOf(InputValidationException.class)
                .hasMessage(INVALID_INTEGER_RANGE.getMessage());
    }

    @Test
    @DisplayName("구분자로 구분된 정수 문자열을 리스트로 변환한다.")
    void parseIntegersWithDelimiter(){
        String input = "1,2,3,4,5,6";
        List<Integer> integers = IntegerParser.parseWithDelimiter(input);
        assertThat(integers).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @ParameterizedTest
    @ValueSource(strings = {", ,2,34,5", ",12,32,12  ,32,12"})
    @DisplayName("쉼표 사이에 빈 값이 있으면 예외처리한다.")
    void throwExceptionWhenInputContainsEmptyValueBetweenCommas(String input) {
        assertThatThrownBy(() -> IntegerParser.parseWithDelimiter(input))
                .isInstanceOf(InputValidationException.class)
                .hasMessage(INVALID_EMPTY_TOKEN.getMessage());
    }

    @Test
    @DisplayName("빈 입력을 받는 경우 예외처리한다.")
    void throwExceptionWhenInputIsEmptyWithDelimiter(){
        String input = "  ";
        assertThatThrownBy(() -> IntegerParser.parse(input))
                .isInstanceOf(InputValidationException.class)
                .hasMessage(INVALID_EMPTY_INPUT.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,2147483648,3", "1,-2147483649,3"})
    @DisplayName("구분자 사이에 정수 범위를 초과하는 값이 있으면 예외를 발생시킨다")
    void throwExceptionWhenOutOfRangeBetweenDelimiters(String input){
        assertThatThrownBy(() -> IntegerParser.parseWithDelimiter(input))
                .isInstanceOf(InputValidationException.class)
                .hasMessage(INVALID_INTEGER_RANGE.getMessage());
    }
}
