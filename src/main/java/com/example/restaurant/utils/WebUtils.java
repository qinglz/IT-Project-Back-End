package com.example.restaurant.utils;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

public class WebUtils {
    public WebUtils() {
    }

    public static String renderString(HttpServletResponse response, String string) {
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        } catch (IOException var3) {
            var3.printStackTrace();
        }

        return null;
    }
}
