package lotto.exception.message;

public enum ErrorMessage {
    NULL_INPUT("[ERROR] 입력값이 null입니다. 오류가 발생하였습니다."),
    INVALID_VALUE("[ERROR] 유효하지 않는 값입니다."),

    INVALID_EMPTY_INPUT("[ERROR] 입력값이 비어있습니다. 다시 입력해주세요."),
    INVALID_EMPTY_TOKEN("[ERROR] 빈 값이 포함되어 있습니다. 다시 입력해주세요."),
    INVALID_INTEGER_FORMAT("[ERROR] 입력은 정수만 가능합니다. 다시 입력해주세요."),
    INVALID_INTEGER_RANGE("[ERROR] 입력 가능한 범위를 초과했습니다. 다시 입력해주세요."),

    INVALID_POSITIVE_AMOUNT("[ERROR] 로또 금액은 양의 정수만 입력 가능합니다. 다시 입력해주세요."),
    INVALID_AMOUNT_UNIT("[ERROR] 로또 금액은 1000원 단위로 입력해야 합니다. 다시 입력해주세요."),

    INVALID_LOTTO_RANGE("[ERROR] 당첨 번호는 1 ~ 45의 정수만 입력 가능합니다. 다시 입력해주세요."),
    INVALID_LOTTO_SIZE("[ERROR] 로또 번호는 6개여야 합니다. 다시 입력해주세요."),
    DUPLICATE_LOTTO_NUMBERS("[ERROR] 로또 번호는 같은 번호가 존재해서는 안됩니다. 다시 입력해주세요."),

    INVALID_BONUS_NUMBER_RANGE("[ERROR] 보너스 번호는 1 ~ 45의 정수만 입력 가능합니다. 다시 입력해주세요."),
    DUPLICATE_BONUS_WITH_WINNING_NUMBER("[ERROR] 보너스 번호는 당첨 번호와 같은 번호가 존재해서는 안됩니다. 다시 입력해주세요.");
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
