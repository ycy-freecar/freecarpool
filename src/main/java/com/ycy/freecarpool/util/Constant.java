package com.ycy.freecarpool.util;

/**
 * 静态常量配置
 * @author JiaYY
 * @create 2016/5/18 20:08
 */
public class Constant {

    public static final String LOGO_MODULE_MERCHANT = "merchant";                           // 默认图标分类-商户
    public static final String MERCHANT_DEFAULT_LOGO = "merchant_default_logo";             // 商户默认图标名称
    public static final String MERCHANT_DEFAULT_GRAY_LOGO = "merchant_default_gray_logo";   // 商户默认图标名称

    public static final int REDIS_TIMES_MINUTE   = 60;                  //缓存时长-分钟
    public static final int REDIS_TIMES_HOUR     = 60 * 60;             //缓存时长-小时
    public static final int REDIS_TIMES_DAY      = 24 * 60 * 60;        //缓存时长-天
    public static final int REDIS_TIMES_WEEK     = 7 *24 * 60 * 60;     //缓存时长-周
    public static final int REDIS_TIMES_MONTH    = 30 * 24 * 60 * 60;   //缓存时长-月

    // 商户类型
    public static final int MER_ENTERPRISE      = 0;  // 企业
    public static final int MER_SELF_EMPLOYED   = 1;  // 个体户
    public static final int MER_PERSON_EMPLOYED = 2;  // 人人开店

    public static final int DEAL_SRC_SHOP_CODE = 1; // 用户扫商户二维码
    public static final int DEAL_SRC_SHOP_APP  = 2; // 商户app扫码
    public static final int DEAL_SRC_SCANER    = 3; // 商户扫码枪扫用户的码
    public static final int DEAL_SRC_NINE_ZONE = 4; // 九宫格

    public static final int DEAL_SRC_SHOP_CODE_DEFAULT = 10; // 默认订单编号业务ID
    public static final int DEAL_SRC_SHOP_CODE_BIZID = 11;   // 用户扫商户二维码-业务ID
    public static final int DEAL_SRC_SHOP_APP_BIZID  = 12;   // 商户app扫码-业务ID
    public static final int DEAL_SRC_SCANER_BIZID    = 13;   // 商户扫码枪扫用户的码-业务ID
    public static final int DEAL_SRC_NINE_ZONE_BIZID = 14;   // 九宫格-业务ID


    public static final int SEX_MALE = 1;           // 性别-男性
    public static final int SEX_FEMALE = 2;         // 性别-女性
    public static final int GENDER_MALE = 0;        // 性别-男性
    public static final int GENDER_FEMALE = 1;      // 性别-女性
    public static final int GENDER_UNKNOWN = 2;     // 性别-未知
    public static final int GENDER_ERROR = 3;       // 性别-异常

    public static final int QUERY_FIRST_PAGE = 1;   // 查询-首页页码
    public static final int QUERY_PAGE_LIMIT = 100; // 查询-每页数量

    // 分发途径
    public static final int DISTE_NONE = 0;
    public static final int DISTE_STORE_FAVOR = 1;
    public static final int DISTE_SCAN_CODE = 2;
    public static final int DISTE_DEAL_DONE = 3;
    public static final int DISTE_REGISTER = 4;
    public static final int DISTE_AUTO = 5;
    public static final int DISTE_ACTIVITY_FAVOR = 7;

    // Favor type
    public static final int FAVOR_COUPON      = 1; // 优惠券
    public static final int FAVOR_ACTIVITY    = 2; // 平台活动

    //Favor type tip
    public static final String FAVOR_COUPON_TIP      = "优惠券"; // 优惠券
    public static final String FAVOR_ACTIVITY_TIP    = "平台活动"; // 平台活动

    // 分发状态
    public static final int DISTE_STATUS_INIT    = 0;
    public static final int DISTE_STATUS_INHAND  = 1;
    public static final int DISTE_STATUS_SUCCESS = 2;
    public static final int DISTE_STATUS_FAILED  = 3;

    //核销状态
    public static final int VERIFY_TYPE_DEFAULT = 0;
    public static final int VERIFY_TYPE_MERCHANT = 1;
    public static final int VERIFY_TYPE_FRONT = 2;
    public static final int VERIFY_TYPE_THIRD = 3;

    public static final int ACCOUNT_ID_OFFSET = 11009527;  //会员表分表取序号使用
    public static final int MERCHANT_PER_TABLE = 200;      //会员表分表取模使用

    public static final int COUPON_CENTER_CHECK_ERRNO = 1015;   //已核销

    //虚拟卡券的状态
    public static final int COUPON_STATUS_UNUSED = 0;
    public static final int COUPON_STATUS_LOCKED = 1;
    public static final int COUPON_STATUS_VERIFIED = 2;
    public static final int COUPON_STATUS_ABANDON = 3;

    //卡券状态
    public static final int COUPON_LOCK       = 0; // 锁定
    public static final int COUPON_VERIFY     = 1; // 核销
    public static final int COUPON_UNLOCK     = 2; // 解锁
    public static final int COUPON_UNKNOWN    = 3; // 未知

    //卡券核销赠送通话时长
    public static final int USER_CALL_MINUTES  = 100; // 赠送通话时长
    public static final int USER_CALL_TYPE     = 1;   // 赠送通话类型-卡券核销
    public static final int USER_CALL_STATUS   = 1;   // 赠送通话状态-已发放

    public static final int ACTIVITY_MERCHANT_RELATION_STATUS = 1;

    //平台活动四种用户类型
    public static final int ACTIVITY_USER_TYPE_ALL = 0;
    public static final int ACTIVITY_USER_TYPE_FIRST_ORDER = 1;
    public static final int ACTIVITY_USER_TYPE_MEMBER = 2;
    public static final int ACTIVITY_USER_TYPE_ASSIGN = 3;

    //平台活动四种状态
    public static final int ACTIVITY_STATUS_DELETE = -1;
    public static final int ACTIVITY_STATUS_UNDO = 0;
    public static final int ACTIVITY_STATUS_DOING = 1;
    public static final int ACTIVITY_STATUS_DONE = 2;

    public static final int COUPON_STATE_ALL = -1;
    public static final int COUPON_STATE_WAIT = 0;
    public static final int COUPON_STATE_PASS = 1;
    public static final int COUPON_STATE_ING = 2;
    public static final int COUPON_STATE_ONLINE = 3;
    public static final int COUPON_STATE_DEL = 4;
    public static final int COUPON_STATE_REJECT = 6;
    public static final int COUPON_STATE_AUDIT = 7;   //已经核销

    // Coupon category
    public static final int COUPON_CATEGORY_MERCHANT = 0; // 商户卡券
    public static final int COUPON_CATEGORY_PERSON   = 1; // 个人卡券

    //卡券颜色
    public static final int COUPON_COLOR_WHITE = 0;
    public static final int COUPON_COLOR_RED = 1;
    public static final int COUPON_COLOR_YELLOW = 2;
    public static final int COUPON_COLOR_BLUE = 3;
    public static final int COUPON_COLOR_GREEN = 4;
    public static final int COUPON_COLOR_BLACK = 5;
    public static final int COUPON_COLOR_PURPLE = 6;

    // Flowing type
    public static final int FLOWING_PAY           = 0; // 交易
    public static final int FLOWING_REFUND_PART   = 1; // 部分
    public static final int FLOWING_REFUND_ALL    = 2; // 全部
    public static final int FLOWING_REFUND_SETTLE = 3; // 结算

    public static final int FLOWING_INIT      = 0; // 初始
    public static final int FLOWING_SUCCESS   = 1; // 成功
    public static final int FLOWING_FAILED    = 2; // 失败
    public static final int FLOWING_INPROCESS = 3; // 进行中
    public static final int FLOWING_CLOSE     = 4; // 关闭
    public static final int FLOWING_FOR_PAY   = 5; // 待处理

    // Deal status
    public static final int DEAL_INIT         = 0; // 超时
    public static final int DEAL_SUCCESS      = 1; // 成功
    public static final int DEAL_FAILED       = 2; // 失败
    public static final int DEAL_INPROCESS    = 3; // 进行中
    public static final int DEAL_CLOSE        = 4; // 关闭
    public static final int DEAL_FOR_PAY      = 5; // 待付款
    public static final int DEAL_REFUND       = 6; // 退款
    public static final int DEAL_REFUND_FAIL  = 7; // 退款失败
    public static final int DEAL_IN_REFUND    = 8; // 退款中

    // Pay mode
    public static final int PAY_MODE_BALANCE   = 1; // 余额支付
    public static final int PAY_MODE_BANK      = 2; // 银行卡支付
    public static final int PAY_MODE_MIXED     = 3; // 混合支付

    //请求应答状态
    public static final String RESPONSE_SUCCESS = "SUCCESS"; //成功
    public static final String RESPONSE_FAILED = "FAILED";   //失败


    //平台活动类型
    public static final int ACTIVITY_TYPE_FIXED = 1;
    public static final int ACTIVITY_TYPE_RANDOM = 2;


    //券类型
    public static final int COUPON_CASH = 1;          //现金券
    public static final int COUPON_DISCOUNT = 2;      //折扣券
    public static final int COUPON_SALE = 3;            //体验券
    public static final int COUPON_PHONE = 4;            //话费券
    public static final int COUPON_FLOW = 5;            //流量券
    public static final int COUPON_LINK = 6;            //链接券
    public static final int COUPON_THIRD = 9;            //第三方券

    // 会员来源
    public static final int SRC_ORDER        = 0; // 下单
    public static final int SRC_REGISTER     = 1; // 注册拉新

    public static final String PASSPORT_UINFO_FIELD  = "ext,vali_status";   // passport用户信息查询属性

    public static final String COUPON_USE_CONDITION_FACE      = "2"; // 肖像认证
    public static final String COUPON_USE_CONDITION_BANKCARD  = "3"; // 帮卡认证

    public static final int COUPON_USE_CONDITION_AGE_MIN   = 18;
    public static final int COUPON_USE_CONDITION_AGE_MAX   = 60;


    public static final int SMS_DEAL_DONE     = 3; // 短信类型

    public static final String COUPON_COMMON_MERCHANT_ID  = "2"; // 通用券商户号

    public static final String CONFIG_ACTIVITY_WHITELIST_NAME    = "white_list"; // 配置活动白名单-名称
    public static final String CONFIG_ACTIVITY_WHITELIST_MODULE  = "measure";    // 配置活动白名单-模块

    public static final String MERCHANT_ACTIVE  = "1"; // 正常商户
    public static final String MERCHANT_NOT_ACTIVE  = "0"; // 下架商户
}
