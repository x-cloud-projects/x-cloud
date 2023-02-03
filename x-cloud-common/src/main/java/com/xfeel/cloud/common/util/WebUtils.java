package com.xfeel.cloud.common.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WebUtils {

    /**
     * 渲染返回结果
     *
     * @param response
     * @param json
     * @return
     */
    public static String render(HttpServletResponse response, String json) {
        response.setStatus(200);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        try {
            response.getWriter().print(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
