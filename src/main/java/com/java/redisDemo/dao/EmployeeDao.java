package com.java.redisDemo.dao;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.Map;

@Repository
@Transactional
public class EmployeeDao {
    private static final String KEY = "employees";

    @Resource(name="redisTemplate")
    private HashOperations<String, Integer, Person> hashOps;

    public void addEmployee(Person person) {
        hashOps.putIfAbsent(KEY, person.getId(), person);
    }
    public void updateEmployee(Person person) {
        hashOps.put(KEY, person.getId(), person);
    }
    public Person getEmployee(Integer id) {
        return hashOps.get(KEY, id);
    }
    public long getNumberOfEmployees() {
        return hashOps.size(KEY);
    }
    public Map<Integer, Person> getAllEmployees() {
        return hashOps.entries(KEY);
    }
    public long deleteEmployees(Integer... ids) {
        return hashOps.delete(KEY, (Object)ids);
    }
}
