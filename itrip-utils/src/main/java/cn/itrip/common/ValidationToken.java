package cn.itrip.common;

import cn.itrip.beans.pojo.ItripUser;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Token验证
 * Created by hanlu on 2017/5/7.
 */
@CommonsLog
@Component
@Data
public class ValidationToken {
    @Autowired
    private StringRedisTemplate redisAPI;
    //private RedisAPI redisAPI;

//    public RedisAPI getRedisAPI() {
//        return redisAPI;
//    }
//    public void setRedisAPI(RedisAPI redisAPI) {
//        this.redisAPI = redisAPI;
//    }
    public ItripUser getCurrentUser(String tokenString){
        //根据token从redis中获取用户信息
			/*
			 test token:
			 key : token:1qaz2wsx
			 value : {"id":"100078","userCode":"myusercode","userPassword":"78ujsdlkfjoiiewe98r3ejrf","userType":"1","flatID":"10008989"}

			*/
        ItripUser itripUser = null;
        if(null == tokenString || "".equals(tokenString)){
            return null;
        }
        try{
            String userInfoJson = redisAPI.opsForValue().get(tokenString);
            itripUser = JSONObject.parseObject(userInfoJson,ItripUser.class);
        }catch(Exception e){
            itripUser = null;
            log.error("get userinfo from redis but is error : " + e.getMessage());
        }
        return itripUser;
    }

}
