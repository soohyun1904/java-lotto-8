package lotto.dto;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.PurchaseAmount;
import java.util.List;

public record LottosDto(int count, List<LottoDto> lottos) {
    private static final int LOTTO_PRICE = 1000;

    public static LottosDto from(Lottos lottos){
        return new LottosDto(
                lottos.size(),
                lottos.getLottos().stream()
                        .map(LottoDto::from)
                        .toList());
    }

    public Lottos to(){
        List<Lotto> lottos = this.lottos.stream()
                .map(LottoDto::to)
                .toList();
        PurchaseAmount purchaseAmount = new PurchaseAmount(lottos.size() * LOTTO_PRICE);
        return new Lottos(lottos, purchaseAmount);
    }
}
