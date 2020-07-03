package com.lzq.springbootmybatis01.mapper;

import com.lzq.springbootmybatis01.entity.ManageInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ManageInfoMapper {
    List<ManageInfo> selectByPrimaryKey();
}
