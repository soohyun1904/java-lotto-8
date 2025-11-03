package lotto.domain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Lottos {
    private final List<Lotto> lottos;
    private final PurchaseAmount purchaseAmount;

    public Lottos(List<Lotto> lottos, PurchaseAmount purchaseAmount) {
        this.lottos = List.copyOf(lottos);
        this.purchaseAmount = purchaseAmount;
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

    public LottoResult calculateResult(WinningLotto winningLotto) {
        Map<Rank, Long> rankCounts = lottos.stream()
                .map(winningLotto::calculateRank)
                .collect(Collectors.groupingBy(
                        rank -> rank,
                        Collectors.counting()
                ));
        return new LottoResult(rankCounts, purchaseAmount);
    }
}
