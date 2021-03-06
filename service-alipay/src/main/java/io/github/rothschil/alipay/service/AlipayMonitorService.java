package io.github.rothschil.alipay.service;

import com.alipay.api.response.MonitorHeartbeatSynResponse;
import io.github.rothschil.alipay.model.builder.AlipayHeartbeatSynRequestBuilder;

/** 提供交易保障服务
 * @author <a href="https://github.com/rothschil">Sam</a>
 * @description //TODO
 * 
 * @date 2018/4/23 - 10:10
 * @since 1.0.0
 */
public interface AlipayMonitorService {

    // 交易保障接口 https://openhome.alipay.com/platform/document.htm#mobileApp-barcodePay-API-heartBeat

    // 可以提供给系统商/pos厂商使用
    MonitorHeartbeatSynResponse heartbeatSyn(AlipayHeartbeatSynRequestBuilder builder);
}
