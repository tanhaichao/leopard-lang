package io.leopard.core.exception.invalid;

import io.leopard.core.exception.InvalidException;

/**
 * 非法昵称.
 * 
 * @author 阿海
 * 
 */
public class NicknameInvalidException extends InvalidException {

	private static final long serialVersionUID = 1L;

	public NicknameInvalidException(String nickname) {
		super("非法昵称[" + nickname + "].");
	}

}
