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

import static com.linyuang.www.config.Constant.LOGIN;
import static com.linyuang.www.config.Constant.QUESTION_MARK;

@Slf4j
public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从请求头获取token
        String token = request.getHeader("token");
        if(token == null){
            //获取请求路径
            String uri = request.getRequestURI();
            //请求路径的后缀(最后一个 / 后面的部分)为请求的方法的名字
            String methodName;
            if(uri.contains(QUESTION_MARK)){
                methodName = uri.substring(uri.lastIndexOf('/') + 1,uri.indexOf('?'));
            }else{
                methodName = uri.substring(uri.lastIndexOf('/') + 1);
            }
            //如果token为空，是/login请求，直接放行
            if(LOGIN.equals(methodName)){
                return true;
            }
        }
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
