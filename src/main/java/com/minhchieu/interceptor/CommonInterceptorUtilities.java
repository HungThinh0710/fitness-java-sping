package com.minhchieu.interceptor;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@Component
public class CommonInterceptorUtilities {
    public String bodyParamString(HttpServletRequest request) throws IOException {

        InputStream payload = request.getInputStream();
        return new BufferedReader(new InputStreamReader(payload)).lines().collect(Collectors.joining("\n"));
    }

}
