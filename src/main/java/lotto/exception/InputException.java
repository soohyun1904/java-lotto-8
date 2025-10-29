package lotto.exception;

import lotto.exception.message.ErrorMessage;

public class InputException extends IllegalArgumentException{
  public InputException() {
  }

  public InputException(ErrorMessage message) {
        super(message.getMessage());
    }
}
