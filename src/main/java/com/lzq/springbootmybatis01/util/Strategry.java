package com.lzq.springbootmybatis01.util;

import java.math.BigDecimal;

public interface Strategry {
    /**
    * @author lzq
    * @params
    * @return
    * @date 2020/5/10
    * @version V1.0
    */
    BigDecimal calRecharge(Integer chanId,Integer goodsId);
}
