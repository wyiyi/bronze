package com.amber.common.model;

import com.amber.common.annotation.FieldDesensitize;
import com.amber.common.annotation.FieldEncrypt;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
public class UserDto {

    private String username;
    @FieldEncrypt
    private String password;

    @FieldDesensitize
    private String phone;

    private Integer sex;

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

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof UserDto)) {
            return false;
        } else {
            UserDto other = (UserDto)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label59: {
                    Object this$username = this.getUsername();
                    Object other$username = other.getUsername();
                    if (this$username == null) {
                        if (other$username == null) {
                            break label59;
                        }
                    } else if (this$username.equals(other$username)) {
                        break label59;
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

                Object this$phone = this.getPhone();
                Object other$phone = other.getPhone();
                if (this$phone == null) {
                    if (other$phone != null) {
                        return false;
                    }
                } else if (!this$phone.equals(other$phone)) {
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

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof UserDto;
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
//        return result;
//    }

    public String toString() {
        return "UserDto(username=" + this.getUsername() + ", password=" + this.getPassword() + ", phone=" + this.getPhone() + ", sex=" + this.getSex() + ")";
    }

    public UserDto(String username, String password, String phone, Integer sex) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.sex = sex;
    }

    public UserDto() {
    }

}
