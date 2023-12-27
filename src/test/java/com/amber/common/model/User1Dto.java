package com.amber.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.amber.common.annotation.FieldDesensitize;
import com.amber.common.annotation.FieldEncrypt;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class User1Dto {

    private String username;
    @FieldEncrypt
    private String password;

    @FieldDesensitize
    private String phone;

    private Integer sex;
    private User1Dto user1Dto;
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

    public User1Dto getUser1Dto() {
        return this.user1Dto;
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

    public void setUser1Dto(User1Dto user1Dto) {
        this.user1Dto = user1Dto;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof User1Dto)) {
            return false;
        } else {
            User1Dto other = (User1Dto)o;
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

                Object this$user1Dto = this.getUser1Dto();
                Object other$user1Dto = other.getUser1Dto();
                if (this$user1Dto == null) {
                    if (other$user1Dto == null) {
                        return true;
                    }
                } else if (this$user1Dto.equals(other$user1Dto)) {
                    return true;
                }

                return false;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof User1Dto;
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
//        Object $user1Dto = this.getUser1Dto();
//        result = result * 59 + ($user1Dto == null ? 43 : $user1Dto.hashCode());
//        return result;
//    }

    public String toString() {
        return "User1Dto(username=" + this.getUsername() + ", password=" + this.getPassword() + ", phone=" + this.getPhone() + ", sex=" + this.getSex() + ", user1Dto=" + this.getUser1Dto() + ")";
    }

    public User1Dto(String username, String password, String phone, Integer sex, User1Dto user1Dto) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.sex = sex;
        this.user1Dto = user1Dto;
    }

    public User1Dto() {
    }

}
