<template>
  <div id="login">
    <div id="loginBox">
      <h4 align="center">Wanda影院 - 用户登录</h4>
      <el-form :model="loginForm" ref="loginForm" label-width="0px">
        <el-form-item prop="username" style="margin-top: 5px">
          <el-input
            prefix-icon="el-icon-user-solid"
            class="inps"
            ref="username"
            v-model="loginForm.username"
            placeholder="用户名"
            type="text"
            tabindex="1"
            auto-complete="on"
          ></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input
            prefix-icon="el-icon-key"
            show-password
            class="inps"
            ref="password"
            v-model="loginForm.userpassword"
            placeholder="密码"
            tabindex="2"
            auto-complete="on"
          ></el-input>
        </el-form-item>
        <div style="padding-top: 20px; margin-left: 15px">
          <el-checkbox v-model="loginForm.remember" style="color: rgb(0, 0, 0)"
            >记住密码</el-checkbox
          >
          <div style="float: right">
            <el-tooltip
              class="item"
              effect="dark"
              content="请联系管理员修改"
              placement="bottom"
            >
              <el-link
                style="font-weight: bolder; font-size: 14px; color: #91949c"
                :underline="false"
              >
                忘记密码?
              </el-link>
            </el-tooltip>
          </div>
        </div>
        <el-form-item style="margin-top: 15px; text-align: center">
          <el-button
            type="primary"
            round
            @click="postLogin"
            class="btn"
            style="margin-right: 50px"
            >登录
          </el-button>
          <el-button type="primary" round @click="Register" class="btn"
            >注册
          </el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
// import {Login} from "../api/user"
export default {
  data() {
    return {
      loginForm: {
        username: "",
        userpassword: "",
        remember: "",
      },
    };
  },
  mounted() {
    //console.log(sessionStorage.getItem("userremember"));
    if (sessionStorage.getItem("username") !== null) {
      this.loginForm.username = sessionStorage.getItem("username");
      if (sessionStorage.getItem("userremember")) {
        this.loginForm.userpassword = sessionStorage.getItem("userpassword");
        this.loginForm.remember = true;
      }
    }
  },
  methods: {
    check() {
      if (this.loginForm.username !== "") {
        if (this.loginForm.userpassword !== "") {
          return true;
        } else {
          this.$message({
            type: "warning",
            message: "密码为空!",
          });
          return false;
        }
      } else {
        this.$message({
          type: "warning",
          message: "用户名为空!",
        });
        return false;
      }
    },
    Register() {
      this.$router.push("/userRegister");
    },
    postLogin() {
      const that = this;
      if (this.check()) {
        sessionStorage.setItem("username", this.loginForm.username);
        sessionStorage.setItem("userpassword", this.loginForm.userpassword);
        console.log(this.loginForm.remember);
        sessionStorage.setItem("userremember", this.loginForm.remember);
        this.$axios({
          method: "post",
          url: "http://localhost:8080/user/login",
          data: {
            username: this.loginForm.username,
            password: this.loginForm.userpassword,
          },
        })
          .then(function (response) {
            if (response.data.success == true) {
              that.$message({
                type: "success",
                message: "登录成功!",
              });
              sessionStorage.setItem("usertoken", response.data.token);
              sessionStorage.setItem("uid", response.data.uid);
              that.$router.go(-1);
            } else {
              that.$message.error("登录失败,请先注册");
            }
          })
          .catch(function (error) {
            console.log(error);
          });
      }
    },
  },
};
</script>

<style>
#login {
  width: 100vw;
  padding: 0;
  margin: 0;
  height: 100vh;
  font-size: 16px;
  background-repeat: no-repeat;
  background-position: left top;
  background-color: #ffffff;
  color: rgb(0, 0, 0);
  font-family: "Source Sans Pro";
  background-size: 100%;
  /* background-image: url("../assets/img/background.png"); */
  position: relative;
}

#loginBox {
  width: 400px;
  height: 380px;
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  margin: auto;
  border-radius: 15px;
  padding: 50px 40px 40px 40px;
  box-shadow: -15px 15px 15px rgba(6, 17, 47, 0.7);
  opacity: 1;
  /* background: linear-gradient(230deg,
              rgba(20, 193, 138, 0.807) 0%,
              rgba(75, 5, 67, 0.372) 100%); */
}

.inps input {
  border: none;
  color: rgb(0, 0, 0);
  background-color: transparent;
  font-size: 20px;
}

.iconfont {
  color: rgb(255, 255, 255);
}

h4 {
  margin-top: 5px;
  display: block;
  text-align: center;
  font-weight: 300;
  font-size: 30px;
  font-style: italic;
  -webkit-box-reflect: below 3px -webkit-linear-gradient(transparent, transparent
        50%, rgba(255, 255, 255, 0.3));
  line-height: 1;
  background: linear-gradient(
    to right,
    rgb(228, 150, 6),
    rgba(202, 7, 212, 0.938),
    rgba(219, 76, 10, 0.703)
  );
  -webkit-background-clip: text;
  background-clip: text;
  color: transparent;
}

.btn {
  margin-top: 10px;
  width: 80px;
  height: 40px;
  color: rgb(252, 245, 245);
  background: #68a2e4f1;
  border-radius: 5px;
}

.btn:hover {
  background: #136fd7;
}

.btn:active {
  margin-top: 18px;
  width: 80px;
  height: 40px;
  color: white;
  border-radius: 5px;
  background: #4a9bfa;
  opacity: 0.5;
}
</style>