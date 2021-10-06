<template>
  <div class="main">
    <el-table :data="userList" style="width: 100%">
      <el-table-column label="管理员 ID" width="300" prop="admin_id">
      </el-table-column>
      <el-table-column label="管理员名" width="300" prop="admin_name">
      </el-table-column>
      <el-table-column label="密码" width="300" prop="admin_password">
      </el-table-column>
      <el-table-column label="操作" width="300">
        <template slot-scope="scope">
          <el-button
            size="mini" icon="el-icon-edit" type="primary"
            @click="change(scope.row.admin_id, scope.row.admin_name, scope.row.admin_password)"
            >修改
          </el-button>
          <el-button size="mini" icon="el-icon-delete" type="danger" @click="del(scope.row.admin_id)"
            >删除</el-button
          >
          <el-button size="mini" icon="el-icon-circle-plus-outline" type="success" @click="loss()">增加</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog
      title="增加"
      :visible.sync="dialogFormVisible"
      style="width: 50%; margin-left: 25%"
    >
      <el-form :model="form">
        <el-form-item label="管理员id" :label-width="formLabelWidth">
          <el-input
            v-model="form.adminid"
            autocomplete="off"
            style="width: 250px"
          ></el-input>
        </el-form-item>
        <el-form-item label="管理员名" :label-width="formLabelWidth">
          <el-input
            v-model="form.adminname"
            autocomplete="off"
            style="width: 250px"
          ></el-input>
        </el-form-item>
        <el-form-item label="密码" :label-width="formLabelWidth">
          <el-input
            v-model="form.adminpasswrod"
            autocomplete="off"
            style="width: 250px"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="add()">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog
      title="修改"
      :visible.sync="dialogFormVisible_2"
      style="width: 50%; margin-left: 25%"
    >
      <el-form :model="form_2">
        <el-form-item label="管理员id" :label-width="formLabelWidth">
          <el-input
            v-model="form_2.adminid"
            autocomplete="off"
            style="width: 250px"
          ></el-input>
        </el-form-item>
        <el-form-item label="管理员名" :label-width="formLabelWidth">
          <el-input
            v-model="form_2.adminname"
            autocomplete="off"
            style="width: 250px"
          ></el-input>
        </el-form-item>
        <el-form-item label="密码" :label-width="formLabelWidth">
          <el-input
            v-model="form_2.adminpasswrod"
            autocomplete="off"
            style="width: 250px"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="dialogFormVisible_2 = false">取 消</el-button>
        <el-button type="primary" @click="changepost()">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      formLabelWidth: "80px",
      dialogFormVisible_2: false,
      dialogFormVisible: false,
      userList: [],
      form: {
        adminname: "",
        adminid: "",
        adminpasswrod: "",
      },
      form_2: {
        adminname: "",
        adminid: "",
        adminpasswrod: "",
      },
    };
  },
  mounted() {
    const that = this;
    this.$axios
      .get("http://localhost:8080/admin/select")
      .then(function (response) {
        that.userList = response.data;
      }).catch(function (error) {
          console.log(error);
        });
  },
  methods: {
    add() {
      const that = this;
      this.$axios({
        method: "post",
        url: "http://localhost:8080/admin/add",
        data: {
          admin_id: this.form.adminid,
          admin_name: this.form.adminname,
          admin_password: this.form.adminpasswrod,
        },
      }).then(function (response) {
        if (response.data.success == true) {
          that.dialogFormVisible = false;
          that.$message({
                type: 'success',
                message: '添加成功!'
            });
            that.$router.go(0);
        } else {
          this.$message.error('添加失败');
          }
      }).catch(function (error) {
          console.log(error);
        });
    },
    del(id) {
      const that = this;
      this.$axios
        .get("http://localhost:8080/admin/delete", {
          params: {
            admin_id: id,
          },
        })
        .then(function (response) {
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
    change(id, adminname, passwrod) {
      this.form_2.adminid = id;
      this.form_2.adminname = adminname;
      this.form_2.adminpasswrod = passwrod;
      this.dialogFormVisible_2 = true;
    },
    changepost() {
      const that = this;
      this.$axios({
        method: "put",
        url: "http://localhost:8080/admin/update",
        data: {
          admin_id: this.form_2.adminid,
          admin_name: this.form_2.adminname,
          admin_password: this.form_2.adminpasswrod,
        },
      }).then(function (response) {
        if (response.data.success == true) {
          that.dialogFormVisible_2 = false;
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
    loss() {
      this.form.adminid = null;
      this.form.adminname = null;
      this.form.adminpasswrod = null;
      this.dialogFormVisible = true;
    },
  },
};
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

.button_3 {
  background-color: yellow;
}
</style>