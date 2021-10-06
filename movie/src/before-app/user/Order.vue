<template>
  <div style="width: 100%">
    <h3 style="letter-spacing: 1px; font-weight: 400; padding-bottom: 20px">
      我的订单
    </h3>
    <el-card v-for="(item, index) in orderList" :key="index" class="box-card">
      <div>
        <img class="item-film-img" alt="" :src="item.cover" />
        <div style="float: left">
          <div class="item-film-name">《{{ item.order_name }}》</div>
          <div class="item-film-seat">订购座位 : {{ item.seats }}</div>
          <div class="item-film-price">价格 : {{ item.price }}</div>
          <div class="item-film-time">下单时间 : {{ item.create_time }}</div>
          <div v-if="item.status === 2" class="item-film-time">
            支付时间 : {{ item.pay_time }}
          </div>
        </div>
        <div class="o3">
          <el-button type="danger" @click="SingleBack(item)">退单</el-button>
        </div>
        <el-button
          type="text"
          @click="handlePay(item.id)"
          v-if="item.status === 0"
          style="line-height: 75px"
          class="o1"
        >
          等待支付
        </el-button>
        <el-tooltip
          class="item"
          effect="dark"
          content="请联系工作人员查询您的订单"
          placement="top"
        >
          <div v-if="item.status === 3" style="color: #e6a23c" class="o1">
            订单异常
          </div>
        </el-tooltip>
        <div v-if="item.status === 2" style="color: #67c23a" class="o1">
          支付成功
        </div>
        <div v-if="item.status === 1" style="color: #f56c6c" class="o1">
          订单超时
        </div>
        <div class="o2">￥{{ item.price }}</div>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "Order",

  data() {
    return {
      centerDialogVisible: false,
      orderList: [
        {
          cover: "",
          id: "",
          uid: "",
          aid: "",
          order_name: "",
          seats: "",
          create_time: "",
          pay_time: "",
          status: 0,
          price: "",
        },
      ],
      payOrderId: "",
    };
  },

  mounted() {
    const that = this;
    setTimeout(() => {
      this.$axios
        .get("http://localhost:8080/order/selectByUid", {
          params: {
            uid: sessionStorage.getItem("uid"),
          },
        })
        .then(function (response) {
          that.orderList = response.data;
          if (that.orderList[0] == null) {
            that.$message({
              type: "waring",
              message: "您还没有订单，请先购票!",
            });
          }
        })
        .catch(function (error) {
          console.log(error);
        });
    }, 100);
  },
  methods: {
    SingleBack(item) {
      const that = this;
      this.$axios
        .get("http://localhost:8080/Alipay/refund", {
          params: {
            id: item.id,
            price: item .price
          },
        })
        .then(function (response) {
          if (response.data.success == true) {
            that.$message({
              type: "success",
              message: "删除成功!",
            });
          } else {
            that.$message({
              type: "warning",
              message: "删除失败!",
            });
          }
        })
        .catch(function (error) {
          console.log(error);
        });
    },

    handlePay(id) {
      this.$axios
        .post("http://localhost:8080/Alipay/PayPage", {
          id: id,
        })
        .then((successResponse) => {
          document.write(successResponse.data);
          // document.querySelector('body').innerHTML = successResponse.data;//查找到当前页面的body，将后台返回的form替换掉他的内容
          // document.forms[0].submit();  //执行submit表单提交，让页面重定向，跳转到支付宝页面
          //console.log(successResponse);
        })
        .catch(function (error) {
          console.log(error);
        });
    },
  },
};
</script>

<style scoped>
.box-card {
  margin-bottom: 10px;
}

.el-card__header {
  background: #c0c4cc;
}

.item-film-img {
  float: left;
  width: 80px;
  height: 100px;
  padding-bottom: 10px;
  padding-right: 10px;
}

.item-film-name {
  letter-spacing: 2px;
  font-weight: 500;
  font-size: 18px;
  padding-bottom: 15px;
  padding-top: 1px;
}

.c-img {
  width: 100%;
  height: 100%;
}

.item-film-seat {
  letter-spacing: 1px;
  font-size: 12px;
  padding-bottom: 5px;
  padding-left: 10px;
  color: #91949c;
}

.item-film-time {
  letter-spacing: 1px;
  font-size: 12px;
  padding-left: 10px;
  padding-bottom: 5px;
  color: #91949c;
}
.item-film-price {
  letter-spacing: 1px;
  font-size: 12px;
  padding-left: 10px;
  padding-bottom: 5px;
  color: #91949c;
}

.o1 {
  float: right;
  line-height: 100px;
  padding-right: 50px;
}

.o2 {
  font-size: 20px;
  float: right;
  line-height: 100px;
  padding-right: 100px;
}

.o3 {
  margin-top: 30px;
  float: right;
  line-height: 20px;
  padding-right: 20px;
}
</style>