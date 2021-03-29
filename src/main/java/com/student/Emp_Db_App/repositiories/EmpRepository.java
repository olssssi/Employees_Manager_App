package com.student.Emp_Db_App.repositiories;

import com.student.Emp_Db_App.models.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmpRepository extends PagingAndSortingRepository<Employee, Long>{

}