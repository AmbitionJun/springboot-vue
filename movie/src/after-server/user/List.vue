<template>
  <div class="main">
    <el-table :data="userList" style="width: 100%">
      <el-table-column label="用户 ID" prop="id">
      </el-table-column>
      <el-table-column label="用户名" prop="username">
      </el-table-column>
      <el-table-column label="用户密码"  prop="password">
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope" >
          <el-button size="mini" icon="el-icon-edit" type="primary" @click="change(scope.row.id,scope.row.username,scope.row.password)">修改
          </el-button>
          <el-button size="mini" icon="el-icon-delete" type="danger" @click="del(scope.row.id);">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog title="修改" :visible.sync="dialogFormVisible" style="width: 50%;margin-left: 25%;">
      <el-form :model="form">
        <el-form-item label="用户id" :label-width="formLabelWidth">
          <el-input v-model="form.id" autocomplete="off" style="width: 250px;"></el-input>
        </el-form-item>
        <el-form-item label="用户名" :label-width="formLabelWidth">
          <el-input v-model="form.username" autocomplete="off" style="width: 250px;"></el-input>
        </el-form-item>
        <el-form-item label="密码" :label-width="formLabelWidth">
          <el-input v-model="form.password" autocomplete="off" style="width: 250px;"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="dialogFormVisible_2 = false">取 消</el-button>
        <el-button type="primary" @click=changepost()>确 定</el-button>

      </div>
    </el-dialog>
  </div>
</template>

<script>
  export default {


    data() {
      return {
        userList: [],
        formLabelWidth:"300",
        content: "空",
        dialogFormVisible: false,
        form: {
          username: '',
          id: '',
          password: ''
        },
      }
    },

    mounted() {
      const that = this;
      this.$axios.get('http://localhost:8080/user/selectAll').then(function (response) {
        //console.log(response.data);
        that.userList = response.data;
      }).catch(function (error) {
          console.log(error);
        });
    },
    methods:{
      del(id) {
        const that = this;
       this.$axios
        .get("http://localhost:8080/user/delete", {
          params: {
            id: id,
          },
        }).then(function (response) {
          if (response.data.success == true) {
            that.$message({
                type: 'success',
                message: '删除成功!'
            });
            that.$router.go(0);
          }else {
          this.$message.error('删除失败');
          }
        }).catch(function (error) {
          console.log(error);
        });
      },
      change(id, adminname, password) {
        this.form.id = id;
        this.form.username = adminname;
        this.form.password = password;
        this.dialogFormVisible = true;

      },
      changepost() {
        const that = this;
        this.$axios({
          method: 'put',
          url: 'http://localhost:8080/user/update',
          data: {
              id:this.form.id,
              username:this.form.username,
              password:this.form.password
          }
          
        }
        ).then(function (response) {
          if (response.data.success == true) {
            that.dialogFormVisible=false;
            that.$message({
                type: 'success',
                message: '修改成功!'
            });
            that.$router.go(0);
          }else {
          this.$message.error('修改失败');
          }
        }).catch(function (error) {
          console.log(error);
        });
      },
    }
  }
</script>

<style scoped>
  .demo-table-expand {
    font-size: 0;
  }

  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }

  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }

  span {
    color: rgb(178, 194, 173);
    font-size: 15px;
    font-display: initial;
  }
  .button_1 {
    background-color: red;
  }

  .button_2 {
    background-color: blue;
  }
</style>