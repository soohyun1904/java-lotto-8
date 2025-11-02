package lotto.domain;

import java.util.Arrays;

public enum Rank {
    FIRST(6, 2_000_000_000, false),
    SECOND(5, 30_000_000, true),
    THIRD(5, 1_500_000, false),
    FOURTH(4, 50_000, false),
    FIFTH(3, 5_000, false),
    NONE(0, 0, false);

    private final int matchCount;
    private final int prize;
    private final boolean requireBonus;

    Rank(int matchCount, int prize, boolean requireBonus) {
        this.matchCount = matchCount;
        this.prize = prize;
        this.requireBonus = requireBonus;
    }

    public static Rank of(int matchCount, boolean bonusMatch){
        return Arrays.stream(values())
                .filter(rank -> rank.matchCount == matchCount)
                .filter(rank -> !rank.requireBonus || bonusMatch)
                .findFirst()
                .orElse(NONE);
    }

    public boolean isWinning() {
        return this != NONE;
    }

    public int getPrize() {
        return prize;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isRequireBonus() {
        return requireBonus;
    }
}
