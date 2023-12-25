package com.bookstore.common;

public interface Constants {

//      "Pending" (Đang chờ xử lý): Đơn hàng đã được đặt nhưng chưa được xử lý hoặc chưa được xác nhận.
//      "Processing" (Đang xử lý): Đơn hàng đang được xử lý và chuẩn bị để giao hàng.
//      "Shipped" (Đã giao hàng): Đơn hàng đã được giao cho đơn vị vận chuyển và đang trong quá trình giao hàng.
//      "Delivered" (Đã giao): Đơn hàng đã được giao thành công cho khách hàng.
//      "Cancelled" (Đã hủy): Đơn hàng đã bị hủy bỏ trước khi giao hàng.
//      "Returned" (Đã trả hàng): Đơn hàng đã được trả lại bởi khách hàng hoặc không thể giao hàng thành công.

    public static interface STATUS_ORDER {
        //dư liệu đang bị fix cứng: sửa sau
        final public static String PENDING = "Pending";
        final public static String PROCESSING = "Processing";
        final public static String SHIPPED = "Shipped";
        final public static String DELIVERED = "Delivered";
        final public static String CANCELLED = "Cancelled";
        final public static String RETURNED = "Returned";
    }

    public static interface ResponseCode {

        final public static String SUCCESS = "00";
        final public static String FAILURE = "01";
        final public static String COMMON_FAILURE = "99";

        final public static String EXPIRED_JWT = "07";
        final public static String INVALID_JWT = "09";

        final public static String INVALID_LDAP_AUTHEN = "10";

        final public static String AccessDenied = "02";
        final public static String NOT_FOUND = "03";
        final public static String KAFKA_SEND_FAILURE = "04";
        final public static String CONNECTION_TIMEOUT = "05";
        final public static String REQUEST_TIMEOUT = "06";
        final public static String RESOURCE_ACCESS = "08";
    }

}
