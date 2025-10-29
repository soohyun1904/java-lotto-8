package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private final static String INPUT_PURCHASE_AMOUNT = "구입금액을 입력해 주세요.";

    public InputView(){
    }

    public String inputPurchaseAmount(){
        System.out.println(INPUT_PURCHASE_AMOUNT);
        return Console.readLine();
    }

}
