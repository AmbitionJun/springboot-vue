<template>
  <div id="Login">
    <div id="LoginBox">
      <h4 align="center">Wanda影院——后台管理</h4>
      <div id="LoginchridenBox">
        <el-form>
          <el-form-item>
            <h3>用户名:</h3>
            <el-input class="inps" ref="adminname" v-model="adminname" placeholder="用户名" type="text"
              tabindex="1">
            </el-input>
          </el-form-item>
          <el-form-item>
            <h3>密码:</h3>
            <el-input class="inps" ref="adminpassword" v-model="adminpassword" placeholder="密码" type="password"
              tabindex="2">
            </el-input>
          </el-form-item>
          <el-form-item>
            <div>
              <!-- <el-checkbox v-model="loginForm.remember" style="color:rgb(65, 149, 228)">记住密码</el-checkbox> -->
              <div style="float: right; margin-right: 75px;">
                <el-link style="font-weight: bolder;font-size: 14px;color: #91949c;" :underline="false"
                  @click="losspwd">
                  忘记密码?
                </el-link>
              </div>
            </div>

          </el-form-item>
        </el-form>


        <el-button class="button" type="primary" @click="sumbit">提交</el-button>
      </div>
    </div>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        adminname: "",
        adminpassword: "",
        // remember: ""

      }
    },
    mounted() {
      if(sessionStorage.getItem("adminname")!==null){
        this.adminname=sessionStorage.getItem("adminname");
      this.adminpassword=sessionStorage.getItem("adminpassword");
    }
    },
    methods: {
      losspwd() {
        alert("请联系管理员修改!")
      },
      sumbit() {
        // if (LoginData.username.length <= 3) {
        //     setTimeout(() => {
        //         this.loading = false; 
        //         this.usernameError = true; //显示用户名错误提示
        //     }, 700)
        //     return
        // } else {
        //     this.usernameError = false;
        // }

        // if (LoginData.password.length <= 5) {
        //     setTimeout(() => {
        //         this.loading = false;
        //         this.passwordError = true; ////显示密码错误提示
        //     }, 700)
        //     return
        // } else {
        //     this.passwordError = false;
        // }
        const that = this;
        sessionStorage.setItem("adminname",this.adminname);
        sessionStorage.setItem("adminpassword", this.adminpassword);
        this.$axios({
          method: 'post',
          url: 'http://localhost:8080/admin/login',
          data: {
            admin_name: this.adminname,
            admin_password: this.adminpassword,
          },
        }
        ).then(function (response) {
          if (response.data.success == true) {
              console.log(response);
            that.$message({
                type: 'success',
                message: '登录成功!'
            });
            console.log(response.data);
            sessionStorage.setItem("token", response.data.token);
            that.$router.push('/admin/Layout');
          } else {
            this.$message.error('登录失败');
          }
        }).catch(function (error) {
          console.log(error);
        });
      }
    },
  }
</script>
<style>
  #LoginBox {
    width: 500px;
    text-align: left;
    margin-left: -250px;
  }

  h3 {
    margin-top: 0px;
    margin-bottom: 0px;
    width: 60px;
  }

  .inps {
    width: 350px;
  }

  #Login {
    padding-left: 50%;
  }

  .button {
    margin-left: 15%;
    width: 50%;
  }

  #LoginchridenBox {
    padding-left: 80px;
  }
</style>