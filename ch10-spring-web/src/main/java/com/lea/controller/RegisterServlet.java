package com.lea.controller;

import com.lea.domain.Student;
import com.lea.service.StudentService;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import java.io.IOException;

/**
 * @author lzc
 * @create 2020-10-10 16:11
 */
public class RegisterServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String age = request.getParameter("age");
//        String config = "applicationContext.xml";
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(config);
        WebApplicationContext context = null;
        // 2.从servlet容器中拿到创建好的ApplicationContext对象
//        ServletContext servletContext = request.getServletContext();
//        Object attribute = servletContext.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
//        if (null != attribute) {
//            context = (WebApplicationContext) attribute;
//        }
        // 3. 从框架工具类中拿到ServletContext,再获取容器对象
        ServletContext servletContext = getServletContext();
        context = WebApplicationContextUtils.getWebApplicationContext(servletContext);

        StudentService studentService = context.getBean("studentService", StudentService.class);
        System.out.println(" 创建的对象" + studentService.getClass().getName());
        Student student = new Student(Integer.valueOf(id), name, email, Integer.valueOf(age));
        studentService.addStudent(student);

        request.getRequestDispatcher("/result.jsp").forward(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request, response);
    }
}
