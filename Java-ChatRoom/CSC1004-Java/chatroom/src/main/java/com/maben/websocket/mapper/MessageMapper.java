package com.maben.websocket.mapper;

import com.maben.websocket.dao.Message;
import com.maben.websocket.dao.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MessageMapper {
    @Select(value = "SELECT * FROM message order by time asc")
    List<Message> list();

    @Select("SELECT * FROM message order by time asc limit ${start},${end}")
    List<Message> page(@Param(value = "start") Integer start, @Param(value = "end") Integer end);

    @Insert(value = "INSERT INTO message (msg,send,`to`,type,time) VALUES ('${message.msg}','${message.send}','${message.to}','${message.type}','${message.time}')")
    Integer add(@Param(value = "message") Message message);

    @Update(value = "UPDATE message SET msg='${message.msg}' , send='${message.send}' , `to`='${message.to}' , type='${message.type}' , time='${message.time}' WHERE ID=${message.id}")
    Integer update(@Param(value = "message") Message message);

    @Delete(value = "DELETE FROM message WHERE ID = #{id}")
    Integer delete(@Param(value = "id")Integer id);

}
