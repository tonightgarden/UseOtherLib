package com.example.tg.useotherlib.bean;

import java.util.List;

/**
 * Created by tg on 2017/6/22.
 */

public class CommentGetResult {

    /**
     * pnum : 1
     * totalRecords : 4
     * records : 20
     * list : [{"msg":"挺好的，喜欢这个导演。","phoneNumber":"一人一心一世界","dataId":"ff8080815cb03cdc015cca32e5be0f64","userPic":"","time":"2017-06-21 18:29:12","likeNum":0},{"msg":"一部好的片子，一定是一个好的导演导出来的，这部电影充分说明了这一点。","phoneNumber":"我们终成陌生人","dataId":"ff8080815cb03cdc015cca32e5c00f65","userPic":"","time":"2017-06-21 18:29:12","likeNum":0},{"msg":"整体一般，不推荐，除非你很有空。","phoneNumber":"自欺欺人","dataId":"ff8080815cb03cdc015cca32e5c20f66","userPic":"","time":"2017-06-21 18:29:12","likeNum":0},{"msg":"好几个朋友推荐这部电影给我，看完也就那样。。。","phoneNumber":"盗不走的爱人","dataId":"ff8080815cb03cdc015cca32e5c40f67","userPic":"","time":"2017-06-21 18:29:12","likeNum":0}]
     * totalPnum : 1
     */

    private int pnum;
    private int totalRecords;
    private int records;
    private int totalPnum;
    private List<CommentBean> list;

    public int getPnum() {
        return pnum;
    }

    public void setPnum(int pnum) {
        this.pnum = pnum;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getRecords() {
        return records;
    }

    public void setRecords(int records) {
        this.records = records;
    }

    public int getTotalPnum() {
        return totalPnum;
    }

    public void setTotalPnum(int totalPnum) {
        this.totalPnum = totalPnum;
    }

    public List<CommentBean> getList() {
        return list;
    }

    public void setList(List<CommentBean> list) {
        this.list = list;
    }

    public static class ListBean {
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
}
