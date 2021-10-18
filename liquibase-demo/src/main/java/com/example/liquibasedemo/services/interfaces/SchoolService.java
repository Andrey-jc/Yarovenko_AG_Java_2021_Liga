package com.example.liquibasedemo.services.interfaces;

import com.example.liquibasedemo.dto.SchoolDTO;
import com.example.liquibasedemo.dto.SchoolFullInfoDTO;
import com.example.liquibasedemo.entity.School;

import java.util.List;

public interface SchoolService {

    List<SchoolDTO> getAllSchool( );

    void saveSchool(SchoolFullInfoDTO school);

    SchoolDTO getSchoolDTO(int id);

    School getSchool(int id);

    void deleteSchool(int id);
}
