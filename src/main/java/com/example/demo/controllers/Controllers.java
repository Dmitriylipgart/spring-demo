package com.example.demo.controllers;

import com.example.demo.entity.FileRecord;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repositories.FileRecordRepository;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
public class Controllers {

    String path = "E:\\spring-demo\\src\\main\\resources\\static\\files\\";

    @Autowired
    UserRepository users;

    @Autowired
    RoleRepository roles;

    @Autowired
    FileRecordRepository fileRecordRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@RequestParam (name = "username", required = false) String username,
                        @RequestParam (name = "password", required = false) String password)
    {
        if(username.equals("") || password.equals("")){
            return "fail";
        }
        Set<Role> set = new HashSet<>();
        set.add(roles.getRoleById(1L));
        User user = new User(username, bCryptPasswordEncoder.encode(password));
        user.setRoles(set);
        users.saveAndFlush(user);

        return "redirect:/login";
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String handleUpload(
            @RequestParam(value = "file", required = false) MultipartFile multipartFile,
            @RequestParam(value = "description", required = false) String description,
            HttpServletResponse httpServletResponse) {

        String filename = multipartFile.getOriginalFilename();
        String filePath = path + filename;
        File dest = new File(filePath);
        int i = 0;
        while (dest.exists()) {
            if (i == 0) {
                filename = filename.substring(0, filename.lastIndexOf(".")) + "(" + ++i + ")" + filename.substring(filename.lastIndexOf("."));
            } else {
                filename = filename.substring(0, filename.lastIndexOf("(") + 1) + ++i + ")" + filename.substring(filename.lastIndexOf("."));
            }
            dest = new File(path + filename);
        }
        fileRecordRepository.saveAndFlush(new FileRecord(filename, description, filePath));
        try {
            multipartFile.transferTo(dest);
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return "File uploaded failed:" + filename;
        } catch (IOException e) {
            e.printStackTrace();
            return "File uploaded failed:" + filename;
        }
        return "redirect:/loadAll";
    }

    @RequestMapping(value = "/loadAll", method = RequestMethod.POST)
    public String loadAll(Model model){
        List<FileRecord> records = fileRecordRepository.findAll();
        model.addAttribute("size", records.size());
        model.addAttribute("records", records);
        return "main";
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(@RequestParam(value = "file_id", required = false) String id,
                         @RequestParam(value = "filename", required = false) String filename){
        fileRecordRepository.deleteById(Long.valueOf(id));
        new File(path + filename).delete();
        return "redirect:/loadAll";
    }
}
