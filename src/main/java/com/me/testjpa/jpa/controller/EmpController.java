package com.me.testjpa.jpa.controller;

import com.me.testjpa.jpa.entity.Employee;
import com.me.testjpa.jpa.model.RequestModel;
import com.me.testjpa.jpa.service.EmpService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/empJpa")
@Api(value="employee管理", description = "employee管理-desc")
public class EmpController {

    @Autowired
    private EmpService empService;

    @ApiOperation(value = "查询所有的employee",notes = "查询所有的employee")
    @RequestMapping(value = "/selectAll")
    public List<Employee> selectAll(){
        return empService.selectAll();
    }

    @RequestMapping(value = "/add")
    @ApiOperation(value = "增加Emp-value", notes = "增加Emp-notes")
    @ApiImplicitParam(name = "emp", value = "单个Emp", dataType = "Employee")
    public String addUser(Employee emp){//增
        try{
            Employee employee=new Employee();
            employee.setEmpName(emp.getEmpName());
            employee.setEmpSex(emp.getEmpSex());
            employee.setMakeTime(emp.getMakeTime());
            employee.setSal(emp.getSal());
            employee.setEmpNo(emp.getEmpNo());
            empService.save(employee);
            System.out.println(employee.toString());
            return "true";
        }catch(Exception ex){
            ex.printStackTrace();
            return "false";
        }
    }

    @RequestMapping(value = "/delete")
    @ApiOperation(value = "删除", notes = "删除-notes")
    @ApiImplicitParam(name = "id", value = "根据ID删除", dataType = "String")
    public void deleteUser(@RequestParam(value = "id")String id){//删
        empService.delete(id);
    }

    @RequestMapping(value="/queryById/{id}")
    public Employee getAccountById(@PathVariable("id") String id){
        return empService.selectById(id);
    }

    @RequestMapping(value = "/update")
    public Employee updateUser(Employee emp){//修
        System.out.println(emp.toString());
        Employee old = empService.selectById(emp.getId());
        System.out.println(old.toString());
        old.setEmpSex(emp.getEmpSex());
        return empService.save(old);
    }

    @RequestMapping(value="/queryById2/{id}")
    public Employee getAccountById2(@PathVariable("id") String id){
        return empService.selectById2(id);
    }

    @ApiOperation(value = "查询所有的employee--selectAllQ",notes = "查询所有的employee")
    @RequestMapping(value = "/selectAllQ")
    public List<RequestModel> selectAllQ(){
        return empService.selectAllQ();
    }

    @ApiOperation(value = "查询所有的employee--selectAllQ2",notes = "查询所有的employee")
    @RequestMapping(value = "/selectAllQ2/{rId}/{rName}")
    public List<RequestModel> selectAllQ2(@PathVariable("rId") String rId, @PathVariable("rName")String rName){
        return empService.selectAllQ2(rId, rName);
    }

    @ApiOperation(value = "查询所有的employee--selectAllQ3",notes = "查询所有的employee")
    @RequestMapping(value = "/selectAllQ3/{rId}/{rName}")
    public List<RequestModel> selectAllQ3(@PathVariable("rId") String rId, @PathVariable("rName")String rName){
        return empService.selectAllQ3(rId, rName);
    }

}
