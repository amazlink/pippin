package io.github.rothschil.alipay.model.hb;

/** 交易状态
 * @author <a href="https://github.com/rothschil">Sam</a>
 * @description //TODO
 *
 * @date 2018/4/23 - 10:04
 * @since 1.0.0
 */
public enum HbStatus {
    /**
     * // 交易成功（包括支付宝返回“处理中”）
     */
     S

    /**
     * // 支付宝返回处理中
     */
    ,I

    /**
     * // 支付宝返回失败
     */
    ,F

    /**
     * // POSP返回失败，或商户系统失败
     */
    ,P

    /**
     * // 建立连接异常
     */
    ,X

    /**
     * // 报文上送异常
     */
    ,Y

    /**
     * // 报文接收异常
     */
    ,Z

    /**
     * // 收银员取消
     */
    ,C
}
