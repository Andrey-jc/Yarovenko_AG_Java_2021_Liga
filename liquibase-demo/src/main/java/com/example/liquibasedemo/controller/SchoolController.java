package com.example.liquibasedemo.controller;

import com.example.liquibasedemo.dto.SchoolDTO;
import com.example.liquibasedemo.dto.SchoolFullInfoDTO;
import com.example.liquibasedemo.entity.School;
import com.example.liquibasedemo.exceptions_handling.NoSuchExceptionSocialNetwork;
import com.example.liquibasedemo.services.interfaces.SchoolService;
import com.example.liquibasedemo.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SchoolController {

    private UserService userService;

    private SchoolService schoolService;

    @Autowired
    public SchoolController(UserService userService, SchoolService schoolService) {
        this.userService = userService;
        this.schoolService = schoolService;
    }

    @GetMapping("/schools/{id}")
    public SchoolDTO getSchool(@PathVariable("id") int id) {
        SchoolDTO schoolDTO = schoolService.getSchoolDTO(id);
        if (schoolDTO == null) {
            throw new NoSuchExceptionSocialNetwork("There is no school with ID = " +
                    id + " in Database");
        }
        return schoolDTO;
    }

    @GetMapping("/schools")
    public List<SchoolDTO> showAllSchool() {
        return schoolService.getAllSchool();
    }

    @PostMapping("/schools")
    public SchoolDTO addNewSchool(@RequestBody SchoolFullInfoDTO school) {
        schoolService.saveSchool(school);
        return schoolService.getSchoolDTO(school.getId());
    }

    @PutMapping("/schools")
    public SchoolDTO updateSchool(@RequestBody SchoolFullInfoDTO school) {
        schoolService.saveSchool(school);
        return schoolService.getSchoolDTO(school.getId());
    }

    @PutMapping("/schools/adduser")
    public SchoolDTO addUserToSchool(@RequestBody SchoolFullInfoDTO schoolFullInfoDTO, @RequestParam("idUser") int idUser) {
        School school = schoolService.getSchool(schoolFullInfoDTO.getId());
        school.setUserList(userService.getUser(idUser));
        schoolService.saveSchool(schoolFullInfoDTO);
        return schoolService.getSchoolDTO(schoolFullInfoDTO.getId());
    }

    @DeleteMapping("/schools/{id}")
    public String deleteUser(@PathVariable int id) {
        SchoolDTO school = schoolService.getSchoolDTO(id);
        if (school == null) {
            throw new NoSuchExceptionSocialNetwork("There is no school with ID = " +
                    id + " in Database");
        }
        schoolService.deleteSchool(id);
        return "School with ID = " + id + " was deleted";
    }
}
