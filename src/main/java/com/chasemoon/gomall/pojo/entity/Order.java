package com.chasemoon.gomall.pojo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="orders")
@Data
public class Order {
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;
    @Id
    private String orderId;
    //private int userId;
    @Column(name="user_currency")
    private String userCurrency;//下单货币类型
    @Embedded
//    @AttributeOverrides({ // 映射字段名到数据库列
//            @AttributeOverride(name = "streetAddress", column = @Column(name = "street_address")),
//            @AttributeOverride(name = "zipCode", column = @Column(name = "zip_code"))
//    })
    private OrderAddress address;
    @Column
    private String email;
    @Column
    private Date created_at;
    @Column
    private OrderStatus orderStatus;

    public enum OrderStatus {
        /**
         * 待支付（用户已下单但未完成支付）
         */
        PENDING_PAYMENT(1, "待支付", false, true),

        /**
         * 已支付（用户已完成支付，等待商家处理）
         */
        PAID(2, "已支付", false, false),

        /**
         * 已发货（商家已发出商品，等待物流配送）
         */
        SHIPPED(3, "已发货", false, false),

        /**
         * 已签收（用户已收到商品）
         */
        DELIVERED(4, "已签收", false, false),

        /**
         * 订单完成（交易成功闭环）
         */
        COMPLETED(5, "已完成", false, false),

        /**
         * 退款中（用户申请退款，系统处理中）
         */
        REFUNDING(6, "退款中", true, false),

        /**
         * 退款成功（退款到账）
         */
        REFUND_SUCCESS(7, "退款成功", true, false),

        /**
         * 退款失败（退款被拒绝）
         */
        REFUND_FAILED(8, "退款失败", true, false),

        /**
         * 订单取消（用户主动取消）
         */
        CANCELLED_BY_USER(9, "用户取消", true, true),

        /**
         * 系统自动取消（超时未支付等）
         */
        CANCELLED_BY_SYSTEM(10, "系统取消", true, true),

        /**
         * 已退货（用户已发起退货）
         */
        RETURNED(11, "已退货", true, false),

        /**
         * 退货审核中（商家处理退货申请）
         */
        RETURNING_AUDITING(12, "退货审核中", true, false),

        /**
         * 退货完成（退货已处理完毕）
         */
        RETURN_COMPLETED(13, "退货完成", true, false),

        /**
         * 异常订单（如欺诈、物流问题等）
         */
        EXCEPTION(14, "异常订单", true, false);

        private final int code;
        private final String name;
        private final boolean canCancel; // 是否可取消
        private final boolean canRefund; // 是否可退款

        OrderStatus(int code, String name, boolean canCancel, boolean canRefund) {
            this.code = code;
            this.name = name;
            this.canCancel = canCancel;
            this.canRefund = canRefund;
        }

        // Getter 方法
        public int getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public boolean isCanCancel() {
            return canCancel;
        }

        public boolean isCanRefund() {
            return canRefund;
        }
    }
}
