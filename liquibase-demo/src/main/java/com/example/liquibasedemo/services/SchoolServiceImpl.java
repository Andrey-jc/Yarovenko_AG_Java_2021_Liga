package com.example.liquibasedemo.services;

import com.example.liquibasedemo.dto.SchoolDTO;
import com.example.liquibasedemo.dto.SchoolFullInfoDTO;
import com.example.liquibasedemo.entity.School;
import com.example.liquibasedemo.entity.User;
import com.example.liquibasedemo.repository.SchoolRepository;
import com.example.liquibasedemo.services.interfaces.SchoolService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@Service
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository schoolRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public SchoolServiceImpl(SchoolRepository schoolRepository, ModelMapper modelMapper) {
        this.schoolRepository = schoolRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<SchoolDTO> getAllSchool() {
        List<School> allSchool = schoolRepository.findAll();
        List<SchoolDTO> schoolDTO = new ArrayList<>();
        for (School school :
                allSchool) {
            SchoolDTO mapSchool = modelMapper.map(school, SchoolDTO.class);
            schoolDTO.add(mapSchool);
        }
        return schoolDTO;
    }

    @Transactional
    @Override
    public void saveSchool(SchoolFullInfoDTO schoolFullInfoDTO) {
        School school = modelMapper.map(schoolFullInfoDTO, School.class);
        schoolRepository.save(school);
    }

    @Override
    public SchoolDTO getSchoolDTO(int id) {
        SchoolDTO schoolDTO = null;
        Optional<School> optional = schoolRepository.findById(id);
        if (optional.isPresent()) {
            schoolDTO = modelMapper.map(optional.get(), SchoolDTO.class);
        }
        return schoolDTO;
    }

    @Override
    public School getSchool(int id) {
        return schoolRepository.getById(id);
    }

    @Transactional
    @Override
    public void deleteSchool(int id) {
        School byId = schoolRepository.getById(id);
        List<User> userList = byId.getUserList();
        for (User user :
                userList) {
            user.setSchool(null);
        }
        schoolRepository.deleteById(id);
    }
}
