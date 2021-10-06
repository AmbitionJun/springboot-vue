<template>
  <div class="header">
    <div class="a">
      <div class="header-avatar">
        <el-dropdown>
          <el-button type="text">
            <div class="header-name"></div>
            <el-avatar style="margin-top: -10px" :size="59"
              :src="admin.avatar">
            </el-avatar>
          </el-button>
          <el-dropdown-menu slot="dropdown">
            <!-- <el-dropdown-item>
              <el-link :underline="false" href="/setting">
                <i style="font-size: 15px; padding-right: 3px" class="el-icon-setting"></i>个人设置
              </el-link>
            </el-dropdown-item> -->
            <el-dropdown-item divided>
              <el-button type="text" @click="handleLogout">
                <i style="font-size: 15px; padding-right: 3px" class="el-icon-switch-button"></i>退出登录
              </el-button>
            </el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>

      
      <!-- <div class="header-icon">
        <el-tooltip content="小小影院 - 管理系统" placement="top">
          <i style="font-size: 20px" class="el-icon-user"></i>
        </el-tooltip>
      </div> -->
    </div>
  </div>
</template>

<script>
  // import {getadmin} from "@/api/user";

  export default {
    name: "Header",

    data() {
      return {
        admin:"",
        adminname:""
      }
    },

    // mounted() {
    //   getadmin().then(res => {
    //     setTimeout(() => {
    //       this.admin = res.data
    //       console.log(this.list);
    //     }, 700)
    //   })
    // },

    mounted() {
      this.adminname=sessionStorage.getItem("adminname");
    },

    methods: {

      handleLogout() {
        //退出清除缓存
        sessionStorage.removeItem("token")
        // sessionStorage.removeItem("role")
        // sessionStorage.removeItem("wid")
        sessionStorage.removeItem("adminname")
        sessionStorage.removeItem("adminpassword")
        this.$router.push("/admin/login")
      }

    },

  }
</script>

<style scoped>
  

  .header-icon {
    float: right;
    font-size: 20px;
    padding-top: 19px;
    padding-right: 30px;
  }

  .header-avatar {
    float: right;
    padding-left: 20px;
  }

  .header-name {
    float: left;
    padding-top: 12px;
    font-size: 15px;
    padding-right: 10px;
    padding-left: 9px;
  }

  .el-button {
    padding-top: 8px;
    color: #000000;
    letter-spacing: 2px;
  }
</style>