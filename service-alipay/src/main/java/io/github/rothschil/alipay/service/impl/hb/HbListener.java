package io.github.rothschil.alipay.service.impl.hb;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import io.github.rothschil.alipay.model.hb.HbStatus;
import io.github.rothschil.alipay.model.hb.SysTradeInfo;

/** 交易保障监听器实现，每当交易完成后，将交易耗时写入队列
 * @author <a href="https://github.com/rothschil">Sam</a>
 * @description //TODO
 *
 * @date 2018/4/23 - 10:14
 * @since 1.0.0
 */
public class HbListener implements TradeListener {
    private static Log log = LogFactory.getLog(HbListener.class);

    private void offerTradeInfo(String outTradeNo, long beforeCall, HbStatus status) {
        // 获取当前毫秒数
        long afterCall = System.currentTimeMillis();

        // 通过交易请求发生时毫秒数计算得到交易耗时
        double timeConsume = ((double) (afterCall - beforeCall)) / 1000.0;

        log.debug(outTradeNo + " offer " + status + ": " + timeConsume);

        // 创建系统商trade_info结构，添加进队列
        HbQueue.offer(SysTradeInfo.newInstance(outTradeNo, timeConsume, status));
    }

    /** 支付成功
     * @author <a href="https://github.com/rothschil">Sam</a>
     * @description //TODO
     * @date 2018/4/23-10:14
     * @param outTradeNo
     * @param beforeCall
     * @return
     **/
    @Override
    public void onPayTradeSuccess(String outTradeNo, long beforeCall) {
        offerTradeInfo(outTradeNo, beforeCall, HbStatus.S);
    }

    @Override
    public void onPayInProgress(String outTradeNo, long beforeCall) {
        offerTradeInfo(outTradeNo, beforeCall, HbStatus.I);
    }

    @Override
    public void onPayFailed(String outTradeNo, long beforeCall) {
        offerTradeInfo(outTradeNo, beforeCall, HbStatus.F);
    }

    @Override
    public void onConnectException(String outTradeNo, long beforeCall) {
        offerTradeInfo(outTradeNo, beforeCall, HbStatus.X);
    }

    @Override
    public void onSendException(String outTradeNo, long beforeCall) {
        offerTradeInfo(outTradeNo, beforeCall, HbStatus.Y);
    }

    @Override
    public void onReceiveException(String outTradeNo, long beforeCall) {
        offerTradeInfo(outTradeNo, beforeCall, HbStatus.Z);
    }
}
