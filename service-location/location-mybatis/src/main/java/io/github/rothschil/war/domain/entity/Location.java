package io.github.rothschil.war.domain.entity;

import io.github.rothschil.common.po.BasePo;
import lombok.*;

/**
 * 行政区域实体类
 *
 * @author WCNGS@QQ.COM
 * @date 2020/9/9 15:27
 * @since 1.0.0
 */
@Builder(toBuilder = true)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Location extends BasePo<Long> {

    private Long id;

    private String flag;

    private String localCode;

    private String localName;

    private Integer lv;

    private String supLocalCode;

    private String url;

    public Location(Long id, String localName, String supLocalCode, String url, Integer lv) {
        this.id = id;
        this.localName = localName;
        this.supLocalCode = supLocalCode;
        this.url = url;
        this.lv = lv;
    }

    public Location(String localCode, String localName, String supLocalCode, String url, Integer lv) {
        super();
        this.localCode = localCode;
        this.localName = localName;
        this.supLocalCode = supLocalCode;
        this.url = url;
        this.lv = lv;
    }

    public Location(String localCode, String localName, String supLocalCode, String url, Integer lv, String flag) {
        super();
        this.localCode = localCode;
        this.localName = localName;
        this.supLocalCode = supLocalCode;
        this.url = url;
        this.lv = lv;
        this.flag = flag;
    }

}