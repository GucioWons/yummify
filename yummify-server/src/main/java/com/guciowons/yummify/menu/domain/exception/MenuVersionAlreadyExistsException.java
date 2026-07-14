package com.guciowons.yummify.menu.domain.exception;

import com.guciowons.yummify.menu.domain.exception.message.MenuErrorMessage;

public class MenuVersionAlreadyExistsException extends MenuDomainException {
  public MenuVersionAlreadyExistsException() {
    super(MenuErrorMessage.MENU_VERSION_ALREADY_EXISTS);
  }
}
