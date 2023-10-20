package jgdabc.yingli.util;

import jakarta.servlet.http.HttpServletResponse;
import jgdabc.yingli.mode.R;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.io.PrintWriter;

public class WirterOutUtil {
    public static final void write(HttpServletResponse response, R result)
    {
        response.setContentType("application/json;character=utf-8");

        response.setCharacterEncoding("UTF-8");
        PrintWriter writer  =  null;
        try {
            writer = response.getWriter();
            String resultJSON = JsonUtils.toJson(result);
            writer.write(resultJSON);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (!ObjectUtils.isEmpty(writer)) {
                writer.close();
            }
        }
    }
}
