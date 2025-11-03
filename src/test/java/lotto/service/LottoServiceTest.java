package lotto.service;

import lotto.config.AppConfig;
import lotto.domain.*;
import lotto.dto.LottoDto;
import lotto.dto.LottoResultDto;
import lotto.dto.LottosDto;
import lotto.exception.DomainValidationException;
import lotto.util.NumbersGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.List;
import java.util.stream.Stream;
import static lotto.exception.message.ErrorMessage.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoServiceTest {
    private LottoService lottoService;
    private NumbersGenerator generator;

    @BeforeEach
    void setUp() {
        AppConfig appConfig = new AppConfig();
        lottoService = appConfig.lottoService();
        generator = () -> List.of(1, 2, 3, 4, 5, 6);
    }

    @Test
    @DisplayName("구매 금액에 맞는 개수의 로또를 생성한다")
    void purchaseLottos() {
        int amount = 3000;
        LottosDto lottosDto = lottoService.purchaseLottos(amount);
        assertThat(lottosDto.count()).isEqualTo(3);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1000, -2300})
    @DisplayName("구입 금액이 0 이하면 예외가 발생한다.")
    void throwExceptionWhenInputIsLessThanOne(int amount){
        assertThatThrownBy(() -> lottoService.purchaseLottos(amount))
                .isInstanceOf(DomainValidationException.class)
                .hasMessage(INVALID_POSITIVE_AMOUNT.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {500, 1500, 2300, 999})
    @DisplayName("구입 금액이 1000원 단위가 아니면 예외가 발생한다.")
    void throwExceptionWhenCreateWithInvalidUnit(int amount){
        assertThatThrownBy(() -> lottoService.purchaseLottos(amount))
                .isInstanceOf(DomainValidationException.class)
                .hasMessage(INVALID_AMOUNT_UNIT.getMessage());
    }

    @Test
    @DisplayName("당첨 결과를 정확하게 계산한다.")
    void checkWinning(){
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;
        LottoDto lottoDto = new LottoDto(generator.generate());
        LottosDto lottosDto = new LottosDto(1, List.of(lottoDto));
        LottoResultDto lottoResultDto = lottoService.checkWinning(lottosDto, numbers, bonusNumber);
        assertThat(lottoResultDto.getCountByRank("FIRST")).isEqualTo(1);
        assertThat(lottoResultDto.totalPrize()).isEqualTo(2_000_000_000);
    }

    @Test
    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생한다.")
    void throwExceptionWhenBonusNumberDuplicateWithWinningNumbers(){
        int amount = 3000;
        LottosDto lottosDto = lottoService.purchaseLottos(amount);
        assertThatThrownBy(() -> lottoService.checkWinning(lottosDto, List.of(1, 2, 7, 8, 9, 10), 9))
                .isInstanceOf(DomainValidationException.class)
                .hasMessage(DUPLICATE_BONUS_WITH_WINNING_NUMBER.getMessage());
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void throwExceptionWhenWinningNumbersDuplicate() {
        int amount = 3000;
        LottosDto lottosDto = lottoService.purchaseLottos(amount);
        assertThatThrownBy(() -> lottoService.checkWinning(lottosDto, List.of(1, 2, 7, 8, 9, 2), 10))
                .isInstanceOf(DomainValidationException.class)
                .hasMessage(DUPLICATE_LOTTO_NUMBERS.getMessage());
    }

    @Test
    @DisplayName("1 ~ 45 사이 숫자가 아니라면 예외가 발생한다..")
    void throwExceptionWhenWinningLottoNumberOutOfValidRange(){
        int amount = 3000;
        LottosDto lottosDto = lottoService.purchaseLottos(amount);
        assertThatThrownBy(() -> lottoService.checkWinning(lottosDto, List.of(1, 2, 7, 8, 9, 46), 3))
                .isInstanceOf(DomainValidationException.class)
                .hasMessage(INVALID_LOTTO_RANGE.getMessage());
    }

    @Test
    @DisplayName("1 ~ 45 사이 숫자가 아니라면 예외가 발생한다..")
    void throwExceptionWhenBonusNumberOutOfValidRange(){
        int amount = 3000;
        LottosDto lottosDto = lottoService.purchaseLottos(amount);
        assertThatThrownBy(() -> lottoService.checkWinning(lottosDto, List.of(1, 2, 7, 8, 9, 10), 49))
                .isInstanceOf(DomainValidationException.class)
                .hasMessage(INVALID_BONUS_NUMBER_RANGE.getMessage());
    }

    private List<LottoNumber> createLottoNumbers(Integer... numbers) {
        return Stream.of(numbers)
                .map(LottoNumber::new)
                .toList();
    }
}
