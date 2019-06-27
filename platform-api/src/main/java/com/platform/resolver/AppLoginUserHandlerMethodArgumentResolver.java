package com.platform.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.platform.annotation.APPLoginUser;
import com.platform.entity.MlsUserEntity2;
import com.platform.interceptor.AuthorizationInterceptor;

/**
 * 有@LoginUser注解的方法参数，注入当前登录用户
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-03-23 22:02
 */
public class AppLoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(MlsUserEntity2.class) && parameter.hasParameterAnnotation(APPLoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container,
                                  NativeWebRequest request, WebDataBinderFactory factory) throws Exception {
        //获取用户ID
    	MlsUserEntity2 user =(MlsUserEntity2) request.getAttribute(AuthorizationInterceptor.LOGIN_USER_KEY, RequestAttributes.SCOPE_REQUEST);
        if (user == null) {
            return null;
        }
        //获取用户信息
//        UserVo user = userService.queryObject((Long) object);
        return user;
    }
}
