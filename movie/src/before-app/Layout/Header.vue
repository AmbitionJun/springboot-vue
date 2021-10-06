<template>
  <div class="header">
    <div style="height: 70px;width: 100%;" class="aa">
      <div class="header-logo">
        <!-- <img style="width: 50px; height: 50px;float: left; " src="../../assets/img/logo.png" alt="" /> -->
        <div class="header-logo-text" style=" margin-top:3px;">Wanda影院</div>
      </div>
      <div class="header-links">
        <el-link href="/" class="header-link" :underline="false">首页</el-link>
        <el-link href="/films?region=全部&type=全部" class="header-link" :underline="false">电影</el-link>
        <el-link href="/active" class="header-link" :underline="false">公告</el-link>
        <br />
      </div>

      <div class="header-search">
        <el-autocomplete style="width: 250px" v-model="state" :fetch-suggestions="querySearchAsync" placeholder="请输入电影名"
          @select="handleSelect" />
      </div>


      <el-link v-if="isLogin" href="/userlogin" style="float: right;padding-top: 23px;padding-right: 50px"
        class="header-link" :underline="false">
        登录
      </el-link>
      <el-dropdown v-if="!isLogin" style="float: right;padding-right: 10px">
        <el-button type="text">
          <div class="header-name">{{ this.username }}
            <i class="el-icon-caret-bottom"></i>
          </div>
          <!-- <img alt="" style="width: 35px;height: 35px;border-radius: 50% ;margin-top:4px" :src="user.avatar"> -->
        </el-button>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>
            <el-link :underline="false" href="/order" style="padding-right: 7px">
              <i style="font-size: 15px; padding-right: 3px" class="el-icon-s-order"></i>我的订单
            </el-link>
          </el-dropdown-item>
          <el-dropdown-item divided>
            <el-button type="text" @click="handleLogout" :underline="false">
              <i style="font-size: 15px; padding-right: 3px" class="el-icon-switch-button"></i>退出登录
            </el-button>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>

    </div>
  </div>
</template>

<script>
  export default {
    name: "Header",
    data() {
      return {
        isLogin: true,
        searchList: [],
        state: '',
        timeout: null,
        username: '',
      }
    },

    mounted() {
      //
      if (sessionStorage.getItem("uid") !== null) {console.log(sessionStorage.getItem("uid"));
        this.isLogin = false;
        this.username = sessionStorage.getItem("username");
      }
    },

    methods: {

      querySearchAsync(queryString, cb) {
        const that =this;
        console.log(queryString);
        if (queryString !==null) {
          this.$axios.get('http://localhost:8080/film/selectByFilmName',{
            params: {
              name: queryString
            }
          }
          ).then(function (response) {
            setTimeout(() => {
              that.searchList = response.data
              cb(that.loadSearchList())
            }, 600)
          }).catch(function (error) {
            console.log(error);
          });
        }
      },

      loadSearchList() {
        let arr = new Array(0)
        for (let i = 0; i < this.searchList.length; i++) {
          arr.push({
            id: this.searchList[i].id,
            value: this.searchList[i].name,
          })
        }
        return arr
      },

      handleSelect(item) {
        this.$router.push('/film?fid=' + item.id)
      },

      handleLogout() {
        localStorage.removeItem("uid")
        localStorage.removeItem("usertoken")
         this.isLogin = true;
        this.$router.push('/home')
      },

    },

  }

</script>

<style scoped>
  .header {
    height: 70px;
    background-color: rgb(255, 245, 232);
    box-shadow: 0 2px 12px 0 rgb(0 0 0/10%);
    border: 1px solid #EBEEF5;
  }

  .header-logo {
    padding-top: 10px;
    padding-left: 10%;
    float: left;
    letter-spacing: 2px;

  }

  .header-logo-text {
    font-size: 30px;
    padding-top: 3px;
    font-weight: bolder;
    padding-left: 15px;
    float: left;
  }

  .header-search {
    float: left;
    width: 300px;
    border-radius: 20px;
    padding-top: 16px;
  }

  .header-links {
    float: left;
    margin-left: 20px;
    padding-left: 50px;
    padding-top: 23px;
  }

  .header-link {
    letter-spacing: 2px;
    font-size: 17px;
    padding-right: 20px;
  }

  .header-name {
    color: #000000;
    float: right;
    padding-top: 15px;
    padding-left: 15px;
    font-weight: bolder;
    font-size: 15px;
    letter-spacing: 2px;
  }

  .el-input__inner {
    border-radius: 20px;
    height: 40px;
  }

  .icon-search {
    position: relative;
    padding-top: 16px;
    width: 100px;
    float: left;
    left: -49.5px;
  }

  .data {
    position: absolute;
    top: 40px;
  }
</style>