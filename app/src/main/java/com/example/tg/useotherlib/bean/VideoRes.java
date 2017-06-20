package com.example.tg.useotherlib.bean;

import java.util.List;

/**
 * Created by tg on 2017/6/20.
 */

public class VideoRes extends Data{


    /**
     * showStyle :
     * loadType : videoList
     * changeOpenFlag : false
     * line : 1
     * showType : banner
     * childList : [{"airTime":0,"duration":"","loadType":"html","score":0,"angleIcon":"","dataId":"","description":"","loadURL":"http://h5.svipmovie.com/h5/transformers5/index.html","shareURL":"","pic":"http://phonemovie.ks3-cn-beijing.ksyun.com/image/2017/06/17/1497656139937008319.jpg","title":"《变形金刚5：最后的骑士》限时送票","roomId":""},{"airTime":2011,"duration":"01:58:14","loadType":"video","score":0,"angleIcon":"http://phonemovie.ks3-cn-beijing.ksyun.com/image/2017/05/09/1494296614609066838.png","dataId":"5f34a8b5c9c14a19965bd7775dbf5ad5","description":"2020年，未来一个并不遥远的年份，整个世界已经发生了许多变化。在那个时代，人类拳击不再被允许，取而代之的是各种机器人走上了擂台，继续这项火爆的运动，愉悦大众。前拳击手查理·肯顿（休·杰克曼 Hugh Jackman 饰）似乎并不那么落寞，他很快适应了时代的发展，带着经过训练的机器人一次次走上拳台。但是倒霉事接二连三，他先是意外落败，债台高筑，继而接到前女友突然过时的消息，并见到了从未谋过面的儿子\u2014\u201411岁男孩麦克斯（达科塔·高尤 Dakota Goyo 饰）。 \r\n　　一次意外，让麦克斯遇到了一台业已被淘汰的陪练机器人Atom。在麦克斯的坚持下，查理同意让Atom参加比赛，却意外发现这台\u201c废物\u201d不仅抗打，而且拥有搏斗的潜质与天赋。肯顿父子和他们的机器人由此踏上新的征途\u2026\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=5f34a8b5c9c14a19965bd7775dbf5ad5","shareURL":"http://h5.svipmovie.com/bqdy/5f34a8b5c9c14a19965bd7775dbf5ad5.shtml?fromTo=shoujimovie","pic":"http://phonemovie.ks3-cn-beijing.ksyun.com/image/2017/06/14/1497430347931065012.jpg","title":"\u201c狼叔\u201d为梦想决战钢铁擂台","roomId":""},{"airTime":0,"duration":"","loadType":"html","score":0,"angleIcon":"","dataId":"","description":"订购手机电影7天会员流量包，可享受7天手机电影视频会员特权及1024MB北京本地7天通用流量。刷微信、看新闻、听歌、看视频均可。仅9.9元/次","loadURL":"http://211.136.93.44/svc/sjdy7.do?t=21&c=121","shareURL":"","pic":"http://phonemovie.ks3-cn-beijing.ksyun.com/image/2017/05/27/1495880483090019858.png","title":"1G流量+7天会员仅9.9元 还送电影优惠券","roomId":""},{"airTime":0,"duration":"","loadType":"html","score":0,"angleIcon":"","dataId":"","description":"","loadURL":"http://a.app.qq.com/o/simple.jsp?pkgname=com.lovebizhi.wallpaper","shareURL":"","pic":"http://phonemovie.ks3-cn-beijing.ksyun.com/image/2017/06/18/1497800824100005933.jpg","title":"爱壁纸-最专业的壁纸应用","roomId":""},{"airTime":2015,"duration":"02:18:06","loadType":"video","score":0,"angleIcon":"http://phonemovie.ks3-cn-beijing.ksyun.com/image/2017/05/09/1494296614609066838.png","dataId":"d5aa5b47161a4944aecdc7cbabfbe765","description":"自从银河帝国衰败之后，正邪轮回再度开始，第一秩序的黑暗力量蔓延滋长，重新为银河系带来威胁。与此同时，对于凯洛·伦的堕落深感内疚的天行者卢克宣告失踪，而围绕卢克下落的一份星系地图，则引起敌我双方的争夺。不愿成为战争机器的第一军团士兵芬恩协助反抗组织成员波·达摩龙逃到蛮荒星球贾库，寻找藏有地图的机器人BB8。两人失散后，芬邂逅拾荒者蕾伊和BB8。他们乘坐破旧的千年隼号躲过了军团的追杀，此后更遇到千年隼号的主人汉·索罗。第一秩序制造了破坏力远超死星的弑星者基地。宇宙和平危在旦夕，某人体内的原力则悄然苏醒\u2026\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=d5aa5b47161a4944aecdc7cbabfbe765","shareURL":"http://h5.svipmovie.com/bqdy/d5aa5b47161a4944aecdc7cbabfbe765.shtml?fromTo=shoujimovie","pic":"http://phonemovie.ks3-cn-beijing.ksyun.com/image/2017/06/12/1497232184317066029.jpg","title":"最经典科幻系列归来","roomId":""},{"airTime":0,"duration":"","loadType":"html","score":0,"angleIcon":"","dataId":"","description":"","loadURL":"http://a.app.qq.com/o/simple.jsp?pkgname=com.example.zhagen","shareURL":"","pic":"http://phonemovie.ks3-cn-beijing.ksyun.com/image/2017/06/16/1497609143340035524.jpg","title":"租房敲优惠 免费领红包","roomId":""}]
     * moreURL :
     * title : Banner
     * bigPicShowFlag :
     */

    public String showStyle;
    public String loadType;
    public String changeOpenFlag;
    public int line;
    public String showType;
    public String moreURL;
    public String title;
    public String bigPicShowFlag;
    public List<ChildListBean> childList;

    public String getShowStyle() {
        return showStyle;
    }

    public void setShowStyle(String showStyle) {
        this.showStyle = showStyle;
    }

    public String getLoadType() {
        return loadType;
    }

    public void setLoadType(String loadType) {
        this.loadType = loadType;
    }

    public String getChangeOpenFlag() {
        return changeOpenFlag;
    }

    public void setChangeOpenFlag(String changeOpenFlag) {
        this.changeOpenFlag = changeOpenFlag;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public String getShowType() {
        return showType;
    }

    public void setShowType(String showType) {
        this.showType = showType;
    }

    public String getMoreURL() {
        return moreURL;
    }

    public void setMoreURL(String moreURL) {
        this.moreURL = moreURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBigPicShowFlag() {
        return bigPicShowFlag;
    }

    public void setBigPicShowFlag(String bigPicShowFlag) {
        this.bigPicShowFlag = bigPicShowFlag;
    }

    public List<ChildListBean> getChildList() {
        return childList;
    }

    public void setChildList(List<ChildListBean> childList) {
        this.childList = childList;
    }

    @Override
    public String toString() {
        return "VideoRes{" +
                "showStyle='" + showStyle + '\'' +
                ", loadType='" + loadType + '\'' +
                ", changeOpenFlag='" + changeOpenFlag + '\'' +
                ", line=" + line +
                ", showType='" + showType + '\'' +
                ", moreURL='" + moreURL + '\'' +
                ", title='" + title + '\'' +
                ", bigPicShowFlag='" + bigPicShowFlag + '\'' +
                ", childList=" + childList +
                '}';
    }

    public static class ChildListBean {
        /**
         * airTime : 0
         * duration :
         * loadType : html
         * score : 0
         * angleIcon :
         * dataId :
         * description :
         * loadURL : http://h5.svipmovie.com/h5/transformers5/index.html
         * shareURL :
         * pic : http://phonemovie.ks3-cn-beijing.ksyun.com/image/2017/06/17/1497656139937008319.jpg
         * title : 《变形金刚5：最后的骑士》限时送票
         * roomId :
         */

        private int airTime;
        private String duration;
        private String loadType;
        private int score;
        private String angleIcon;
        private String dataId;
        private String description;
        private String loadURL;
        private String shareURL;
        private String pic;
        private String title;
        private String roomId;

        public int getAirTime() {
            return airTime;
        }

        public void setAirTime(int airTime) {
            this.airTime = airTime;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getLoadType() {
            return loadType;
        }

        public void setLoadType(String loadType) {
            this.loadType = loadType;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getAngleIcon() {
            return angleIcon;
        }

        public void setAngleIcon(String angleIcon) {
            this.angleIcon = angleIcon;
        }

        public String getDataId() {
            return dataId;
        }

        public void setDataId(String dataId) {
            this.dataId = dataId;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getLoadURL() {
            return loadURL;
        }

        public void setLoadURL(String loadURL) {
            this.loadURL = loadURL;
        }

        public String getShareURL() {
            return shareURL;
        }

        public void setShareURL(String shareURL) {
            this.shareURL = shareURL;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getRoomId() {
            return roomId;
        }

        public void setRoomId(String roomId) {
            this.roomId = roomId;
        }

        @Override
        public String toString() {
            return "ChildListBean{" +
                    "airTime=" + airTime +
                    ", duration='" + duration + '\'' +
                    ", loadType='" + loadType + '\'' +
                    ", score=" + score +
                    ", angleIcon='" + angleIcon + '\'' +
                    ", dataId='" + dataId + '\'' +
                    ", description='" + description + '\'' +
                    ", loadURL='" + loadURL + '\'' +
                    ", shareURL='" + shareURL + '\'' +
                    ", pic='" + pic + '\'' +
                    ", title='" + title + '\'' +
                    ", roomId='" + roomId + '\'' +
                    '}';
        }
    }
}
