package com.maben.websocket.mapper;


import com.maben.websocket.dao.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select(value = "SELECT * FROM USER")
    List<User> list();

    @Select("SELECT * FROM USER limit ${start},${end}")
    List<User> page(@Param(value = "start") Integer start,@Param(value = "end") Integer end);

    @Select("SELECT * FROM USER where name = '${name}' and pwd = '${pwd}'")
    List<User> login(@Param(value = "name") String name,@Param(value = "pwd") String pwd);

    @Insert(value = "INSERT INTO USER (name,pwd,phone,email) VALUES ('${user.name}','${user.pwd}','${user.phone}','${user.email}')")
    Integer add(@Param(value = "user") User user);

    @Update(value = "UPDATE USER SET name='${user.name}' , pwd='${user.pwd}' , phone='${user.phone}' , email='${user.email}' WHERE ID=${user.id}")
    Integer update(@Param(value = "user") User user);

    @Delete(value = "DELETE FROM USER WHERE ID = #{id}")
    Integer delete(@Param(value = "id")Integer id);

}
