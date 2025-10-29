package lotto.dto;

import lotto.domain.Lotto;
import java.util.List;

public record LottosDto(int count, List<LottoDto> lottos) {
    public static LottosDto from(List<Lotto> lottos){
        return new LottosDto(
                lottos.size(),
                lottos.stream().map(LottoDto::from).toList());
    }
}
