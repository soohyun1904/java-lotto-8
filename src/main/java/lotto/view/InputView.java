package lotto.view;

import static camp.nextstep.edu.missionutils.Console.*;

public class InputView {
    private final static String PURCHASE_AMOUNT_INPUT_MESSAGE = "구입금액을 입력해 주세요.";
    private final static String WINNING_NUMBERS_INPUT_MESSAGE = "당첨 번호를 입력해 주세요.";

    public InputView(){
    }

    public String inputPurchaseAmount(){
        System.out.println(PURCHASE_AMOUNT_INPUT_MESSAGE);
        return readLine();
    }

    public String inputWinningNumbers(){
        System.out.println(WINNING_NUMBERS_INPUT_MESSAGE);
        return readLine();
    }
}
