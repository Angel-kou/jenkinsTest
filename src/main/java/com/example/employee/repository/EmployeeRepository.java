package com.example.employee.repository;

import com.example.employee.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    //以下所有的*都代表变量



    //1.查询名字是*的第一个employee
    @Query("select e from Employee e where e.name = :name order by e.id asc nulls first ")
    Employee findFirstEmployeeByName(@Param("name") String name);


    //2.找出Employee表中第一个姓名包含`*`字符并且薪资大于*的雇员个人信息
    @Query(value = "select * from Employee e where e.salary > ?2 and e.name like concat('%',?1,'%') order by e.id asc limit 1", nativeQuery = true)
    Employee findFirstMeetTwoConditionEmployee(@Param("c") char c,@Param("salary") int salary);

    //3.找出一个薪资最高且公司ID是*的雇员以及该雇员的姓名
    @Query("select e from Employee e where e.id = :id order by e.companyId desc nulls first")
    Employee findSalaryHighestAndIdIsSatisfied(@Param("id") int id);

    //4.实现对Employee的分页查询，每页两个数据
    Page<Employee> getEmployeePage(@Param("page") int page,@Param("size") int size);

    //5.查找**的所在的公司的公司名称
    @Query("select c.companyName from Employee e , Company c where e.companyId = c.id and e.name = :name")
    String findCompanyNameOfEmployee(@Param("name") String name);

    //6.将*的名字改成*,输出这次修改影响的行数
    @Modifying
    @Query("update Employee e set e.name = :newName where e.name = :originalName")
    Integer getUpdateEffectedRows(@Param("originalName") String originalName , @Param("newName") String newName);

    //7.删除姓名是*的employee
    @Modifying
    @Query("delete from Employee e where e.name = :name")
    int deleteByName(@Param("name") String name);
}
