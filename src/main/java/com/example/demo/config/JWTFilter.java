package com.example.demo.config;

import com.example.demo.models.User;
import com.example.demo.repo.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Configuration
public class JWTFilter extends OncePerRequestFilter {

    @Autowired
    private UserRepository  userRepository;

    @Autowired
    private JWTUtils jwtUtils;
    List<String> protectedRoutes = List.of(
//            "/user/get-users","/product/create-product",
            "/order/order-product" ,"/consultation/create-consultation",
            "/order/get-user-orders","/consultation/get-user-consultation"
    );


    private boolean startsWith(String url){
        for(int i=0;i<protectedRoutes.size();i++){
            if(url.startsWith(protectedRoutes.get(i))){
                return true;
            }
        }
        return false;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if (request.getMethod().equals("OPTIONS"))
        {
            response.setHeader("Access-Control-Allow-Origin",
                    "*"); // Replace with allowed origins if needed
            response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
            response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");

            response.setHeader("Access-Control-Allow-Max-Age",
                    "3600"); // Pre-flight cache duration
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        String url = req.getRequestURI();
        System.out.println(url);
//        System.out.println(request.getHeader(""));
        System.out.println("Entering in the jwt filter");
        System.out.println(url);
        if(protectedRoutes.contains(url) || startsWith( url ) ){
            System.out.println("This API is protected");
            String header = req.getHeader("Authorization");
            System.out.println(header);
//            System.out.println(request.getAttribute("Authorization"));

                if(header == null || !header.startsWith("Bearer ")){
                    res.sendError(401 ,"Sorry, unauthorised access");
                    System.out.println("You have been blcoked");

                    return;
                }

                String token = header.substring(7);
                req.setAttribute("token" ,token);
                String email = jwtUtils.extractEmail(token);
                Optional<User> Ou = userRepository.findByEmail(email);
                if(Ou.isPresent()){
                    System.out.println("you have the right");
                    filterChain.doFilter(req ,res);
                    return;
                }else{
                    System.out.println("sorry you don't have the right");
                    res.sendError(403 ,"Invalid token");
                    return;
                }


        }
        else{
            System.out.println("This API is not protected");
            filterChain.doFilter(request ,response);
            return;
        }

    }
}