package com.me.testjpa.jpa.repository;

import com.me.testjpa.jpa.entity.Employee;
import com.me.testjpa.jpa.model.RequestModel;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmpRepository extends JpaRepository<Employee, String> {

    @Query(value = "select new com.me.testjpa.jpa.model.RequestModel(e.id, e.empNo, e.empName, e.makeTime) from Employee e")
    public List<RequestModel> queryDIY();

    //@Query(value = "select new com.me.testjpa.jpa.model.RequestModel(e.id, e.empNo, e.empName, e.makeTime) from Employee e where e.id =?1 and e.empName=?2")
    @Query(value = "select new com.me.testjpa.jpa.model.RequestModel(e.id, e.empNo, e.empName, e.makeTime) from Employee e where e.id =:rId and e.empName=:rName")
    public List<RequestModel> queryDIY2(@Param("rId") String rId, @Param("rName")String rName);

    @Query(value = "select new com.me.testjpa.jpa.model.RequestModel(e.id, e.empNo, e.empName, e.makeTime) from Employee e where e.id =:id and e.empName=:empName")
    public List<RequestModel> queryDIY3(@Param("rId") String id, @Param("rName")String empName);
}
