package lotto.dto;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import java.util.List;

public record LottoDto(List<Integer> numbers) {
    public static LottoDto from(Lotto lotto){
        return new LottoDto(lotto.getNumbers().stream()
                .map(LottoNumber::number)
                .toList());
    }

    public Lotto to(){
        return new Lotto(numbers.stream()
                .map(LottoNumber::new)
                .toList());
    }
}
