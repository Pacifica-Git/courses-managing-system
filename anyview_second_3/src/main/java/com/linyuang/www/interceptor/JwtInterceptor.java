package com.linyuang.www.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.linyuang.www.po.ResultDto;
import com.linyuang.www.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从请求头获取token
        String token = request.getHeader("token");
        ResultDto resultDto = new ResultDto();
        try {
            JwtUtil.verify(token);
            return true;
        }catch (SignatureVerificationException e){
            log.error("token所使用的签名无效，[{}]",e.getMessage());
            resultDto.put("msg","token所使用的签名无效");
        }catch (TokenExpiredException e){
            log.error("token已过期，[{}]",e.getMessage());
            resultDto.put("msg","token已过期");
        }catch (AlgorithmMismatchException e){
            log.error("token所使用的算法不一致,[{}]",e.getMessage());
            resultDto.put("msg","token所使用的算法不一致");
        }catch (Exception e){
            log.error("无效token！[{}]",e.getMessage());
            resultDto.put("msg","token无效");
        }
        response.getWriter().println(new ObjectMapper().writeValueAsString(resultDto));
        return false;
    }
}
