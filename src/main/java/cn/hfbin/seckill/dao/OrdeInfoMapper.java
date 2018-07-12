package cn.hfbin.seckill.dao;

import cn.hfbin.seckill.entity.OrdeInfo;

public interface OrdeInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrdeInfo record);

    int insertSelective(OrdeInfo record);

    OrdeInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrdeInfo record);

    int updateByPrimaryKey(OrdeInfo record);
}