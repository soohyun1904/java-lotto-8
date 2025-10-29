package lotto.exception.message;

public enum ErrorMessage {
    INVALID_VALUE("[ERROR] 유효하지 않는 값입니다."),
    EMPTY_PURCHASE_AMOUNT("[ERROR] 로또 금액이 입력되지 않았습니다. 로또 금액을 입력해주세요."),
    INVALID_INTEGER_FORMAT("[ERROR] 로또 금액은 정수만 입력 가능합니다. 다시 입력해주세요.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
