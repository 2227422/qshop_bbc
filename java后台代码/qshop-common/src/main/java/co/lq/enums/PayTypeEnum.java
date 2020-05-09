package co.lq.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 支付相关枚举
 */
@Getter
@AllArgsConstructor
public enum PayTypeEnum {

    WEIXIN("weixin", "微信支付"),
    YUE("yue", "余额支付");

    private String value;
    private String desc;

}
