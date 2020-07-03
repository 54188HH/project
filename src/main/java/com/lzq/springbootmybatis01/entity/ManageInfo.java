package com.lzq.springbootmybatis01.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @program: springbootshiro
 * @description:
 * @author: liuzhenqi
 * @create: 2020-07-01 13:38
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManageInfo {
    private Integer id;
    private String manageName;
    private String manageNote;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date insertTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    private List<ManageBranch> list;

}
