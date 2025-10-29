package lotto.view;

import lotto.dto.LottoDto;
import lotto.dto.LottosDto;

public class OutputView {
    private final static String LOTTO_PURCHASE_MESSAGE = "개를 구입했습니다.";

    public void printLottos(LottosDto lottos){
        System.out.println(lottos.count() + LOTTO_PURCHASE_MESSAGE);
        lottos.lottos().forEach(this::printLotto);
    }

    private void printLotto(LottoDto lotto){
        System.out.println(lotto.numbers());
    }
}
