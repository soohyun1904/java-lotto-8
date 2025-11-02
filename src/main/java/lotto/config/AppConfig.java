package lotto.config;

import lotto.controller.LottoController;
import lotto.service.LottoService;
import lotto.util.NumbersGenerator;
import lotto.util.RandomNumbersGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class AppConfig {
    public InputView inputView(){
        return new InputView();
    }

    public OutputView outputView(){
        return new OutputView();
    }

    public NumbersGenerator numbersGenerator(){
        return new RandomNumbersGenerator();
    }

    public LottoService lottoService(){
        return new LottoService(numbersGenerator());
    }

    public LottoController lottoController(){
        return new LottoController(inputView(), outputView(), lottoService());
    }
}
