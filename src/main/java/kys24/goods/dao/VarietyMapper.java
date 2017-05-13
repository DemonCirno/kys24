package kys24.goods.dao;

import kys24.goods.model.Variety;

public interface VarietyMapper {
    int deleteByPrimaryKey(Integer varietyId);

    int insert(Variety record);

    int insertSelective(Variety record);

    Variety selectByPrimaryKey(Integer varietyId);

    int updateByPrimaryKeySelective(Variety record);

    int updateByPrimaryKey(Variety record);
}