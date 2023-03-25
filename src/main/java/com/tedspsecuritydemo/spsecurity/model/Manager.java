package com.tedspsecuritydemo.spsecurity.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;

@Data
@SuperBuilder(toBuilder = true)
@Entity
@DiscriminatorColumn(name = "manager")
public class Manager extends Users{

    Manager(){}

    private int approveLimit;

}

/*
mysql> desc user;
+---------------+--------------+------+-----+---------+----------------+
| Field         | Type         | Null | Key | Default | Extra          |
+---------------+--------------+------+-----+---------+----------------+
| user_id       | int          | NO   | PRI | NULL    | auto_increment |
| active        | int          | YES  |     | NULL    |                |
| email         | varchar(255) | YES  |     | NULL    |                |
| last_name     | varchar(255) | YES  |     | NULL    |                |
| name          | varchar(255) | YES  |     | NULL    |                |
| password      | varchar(255) | YES  |     | NULL    |                |
| dtype         | varchar(31)  | NO   |     | NULL    |                |
| approve_limit | int          | YES  |     | NULL    |                |
+---------------+--------------+------+-----+---------+----------------+
8 rows in set (0.00 sec)
 */