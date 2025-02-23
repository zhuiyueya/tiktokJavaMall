package com.chasemoon.gomall.common;

public interface Constants {
    // 定义HTTP头部字段名常量
    public static final String REQUEST_HEADER_AUTHORIZATION = "Authorization";

    // 定义Bearer Token前缀常量
    public static final String TOKEN_PREFIX_BEARER = "Bearer ";

    // 定义Bearer Token前缀的长度常量
    public static final int TOKEN_PREFIX_BEARER_LENGTH = TOKEN_PREFIX_BEARER.length();

    //无效Token常量
    public static final String INVALID_TOKEN = "Invalid Token";

    public static final String LIST_PRODUCTS_FAILED="list所有商品失败";

    public static final String MARK_ORDER_PAIED_FAILED="将订单标记为已支付失败：订单号错误/用户不存在/订单号非该用户所持有";
}
