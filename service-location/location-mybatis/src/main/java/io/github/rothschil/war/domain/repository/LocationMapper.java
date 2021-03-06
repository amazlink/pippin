package io.github.rothschil.war.domain.repository;

import io.github.rothschil.base.persistence.mybatis.mapper.BaseMapper;
import io.github.rothschil.war.domain.entity.Location;

import java.util.List;

/**
 * @author WCNGS@QQ.COM
 * @date 2020/9/9 15:12
 * @since 1.0.0
 */
public interface LocationMapper extends BaseMapper<Location, Long> {

    /**
     * 根据主键ID删除实体信息
     *
     * @param id 主键
     * @return int
     * @date 2020/9/9 15:12
     */
    @Override
    int deleteByPrimaryKey(Long id);

    /**
     * 写入
     *
     * @param record    实例
     * @return  int
     * @date 2020/9/9 15:12
     */
    @Override
    int insert(Location record);

    /**
     * 按照条件依次写入
     *
     * @param record    实例
     * @return  int
     * @date 2020/9/9 15:12
     */
    @Override
    int insertSelective(Location record);

    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return  Location
     * @date 2020/9/9 15:12
     */
    @Override
    Location selectByPrimaryKey(Long id);

    /**
     * 根据主键修改 所选中的信息
     *
     * @param record 实例
     * @return  int
     * @date 2020/9/9 15:12
     */
    @Override
    int updateByPrimaryKeySelective(Location record);

    /**
     * 根据属性获取数量
     *
     * @param location 实例
     * @return int
     * @date 2020/9/9 15:12
     */
    int getCount(Location location);

    /**
     * 根据属性获取结果列表
     *
     * @param location 实例
     * @return List<Location>
     * @date 2020/9/9 15:12
     */
    @Override
    List<Location> getList(Location location);

    /**
     * 批量插入
     *
     * @param locations Location列表
     * @return int
     * @date 2020/9/9 15:12
     */
    int insertBatchByOn(List<Location> locations);

    /**
     * 根据属性获取结果列表
     *
     * @param location 实例
     * @return List<Location>
     * @date 2020/9/9 15:12
     */
    List<Location> getList2(Location location);

}