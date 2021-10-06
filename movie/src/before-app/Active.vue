<template>
  <div class="main">
    <el-timeline>
      <el-timeline-item style="letter-spacing: 2px" v-for="(item, index) in list" :key="index"
        :timestamp="item.startTime" placement="top">
        <h4 class="p1">公告时间：{{ item.create_time }}</h4> 
        <el-card>
          <!-- <h4 class="h1">{{ item.content }}</h4> -->
          <el-row>
            <el-col :span="4">
              <el-image style="width: 100px; height: 100px" :src="item.cover" :fit="fit"></el-image>
            </el-col>
            <el-col :span='20'>
              <el-row>
                <el-col>电影名称:{{item.name}}</el-col>
              </el-row>
              <el-row>
                <el-col>简介:{{item.introduction}}</el-col>
              </el-row>
              <el-row>
                <el-col>类型:{{item.type}} {{item.type_d}}</el-col>
              </el-row>
              <el-row>
                <el-col>地区:{{item.region}}  导演:{{item.actors}} 主演:{{item.director}}</el-col>
              </el-row>
              <el-row>
                <el-col>时长:{{item.duration}}分钟</el-col>
              </el-row>
              <el-row>
                <el-col>上映时间:{{item.date}}</el-col>
              </el-row>
            </el-col>
          </el-row>
        </el-card>
      </el-timeline-item>
    </el-timeline>
  </div>
</template>

<script>
  export default {

    data() {
      return {
        list: [{startTime:'2021-9-22',
        name:'123',
        introduction:'12345456123',
        type:'爱情',
        type_d:'2Dfy',
        region:'美国',
        actors:'123456123',
        director:'123456123',
        duration:120,
        date:'2018-6-4',


      }],
      }
    },

    mounted() {
      this.load()
    },

    methods: {
      load() {
        const that = this;
        this.$axios.get("http://localhost:8080/notice/select").then(function (response) {
          that.list = response.data;
        }).catch(function (error) {
          console.log(error);
        });
      },
    },

  }

</script>

<style scoped>
  .main {
    padding: 100px;
  }

  .h1 {
    font-size: 18px;
  }

  .p1 {
    color: #666666;
    font-weight: 300;
  }
</style>