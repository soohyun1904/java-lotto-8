package lotto.exception;

import lotto.exception.message.ErrorMessage;

import static lotto.exception.message.ErrorMessage.INVALID_VALUE;

public class InputValidationException extends IllegalArgumentException{
  public InputValidationException() {
      super(INVALID_VALUE.getMessage());
  }

  public InputValidationException(ErrorMessage message) {
        super(message.getMessage());
    }
}
