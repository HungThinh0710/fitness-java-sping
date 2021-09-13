package com.minhchieu.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minhchieu.model.Account;
import com.minhchieu.orm.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class CustomerInterceptor implements HandlerInterceptor {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("[Interceptor]: Checking permission for access customer pattern..");
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        if(antPathMatcher.match("/customer/**",request.getRequestURI())){
            Account account = accountRepository.findByEmail(request.getUserPrincipal().getName());
            if(account.getCustomer() != null)
                return true;
            else{
                ObjectMapper mapper = new ObjectMapper();
                response.setContentType("application/json");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write(mapper.writeValueAsString(Map.of("message","Sorry! You are not a customer!")));
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        System.out.println("Post Handle method is Calling");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        System.out.println("Request and Response is completed");
    }
}
