package com.k15t.pat.registration;

import com.k15t.pat.model.User;
import com.k15t.pat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class RegistrationResource {

    @Autowired
    UserService userService;


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            session.setAttribute("userBindingResult", bindingResult);
            session.setAttribute("user", user);
            return "redirect:/registration.html";
        }
        if (userService.checkIfUserExists(user.getEmail())) {
            bindingResult.rejectValue("email", "error.user", "An account already exists for this email!");
            session.setAttribute("userBindingResult", bindingResult);
            session.setAttribute("user", user);
            return "redirect:/registration.html";
        }
        userService.createUserForRegistration(user);

        session.setAttribute("user", user);
        return "redirect:/registration.html?register=true";
    }

}