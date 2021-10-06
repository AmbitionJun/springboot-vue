<template>
  <div class="film">
    <div class="film-header" ref="header"></div>
    <div class="film-card">
      <img alt="" style="width: 100%; height: 100%" :src="this.film[0].cover" />
    </div>
    <div class="film-des">
      <div class="p1">{{ film.name }}</div>
      <div class="p2">电影类型 : {{ film[0].type }}</div>
      <div class="p2">主要演员 : {{ film[0].actors }}</div>
      <div class="p2">导演 : {{ film[0].director }}</div>
      <div class="p2">上映地区 : {{ film[0].region }}</div>
      <div class="p2">影片时长 : {{ film[0].duration }}分钟</div>
    </div>
    <div class="film-content">
      <div class="buttpage">
        <el-page-header @back="goBack" content="详情页面"> </el-page-header>
        <div
          style="
            font-weight: bolder;
            font-size: 25px;
            padding-top: 40px;
            padding-bottom: 40px;
          "
        >
          剧情简介
        </div>
        <div
          style="
            line-height: 30px;
            text-indent: 2.5em;
            padding-top: 20px;
            padding-bottom: 20px;
          "
        >
          {{ film[0].introduction }}
        </div>
        <div
          style="
            font-weight: bolder;
            font-size: 25px;
            padding-top: 40px;
            padding-bottom: 40px;
          "
        >
          电影购票
        </div>
        <div
          style="
            font-weight: bold;
            letter-spacing: 2px;
            font-size: 25px;
            color: red;
          "
          v-if="flag !== true"
        >
          影院还没有添加排片
        </div>
        <div v-if="flag == true">
          <div class="item" v-for="(item, index) in film" :key="index">
            <div class="item-des">
              <div class="d1">{{ item.name }}</div>
              <div class="d2">{{ item.date }} {{ item.start_time }}</div>
              <el-tag effect="plain">{{ item.type }}</el-tag>
            </div>
            <div class="item-btn">
              <el-button type="danger" @click="handleSelectSeat(item.id)" round
                >选座购票</el-button
              >
            </div>
            <div class="item-price">￥{{ item.price }}</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Film",
  data() {
    return {
      flag: true,
      film: [{}],
    };
  },

  mounted() {
    const that = this;
    this.$axios
      .get("http://localhost:8080/arrangement/selectByFid", {
        params: {
          fid: this.$route.query.fid,
        },
      })
      .then(function (response) {
        that.film = response.data.arrangementFilmList;
        that.flag = response.data.flag;
        console.log(that.flag);
      })
      .catch(function (error) {
        console.log(error);
      });
  },
  methods: {
    goBack() {
      this.$router.go(-1);
    },
    handleSelectSeat(id) {
      if (!sessionStorage.getItem("usertoken")) {
        this.$confirm("系统还没有检测到您的登陆信息, 是否去登录?", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }).then(() => {
          this.$router.push("/userlogin");
        });
      } else {
        this.$router.push("/seat?id=" + id);
      }
    },
  },
};
</script>

<style scoped>
.film-header {
  position: relative;
  width: 100%;
  height: 380px;
  background-size: cover;
  filter: blur(40px);
  margin-top: 12px;
}

.film-content {
  padding: 80px 200px;
  letter-spacing: 2px;
}

.p1 {
  font-weight: bolder;
  letter-spacing: 3px;
  color: #000000;
  font-size: 30px;
  padding-top: 20px;
  padding-bottom: 10px;
}

.p2 {
  font-size: 14px;
  padding-top: 20px;
  letter-spacing: 2px;
  color: #000000;
}

.film-card {
  width: 240px;
  height: 330px;
  background: #fffdfd;
  margin-right: 100px;
  margin-left: 190px;
  position: absolute;
  overflow: hidden;
  top: 160px;
  z-index: 999;
}
.item {
  width: 100%;
  height: 100px;
}
.film-des {
  margin-left: 500px;
  position: absolute;
  overflow: hidden;
  top: 150px;
  z-index: 999;
}

.el-rate__icon {
  font-size: 25px;
}
.el-rate__text {
  padding-left: 8px;
  font-size: 30px;
}
.item-des {
  float: left;
}

.item-btn {
  float: right;
  padding-top: 30px;
  padding-left: 50px;
}

.item-price {
  float: right;
  color: #f34d41;
  line-height: 100px;
}
</style>
