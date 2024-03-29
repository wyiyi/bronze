package com.amber.common.sensitive.mock.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@Data
@TableName("USERINFO")
public class UserDO {

    @TableId(type = IdType.UUID, value = "rid")
    String id;

    @TableField("user_name")
    String name;

    String phone;

    String idCard;

    String password;

    @TableField(exist = false)
    List<UserHistoryDO> histories;

    @TableField(exist = false)
    List<RoleDO> roles;


    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getIdCard() {
        return this.idCard;
    }

    public String getPassword() {
        return this.password;
    }

    public List<UserHistoryDO> getHistories() {
        return this.histories;
    }

    public List<RoleDO> getRoles() {
        return this.roles;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHistories(List<UserHistoryDO> histories) {
        this.histories = histories;
    }

    public void setRoles(List<RoleDO> roles) {
        this.roles = roles;
    }


}
