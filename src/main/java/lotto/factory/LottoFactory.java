package lotto.factory;

import lotto.domain.Lotto;
import lotto.util.NumbersGenerator;
import java.util.List;
import java.util.stream.Stream;

public class LottoFactory {
    private final NumbersGenerator numbersGenerator;

    public LottoFactory(NumbersGenerator numbersGenerator) {
        this.numbersGenerator = numbersGenerator;
    }

    public Lotto createLotto(){
        return new Lotto(numbersGenerator.generate());
    }

    public List<Lotto> createLottos(int count){
        return Stream.generate(this::createLotto)
                .limit(count)
                .toList();
    }
}
