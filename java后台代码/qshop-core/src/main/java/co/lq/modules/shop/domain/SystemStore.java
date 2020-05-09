package co.lq.modules.shop.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.Data;

/**
 * @author billy
 * @date 2020-03-03
 */
@Entity
@Data
@Table(name = "system_store")
public class SystemStore implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long    id;

    /** 门店名称 */
    @Column(name = "name", nullable = false)
    @NotBlank
    private String  name;

    /** 简介 */
    @Column(name = "introduction", nullable = false)
    @NotBlank
    private String  introduction;

    /** 手机号码 */
    @Column(name = "phone", nullable = false)
    @NotBlank
    private String  phone;

    /** 省市区 */
    @Column(name = "address", nullable = false)
    @NotBlank
    private String  address;

    /** 详细地址 */
    @Column(name = "detailed_address", insertable = false)
    private String  detailedAddress;

    /** 门店logo */
    @Column(name = "image", nullable = false)
    @NotBlank(message = "请上传门店logo")
    private String  image;

    /** 纬度 */
    @Column(name = "latitude", nullable = false)
    @NotBlank
    private String  latitude;

    /** 经度 */
    @Column(name = "longitude", nullable = false)
    @NotBlank
    private String  longitude;

    /** 核销有效日期 */
    @Column(name = "valid_time", nullable = false)
    @NotBlank
    private String  validTime;

    @Column(name = "valid_time_start", nullable = false)
    private Date    validTimeStart;

    @Column(name = "valid_time_end", nullable = false)
    private Date    validTimeEnd;

    /** 每日营业开关时间 */
    @Column(name = "day_time", nullable = false)
    @NotBlank
    private String  dayTime;

    @Column(name = "day_time_start", nullable = false)
    private Date    dayTimeStart;

    @Column(name = "day_time_end", nullable = false)
    private Date    dayTimeEnd;

    /** 添加时间 */
    @Column(name = "add_time", nullable = false)
    private Integer addTime;

    /** 是否显示 */
    @Column(name = "is_show", insertable = false)
    private Integer isShow;

    /** 是否删除 */
    @Column(name = "is_del", insertable = false)
    private Integer isDel;

    // 店铺id
    @Column(name = "store_id", nullable = false)
    private Long    storeId;

    public void copy(SystemStore source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
