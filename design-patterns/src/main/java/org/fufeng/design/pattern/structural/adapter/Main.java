package org.fufeng.design.pattern.structural.adapter;

/**
 * 对象适配器
 * {@link org.springframework.web.servlet.DispatcherServlet#doDispatch(jakarta.servlet.http.HttpServletRequest, jakarta.servlet.http.HttpServletResponse)}
 * {@link org.springframework.web.servlet.HandlerAdapter}
 *
 * @author fufeng
 * {@code @Date} 2024-04-05 19:54
 */
public class Main {

    public static void main(String[] args) {
        DC5 adapter = new PowerAdapter();
        adapter.outputDC5V();
    }

}
