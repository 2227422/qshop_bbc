package co.lq.mp.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.lq.mp.domain.WechatTemplate;
import co.lq.utils.RedisUtil;

/**
 * @ClassName 微信公众号模板通知
 * @Author billy
 * @Date 2020/3/2
 **/
@Service
public class TemplateService {
    private final String               PAY_SUCCESS_KEY      = "OPENTM207791277"; //pay
    private final String               DELIVERY_SUCCESS_KEY = "OPENTM200565259"; //Delivery
    private final String               REFUND_SUCCESS_KEY   = "OPENTM410119152"; //Refund
    private final String               RECHARGE_SUCCESS_KEY = "OPENTM405847076"; //Recharge

    @Autowired
    private WechatTemplateService      templateService;
    @Autowired
    private WxMpTemplateMessageService templateMessageService;

    /**
     * 支付成功通知
     *
     * @param time
     * @param price
     * @param openid
     */
    public void rechargeSuccessNotice(String time, String price, String openid) {
        String siteUrl = RedisUtil.get("site_url");
        WechatTemplate WechatTemplate = templateService.findByTempkey(RECHARGE_SUCCESS_KEY);
        Map<String, String> map = new HashMap<>();
        map.put("first", "您的账户金币发生变动，详情如下：");
        map.put("keyword1", "充值");
        map.put("keyword2", time);
        map.put("keyword3", price);
        map.put("remark", "qshop电商系统为你服务！");
        templateMessageService.sendWxMpTemplateMessage(openid, WechatTemplate.getTempid(),
                siteUrl + "/systemUsers/account", map);
    }

    /**
     * 支付成功通知
     *
     * @param orderId
     * @param price
     * @param openid
     */
    public void paySuccessNotice(String orderId, String price, String openid) {
        String siteUrl = RedisUtil.get("site_url");
        WechatTemplate WechatTemplate = templateService.findByTempkey(PAY_SUCCESS_KEY);
        Map<String, String> map = new HashMap<>();
        map.put("first", "您的订单已支付成功，我们会尽快为您发货。");
        map.put("keyword1", orderId);//订单号
        map.put("keyword2", price);
        map.put("remark", "qshop电商系统为你服务！");
        templateMessageService.sendWxMpTemplateMessage(openid, WechatTemplate.getTempid(),
                siteUrl + "/order/detail/" + orderId, map);
    }

    /**
     * 退款成功通知
     *
     * @param orderId
     * @param price
     * @param openid
     * @param time
     */
    public void refundSuccessNotice(String orderId, String price, String openid, String time) {
        String siteUrl = RedisUtil.get("site_url");
        WechatTemplate WechatTemplate = templateService.findByTempkey(REFUND_SUCCESS_KEY);
        Map<String, String> map = new HashMap<>();
        map.put("first", "您在qshop的订单退款申请被通过，钱款将很快还至您的支付账户。");
        map.put("keyword1", orderId);//订单号
        map.put("keyword2", price);
        map.put("keyword3", time);
        map.put("remark", "qshop电商系统为你服务！");
        templateMessageService.sendWxMpTemplateMessage(openid, WechatTemplate.getTempid(),
                siteUrl + "/order/detail/" + orderId, map);
    }

    /**
     * 发货成功通知
     *
     * @param orderId
     * @param deliveryName
     * @param deliveryId
     * @param openid
     */
    public void deliverySuccessNotice(String orderId, String deliveryName, String deliveryId, String openid) {
        String siteUrl = RedisUtil.get("site_url");
        WechatTemplate WechatTemplate = templateService.findByTempkey(DELIVERY_SUCCESS_KEY);
        Map<String, String> map = new HashMap<>();
        map.put("first", "亲，宝贝已经启程了，好想快点来到你身边。");
        map.put("keyword1", orderId);//订单号
        map.put("keyword2", deliveryName);
        map.put("keyword3", deliveryId);
        map.put("remark", "qshop电商系统为你服务！");
        templateMessageService.sendWxMpTemplateMessage(openid, WechatTemplate.getTempid(),
                siteUrl + "/order/detail/" + orderId, map);
    }

}
