<template>
  <div class="seat-main">
    <el-steps style="padding-bottom: 50px" :active="2" align-center>
      <el-step title="步骤1" description="选择电影场次"></el-step>
      <el-step title="步骤2" description="选择座位号"></el-step>
      <!-- <el-step title="步骤3" description="加入购物车"></el-step> -->
      <el-step title="步骤4" description="支付"></el-step>
    </el-steps>
    <div class="seat-content">
      <div class="seat-aside">
        <div style="display: flex">
          <img style="padding: 30px" :src="arrangement.cover" alt="" />
          <div>
            <div
              style="
                padding-top: 40px;
                padding-bottom: 10px;
                font-size: 25px;
                letter-spacing: 2px;
              "
            >
              {{ arrangement.name }}
            </div>
            <div class="seat-aside-text">类型：{{ arrangement.type }}</div>
            <div class="seat-aside-text">地区：{{ arrangement.region }}</div>
            <div class="seat-aside-text">
              时长：{{ arrangement.duration }}分钟
            </div>
          </div>
        </div>
        <div style="padding: 5px 30px">
          <div class="d1"><span>放映：</span>{{ arrangement.type }}</div>
          <div class="d1" style="color: #f56c6c">
            <span>开场：</span>{{ arrangement.date }}
            {{ arrangement.start_time }}
          </div>
          <div class="d1" style="color: #f56c6c">
            <span>散场：</span>{{ arrangement.date }} {{ arrangement.end_time }}
          </div>
          <div class="d1"><span>票价：</span>¥{{ arrangement.price }}/张</div>
          <el-divider></el-divider>
          <div class="d1">
            已选座位：
            <el-tag
              v-for="(item, i) in userSelectSeats"
              :key="i"
              type="danger"
              style="margin-right: 5px"
              effect="plain"
            >
              {{ item }} 号
            </el-tag>
          </div>
          <div class="d1" style="padding-top: 10px">
            总计：
            <span style="color: #f56c6c">¥ </span>
            <span style="color: #f56c6c; font-size: 25px; font-weight: bold">{{
              cart.price
            }}</span>
          </div>
          <el-divider></el-divider>
          <div style="padding: 0 50px">
            <el-button
              @click="submitSeat"
              class="add-cart-btn"
              type="danger"
              round
              >支付
            </el-button>
          </div>
        </div>
      </div>

      <div class="hall seat-select">
        <div style="padding-left: 30px" class="seat-example">
          <div class="selectable-example example">
            <span>可选座位</span>
          </div>
          <div class="sold-example example">
            <span>已售座位</span>
          </div>
          <div class="selected-example example">
            <span>已选座位</span>
          </div>
        </div>

        <div class="seats-block">
          <div class="seats-container">
            <div class="screen-container" style="left: 5px">
              <div class="screen">银幕位置</div>
              <div class="c-screen-line"></div>
            </div>
            <div class="seats-wrapper">
              <div style="padding: 0 40px; width: 500px">
                <span v-for="(item, index) in seats" :key="index">
                  <!--不可选-->
                  <span v-if="item.status === 0" class="seat sold item" />
                  <!--可选-->
                  <span
                    v-if="item.status === 1"
                    @click="handleSelect(index)"
                    class="seat selectable item"
                  />
                  <!--已选-->
                  <span
                    v-if="item.status === 2"
                    @click="handleDisSelect(index)"
                    class="seat selected item"
                  />
                </span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      cart: {
        id: "",
        uid: sessionStorage.getItem("uid"),
        aid: this.$route.query.id,
        order_name: "",
        seats: "",
        price: "",
      },
      arrangement: {},
      seats: [],
      userSelectSeats: [],
      selectSeats: [],
    };
  },
  created() {
    const that = this;
    this.$axios
      .get("http://localhost:8080/arrangement/selectById", {
        params: {
          id: this.$route.query.id,
        },
      })
      .then(function (response) {
        that.arrangement = Object.assign(response.data);
        console.log(that.arrangement);
        that.cart.order_name = response.data.name;
      })
      .catch(function (error) {
        console.log(error);
      });
    setTimeout(() => {
      this.$axios
        .get("http://localhost:8080/order/selectByAid", {
          params: {
            aid: this.$route.query.id,
          },
        })
        .then(function (response) {
          that.selectSeats = response.data;
          that.loadSeats();
        })
        .catch(function (error) {
          console.log(error);
        });
    }, 100);
  },
  methods: {
    //0 已选
    //1 可选
    //2 已选中
    loadSeats() {
      const n = this.arrangement.seat_number;
      //console.log(this.arrangement.seat_number);
      let arr = new Array(n);
      for (let i = 0; i < n; i++) {
        if (this.selectSeats.indexOf(i + 1) === -1) {
          arr[i] = { seat: i + 1, status: 1 };
        } else {
          arr[i] = { seat: i + 1, status: 0 };
        }
      }
      this.seats = arr;
      //console.log(this.seats);
    },

    handleSelect(index) {
      this.userSelectSeats.push(index + 1);
      this.seats[index].status = 2;
      this.cart.price = this.arrangement.price * this.userSelectSeats.length;
    },

    handleDisSelect(index) {
      this.seats[index].status = 1;
      this.userSelectSeats.splice(this.userSelectSeats.indexOf(index + 1), 1);
      this.cart.price = this.arrangement.price * this.userSelectSeats.length;
    },
    submitSeat() {
      //const that = this;
      this.cart.id = this.randomID();
      for (let i = 0; i < this.userSelectSeats.length; i++) {
        this.cart.seats += this.userSelectSeats[i] + "号";
      }

      this.$axios
        .post("http://localhost:8080/Alipay/PayPage", {
          id: this.cart.id,
          uid: this.cart.uid,
          aid: this.cart.aid,
          order_name: this.cart.order_name,
          seats: this.cart.seats,
          price: this.cart.price.toString(),
        })
        .then((successResponse) => {
          document.write(successResponse.data);
          // document.querySelector('body').innerHTML = successResponse.data;//查找到当前页面的body，将后台返回的form替换掉他的内容
          // document.forms[0].submit();  //执行submit表单提交，让页面重定向，跳转到支付宝页面
          console.log(successResponse);
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    randomID() {
      return Number(
        Math.random().toString().substr(2, 0) + Date.now()
      ).toString(36);
    },
  },
};
</script>

<style scoped>
@import "../../assets/css/seat.css";

.seat-main {
  padding: 80px;
}

.seat-content {
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
  height: 850px;
}

.seat-aside {
  width: 30%;
  height: 100%;
  float: left;
  background: #f5f6f7;
}

.seat-aside img {
  width: 140px;
  height: 200px;
}

.seat-select {
  padding-top: 50px;
  padding-left: 8%;
}

.seat-aside-text {
  padding-top: 8px;
  font-size: 13px;
  letter-spacing: 1px;
  color: #666666;
}

.d1 {
  font-size: 16px;
  letter-spacing: 2px;
  padding-bottom: 15px;
}

.d1 span {
  color: #91949c;
}

.add-cart-btn {
  width: 100%;
  height: 60px;
  border-radius: 50px;
}

.item {
  padding-bottom: 20px;
}

.row-id {
  padding-bottom: 11px;
}

.el-input__inner {
  border-radius: 50px;
  height: 55px;
}
</style>