package lotto.dto;

import lotto.domain.Lotto;
import java.util.List;

public record LottoDto(List<Integer> numbers) {
    public static LottoDto from(Lotto lotto){
        return new LottoDto(lotto.getNumbers());
    }
}
