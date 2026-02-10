package com.guciowons.yummify.menu.domain.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;

public class MenuVersionAlreadyExistsException extends DomainException {
  public MenuVersionAlreadyExistsException() {
    super("Menu version already exists");
  }
}
