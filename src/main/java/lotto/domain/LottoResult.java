package lotto.domain;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoResult {
    private final Map<Rank, Long> rankCounts;
    private final PurchaseAmount purchaseAmount;

    public LottoResult(Map<Rank, Long> rankCounts, PurchaseAmount purchaseAmount) {
        this.rankCounts = new EnumMap<>(rankCounts);
        this.purchaseAmount = purchaseAmount;
        initializeAllRanks();
    }

    private void initializeAllRanks() {
        Arrays.stream(Rank.values())
                .filter(Rank::isWinning)
                .forEach(rank -> rankCounts.putIfAbsent(rank, 0L));
    }

    public long getCountByRank(Rank rank) {
        return rankCounts.getOrDefault(rank, 0L);
    }

    public Map<Rank, Long> getRankCounts() {
        return Map.copyOf(rankCounts);
    }


    public int getTotalPrize() {
        return rankCounts.entrySet().stream()
                .filter(entry -> entry.getKey().isWinning())
                .mapToInt(entry -> entry.getKey().getPrize() * entry.getValue().intValue())
                .sum();
    }

    public double calculateRateOfReturn() {
        int totalInvestment = purchaseAmount.getAmount();
        if (totalInvestment == 0) {
            return 0.0;
        }
        return (double) getTotalPrize() / totalInvestment;
    }

    public double calculateRateOfReturnPercentage() {
        double rate = calculateRateOfReturn() * 100;
        return Math.round(rate * 10) / 10.0;
    }
}
