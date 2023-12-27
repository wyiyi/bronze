package com.amber.common.model;

import com.amber.common.annotation.FieldDesensitize;
import com.amber.common.annotation.FieldEncrypt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class User2Dto {

    private String username;
    @FieldEncrypt
    private String password;

    @FieldDesensitize
    private String phone;

    private Integer sex;

    private List<User2Dto> user2DtoList;

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getPhone() {
        return this.phone;
    }

    public Integer getSex() {
        return this.sex;
    }

    public List<User2Dto> getUser2DtoList() {
        return this.user2DtoList;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public void setUser2DtoList(List<User2Dto> user2DtoList) {
        this.user2DtoList = user2DtoList;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof User2Dto)) {
            return false;
        } else {
            User2Dto other = (User2Dto)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label71: {
                    Object this$username = this.getUsername();
                    Object other$username = other.getUsername();
                    if (this$username == null) {
                        if (other$username == null) {
                            break label71;
                        }
                    } else if (this$username.equals(other$username)) {
                        break label71;
                    }

                    return false;
                }

                Object this$password = this.getPassword();
                Object other$password = other.getPassword();
                if (this$password == null) {
                    if (other$password != null) {
                        return false;
                    }
                } else if (!this$password.equals(other$password)) {
                    return false;
                }

                label57: {
                    Object this$phone = this.getPhone();
                    Object other$phone = other.getPhone();
                    if (this$phone == null) {
                        if (other$phone == null) {
                            break label57;
                        }
                    } else if (this$phone.equals(other$phone)) {
                        break label57;
                    }

                    return false;
                }

                Object this$sex = this.getSex();
                Object other$sex = other.getSex();
                if (this$sex == null) {
                    if (other$sex != null) {
                        return false;
                    }
                } else if (!this$sex.equals(other$sex)) {
                    return false;
                }

                Object this$user2DtoList = this.getUser2DtoList();
                Object other$user2DtoList = other.getUser2DtoList();
                if (this$user2DtoList == null) {
                    if (other$user2DtoList == null) {
                        return true;
                    }
                } else if (this$user2DtoList.equals(other$user2DtoList)) {
                    return true;
                }

                return false;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof User2Dto;
    }

//    public int hashCode() {
//        int PRIME = true;
//        int result = 1;
//        Object $username = this.getUsername();
//        result = result * 59 + ($username == null ? 43 : $username.hashCode());
//        Object $password = this.getPassword();
//        result = result * 59 + ($password == null ? 43 : $password.hashCode());
//        Object $phone = this.getPhone();
//        result = result * 59 + ($phone == null ? 43 : $phone.hashCode());
//        Object $sex = this.getSex();
//        result = result * 59 + ($sex == null ? 43 : $sex.hashCode());
//        Object $user2DtoList = this.getUser2DtoList();
//        result = result * 59 + ($user2DtoList == null ? 43 : $user2DtoList.hashCode());
//        return result;
//    }

    public String toString() {
        return "User2Dto(username=" + this.getUsername() + ", password=" + this.getPassword() + ", phone=" + this.getPhone() + ", sex=" + this.getSex() + ", user2DtoList=" + this.getUser2DtoList() + ")";
    }

    public User2Dto(String username, String password, String phone, Integer sex, List<User2Dto> user2DtoList) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.sex = sex;
        this.user2DtoList = user2DtoList;
    }

    public User2Dto() {
    }

}
