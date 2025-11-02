package lotto.domain;

import lotto.exception.DomainValidationException;
import java.util.HashSet;
import java.util.List;
import static lotto.exception.message.ErrorMessage.DUPLICATE_LOTTO_NUMBERS;
import static lotto.exception.message.ErrorMessage.INVALID_LOTTO_SIZE;

public class Lotto {
    private static final int LOTTO_NUMBER_COUNT = 6;

    private final List<LottoNumber> numbers;

    public Lotto(List<LottoNumber> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers.stream()
                .map(LottoNumber::number)
                .toList();
    }

    private void validate(List<LottoNumber>  numbers) {
        validateSize(numbers);
        validateNotDuplicates(numbers);
    }

    private void validateSize(List<LottoNumber>  numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new DomainValidationException(INVALID_LOTTO_SIZE);
        }
    }

    private void validateNotDuplicates(List<LottoNumber> numbers){
        if(hasDuplicates(numbers)){
            throw new DomainValidationException(DUPLICATE_LOTTO_NUMBERS);
        }
    }

    private boolean hasDuplicates(List<LottoNumber> numbers) {
        return new HashSet<>(numbers).size() != numbers.size();
    }

    // TODO: 추가 기능 구현
}
