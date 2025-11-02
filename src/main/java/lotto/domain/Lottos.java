package lotto.domain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = List.copyOf(lottos);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public int size(){
        return lottos.size();
    }

    public int amount(){
        return size()*1000;
    }

    // 도메인 로직: 당첨 통계 계산
    public LottoResult calculateResult(WinningLotto winningLotto, PurchaseAmount purchaseAmount) {
        Map<Rank, Long> rankCounts = lottos.stream()
                .map(winningLotto::calculateRank)
                .collect(Collectors.groupingBy(
                        rank -> rank,
                        Collectors.counting()
                ));

        return new LottoResult(rankCounts, purchaseAmount);
    }
}
