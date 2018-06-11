package com.example.SpringPSS.controllers;

import com.example.SpringPSS.components.EmailSender;
import com.example.SpringPSS.dtos.UserDto;
import com.example.SpringPSS.dtos.UsersWrapper;
import com.example.SpringPSS.entities.User;
import com.example.SpringPSS.repositories.RoleRepository;
import com.example.SpringPSS.repositories.UserRepository;
import com.example.SpringPSS.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private EmailSender emailSender;

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/user/index", method = RequestMethod.GET)
    public String userIndex() {
        return "/user/index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(WebRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid UserDto userDto,
             BindingResult result, WebRequest request, Errors errors) {
        User registered = new User();
        if (!result.hasErrors()) {
            userDto.setRoles(Collections.singletonList(roleRepository.findByName("ROLE_USER")));
            userDto.setEnabled(true);
            registered = createUserAccount(userDto);
        }
        if (registered == null) {
            result.rejectValue("username", "message.regError");
        }
        if (result.hasErrors()) {
            return new ModelAndView("register", "user", userDto);
        }
        else {
            emailSender.sendEmail(userDto.getEmail(), "Stworzyłeś nowe konto!", "Pomyślnie stworzono nowe konto");
            return new ModelAndView("index", "user", userDto);
        }
    }

    private User createUserAccount(UserDto userDto) {
        User registered;
        try {
            registered = userService.registerNewUserAccount(userDto);
        } catch (Exception e) {
            return null;
        }
        return registered;
    }

    @RequestMapping(value = "/admin/index", method = RequestMethod.GET)
    public String adminIndex() {
        return "/admin/index";
    }

    @RequestMapping(value = "/admin/register", method = RequestMethod.GET)
    public String adminPanelRegister(WebRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "/admin/register";
    }

    @RequestMapping(value = "/admin/register", method = RequestMethod.POST)
    public ModelAndView adminPanelRegisterUserAccount(@ModelAttribute("user") @Valid UserDto userDto,
                                            BindingResult result, WebRequest request, Errors errors) {
        String roleRadio = request.getParameter("roleRadio");
        if (roleRadio == null) {
            return new ModelAndView("admin/register", "user", userDto);
        }
        if (roleRadio.equals("user")) {
            userDto.setRoles(Collections.singletonList(roleRepository.findByName("ROLE_USER")));
        } else if (roleRadio.equals("admin")) {
            userDto.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER"),
                    roleRepository.findByName("ROLE_ADMIN")));
        }

        String enableRadio = request.getParameter("enableRadio");
        if (enableRadio == null) {
            return new ModelAndView("admin/register", "user", userDto);
        }
        if (enableRadio.equals("true")) {
            userDto.setEnabled(true);
        } else if (enableRadio.equals("false")) {
            userDto.setEnabled(false);
        }

        User registered = new User();
        if (!result.hasErrors()) {
            registered = createUserAccount(userDto);
        }
        if (registered == null) {
            result.rejectValue("username", "message.regError");
        }
        if (result.hasErrors()) {
            return new ModelAndView("admin/register", "user", userDto);
        }
        else {
            return new ModelAndView("admin/index", "user", userDto);
        }
    }

    @RequestMapping(value = "/admin/enable", method = RequestMethod.GET)
    public String adminEnable(Model model) {
        UsersWrapper usersWrapper = new UsersWrapper();
        usersWrapper.setUsers((ArrayList<User>)userRepository.findAll());
        model.addAttribute("usersWrapper", usersWrapper);
        return "/admin/enable";
    }

    @RequestMapping(value = "/admin/enable/activate{userId}", method = RequestMethod.GET)
    public RedirectView adminEnablePost(@RequestParam(value = "userId", required = false) int userId, Model model) {
        User user = userRepository.findUserById(userId);
        user.setEnabled(true);
        userRepository.save(user);

        return new RedirectView("/admin/enable");
    }

    @RequestMapping(value = "/admin/enable/deactivate{userId}", method = RequestMethod.GET)
    public RedirectView adminDisablePost(@RequestParam(value = "userId", required = false) int userId, Model model){
        User user = userRepository.findUserById(userId);
        user.setEnabled(false);
        userRepository.save(user);
        return new RedirectView("/admin/enable");
    }
}