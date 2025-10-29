package lotto.util;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class RandomNumbersGenerator implements NumbersGenerator{
    private static final int MIN_RANGE = 1;
    private static final int MAX_RANGE = 45;
    private static final int NUMBER_COUNT = 6;

    @Override
    public List<Integer> generate() {
        return List.copyOf(Randoms.pickUniqueNumbersInRange(MIN_RANGE, MAX_RANGE, NUMBER_COUNT));
    }
}
