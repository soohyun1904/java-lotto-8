package lotto.domain;

import lotto.exception.DomainValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {

    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        List<LottoNumber> numbers = createLottoNumbers(1, 2, 3, 4, 5, 6, 7);
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(DomainValidationException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        List<LottoNumber> numbers = createLottoNumbers(1, 2, 3, 4, 5, 5);
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(DomainValidationException.class);
    }

    @Test
    @DisplayName("로또 번호에 보너스 번호를 가지면 true가 반환된다.")
    void 로또_번호에_보너스_번호를_가지면_true가_반환된다(){
        List<LottoNumber> numbers = createLottoNumbers(1, 2, 3, 4, 5, 6);
        Lotto lotto = new Lotto(numbers);
        BonusNumber bonusNumber = new BonusNumber(1);
        boolean result = lotto.hasBonusNumber(bonusNumber);
        assertThat(result).isEqualTo(true);
    }

    @Test
    @DisplayName("당첨 번호와 비교하여 같은 수만큼 카운팅한다.")
    void 당첨_번호와_비교하여_같은_수만큼_카운팅한다(){
        List<LottoNumber> numbers = createLottoNumbers(1, 2, 3, 4, 5, 6);
        Lotto lotto = new Lotto(numbers);
        Lotto other = new Lotto(numbers);
        int count = lotto.countMatch(other);
        assertThat(count).isEqualTo(6);
    }

    private List<LottoNumber> createLottoNumbers(Integer... numbers) {
        return Arrays.stream(numbers)
                .map(LottoNumber::new)
                .toList();
    }
}
