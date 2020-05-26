package com.lzq.springbootmybatis01.Controller;

import com.lzq.springbootmybatis01.Entity.Admin;
import com.lzq.springbootmybatis01.Mapper.AdminMapper;
import com.lzq.springbootmybatis01.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
public class LoginController {

    @Value("{$token.key}")
    private String key;
    @Autowired
    private AdminMapper adminMapper;

    @RequestMapping("/login")
    @ResponseBody
    public String loginHl(Admin admin){
        Admin ad = adminMapper.selectOne(admin);
        //通过request获取服务器的ip地址，这里呢我写死了
        String salt = "192.168.3.116";
        if (null != ad){
            //生成token
            HashMap<String, Object> map = new HashMap<>();
            map.put("userId",ad.getId());
            map.put("nickName",ad.getUserName());
            String token = JwtUtil.encods(key,map,salt);
            return token;
        }
        return "fail";
    }
}
