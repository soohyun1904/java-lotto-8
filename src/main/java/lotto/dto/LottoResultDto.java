package lotto.dto;

import lotto.domain.LottoResult;
import lotto.domain.Rank;

import java.util.LinkedHashMap;
import java.util.Map;

public record LottoResultDto(Map<String, Long> rankCounts, int totalPrize, double rateOfReturn) {

    public static LottoResultDto from(LottoResult result) {
        Map<String, Long> rankCounts = new LinkedHashMap<>();
        for (Rank rank : Rank.values()) {
            if (rank.isWinning()) {
                rankCounts.put(rank.name(), result.getCountByRank(rank));
            }
        }
        return new LottoResultDto(
                rankCounts,
                result.getTotalPrize(),
                result.calculateRateOfReturnPercentage()
        );
    }

    public long getCountByRank(String rankName) {
        return rankCounts.getOrDefault(rankName, 0L);
    }

}
