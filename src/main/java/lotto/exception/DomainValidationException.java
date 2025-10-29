package lotto.exception;

import lotto.exception.message.ErrorMessage;
import static lotto.exception.message.ErrorMessage.INVALID_VALUE;

public class DomainValidationException extends IllegalArgumentException{
  public DomainValidationException() {
    super(INVALID_VALUE.getMessage());
  }

  public DomainValidationException(ErrorMessage message) {
        super(message.getMessage());
    }
}
