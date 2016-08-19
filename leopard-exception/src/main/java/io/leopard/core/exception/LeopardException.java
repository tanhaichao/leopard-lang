package io.leopard.core.exception;

/**
 * 顶层显示异常类
 * 
 * @author 阿海
 * 
 */
public class LeopardException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * 在JSON接口时使用，比如转成英文
	 */
	private String apiMessage;

	public LeopardException() {
		super();
	}

	public LeopardException(String message) {
		super(message);
	}

	public LeopardException(Throwable cause) {
		super(cause.getMessage(), cause);
	}

	public String getApiMessage() {
		return apiMessage;
	}

	public void setApiMessage(String apiMessage) {
		this.apiMessage = apiMessage;
	}

	/**
	 * 该接口用于生成文档.
	 * 
	 * @return
	 */
	public String getDesc() {
		return null;
	}

}
