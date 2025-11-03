package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.EnumMap;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

class LottoResultTest {

    @Test
    @DisplayName("여러 등수의 총 당첨금을 계산한다")
    void getTotalPrizeMultipleRanks() {
        Map<Rank, Long> rankCounts = new EnumMap<>(Rank.class);
        rankCounts.put(Rank.FIRST, 1L);
        rankCounts.put(Rank.SECOND, 1L);
        rankCounts.put(Rank.THIRD, 1L);
        rankCounts.put(Rank.FOURTH, 1L);
        rankCounts.put(Rank.FIFTH, 1L);
        LottoResult result = new LottoResult(rankCounts, new PurchaseAmount(5000));
        int totalPrize = result.getTotalPrize();

        assertThat(totalPrize).isEqualTo(2_031_555_000);
    }

    @Test
    @DisplayName("같은 등수가 여러 개인 경우 총 당첨금을 계산한다")
    void getTotalPrizeSameRankMultiple() {
        Map<Rank, Long> rankCounts = new EnumMap<>(Rank.class);
        rankCounts.put(Rank.FIFTH, 3L);  // 5,000 * 3 = 15,000
        LottoResult result = new LottoResult(rankCounts, new PurchaseAmount(3000));
        int totalPrize = result.getTotalPrize();

        assertThat(totalPrize).isEqualTo(15_000);
    }

    @Test
    @DisplayName("수익률을 백분율로 계산한다")
    void calculateRateOfReturnPercentage() {
        Map<Rank, Long> rankCounts = new EnumMap<>(Rank.class);
        rankCounts.put(Rank.FIFTH, 1L);  // 5,000원
        LottoResult result = new LottoResult(rankCounts, new PurchaseAmount(8000));
        double rateOfReturn = result.calculateRateOfReturnPercentage();

        assertThat(rateOfReturn).isEqualTo(62.5);
    }

    @Test
    @DisplayName("특정 등수의 개수를 조회한다")
    void getCountByRank() {
        Map<Rank, Long> rankCounts = new EnumMap<>(Rank.class);
        rankCounts.put(Rank.FIRST, 1L);
        rankCounts.put(Rank.FIFTH, 3L);
        LottoResult result = new LottoResult(rankCounts, new PurchaseAmount(4000));

        assertThat(result.getCountByRank(Rank.FIRST)).isEqualTo(1);
        assertThat(result.getCountByRank(Rank.FIFTH)).isEqualTo(3);
    }
}

