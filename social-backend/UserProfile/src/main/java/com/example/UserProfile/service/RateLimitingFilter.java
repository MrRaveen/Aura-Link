package com.example.UserProfile.service;

import io.github.bucket4j.Bucket;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RateLimitingFilter implements Filter {

    //initialize the bucket
    private final Bucket bucket;
    @Autowired
    public RateLimitingFilter(Bucket bucket) {
        this.bucket = bucket;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //This method intercepts incoming HTTP requests.
        if (bucket.tryConsume(1)) {
            /*
            * It checks whether a token can be consumed from the rate-limiting bucket.
              If true, it means the request is within the allowed limit, so it proceeds with chain.doFilter(request, response), allowing the request to reach the intended destination.
              If false, it means the rate limit has been exceeded, and the response status is set to 429 (Too Many Requests) to reject additional requests.
            * */
            filterChain.doFilter(servletRequest, servletResponse); // Forward the request if rate limiting is not hit
        } else {
            ((HttpServletResponse) servletResponse).setStatus(429); // Return 429 if rate limit is exceeded
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
