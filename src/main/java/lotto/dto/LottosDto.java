package lotto.dto;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import java.util.List;

public record LottosDto(int count, List<LottoDto> lottos) {
    public static LottosDto from(Lottos lottos){
        return new LottosDto(
                lottos.size(),
                lottos.getLottos().stream()
                        .map(LottoDto::from)
                        .toList());
    }

    public Lottos to(){
        return new Lottos(
                lottos.stream()
                        .map(LottoDto::to)
                        .toList()
        );
    }
}
