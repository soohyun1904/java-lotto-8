package lotto.service;

import lotto.domain.*;
import lotto.dto.LottoResultDto;
import lotto.dto.LottosDto;
import lotto.util.NumbersGenerator;
import java.util.List;
import java.util.stream.Stream;

public class LottoService {
    private final NumbersGenerator numbersGenerator;

    public LottoService(NumbersGenerator numbersGenerator) {
        this.numbersGenerator = numbersGenerator;
    }

    public LottosDto purchaseLottos(int amount) {
        PurchaseAmount purchaseAmount = new PurchaseAmount(amount);
        List<Lotto> generatedLottos = generateLottos(purchaseAmount.calculateLottoCount());
        Lottos lottos = new Lottos(generatedLottos, purchaseAmount);
        return LottosDto.from(lottos);
    }

    public LottoResultDto checkWinning(LottosDto lottosDto, List<Integer> winningNumbers, int bonusNumber) {
        Lottos lottos = lottosDto.to();
        List<LottoNumber> lottoNumbers = winningNumbers.stream()
                .map(LottoNumber::new)
                .toList();
        Lotto lotto = new Lotto(lottoNumbers);
        BonusNumber bonus = new BonusNumber(bonusNumber);
        WinningLotto winningLotto = new WinningLotto(lotto, bonus);
        LottoResult lottoResult = lottos.calculateResult(winningLotto);
        return LottoResultDto.from(lottoResult);
    }

    private List<Lotto> generateLottos(int count){
        return Stream.generate(this::createLotto)
                .limit(count)
                .toList();
    }

    private Lotto createLotto(){
        List<Integer> numbers = numbersGenerator.generate();
        List<LottoNumber> lottoNumbers = convertToLottoNumbers(numbers);
        return new Lotto(lottoNumbers);
    }

    private List<LottoNumber> convertToLottoNumbers(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = numbers.stream()
                .map(LottoNumber::new)
                .toList();
        return lottoNumbers;
    }
}
