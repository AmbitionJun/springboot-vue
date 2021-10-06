<template>
  <div class="main">
    <el-button type="primary" icon="el-icon-plus" @click="Arrangeadd">添加</el-button>
    <el-dialog :title="title" :visible.sync="dialog" width="50%">
      <el-form ref="form" :model="arrangement" label-width="80px">
        <el-form-item label="电影封面">
        <el-upload class="upload-demo" drag accept=".png,.jpg" :action="uploadAction"
          :on-success="handleUploadSuccess" multiple>
          <i class="el-icon-upload"></i>
          <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
          <div class="el-upload__tip" slot="tip">只能上传jpg/png文件</div>
        </el-upload>
      </el-form-item>
        <!-- <el-form-item label="公告id">
          <el-input :disabled="flag !== true" v-model="arrangement.id"></el-input>
        </el-form-item> -->
        <el-form-item label="电影名">
          <el-input v-model="arrangement.name"></el-input>
        </el-form-item>
        <el-form-item label="简介">
          <el-input v-model="arrangement.introduction"></el-input>
        </el-form-item>
        <el-form-item label="电影类型">
        <el-radio-group v-model="arrangement.type">
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
        <el-form-item label="放映类型">
          <el-select v-model="arrangement.type_d" placeholder="请选择放映类型">
            <el-option label="2D 放映" value="2D放映"></el-option>
            <el-option label="3D 放映" value="3D放映"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item style="width: 500px" label="上映日期">
          <el-col :span="11">
            <el-date-picker type="date" placeholder="选择日期" v-model="arrangement.date" value-format="yyyy-MM-dd"
              style="width: 100%;"></el-date-picker>
          </el-col>
        </el-form-item>
        <el-form-item label="地区">
          <el-select v-model="arrangement.region" placeholder="请选择上映区域">
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
        <el-form-item label="导演">
          <el-input v-model="arrangement.actors"></el-input>
        </el-form-item>
        <el-form-item label="主演">
          <el-input v-model="arrangement.director"></el-input>
        </el-form-item>
        <el-form-item label="时长">
          <el-input v-model="arrangement.duration"></el-input>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button @click="dialog = false">取 消</el-button>
        <el-button type="primary" @click="submitEditArrange">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 列出所有排片 -->
    <el-table v-loading="loading"
      :data="List"
      style="width: 100%">
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
      <el-table-column label="电影名" prop="name">
      </el-table-column>
      <el-table-column label="简介" prop="introduction">
      </el-table-column>
      <el-table-column label="放映类型">
        <template slot-scope="scope">
          <el-tag effect="dark" v-if="scope.row.type_d === '2D放映'" type="success" disable-transitions>2D 放映</el-tag>
          <el-tag effect="dark" v-if="scope.row.type_d === '3D放映'" type="primary" disable-transitions>3D 放映</el-tag>
        </template>
      </el-table-column>
      <!-- <el-table-column label="票房统计" prop="boxOffice">
      </el-table-column> -->
      <el-table-column label="上映日期" prop="date">
      </el-table-column>
      <el-table-column label="地区" prop="region">
      </el-table-column>
      <el-table-column label="导演" prop="actors">
      </el-table-column>
      <el-table-column label="主演" prop="director">
      </el-table-column>
      <el-table-column label="时长" prop="duration">
      </el-table-column>
      <el-table-column width="300" align="right">
        <template style="white-space: nowrap" slot-scope="scope">
          <el-button size="mini" icon="el-icon-edit" type="primary" @click="Arrangechange(scope.row.id)">修改
          </el-button>
          <el-popconfirm confirm-button-text='确定' cancel-button-text='不用了' icon="el-icon-info" icon-color="red"
            @confirm="Arrangedel(scope.row.id)" title=" 确定要删除此电影档期吗？ ">
            <el-button style="margin-left: 8px" size="mini" icon="el-icon-delete" type="danger" slot="reference">删除
            </el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>


  export default {
    data() {
      return {
        title: '',
        flag: true,
        dialog: false,
        search: '',
        List: [],
        loading: false,
        arrangement: {},
        uploadAction: "http://localhost:8080" + "/upload/insert",
        url:"",
        arrangement_add: {
          id:'',
          name: '',
          introduction: '',
          type: '',
          type_d: '',
          date: '',
          region: '',
          actors: '',
          director: '',
          duration: '',
          cover:''
        },
      }
    },

    mounted() {
      const that = this;

      this.$axios
        .get("http://localhost:8080/notice/select")
        .then(function (response) {
          that.List = response.data;
          //console.log(that.List);
        }).catch(function (error) {
          console.log(error);
        });
    },
    methods: {

      Arrangeadd() {
        this.title = "添加公告";
        this.flag = true;
        this.arrangement = this.arrangement_add;
        this.dialog = true;
      },
      Arrangechange(id) {
        this.title = "修改公告";
        this.arrangement = this.List[id - 1];
        this.flag = false;
        this.dialog = true;

      },
      Arrangedel(id) {
        const that = this;
        this.$axios
          .get("http://localhost:8080/notice/delete", {
            params: {
              "id": id,
            },
          })
          .then(function (response) {
            if (response.data.success == true) {
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
      submitEditArrange() {
        const that = this;
        if (this.flag == false) {
          if(this.url !== ""){
            this.arrangement.cover = this.url;
            this.url="";
          }
          
          this.$axios({
            method: 'post',
            url: 'http://localhost:8080/notice/update',
            data: this.arrangement
          }
          ).then(function (response) {
            if (response.data.success == true) {
              that.$message({
                type: 'success',
                message: '修改成功!'
              });
              that.$router.go(0);
            } else {
              this.$message.error('修改失败');
            }
          }).catch(function (error) {
            console.log(error);
          });
        }
        else {
          if (this.arrangement.name !== '' && this.arrangement.introduction !== '' && this.arrangement.type !== '' && this.arrangement.type_d !== '' && this.arrangement.date !== '' && this.arrangement.region !== '' && this.arrangement.actors !== ''&& this.arrangement.director !== ''&& this.arrangement.duration !== '') {
            this.arrangement.cover = this.url;
            this.$axios({
              method: 'post',
              url: 'http://localhost:8080/notice/insert',
              data: this.arrangement,
            }
            ).then(function (response) {
              if (response.data.success == true) {
                that.$message({
                  type: 'success',
                  message: '添加成功!',
                });
                that.$router.go(0);
              } else {
                this.$message.error('添加失败');
              }
            }).catch(function (error) {
              console.log(error);
            });
          }
          else {
            that.$message({
              type: 'warning',
              message: '请完整填写表单!'
            });
          }
        }

      },
         handleUploadSuccess(res) {
        this.url = res;
        console.log(res);
      },
    },
  }
</script>

<style scoped>
  .main {
    padding: 20px;
  }
</style>