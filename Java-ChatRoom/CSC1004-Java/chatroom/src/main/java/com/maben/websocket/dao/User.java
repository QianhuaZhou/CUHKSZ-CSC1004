package com.maben.websocket.dao;


import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements Serializable {
    Integer id;
    String name;
    String pwd;
    String phone;
    String email;
}
