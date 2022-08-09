package com.java.redisDemo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class FriendaDao {
    private static final String key="Friend";
    @Autowired
    private RedisTemplate<String,Person>redisTemplate;
    public void addFriend(Person person){
        redisTemplate.opsForList().leftPush(key, person);
    }
    public long getNoOfFriends(){
       return  redisTemplate.opsForList().size(key);
    }
    public Person getFriendAtIndex(Integer index){
        return redisTemplate.opsForList().index(key,index);
    }
    public Long removeFriend(Person p){
        return redisTemplate.opsForList().remove(key, 1, p);
    }
}
