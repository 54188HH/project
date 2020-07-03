package com.lzq.springbootmybatis01.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: springbootshiro
 * @description:
 * @author: liuzhenqi
 * @create: 2020-07-01 13:38
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManageBranch {
    private Integer id;
    private Integer manageId;
    private String manageMajorName;
    private String manageMajorNote;
}
