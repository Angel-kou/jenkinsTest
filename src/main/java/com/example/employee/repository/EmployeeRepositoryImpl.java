package com.example.employee.repository;


import com.example.employee.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class EmployeeRepositoryImpl {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Page<Employee> getEmployeePage(int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "id");
        Page<Employee> employeeList = employeeRepository.findAll(pageable);
        return employeeList;
    }

}
