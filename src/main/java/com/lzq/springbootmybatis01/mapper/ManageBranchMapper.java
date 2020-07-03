package com.lzq.springbootmybatis01.mapper;

import com.lzq.springbootmybatis01.entity.ManageBranch;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ManageBranchMapper {
    ManageBranch oneMany(@Param("id") Integer id);
}
