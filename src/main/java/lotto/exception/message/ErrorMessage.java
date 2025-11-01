package lotto.exception.message;

public enum ErrorMessage {
    NULL_INPUT("[ERROR] 입력값이 null입니다. 오류가 발생하였습니다."),
    INVALID_VALUE("[ERROR] 유효하지 않는 값입니다."),

    INVALID_EMPTY_INPUT("[ERROR] 입력값이 비어있습니다. 다시 입력해주세요."),
    INVALID_EMPTY_TOKEN("[ERROR] 빈 값이 포함되어 있습니다. 다시 입력해주세요."),
    INVALID_INTEGER_FORMAT("[ERROR] 입력은 정수만 가능합니다. 다시 입력해주세요."),
    INVALID_INTEGER_RANGE("[ERROR] 입력 가능한 범위를 초과했습니다. 다시 입력해주세요."),

    EMPTY_PURCHASE_AMOUNT("[ERROR] 로또 금액이 입력되지 않았습니다. 로또 금액을 입력해주세요."),
    INVALID_INTEGER2_FORMAT("[ERROR] 로또 금액은 정수만 입력 가능합니다. 다시 입력해주세요."),

    INVALID_POSITIVE_AMOUNT("[ERROR] 로또 금액은 양의 정수만 입력 가능합니다. 다시 입력해주세요."),
    INVALID_AMOUNT_UNIT("[ERROR] 로또 금액은 1000원 단위로 입력해야 합니다. 다시 입력해주세요.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
