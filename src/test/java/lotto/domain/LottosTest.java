package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class LottosTest {
    private List<Lotto> lottos;
    private WinningLotto winningLotto;

    @BeforeEach
    void init(){
        List<LottoNumber> numbers1 = createLottoNumbers(1, 2, 3, 4, 5, 6);
        List<LottoNumber> numbers2 = createLottoNumbers(7, 8, 9, 10, 11, 12);
        List<LottoNumber> numbers3 = createLottoNumbers(13, 14, 15, 16, 17, 18);
        Lotto lotto1 = new Lotto(numbers1);
        Lotto lotto2 = new Lotto(numbers2);
        Lotto lotto3 = new Lotto(numbers3);
        lottos = List.of(lotto1, lotto2, lotto3);

        List<LottoNumber> winningNumbers = createLottoNumbers(1, 2, 3, 7, 8, 9);
        BonusNumber bonusNumber = new BonusNumber(12);
        winningLotto = new WinningLotto(new Lotto(winningNumbers), bonusNumber);
    }

    @Test
    @DisplayName("로또번호들이 당첨되었는지 판단한다.")
    void calculateResult(){
        Lottos mylottos = new Lottos(lottos, new PurchaseAmount(3000));
        LottoResult lottoResult = mylottos.calculateResult(winningLotto);

        assertThat(lottoResult.getCountByRank(Rank.FIRST)).isEqualTo(0);
        assertThat(lottoResult.getCountByRank(Rank.SECOND)).isEqualTo(0);
        assertThat(lottoResult.getCountByRank(Rank.THIRD)).isEqualTo(0);
        assertThat(lottoResult.getCountByRank(Rank.FOURTH)).isEqualTo(0);
        assertThat(lottoResult.getCountByRank(Rank.FIFTH)).isEqualTo(2);
        assertThat(lottoResult.getCountByRank(Rank.NONE)).isEqualTo(1);
        assertThat(lottoResult.getTotalPrize()).isEqualTo(10000);
        assertThat(lottoResult.calculateRateOfReturnPercentage()).isEqualTo(333.3);
    }

    private List<LottoNumber> createLottoNumbers(Integer... numbers) {
        return Arrays.stream(numbers)
                .map(LottoNumber::new)
                .toList();
    }
}