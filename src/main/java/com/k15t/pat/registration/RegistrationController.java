package com.k15t.pat.registration;

import com.k15t.pat.model.User;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.StringWriter;


@RestController
public class RegistrationController {

    @Autowired
    private VelocityEngine velocityEngine;


    @RequestMapping("/registration.html")
    public String registration(HttpSession session,
                               @RequestParam(value = "register", required = false) String register) {

        Template template = velocityEngine.getTemplate("templates/registration.vm");
        VelocityContext context = new VelocityContext();
        StringWriter writer = new StringWriter();

        if (session.getAttribute("user") == null) {
            context.put("user", new User());
        } else {
            context.put("user", session.getAttribute("user"));
            if (register != null)
                context.put("register", true);
            else
                context.put("binding", session.getAttribute("register"));
        }

        BindingResult bindingResult = (BindingResult) session.getAttribute("userBindingResult");

        if (bindingResult != null) {
            context.put("errors", bindingResult.getFieldErrors());

        }

        template.merge(context, writer);
        return writer.toString();
    }
}
