package com.maben.websocket.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Message implements Serializable {
    Integer id;
    String msg;
    String send;
    String to;
    Integer type;
    Timestamp time;
}
