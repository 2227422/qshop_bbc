package co.lq.modules.shop.web.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 订单退货申请 查询结果对象
 * </p>
 *
 * @author billy
 * @date 2020-04-04
 */
@Data
@ApiModel(value = "OrderReturnApplyQueryVo对象", description = "订单退货申请查询参数")
public class OrderReturnApplyQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long              id;

    @ApiModelProperty(value = "订单id")
    private Long              oid;

    @ApiModelProperty(value = "收货地址表id")
    private Long              addressId;

    @ApiModelProperty(value = "退货商品id")
    private Long              productId;

    @ApiModelProperty(value = "订单编号")
    private String            orderId;

    @ApiModelProperty(value = "会员用户名")
    private String            memberUsername;

    @ApiModelProperty(value = "退款金额")
    private BigDecimal        returnAmount;

    @ApiModelProperty(value = "退货人姓名")
    private String            returnName;

    @ApiModelProperty(value = "退货人电话")
    private String            returnPhone;

    @ApiModelProperty(value = "申请状态：0->待处理；1->退货中；2->已完成；3->已拒绝")
    private Integer           status;

    @ApiModelProperty(value = "商品图片")
    private String            productPic;

    @ApiModelProperty(value = "商品名称")
    private String            productName;

    @ApiModelProperty(value = "商品品牌")
    private String            productBrand;

    @ApiModelProperty(value = "商品销售属性：颜色：红色；尺码：xl;")
    private String            productAttr;

    @ApiModelProperty(value = "退货数量")
    private Integer           productCount;

    @ApiModelProperty(value = "商品单价")
    private BigDecimal        productPrice;

    @ApiModelProperty(value = "商品实际支付单价")
    private BigDecimal        productRealPrice;

    @ApiModelProperty(value = "原因")
    private String            reason;

    @ApiModelProperty(value = "描述")
    private String            description;

    @ApiModelProperty(value = "凭证图片，以逗号隔开")
    private String            proofPics;

    @ApiModelProperty(value = "处理备注")
    private String            handleNote;

    @ApiModelProperty(value = "处理人员")
    private String            handleMan;

    @ApiModelProperty(value = "收货人")
    private String            receiveMan;

    @ApiModelProperty(value = "收货时间")
    private Timestamp         receiveTime;

    @ApiModelProperty(value = "收货备注")
    private String            receiveNote;

    @ApiModelProperty(value = "所属店铺")
    private Long              storeId;

    @ApiModelProperty(value = "添加时间")
    private Timestamp         addTime;

    @ApiModelProperty(value = "更新时间")
    private Timestamp         modifyTime;

    @ApiModelProperty(value = "逻辑删除")
    private Boolean           deleted;

    @ApiModelProperty(value = "订单金额")
    private BigDecimal        orderAmount;

    @ApiModelProperty(value = "退货换类型")
    private Integer           returnType;

    @ApiModelProperty(value = "订单状态")
    private Integer           orderStatus;

}
