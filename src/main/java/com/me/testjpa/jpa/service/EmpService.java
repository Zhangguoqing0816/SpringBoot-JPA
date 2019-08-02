package com.me.testjpa.jpa.service;

import com.me.testjpa.jpa.entity.Employee;
import com.me.testjpa.jpa.model.RedisModel;
import com.me.testjpa.jpa.repository.EmpRepository;
import jdk.nashorn.internal.runtime.options.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class EmpService {
    private static final Logger logger = LoggerFactory.getLogger(EmpService.class);

    @Autowired
    private EmpRepository empRepository;

    @Autowired
    private RedisTemplate<String, RedisModel> redisTemplate = new RedisTemplate<String, RedisModel>();

    public List<Employee> selectAll(){
        List<Employee> empList = empRepository.findAll();
        return empList;
    };

    public Employee selectById(String id){
        String redisKey = "redis:" + id;
        if(redisTemplate.hasKey(redisKey)){
            RedisModel rm = redisTemplate.opsForValue().get(redisKey);
            Employee emp = rm.getEmp();
            return emp;
        }

        System.out.println("查数据库了，redis里面没有");

        Employee emp = empRepository.findById(id).orElseThrow(()->new RuntimeException("没有此人"));
        RedisModel rm = new RedisModel();
        String redisModelId = UUID.randomUUID().toString();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String redisModelName = sf.format(new Date()) + emp.getEmpName();
        String redisContent = sf.format(new Date()) + emp.getEmpName() + "-" + emp.getEmpSex();
        rm.setId(redisModelId);
        rm.setName(redisModelName);
        rm.setContent(redisContent);
        rm.setEmp(emp);
        redisTemplate.opsForValue().set(redisKey, rm, 600, TimeUnit.SECONDS);
        //redisTemplate.delete(redisKey);
        //redisTemplate.opsForValue().getAndSet(redisKey, newObj);
        return emp;
    };

    /**
     * ADD
     * @param emp
     */
    public Employee save(Employee emp){
        return empRepository.save(emp);
    }

    /**
     *  UPDATE
     * @param emp
     */
    //@CachePut(key="'userCache'")
    public void update(Employee emp){
        //redisTemplate.opsForValue().getAndSet(redisKey, newObj);
        empRepository.save(emp);
    }

    /**
     * DELETE
     * @param id
     */
    //@CacheEvict(key="'emp'")
    public void delete(String id){
        //redisTemplate.delete(redisKey);
        empRepository.deleteById(id);
    }

    @Cacheable(value = "selectById2", key = "'emp'")
    public Employee selectById2(String id){
        Employee emp = empRepository.findById(id).orElseThrow(()->new RuntimeException("没有此人"));
        System.out.println("selectById2查数据库了，redis里面没有");
        return emp;
    };

}
