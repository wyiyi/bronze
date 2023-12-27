package com.amber.common.model;

import com.amber.common.annotation.FieldDesensitize;
import com.amber.common.annotation.FieldEncrypt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class User3Dto extends UserDto{

    private String username;
    @FieldEncrypt
    private String password;

    @FieldDesensitize
    private String phone;

    private Integer sex;

    private User3Dto user3Dto;

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

    public User3Dto getUser3Dto() {
        return this.user3Dto;
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

    public void setUser3Dto(User3Dto user3Dto) {
        this.user3Dto = user3Dto;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof User3Dto)) {
            return false;
        } else {
            User3Dto other = (User3Dto)o;
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

                Object this$user3Dto = this.getUser3Dto();
                Object other$user3Dto = other.getUser3Dto();
                if (this$user3Dto == null) {
                    if (other$user3Dto == null) {
                        return true;
                    }
                } else if (this$user3Dto.equals(other$user3Dto)) {
                    return true;
                }

                return false;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof User3Dto;
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
//        Object $user3Dto = this.getUser3Dto();
//        result = result * 59 + ($user3Dto == null ? 43 : $user3Dto.hashCode());
//        return result;
//    }

    public String toString() {
        return "User3Dto(username=" + this.getUsername() + ", password=" + this.getPassword() + ", phone=" + this.getPhone() + ", sex=" + this.getSex() + ", user3Dto=" + this.getUser3Dto() + ")";
    }

    public User3Dto(String username, String password, String phone, Integer sex, User3Dto user3Dto) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.sex = sex;
        this.user3Dto = user3Dto;
    }

    public User3Dto() {
    }

}
