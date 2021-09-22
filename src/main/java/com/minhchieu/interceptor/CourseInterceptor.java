package com.minhchieu.interceptor;

import com.minhchieu.model.Account;
import com.minhchieu.orm.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Component
public class CourseInterceptor implements HandlerInterceptor {

    private static final String courseURI = "/course";

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    TeacherInterceptor teacherInterceptor;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        AntPathMatcher antPathMatcher = new AntPathMatcher();

        if(antPathMatcher.match(courseURI, request.getRequestURI()) && Objects.equals(request.getMethod(), "POST")
        ){
            Account account = accountRepository.findByEmail(request.getUserPrincipal().getName());
            System.out.println("[Interceptor]: Checking permission for creating a course pattern...");
            return teacherInterceptor.isTeacher(account, response);
        }

        return true;
    }
}
