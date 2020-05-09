package co.lq.common.web.param;

import java.util.Arrays;
import java.util.List;

import com.baomidou.mybatisplus.core.metadata.OrderItem;

import cn.hutool.core.collection.CollectionUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("可排序查询参数对象")
public abstract class OrderQueryParam extends QueryParam {
    private static final long serialVersionUID = 57714391204790143L;

    @ApiModelProperty(value = "排序")
    private List<OrderItem>   orders;

    public void defaultOrder(OrderItem orderItem) {
        this.defaultOrders(Arrays.asList(orderItem));
    }

    public void defaultOrders(List<OrderItem> orderItems) {
        if (CollectionUtil.isEmpty(orderItems)) {
            return;
        }
        this.orders = orderItems;
    }

}
