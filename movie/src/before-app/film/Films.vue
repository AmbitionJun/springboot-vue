<template>
  <div class="films">
    <div class="tags-panel">
      <ul class="tags-lines">
        <li class="tags-line">
          <div class="tags-title">类型 :</div>
          <ul class="tags">
            <li
              v-for="(item, index) in typeList"
              :key="index"
              :class="selectType === item ? 'active' : ''"
              @click="handleTypeSelect"
            >
              <router-link
                :to="'/films?region=' + selectRegion + '&type=' + item"
                >{{ item }}</router-link
              >
            </li>
          </ul>
        </li>
        <li class="tags-line">
          <div class="tags-title">地区 :</div>
          <ul class="tags">
            <li
              v-for="(item, index) in regionList"
              :key="index"
              :class="selectRegion === item ? 'active' : ''"
              @click="handleRegionSelect"
            >
              <router-link
                :to="'/films?region=' + item + '&type=' + selectType"
                >{{ item }}</router-link
              >
            </li>
          </ul>
        </li>
      </ul>
    </div>
    <div class="d">
      <el-row :gutter="20" style="padding-top: 60px">
        <el-col
          style="padding-bottom: 40px; text-align: center"
          v-for="(item, index) in filmList"
          :key="index"
          :span="4"
        >
          <router-link :to="'/film?fid=' + item.id">
            <el-card shadow="hover" style="padding: 0" class="a">
              <img
                style="width: 100%; height: 250px; padding-bottom: 10px"
                :src="item.cover"
                alt=""
              />
              <p class="s">{{ item.name }}</p>
            </el-card>
          </router-link>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
  name: "Films",

  data() {
    return {
      selectType: "全部",
      selectRegion: "全部",
      typeList: [
        "全部",
        "剧情",
        "喜剧",
        "科幻",
        "爱情",
        "动作",
        "动画",
        "悬疑",
        "恐怖",
        "犯罪",
        "音乐",
        "歌舞",
        "传记",
        "历史",
        "战争",
        "西部",
        "奇幻",
        "冒险",
        "灾难",
        "情色",
      ],
      regionList: [
        "全部",
        "中国大陆",
        "欧美",
        "中国台湾",
        "美国",
        "中国香港",
        "韩国",
        "日本",
        "泰国",
        "印度",
        "法国",
        "英国",
        "德国",
        "意大利",
        "西班牙",
        "伊朗",
        "加拿大",
        "澳大利亚",
        "爱尔兰",
        "雅典",
        "瑞典",
        "巴西",
        "丹麦",
      ],
      filmList: [],
    };
  },

  mounted() {
    const that = this;
    if (this.$route.query.region !== "全部") {
      this.selectRegion = this.$route.query.region;
    }
    if (this.$route.query.type !== "全部") {
      this.selectType = this.$route.query.type;
    }
    this.$axios
      .get("http://localhost:8080/film/selectFilmByTypeRegion", {
        params: {
          region: this.$route.query.region,
          type: this.$route.query.type,
        },
      })
      .then(function (response) {
        that.filmList = response.data;
        if (that.filmList[0] == null) {
           that.$message({
                type: "waring",
                message: "暂无此类电影资源!",
              });
        }
      })
      .catch(function (error) {
        console.log(error);
      });
  },

  methods: {
    handleTypeSelect(item) {
      this.selectType = item;
      this.$router.go(0);
    },

    handleRegionSelect(item) {
      this.selectRegion = item;
      this.$router.go(0);
    },
  },
};
</script>

<style scoped>
@import "../../assets/css/movie-list.css";

.films {
  padding: 20px 120px;
  background: #b1acb7;
}

.el-card__body {
  padding: 0 0 10px;
}

.s {
  margin-bottom: 10px;
  padding: 0 8px;
  letter-spacing: 1px;
  color: coral;
  text-align: center;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}

.tags li a:hover {
  color: #f6f6f6;
}
</style>
