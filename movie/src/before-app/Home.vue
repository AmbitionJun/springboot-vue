<template>
  <div class="app">
    <!-- interval为自动切换的时间 -->
    <div>
      <div>
        <span class="hot">热门推荐</span>
      </div>
      <div>
        <el-carousel :interval="2000" type="card" height="500px">
          <el-carousel-item v-for="(item, index) in posterList" :key="index">
            <router-link :to="'/film?fid=' + item.fid"> <img alt="" :src="item.url" style="width: 100%; height: 500px" />
            </router-link>
           
          </el-carousel-item>
        </el-carousel>
      </div>
    </div>

    <div class="main">
      <div class="movie-grid">
        <div class="panel">
          <div class="panel-header">
            <span class="panel-title">
              <span class="hot">正在热播 ( {{ total.length }} )</span>
            </span>
          </div>
          <div class="panel-content">
            <el-row class="movie-list">
              <el-col
                :span="span"
                v-for="(item, index) in filmList"
                :key="index"
              >
                <router-link :to="'/film?fid=' + item.id">
                  <div class="movie-item">
                    <div class="movie-poster">
                      <img
                        class="movie-poster-img"
                        alt=""
                        :src="item.cover"
                        height="250px"
                        width="180px"
                      />

                      <div class="movie-overlay ">
                        <div class="movie-info">
                          <div class="movie-title" title="">
                            {{ item.name }}
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="movie-ver"></div>
                  </div>
                  <!-- <div class="movie-detail movie-rt">{{ item.releaseTime }} 上映</div> -->
                </router-link>
              </el-col>
            </el-row>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      span: 6,
      filmList: [],
      arrangementList: [],
      total: [],
      posterList: [],
    };
  },
  mounted() {
    const that = this;
    this.$axios
      .get("http://localhost:8080/poster/select")
      .then(function (response) {
        console.log(response.data);
        that.posterList = response.data;
      })
      .catch(function (error) {
        console.log(error);
      });
    this.$axios
      .get("http://localhost:8080/film/select")
      .then(function (response) {
        //console.log(response.data);
        that.filmList = response.data;
      })
      .catch(function (error) {
        console.log(error);
      });
    this.$axios
      .get("http://localhost:8080/film/select")
      .then(function (response) {
        that.total = response.data;
        for (let i of that.total) {
          for (let j of that.filmList) {
            if (i.fid == j.fid) {
              this.arrangementList.push(j);
              break;
            }
          }
        }
      })
      .catch(function (error) {
        console.log(error);
      });
  },
};
</script>

<style scoped>
/* .movie-title {
    font-size: 16px;
    line-height: 22px;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    margin: 0 10px;
}
.movie-overlay .movie-info {
    color: #fff;
    position: absolute;
    bottom: 7px;
    width: 100%;
} */
/* @import "../assets/css/movie-list.css"; */
.app {
  padding: 50px 120px;
  background: #f6f6f6;
}

.as {
  float: right;
  background: rgb(180, 180, 168);
}

.main {
  width: 100%;

  padding-top: 40px;
  background: #f6f6f6;
}

.hot {
  font-size: 26px;
  color: #ffb400;
}
</style>