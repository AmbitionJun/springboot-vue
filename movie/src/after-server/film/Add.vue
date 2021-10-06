<template>
  <div class="film-list">
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="电影封面">
        <el-upload class="upload-demo" drag accept=".png,.jpg" :headers="header" :action="uploadAction"
          :on-success="handleUploadSuccess" multiple>
          <i class="el-icon-upload"></i>
          <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
          <div class="el-upload__tip" slot="tip">只能上传jpg/png文件</div>
        </el-upload>
      </el-form-item>

      <el-form-item label="电影名称">
        <el-input v-model="form.name"></el-input>
      </el-form-item>

      <el-form-item label="上映地区">
        <el-select v-model="form.region" placeholder="请选择上映区域">
          <el-option label="中国大陆" value="中国大陆"></el-option>
          <el-option label="欧美" value="欧美"></el-option>
          <el-option label="中国台湾" value="中国台湾"></el-option>
          <el-option label="中国香港" value="中国香港"></el-option>
          <el-option label="日本" value="日本"></el-option>
          <el-option label="泰国" value="泰国"></el-option>
          <el-option label="印度" value="印度"></el-option>
          <el-option label="英国" value="英国"></el-option>
          <el-option label="德国" value="德国"></el-option>
          <el-option label="西班牙" value="西班牙"></el-option>
          <el-option label="加拿大" value="加拿大"></el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="全片时长">
        <el-input-number v-model="form.duration" :min="10" :max="550"></el-input-number>
      </el-form-item>

      <el-form-item label="导演">
        <el-input v-model="form.actors"></el-input>
      </el-form-item>

      <el-form-item label="主演">
        <el-input v-model="form.director"></el-input>
      </el-form-item>

      <el-form-item label="电影类型">
        <el-radio-group v-model="form.type">
          <el-radio style="padding-bottom: 20px" label="爱情"></el-radio>
          <el-radio label="喜剧"></el-radio>
          <el-radio label="科幻"></el-radio>
          <el-radio label="动画"></el-radio>
          <el-radio style="padding-bottom: 20px" label="恐怖"></el-radio>
          <el-radio label="悬疑"></el-radio>
          <el-radio label="冒险"></el-radio>
          <el-radio label="动作"></el-radio>
          <el-radio style="padding-bottom: 20px" label="犯罪"></el-radio>
          <el-radio label="历史"></el-radio>
          <el-radio label="古装"></el-radio>
          <el-radio label="战争"></el-radio>
          <el-radio label="纪录片"></el-radio>
          <el-radio style="padding-bottom: 20px" label="传记"></el-radio>
          <el-radio label="武侠"></el-radio>
          <el-radio label="同性"></el-radio>
          <el-radio label="音乐"></el-radio>
        </el-radio-group>
      </el-form-item>

      <el-form-item label="内容简介">
        <el-input rows="8" type="textarea" v-model="form.introduction"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="onSubmit">确认提交</el-button>
        <el-button @click="oncancel">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        header: {
          Authorization: localStorage.getItem("token"),
        },
        uploadAction: "http://localhost:8080" + "/upload/insert",
        url: "",
        form: {
          name: "",
          introduction: "",
          type: "",
          actors: "",
          director: "",
          region: "",
          duration: 120,
          cover: "null",
        },
      };
    },

    methods: {
      oncancel() {
        this.$router.push("/admin/Layout/filmlist");
      },
      onSubmit() {
        this.form.cover = this.url;
        //const film = this.form;
        const that = this;
        this.$axios({
          method: "post",
          url: "http://localhost:8080/film/insert",
          data: {
            name: that.form.name,
            introduction: that.form.introduction,
            type: that.form.type,
            actors: that.form.actors,
            director: that.form.director,
            region: that.form.region,
            duration: that.form.duration,
            cover: that.form.cover,
          },
        }).then(function (response) {
          if (response.data.success == true) {
            that.$message({
                type: 'success',
                message: '电影添加成功!'
            });
            that.$router.push("/admin/Layout/filmlist");
          }
        }).catch(function (error) {
          console.log(error);
        });
       
      },

      //图片上传成功自动执行，返回图片存储id
      handleUploadSuccess(res) {
        this.url = res;
        console.log(res);
      },
    },
  };
</script>

<style scoped>
  .film-list {
    padding: 50px;
  }

  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }

  .avatar-uploader .el-upload:hover {
    border-color: #409eff;
  }

  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }

  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>