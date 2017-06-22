package com.example.tg.useotherlib.bean;

import java.util.List;

/**
 * Created by tg on 2017/6/21.
 */

public class CommentBean extends Data {


        /**
         * msg : 挺好的，喜欢这个导演。
         * phoneNumber : 一人一心一世界
         * dataId : ff8080815cb03cdc015cca32e5be0f64
         * userPic :
         * time : 2017-06-21 18:29:12
         * likeNum : 0
         */

        private String msg;
        private String phoneNumber;
        private String dataId;
        private String userPic;
        private String time;
        private int likeNum;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getDataId() {
            return dataId;
        }

        public void setDataId(String dataId) {
            this.dataId = dataId;
        }

        public String getUserPic() {
            return userPic;
        }

        public void setUserPic(String userPic) {
            this.userPic = userPic;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getLikeNum() {
            return likeNum;
        }

        public void setLikeNum(int likeNum) {
            this.likeNum = likeNum;
        }
}
