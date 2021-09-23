package com.minhchieu.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minhchieu.model.Account;
import com.minhchieu.model.Course;
import com.minhchieu.orm.AccountRepository;
import com.minhchieu.orm.CourseRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class PostInterceptor implements HandlerInterceptor {
    private static final String CREATE_POST_URI = "/post";
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    TeacherInterceptor teacherInterceptor;
    @Autowired
    CommonInterceptorUtilities commonInterceptorUtilities;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        AntPathMatcher antPathMatcher = new AntPathMatcher();

        // | URI: /course | Method: POST
        if(antPathMatcher.match(CREATE_POST_URI, request.getRequestURI()) && Objects.equals(request.getMethod(), "POST")){
            System.out.println("[Interceptor]: Checking permission for creating a post pattern...");
//            String payload = request.getReader().lines().collect(Collectors.joining(System.lineSeparator())); //Get error with getReader
            String payload = commonInterceptorUtilities.bodyParamString(request);
            try {
                JSONObject jsonObject = new JSONObject(payload);
                int courseId = Integer.parseInt(jsonObject.get("course_id").toString());
                Account account = accountRepository.findByEmail(request.getUserPrincipal().getName());
                Course course = courseRepository.findById((long) courseId).orElseThrow(EntityNotFoundException::new);
                long teacherId = course.getTeacherCourse().getId();
                if(teacherId == account.getTeacher().getId()){
                    return teacherInterceptor.isTeacher(account, response);
                }
                return this.throwNonTeacher(response);

            }
            catch (JSONException ex){
                System.out.println(ex.getMessage());
                return this.throwNotFoundCourseIdInJsonException(response);
            }
            catch (EntityNotFoundException exception){
                return this.throwNotFoundEntityException(response);
            }
            catch (Exception ex){
                System.out.println("ERROR IN HERE");
                System.out.println(ex.getMessage());
            }
        }
        //Not at all
        return true;
    }

    private void throwErrorResponse(HttpServletResponse response, int statusCode, String message) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json");
        response.setStatus(statusCode);
        response.getWriter().write(mapper.writeValueAsString(Map.of("message",message)));
    }

    private boolean throwNotFoundCourseIdInJsonException(HttpServletResponse response) throws Exception {
        throwErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, "Oops! course_id is required!");
        return false;
    }

    private boolean throwNotFoundEntityException(HttpServletResponse response) throws Exception{
        throwErrorResponse(response, HttpServletResponse.SC_NOT_FOUND, "Not found course!");
        return false;
    }

    private boolean throwNonTeacher(HttpServletResponse response) throws Exception{
        throwErrorResponse(response, HttpServletResponse.SC_FORBIDDEN, "You are not a teacher of this course!");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

}
