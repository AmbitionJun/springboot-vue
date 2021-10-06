<template>
  <div>
    <!-- interval为自动切换的时间间隔 默认为3000ms -->
    <el-carousel :interval="2000" type="card" height="500px">
      <el-carousel-item v-for="(item,index) in posterList" :key="index">
        <img alt="" :src="item.url" class="image">
      </el-carousel-item>
    </el-carousel>

    <div class="content">
      <el-button @click="dialogFormVisible = true" type="primary" icon="el-icon-upload">
        上传轮播海报
      </el-button>
      <el-button style="margin-left: 10px" type="danger" icon="el-icon-delete" @click="deleteAllPoster">
        删除所有海报
      </el-button>
      <el-table ref="filterTable" :data="posterList" style="width: 100%;margin-top: 15px;">
        <el-table-column prop="create_time" label="上传时间" width="220">
        </el-table-column>
        <el-table-column prop="title" label="标题" width="150">
        </el-table-column> 
        <el-table-column prop="fid" label="电影ID" width="150">
        </el-table-column>
        <el-table-column label="地址预览">
          <template slot-scope="scope">
            <a target="_blank" :href="scope.row.url">{{ scope.row.url }}</a>
          </template>
        </el-table-column>
        <el-table-column prop="status" label="上架状态" width="220">
          <template slot-scope="scope">
            <template style="padding-left: 100px">
              <el-popconfirm @confirm="handleDeletePoster(scope.$index, scope.row.id)" title="确定要删除这条内容吗？">
                <el-button slot="reference" style="color: #F56C6C" type="text" icon="el-icon-delete">删除
                </el-button>
              </el-popconfirm>
            </template>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <el-dialog title="轮播海报" :visible.sync="dialogFormVisible">
      <el-form :model="uploadPoster">
        <el-form-item label="海报标题" style="margin-bottom: 35px" label-width="120px">
          <el-input style="width: 360px" v-model="uploadPoster.title" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="电影ID" style="margin-bottom: 35px" label-width="120px">
          <el-input style="width: 360px" v-model="uploadPoster.fid" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="选择图片" label-width="120px">
          <el-upload style="float: left" class="upload-demo" accept=".png,.jpg" :headers="header" :action="uploadAction"
            :on-success="handleUploadSuccess" multiple :limit="1">
            <el-button type="primary" icon="el-icon-upload">上传轮播海报</el-button>
          </el-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitUpload">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>


  export default {
    data() {
      return {
        header: {
          "Authorization": localStorage.getItem("token")
        },
        dialogFormVisible: false,
        uploadAction: "http://localhost:8080" + '/upload/insert',
        uploadPoster: {
          url: '',
          title: '',
          fid:0,
          create_time: '',
        },
        posterList: [],
      }
    },
    mounted() {
      const that = this
      this.$axios.get('http://localhost:8080/poster/select').then(function (response) {
        //console.log(response.data)
        that.posterList = response.data
      }).catch(function (error) {
        console.log(error);
      });
    },
    methods: {
      //获取图片URL
      handleUploadSuccess(res) {
        this.uploadPoster.url = res
      },
      submitUpload() {
        const that = this;
        this.uploadPoster.create_time = new Date();
        this.$axios({
          method: 'post',
          url: 'http://localhost:8080/poster/insert',
          data: this.uploadPoster
        }
        ).then(function (response) {
          if (response.data.success == true) {
            that.dialogFormVisible = false;
            that.$message({
                type: 'success',
                message: '上传成功!'
            });
            that.$router.go(0);
          }
        }).catch(function (error) {
          console.log(error);
        });
      },
      deleteAllPoster() {
        const that = this;
        this.$axios({
          method: 'get',
          url: 'http://localhost:8080/poster/deleteAll',
        }
        ).then(function (response) {
          if (response.data.success == true) {
            that.posterList = null;
            that.$message({
                type: 'success',
                message: '删除成功!'
            });
            that.$router.go(0);
          }
        }).catch(function (error) {
          console.log(error);
        });
      },
      handleDeletePoster(index, id) {
        const that = this;
        this.$axios.get('http://localhost:8080/poster/delete',
          {
            params: {
              "id": id
            }
          }
        ).then(function (response) {
          if (response.data.success == true) {
            that.posterList.splice(index, 1);
            that.$message({
                type: 'success',
                message: '删除成功!'
            });
            that.$router.go(0);
          }
        }).catch(function (error) {
          console.log(error);
        });
      },

    }
  }
</script>

<style scoped>
  .content {
    padding: 20px 40px;
  }

  .el-carousel__item h3 {
    color: #475669;
    font-size: 18px;
    opacity: 0.75;
    line-height: 300px;
    margin: 0;
  }

  .image {
    width: 100%;
    height: 500px;
  }
</style>