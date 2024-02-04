package com.amber.common.sensitive.mock.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@TableName("USERINFO_HISTORY")
public class UserHistoryDO {

    @TableId(type = IdType.UUID, value = "rid")
    String id;

    String userId;

    String historyEvent;

    UserHistoryDO(String id, String userId, String historyEvent) {
        this.id = id;
        this.userId = userId;
        this.historyEvent = historyEvent;
    }

    public static UserHistoryDOBuilder builder() {
        return new UserHistoryDOBuilder();
    }

    public String getId() {
        return this.id;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getHistoryEvent() {
        return this.historyEvent;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setHistoryEvent(String historyEvent) {
        this.historyEvent = historyEvent;
    }

    public String toString() {
        return "UserHistoryDO(id=" + this.getId() + ", userId=" + this.getUserId() + ", historyEvent=" + this.getHistoryEvent() + ")";
    }

    public static class UserHistoryDOBuilder {
        private String id;
        private String userId;
        private String historyEvent;

        UserHistoryDOBuilder() {
        }

        public UserHistoryDOBuilder id(String id) {
            this.id = id;
            return this;
        }

        public UserHistoryDOBuilder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public UserHistoryDOBuilder historyEvent(String historyEvent) {
            this.historyEvent = historyEvent;
            return this;
        }

        public UserHistoryDO build() {
            return new UserHistoryDO(this.id, this.userId, this.historyEvent);
        }

        public String toString() {
            return "UserHistoryDO.UserHistoryDOBuilder(id=" + this.id + ", userId=" + this.userId + ", historyEvent=" + this.historyEvent + ")";
        }
    }
}
