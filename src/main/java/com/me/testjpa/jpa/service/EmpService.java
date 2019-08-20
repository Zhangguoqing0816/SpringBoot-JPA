package com.me.testjpa.jpa.service;

import com.me.testjpa.jpa.entity.Employee;
import com.me.testjpa.jpa.model.RedisModel;
import com.me.testjpa.jpa.model.RequestModel;
import com.me.testjpa.jpa.repository.EmpRepository;
import com.me.testjpa.jpa.util.CSVUtils;
import jdk.nashorn.internal.runtime.options.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class EmpService {
    private static final Logger logger = LoggerFactory.getLogger(EmpService.class);

    @Autowired
    private EmpRepository empRepository;

    @Autowired
    private RedisTemplate<String, RedisModel> redisTemplate = new RedisTemplate<String, RedisModel>();

    //@Value("${file.filepath}")
    //private String filePath;

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

    public List<RequestModel> selectAllQ(){
        List<RequestModel> list = empRepository.queryDIY();
        return list;
    };

    public List<RequestModel> selectAllQ2(String rId,String rName){
        List<RequestModel> list = empRepository.queryDIY2(rId,rName);
        return list;
    };

    public List<RequestModel> selectAllQ3(String rId,String rName){
        List<RequestModel> list = empRepository.queryDIY2(rId,rName);
        //按时间排序  降序
//        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
//        Iterable<TaskInfo> all = taskInfoRepository.findAll(builder, sort);
        return list;
    };

    /**
     * 导出数据
     * @return
     */
    public File exportEmp(RequestModel requestModel){
        File empFile = null;

        //表头
        LinkedHashMap<String, Object> headMap = new LinkedHashMap<String, Object>();
        headMap.put("id", "主键");
        headMap.put("empNo", "员工编号");
        headMap.put("empName", "姓名");
        headMap.put("empSex", "性别");
        headMap.put("sal", "工资");
        headMap.put("makeTime", "操作时间");

        //导出的数据
        List<Map<String, String>> data = new ArrayList<>();
        List<Employee> all = empRepository.findAll(new Sort(Sort.Direction.DESC, "makeTime"));
        all.stream().forEach(employee -> {
            //一行数据
            LinkedHashMap<String, String> rowMap = new LinkedHashMap<String, String>();
            rowMap.put("id", employee.getId());
            rowMap.put("empNo", employee.getEmpNo());
            rowMap.put("empName", employee.getEmpName());
            rowMap.put("empSex", employee.getEmpSex());
            rowMap.put("sal", employee.getSal());
            rowMap.put("makeTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(employee.getMakeTime()));
            data.add(rowMap);
        });
        String filePath = System.getProperty("java.io.tmpdir") + File.separator;
        empFile = CSVUtils.createCSVFile(data, headMap, filePath, requestModel.getFileName() !=null ? requestModel.getFileName() : "Download");

        return empFile;
    }

}
