package lotto.controller;

import lotto.domain.PurchaseAmount;
import lotto.dto.LottoDto;
import lotto.dto.LottoResultDto;
import lotto.dto.LottosDto;
import lotto.service.LottoService;
import lotto.util.IntegerParser;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;
import java.util.function.Supplier;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoService lottoService;

    public LottoController(InputView inputView, OutputView outputView, LottoService lottoService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoService = lottoService;
    }

    public void run(){
        LottosDto lottos = purchaseLottos();
        outputView.printLottos(lottos);
        LottoResultDto lottoResult = checkWinning(lottos);
        outputView.printResult(lottoResult);
    }

    private LottosDto purchaseLottos(){
        return retryOnException(() -> {
            String input = inputView.inputPurchaseAmount();
            int amount = IntegerParser.parse(input);
            return lottoService.purchaseLottos(amount);
        });
    }

    private LottoResultDto checkWinning(LottosDto lottosDto){
        return retryOnException(() -> {
            List<Integer> winningNumbers = inputWinningNumbers();
            int bonusNumber = inputBonusNumber();
            return lottoService.checkWinning(lottosDto, winningNumbers, bonusNumber);
        });
    }

    private List<Integer> inputWinningNumbers() {
        return retryOnException(() -> {
            String input = inputView.inputWinningNumbers();
            return IntegerParser.parseWithDelimiter(input);
        });
    }

    private int inputBonusNumber() {
        return retryOnException(() -> {
            String input = inputView.inputBonusNumber();
            return IntegerParser.parse(input);
        });
    }

    private <T> T retryOnException(Supplier<T> supplier){
        while(true){
            try{
                return supplier.get();
            }catch (IllegalArgumentException e){
                outputView.printError(e.getMessage());
            }
        }
    }
}
