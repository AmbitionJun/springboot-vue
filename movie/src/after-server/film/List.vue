<template>
  <div class="main">
    <el-table
      :data="
        filmList.filter(
          (data) =>
            !search || data.name.toLowerCase().includes(search.toLowerCase())
        )
      "
      style="width: 100%"
    >
      <el-table-column label="影片ID" prop="id" width="70"> </el-table-column>
      <el-table-column label="影片名" prop="name" show-overflow-tooltip="true">
      </el-table-column>
      <el-table-column
        label="简介"
        prop="introduction"
        width="130"
        show-overflow-tooltip="true"
      ></el-table-column>
      <el-table-column label="类型" prop="type"> </el-table-column>
      <el-table-column label="导演" prop="actors" show-overflow-tooltip="true">
      </el-table-column>
      <el-table-column
        label="主演"
        prop="director"
        show-overflow-tooltip="true"
      >
      </el-table-column>
      <el-table-column label="地区" prop="region"> </el-table-column>
      <el-table-column label="时长" prop="duration"> </el-table-column>
      <el-table-column label="封面URl" prop="cover">
        <template slot-scope="scope">
          <el-popover placement="top-start" title="" trigger="hover">
            <img
              :src="scope.row.cover"
              alt=""
              style="width: 150px; height: 150px"
            />
            <img
              slot="reference"
              :src="scope.row.cover"
              style="width: 100px; height: 100px"
            />
            <p>{{ scope.row.cover }}</p>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column width="300">
        <template slot="header">
          <el-input v-model="search" size="mini" placeholder="输入关键字搜索" />
        </template>
        <template slot-scope="scope">
          <el-button
            size="mini"
            icon="el-icon-edit"
            type="primary"
            @click="
              change(
                scope.row.id,
                scope.row.name,
                scope.row.introduction,
                scope.row.type,
                scope.row.actors,
                scope.row.director,
                scope.row.region,
                scope.row.duration,
                scope.row.cover
              )
            "
          >
            修改
          </el-button>
          <el-button
            size="mini"
            icon="el-icon-delete"
            type="danger"
            @click="del(scope.row.id)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <el-dialog
      title="修改"
      :visible.sync="dialogFormVisible"
      style="width: 60%; margin-left: 25%"
    >
      <el-form :model="form">
        <el-form-item label="影片ID" :label-width="formLabelWidth">
          <el-input
            v-model="form.id"
            autocomplete="off"
            style="width: 250px"
          ></el-input>
        </el-form-item>
        <el-form-item label="影片名" :label-width="formLabelWidth">
          <el-input
            v-model="form.name"
            autocomplete="off"
            style="width: 250px"
          ></el-input>
        </el-form-item>
        <el-form-item label="简介" :label-width="formLabelWidth">
          <el-input
            v-model="form.introduction"
            autocomplete="off"
            style="width: 250px"
          ></el-input>
        </el-form-item>
        <el-form-item label="类型" :label-width="formLabelWidth">
          <el-input
            v-model="form.type"
            autocomplete="off"
            style="width: 250px"
          ></el-input>
        </el-form-item>
        <el-form-item label="导演" :label-width="formLabelWidth">
          <el-input
            v-model="form.actors"
            autocomplete="off"
            style="width: 250px"
          ></el-input>
        </el-form-item>
        <el-form-item label="主演" :label-width="formLabelWidth">
          <el-input
            v-model="form.director"
            autocomplete="off"
            style="width: 250px"
          ></el-input>
        </el-form-item>
        <el-form-item label="地区" :label-width="formLabelWidth">
          <el-input
            v-model="form.region"
            autocomplete="off"
            style="width: 250px"
          ></el-input>
        </el-form-item>
        <el-form-item label="时长" :label-width="formLabelWidth">
          <el-input
            v-model="form.duration"
            autocomplete="off"
            style="width: 250px"
          ></el-input>
        </el-form-item>
        <el-form-item label="封面URl" :label-width="formLabelWidth">
          <!-- <el-input v-model="form.cover" autocomplete="off" style="width: 250px"></el-input> -->
          <el-upload
            class="upload-demo"
            drag
            accept=".png,.jpg"
            :action="uploadAction"
            :on-success="handleUploadSuccess"
            multiple
          >
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">
              将文件拖到此处，或<em>点击上传</em>
            </div>
            <div class="el-upload__tip" slot="tip">只能上传jpg/png文件</div>
          </el-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="changepost()">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
export default {
  data() {
    return {
      uploadAction: "http://localhost:8080" + "/upload/insert",
      url: "",
      search: "",
      filmList: [],
      dialogFormVisible: false,
      formLabelWidth: "300",
      form: {
        id: "",
        name: "",
        introduction: "",
        type: "",
        actors: "",
        director: "",
        region: "",
        duration: "",
        cover: "",
      },
    };
  },
  mounted() {
    const that = this;
    this.$axios
      .get("http://localhost:8080/film/select")
      .then(function (response) {
        that.filmList = response.data;
      })
      .catch(function (error) {
        console.log(error);
      });
  },
  methods: {
    del(id) {
      const that = this;
      this.$axios
        .get("http://localhost:8080/film/delete", {
          params: {
            id: id,
          },
        })
        .then(function (response) {
          if (response.data.success == true) {
            that.$message({
              type: "success",
              message: "删除成功!",
            });
            that.$router.go(0);
          }
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    change(
      id,
      name,
      introduction,
      type,
      actors,
      director,
      region,
      duration,
      cover
    ) {
      this.form.id = id;
      this.form.introduction = introduction;
      this.form.type = type;
      this.form.actors = actors;
      this.form.director = director;
      this.form.region = region;
      this.form.duration = duration;
      this.form.cover = cover;
      this.form.name = name;
      this.dialogFormVisible = true;
    },
    changepost() {
      const that = this;
      if (this.url !== "") {
        this.form.cover = this.url;
        this.url = "";
      }
      this.$axios({
        method: "post",
        url: "http://localhost:8080/film/update",
        data: this.form,
      })
        .then(function (response) {
          if (response.data.success == true) {
            that.$message({
              type: "success",
              message: "修改成功!",
            });
            that.$router.go(0);
            that.dialogFormVisible = false;
          }
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    handleUploadSuccess(res) {
      this.url = res;
      console.log(res);
    },
  },
};
</script>
<style scoped>
.button_1 {
  background-color: red;
}

.button_2 {
  background-color: blue;
}
.el-table .cell,
.el-table th div {
  padding-right: 0;
}
</style>