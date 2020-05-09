package co.lq.mp.config;

import java.util.Map;

import org.springframework.context.annotation.Configuration;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import com.google.common.collect.Maps;

import co.lq.constant.ShopConstants;
import co.lq.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 支付配置
 *
 * @author billy
 * @date 2020/03/01
 */
@Slf4j
@Configuration
public class WxPayConfiguration {

    private static Map<String, WxPayService> payServices = Maps.newHashMap();

    /**
     * 获取WxPayService
     *
     * @return
     */
    public static WxPayService getPayService() {
        WxPayService wxPayService = payServices.get(ShopConstants.QSHOP_WEIXIN_PAY_SERVICE);
        if (wxPayService == null || RedisUtil.get(ShopConstants.QSHOP_WEIXIN_PAY_SERVICE) == null) {
            WxPayConfig payConfig = new WxPayConfig();
            payConfig.setAppId(RedisUtil.get("wechat_appid"));
            payConfig.setMchId(RedisUtil.get("wxpay_mchId"));
            payConfig.setMchKey(RedisUtil.get("wxpay_mchKey"));
            payConfig.setKeyPath(RedisUtil.get("wxpay_keyPath"));
            // 可以指定是否使用沙箱环境
            payConfig.setUseSandboxEnv(false);
            wxPayService = new WxPayServiceImpl();
            wxPayService.setConfig(payConfig);
            payServices.put(ShopConstants.QSHOP_WEIXIN_PAY_SERVICE, wxPayService);

            //增加标识
            RedisUtil.set(ShopConstants.QSHOP_WEIXIN_PAY_SERVICE, "yshop");
        }
        return wxPayService;
    }

    /**
     * 获取WxAppPayService
     *
     * @return
     */
    public static WxPayService getWxAppPayService() {
        WxPayService wxPayService = payServices.get(ShopConstants.QSHOP_WEIXIN_MINI_PAY_SERVICE);
        if (wxPayService == null || RedisUtil.get(ShopConstants.QSHOP_WEIXIN_PAY_SERVICE) == null) {
            WxPayConfig payConfig = new WxPayConfig();
            payConfig.setAppId(RedisUtil.get("wxapp_appId"));
            payConfig.setMchId(RedisUtil.get("wxpay_mchId"));
            payConfig.setMchKey(RedisUtil.get("wxpay_mchKey"));
            payConfig.setKeyPath(RedisUtil.get("wxpay_keyPath"));
            // 可以指定是否使用沙箱环境
            payConfig.setUseSandboxEnv(false);
            wxPayService = new WxPayServiceImpl();
            wxPayService.setConfig(payConfig);
            payServices.put(ShopConstants.QSHOP_WEIXIN_MINI_PAY_SERVICE, wxPayService);

            //增加标识
            RedisUtil.set(ShopConstants.QSHOP_WEIXIN_PAY_SERVICE, "yshop");
        }
        return wxPayService;
    }

    /**
     * 移除WxPayService
     */
    public static void removeWxPayService() {
        RedisUtil.del(ShopConstants.QSHOP_WEIXIN_PAY_SERVICE);
        payServices.remove(ShopConstants.QSHOP_WEIXIN_PAY_SERVICE);
        payServices.remove(ShopConstants.QSHOP_WEIXIN_MINI_PAY_SERVICE);
    }

}
