<template>
  <div class="main">
    <el-table :data="orders" style="width: 100%">
      <el-table-column label="订单 ID" width="150" prop="id"> </el-table-column>
      <el-table-column width="150" label="订单金额" prop="price">
      </el-table-column>
      <el-table-column label="电影名称" width="200" prop="order_name">
      </el-table-column
      ><el-table-column label="支付宝交易单号" width="300" prop="trade_no">
      </el-table-column>
      <el-table-column label="订单状态">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status === 2" type="success">支付成功</el-tag>
          <el-tag v-if="scope.row.status === 0" type="info">等待支付</el-tag>
          <el-tag v-if="scope.row.status === 3" type="warning">已被撤销</el-tag>
          <el-tag v-if="scope.row.status === 1" type="danger">支付失败</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="150">
        <template slot-scope="scope">
          <el-button
            @click="handle1(scope.row.id)"
            size="small"
            type="warning"
            icon="el-icon-refresh-right"
            :disabled="scope.row.status === 3"
            plain
            >撤销订单
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  data() {
    return {
      dialogFormVisible: false,
      orders: [],
      form: {
        oid: "",
        reason: "",
        reviewer: "",
      },
    };
  },

  mounted() {
    const that = this;
    this.$axios
      .get("http://localhost:8080/order/select")
      .then(function (response) {
        that.orders = response.data;
        console.log(that.orders);
      });
  },

  methods: {
    handle1(id) {
      // this.orders[index].order.status = 3
      // UpdateOrder(this.orders[index].order).then(res => {
      //   console.log(res)
      //   this.loadOrderList();
      //   this.$message({
      //     type: 'success',
      //     message: '订单撤销成功!'
      //   });
      // })
      //
      const that = this;
      this.$axios
        .get("http://localhost:8080/order/deleteOrder",{
          params:{
            id : id
          }
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
  },
};
</script>

<style scoped>
.main {
  padding: 30px;
}

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

  width: 100%;
}
</style>