/**
 * 
 */
package com.typ.gk.net;

/**
 * @author fangjunfeng
 *
 */
public class ReturnCode {

	/**
	 * 逻辑正常
	 */
	public final static int SUCCESS = 0;
	
	/**
	 * access token已过期
	 */
	public final static int ACCESS_TOKEN_EXPIRED = 9017;
	
	/**
	 * 请求参数无效
	 */
	public final static int REQUEST_PARAMS_INVALID = 10000;
	
	/**
	 * 调用qq api错误
	 */
	public final static int CALL_QQ_API_ERROR = 10001;
	
	/**
	 * 该用户名已经存在
	 */
	public final static int USERNAME_EXISTED = 10002;
	
	/**
	 * 用户名或密码错误
	 */
	public final static int USERNAME_OR_PASSWORD_ERROR = 10003;
	
	/**
	 * 无效的手机号
	 */
	public final static int INVALID_PHONE_NUMBER = 10004;
	
	/**
	 * 验证码错误
	 */
	public final static int SECURITY_CODE_ERROR = 10005;
	
	/**
	 * 手机号已被绑定
	 */
	public final static int PHONE_BINDED = 10006;
	
	/**
	 * 非法操作
	 */
	public final static int ILLEGAL_OPERATION = 10007;
	
	/**
	 * 发送手机短信异常
	 */
	public final static int SEND_SMS_EXCEPTION = 10008;
	
	/**
	 * 用户不存在
	 */
	public final static int USER_NOT_EXIST = 21001;
	
	/**
	 * 后端程序异常
	 */
	public final static int SERVER_EXCEPTION = 555001;
}
