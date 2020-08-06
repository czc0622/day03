package com.xiaoshu.dao;

import com.xiaoshu.base.dao.BaseMapper;
import com.xiaoshu.entity.Dtype;
import com.xiaoshu.entity.DtypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DtypeMapper extends BaseMapper<Dtype> {
    long countByExample(DtypeExample example);

    int deleteByExample(DtypeExample example);

    List<Dtype> selectByExample(DtypeExample example);

    int updateByExampleSelective(@Param("record") Dtype record, @Param("example") DtypeExample example);

    int updateByExample(@Param("record") Dtype record, @Param("example") DtypeExample example);
}