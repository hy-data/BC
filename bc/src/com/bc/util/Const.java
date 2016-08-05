package com.bc.util;

import org.springframework.context.ApplicationContext;

/**
 * 项目名称：
 * 
 * @author:fh
 * 
 */ 
public class Const {
	public static final String			SESSION_SECURITY_CODE					= "sessionSecCode";
	public static final String			SESSION_USER							= "sessionUser";
	public static final String			SESSION_ROLE_RIGHTS						= "sessionRoleRights";
	public static final String			SESSION_menuList						= "menuList";																					// 当前菜单
	public static final String			SESSION_allmenuList						= "allmenuList";																				// 全部菜单
	public static final String			SESSION_QX								= "QX";
	public static final String			SESSION_userpds							= "userpds";
	public static final String			SESSION_USERROL							= "USERROL";																					// 用户对象
	public static final String			SESSION_USERNAME						= "USERNAME";																					// 用户名
	public static final String			TRUE									= "T";
	public static final String			FALSE									= "F";
	public static final String			LOGIN									= "/login_toLogin.do";																			// 登录地址
	public static final String			SYSNAME									= "admin/config/SYSNAME.txt";																	// 系统名称路径
	public static final String			PAGE									= "admin/config/PAGE.txt";																		// 分页条数配置路径
	public static final String			EMAIL									= "admin/config/EMAIL.txt";																	// 邮箱服务器配置路径
	public static final String			SMS1									= "admin/config/SMS1.txt";																		// 短信账户配置路径1
	public static final String			SMS2									= "admin/config/SMS2.txt";																		// 短信账户配置路径2
	public static final String			FWATERM									= "admin/config/FWATERM.txt";																	// 文字水印配置路径
	public static final String			IWATERM									= "admin/config/IWATERM.txt";																	// 图片水印配置路径
	public static final String			WEIXIN									= "admin/config/WEIXIN.txt";																	// 微信配置路径
	public static final String			FILEPATHIMG								= "uploadFiles/uploadImgs/";																	// 图片上传基路径
	public static final String			TEMP_IMG_PATH							= "uploadFiles/temp/";																			// 艺术品图片临时保存位置
	public static final String			PATH_PBL								= "pbl/";																						// 艺术品图片瀑布流缩略图子目录
	public static final String			PATH_TYPE								= "type/";																						// 艺术品图片类型缩略图子目录
	public static final int				PBL_WIDTH								= 268;																							// 艺术品图片瀑布流缩略图指定宽度
	public static final int				TYPE_WIDTH								= 268;																							// 艺术品图片类型缩略图指定宽度
	public static final int				TYPE_HEIGHT								= 160;																							// 艺术品图片类型缩略图指定高度
	public static final int				MAX_HEIGHT								= 500;																							// 艺术品图片最大高度
	public static final String			FILEPATHFILE							= "uploadFiles/file/";																			// 文件上传路径
	public static final String			UPLOAD_MEMBER_IMAGE_PATH				= "Members/";																					// 会员头像路径
	public static final String			UPLOAD_ARTWORK_IMAGE_PATH				= "Arts/";																						// 艺术品路径
	public static final String			UPLOAD_ARTWORK_SCENCE_PATH				= "Arts/scence/";																				// 艺术品路径
	public static final String			UPLOAD_ARTWORK_IDCARDS_PATH				= "IDcards/";																					// 身份证照片路径
	public static final String			UPLOAD_EXHIBIT_BANNER_PATH				= "Exhibits/banner";																			// 展讯图片路径
																																												// 2016-04-05
	public static final String			UPLOAD_EXHIBIT_TAILMAP_PATH				= "Exhibits/tailmap";																			// 展讯尾图路径
																																												// 2016-04-05
	public static final String			FILEPATHTWODIMENSIONCODE				= "uploadFiles/twoDimensionCode/";																// 二维码存放路径
	public static final String			NO_INTERCEPTOR_PATH						= ".*/((login)|(logout)|(code)|(sc)|(weixin)|(static)|(uploadFiles)|(main)|(websocket)).*";	// 不对匹配该值的访问路径拦截（正则）
	public static final String			ARTWORK_BASE_PATH						= "uploadFiles/uploadImgs/";
	public static final String			DEFAULT_MEMBER_IMAGE					= UPLOAD_MEMBER_IMAGE_PATH
																						+ "member.png";																		// 默认头像地址
	/**
	 * 数据库删除标识（已删除）
	 */
	public static final int				IS_DELETE_YES							= 1;

	/**
	 * 数据库删除标识（未删除）
	 */
	public static final int				IS_DELETE_NO							= 0;
	/**
	 * 用户被封号标识
	 */
	public static final int				IS_DELETE_LIMIT							= 2;

	public static ApplicationContext	WEB_APP_CONTEXT							= null;																						// 该值会在web容器启动时由WebAppContextListener初始化

	/**
	 * 订单状态-已取消：0
	 */
	public static final int				INDENT_STATUS_CANCELLED					= 0;

	/**
	 * 订单状态-待付款：1
	 */
	public static final int				INDENT_STATUS_ToBePaid					= 1;
	/**
	 * 订单状态-待发货2(已付款)
	 */
	public static final int				INDENT_STATUS_ToBeShipped				= 2;
	/**
	 * 订单状态-售后处理中3
	 */
	public static final int				INDENT_STATUS_SaleSupport				= 3;
	/**
	 * 订单状态-待收货：4
	 * */
	public static final int				INDENT_STATUS_ToBeReceived				= 4;
	/**
	 * 订单状态-已完成：5
	 */
	public static final int				INDENT_STATUS_Completed					= 5;
	/**
	 * 订单状态-退款中：6
	 */
	public static final int				INDENT_STATUS_Refund					= 6;
	/**
	 * 订单状态-已处理：7
	 */
	public static final int				INDENT_STATUS_OVER						= 7;
	/**
	 * 订单状态-已退款：8
	 */
	public static final int				INDENT_STATUS_Refunded					= 8;

	// ------自定义短信中变量名称---------

}
