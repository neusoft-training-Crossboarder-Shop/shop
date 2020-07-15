<template xmlns="http://www.w3.org/1999/html">

  <div style="margin: 2%">
    <div class="card">
      <el-row :gutter="10">
        <el-col :span="4">
          <div style="position: relative;left: 14%">
            <el-progress type="circle" :percentage="20"></el-progress>
          </div>
        </el-col>
        <el-col :span="20">
          <el-steps :active="active" process-status="wait" finish-status="success">
            <el-step class="clickable" @click.native="on_click(1)" title="Waiting For Pay"></el-step>
            <el-step class="clickable" @click.native="on_click(2)" title="Waiting For Dispatching"></el-step>
            <el-step class="clickable" @click.native="on_click(3)" title="Waiting For Arriving"></el-step>
            <el-step class="clickable" @click.native="on_click(4)" title="Waiting For Accepting"></el-step>
            <el-step class="clickable" @click.native="on_click(5)" title="Eventually Completed"></el-step>
          </el-steps>
        </el-col>
      </el-row>
      <el-row :gutter="10">
        <el-tabs tab-position="left" v-model="active" stretch style="width:100%;height:680px; overflow: auto"
                 @tab-click="handleTabClick">
          <el-tab-pane label="Waiting For Pay" name="1">
            <div>
              <div class="price-tag">
                <div v-if>
                  <el-form ref="form" :model="stoOrder">
                    <el-row :gutter="10">
                      <el-col :span="2">
                        <span>Quantity</span>
                      </el-col>
                      <el-col :span="4">
                        <el-input-number v-model="stoOrder.qty" :disabled="stoOrder.orderStatus != 1" :min="1" :max="10"
                                         size="mini" label="Quantity"></el-input-number>
                      </el-col>
                      <el-col :span="4">
                        <span>Store Name : {{stoOrder.store.storeName}}</span>
                      </el-col>
                      <el-col :span="4">
                        <span>Single Price :{{stoOrder.product.retailPrice}}</span>
                      </el-col>
                      <el-col :span="2">
                        <span>Total Price </span>
                      </el-col>
                      <el-col :span="4">
                        <span>{{stoOrder.qty*stoOrder.product.retailPrice}}</span>
                      </el-col>

                      <el-col v-if="stoOrder.orderStatus == 1" :span="2">
                        <el-button type="primary" @click="click_updateStoByStoId">Save</el-button>
                      </el-col>

                      <el-col :span="2">
                        <el-button v-if="stoOrder.orderStatus == 1" type="danger" @click="purchaseUi">Purchase
                        </el-button>
                        <el-button v-else type="info" :disabled="true">已支付</el-button>
                      </el-col>
                    </el-row>
                  </el-form>
                </div>

              </div>


              <el-divider><i class="el-icon-mobile-phone"></i></el-divider>
              <el-form style="margin: 3%" ref="addressForm" :model="shippingAddress" label-width="150px">
                <el-row :gutter="10">
                  <el-col :span="8">
                    <el-form-item label="Family Name">
                      <el-input v-model="shippingAddress.familyName"></el-input>
                    </el-form-item>

                  </el-col>
                  <el-col :span="8">
                    <el-form-item label="Given Name">
                      <el-input v-model="shippingAddress.givenName"></el-input>
                    </el-form-item>
                  </el-col>
                </el-row>

                <el-row :gutter="10">
                  <el-col :span="8">
                    <el-form-item label="Country Name">
                      <el-input v-model="shippingAddress.countryName"></el-input>
                    </el-form-item>

                  </el-col>
                  <el-col :span="8">
                    <el-form-item label="State/Province Name">
                      <el-input v-model="shippingAddress.stateOrProvinceName"></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8">
                    <el-form-item label="State/Province CD">
                      <el-input v-model="shippingAddress.stateOrProvinceCd"></el-input>
                    </el-form-item>
                  </el-col>
                </el-row>

                <el-row :gutter="10">
                  <el-col :span="8">
                    <el-form-item label="City Name">
                      <el-input v-model="shippingAddress.cityName" placeholder="placeholder"></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8">
                    <el-form-item label="Postal Cd">
                      <el-input v-model="shippingAddress.postalCd" placeholder="placeholder"></el-input>
                    </el-form-item>
                  </el-col>
                </el-row>

                <el-row :gutter="10">
                  <el-col :span="8">
                    <el-form-item label="Email">
                      <el-input v-model="shippingAddress.email" placeholder="placeholder"></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8">
                    <el-form-item label="Phone Number">
                      <el-input v-model="shippingAddress.contactPhoneNo" placeholder="placeholder"></el-input>
                    </el-form-item>
                  </el-col>
                </el-row>

                <el-row :gutter="10">
                  <el-col :span="18">
                    <el-form-item label="Detail Address">
                      <el-input type="textarea" v-model="shippingAddress.addressLine1"></el-input>
                    </el-form-item>
                  </el-col>
                </el-row>

                <el-row :gutter="10">
                  <el-col :span="9">
                    <el-form-item label="Carrier Name">
                      <el-input type="input" v-model="shippingAddress.carrierName"></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="9">
                    <el-form-item label="Freight Cost">
                      <el-input type="input" :disabled="true" v-model="shippingAddress.freightCost"></el-input>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row :gutter="10">

                  <el-col :span="12" style="horiz-align: center">
                    <el-form-item>
                      <el-button type="primary" v-if="stoOrder.orderStatus==1" @click="click_updateShippingAddress">
                        SAVE
                      </el-button>
                      <el-button type="primary" v-else :disabled="true">Forbidding SAVE</el-button>
                    </el-form-item>
                  </el-col>

                </el-row>

              </el-form>
            </div>
          </el-tab-pane>
          <el-tab-pane label="Waiting For Dispatching" name="2" :disabled="stoOrder.orderStatus<2">
            <h2>Waiting For The Manufacturer Dispatching</h2>
            <div class="main_content detail_info">
              <div>
                <h3 style=" font: italic 1em Georgia, serif">Your Commodity</h3>
                <div><span class="tag">Name </span> <span>{{salInfo.product.title}}</span></div>
                <div><span class="tag">Quantity</span> <span>{{salInfo.qty}}</span></div>
                <div><span class="tag">Purchase Price</span> <span class="price">${{salInfo.purchasePrice}}</span></div>
                <div><span class="tag">Total Price</span> <span class="price">${{salInfo.price}}</span></div>
                <div><span class="tag">Manufacturer Name</span><span
                  style="display: block;position: relative;left: 15%;top: -35px">{{salInfo.manufacturer.nameEn}}</span>
                </div>
                <div><span class="tag">description</span> <span
                  style="display: block;position: relative;left: 15%;top: -35px">{{salInfo.manufacturer.description}}</span>
                </div>
              </div>
            </div>

          </el-tab-pane>
          <el-tab-pane label="Waiting For Arriving" name="3" :disabled="stoOrder.orderStatus<3">
            <div>
              <h2>Your patch is on the road .... </h2>
              <div><span class="tag">Carrier Name </span> <span>{{shippingAddress.carrierName}}</span></div>
              <div><span class="tag">Tracking Number </span> <span>{{salInfo.trackingNo}}</span></div>
              <div>
                <el-timeline>
                  <el-timeline-item timestamp="2020/7/10 8:35" placement="top">
                    <el-card>
                      <h4>Arriving at Bei,Jing City</h4>
                      <p>The postman is delivering</p>
                    </el-card>
                  </el-timeline-item>
                  <el-timeline-item timestamp="2020/7/8 12:32" placement="top">
                    <el-card>
                      <h4>Arriving at Hu Nan Province</h4>
                      <p>About two days to arrive the destination</p>
                    </el-card>
                  </el-timeline-item>
                  <el-timeline-item timestamp="2020/7/6 17:16" placement="top">
                    <el-card>
                      <h4>Dispatching ...</h4>
                      <p>Start from Nan Jing， Jiang Su City </p>
                    </el-card>
                  </el-timeline-item>
                </el-timeline>
              </div>

            </div>
          </el-tab-pane>
          <el-tab-pane label="Waiting For Accepting" name="4" :disabled="stoOrder.orderStatus<4">
            <div>
              <el-card class="box-card" style="position: relative;left: 30%;top: 50%; width: 40%">
                <div slot="header" class="clearfix">
                  <span>Accepting</span>
                </div>
                <el-form ref="form" :model="form" style="padding: 3%">
                  <el-input v-model="password" show-password placeholder="Input Your wallet password"></el-input>
                  <el-button v-if="stoOrder.orderStatus==4"
                             style="display: inline-block;width: 100%;margin-top: 3%;margin-bottom: 3%" type="primary">
                    确认收货
                  </el-button>
                  <el-button v-else style="display: inline-block;width: 100%;margin-top: 3%;margin-bottom: 3%"
                             type="info">不可点击
                  </el-button>
                </el-form>
              </el-card>
            </div>
          </el-tab-pane>
          <el-tab-pane label="Eventually Completed" name="5" :disabled="stoOrder.orderStatus<5">

            <el-card class="box-card" style="position: relative;left: 30%;top: 50%; width: 40%">
              <div slot="header" class="clearfix">
                <span>Purchasing Completed </span>
              </div>
              <el-form ref="form" :model="form" style="padding: 3%">
                <div class="block" style="position: relative ;text-align: center;margin-top: 3%;margin-bottom: 3%">
                  <span class="demonstration" style="text-align: center">Rate</span>
                  <el-rate
                    v-model="rate"
                    :colors="colors">
                  </el-rate>
                </div>
                <el-button @click="redirectToBrowse"
                           style="display: inline-block;width: 100%;margin-top: 3%;margin-bottom: 3%;position: relative;left: 3%"
                           type="primary">Continue To Browse
                </el-button>
                <el-button @click="redirectToMyStore"
                           style="display: inline-block;width: 100%;margin-top: 3%;margin-bottom: 3%;" type="danger">My
                  Stores
                </el-button>
              </el-form>
            </el-card>

          </el-tab-pane>
        </el-tabs>
      </el-row>
    </div>
  </div>
</template>
<script>
  import {
    getStoByStoId,
    updateStoByStoId,
    payStoBySto,
    getShippingAddressByStoId,
    insertShippingAddress,
    updateShippingAddress
  } from "../../../api/bvo/order";

  export default {
    name: "orderDetail.vue",
    data: function () {
      return {
        active: 2,
        status: 2,
        stoOrder: {
          stoId: '',
          qty: '1',
          product: {
            proId: '1',
            title: '汉堡',
            retailPrice: '180',
          },
          store: {
            strId: '23',
            storeName: 'KFC',
            platformType: 1,
          },
          purchasePrice: 1280,
          paidTime: '',
          createTime: '',
          lastUpdateTime: '',
          orderStatus: 5,
        },
        purchaseVisible: false,
        shippingAddress: {
          stoId: undefined,
          familyName: '',
          givenName: '',
          countryName: '',
          stateOrProvinceName: '',
          stateOrProvinceCd: '',
          cityName: '',
          postalCd: '',
          email: '',
          contactPhoneNo: '',
          addressLine1: '',
          freightCost: 5,
          carrierName: '顺丰快递',
        },
        password: '',
        salInfo: {
          qty: 13,
          product: {
            proId: '1',
            title: '汉堡',
            retailPrice: 180,
          },
          manufacturer: {
            nameEn: '江南皮革厂',
            description: '江南皮革厂好鞋造江南皮革厂造好鞋江南皮革厂造好鞋江南皮革厂造好鞋江南皮革厂造好鞋',
          },
          //总价格
          price: 12.12,
          purchasePrice: 12.12,
          trackingNo: 'BE661664243JP',
          rate: 3,
          colors: ['#99A9BF', '#F7BA2A', '#FF9900']  // 等同于 { 2: '#99A9BF', 4: { value: '#F7BA2A', excluded: true }, 5: '#FF9900' }
        }
      }
    },
    created() {
      const stoId = this.$route.params && this.$route.params.orderId;
      // this.getType(dictId);
      // this.getTypeList();
      // this.getDicts("sys_normal_disable").then(response => {
      //   this.statusOptions = response.data;
      // });

      this.$notify({
        type: "success",
        message: `你当前正在访问的订单详情为${stoId}`
      })

      getStoByStoId(stoId).then(response => {
        this.stoOrder = response.data;
      })

      getShippingAddressByStoId(stoId).then(response => {
        this.shippingAddress = response.data;
      })

      on_click(this.stoOrder.orderStatus)

    },
    methods: {
      on_click(e) {
        if (e > this.stoOrder.orderStatus) {
          this.$notify({
            type: 'info',
            message: 'You cannot click'
          })
        } else {
          this.active = '' + e
        }
      },
      click_updateStoByStoId() {
        console.log("click_updateStoByStoId")

        updateStoByStoId({
          stoId: this.stoOrder.stoId,
          qty: this.stoOrder.qty
        }).then(response => {
          getStoByStoId(this.stoOrder.stoId).then(response => {
            this.stoOrder = response.data;
          })
        })

      },
      purchaseUi() {
        this.$prompt('Please Enter Wallet Password ', `Total Price ${this.stoOrder.qty * this.stoOrder.product.retailPrice + this.shippingAddress.freightCost}`, {
          confirmButtonText: 'Yes',
          cancelButtonText: 'Cancel',
          inputPattern: /.+/,
          inputErrorMessage: 'Password Cannot be Empty'
        }).then(({value}) => {
          let data = {
            password: value,
            stoId: this.stoOrder.stoId,
            freightCost: this.shippingAddress.freightCost || 5
          }
          payStoBySto(data).then(response => {

            getStoByStoId(this.stoOrder.stoId).then(response => {
              this.stoOrder = response.data;
            })
            this.$notify(
              {
                type: "success",
                message: "支付成功"
              }
            )

          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '取消输入'
          });

        });

      },
      click_updateShippingAddress() {
        console.log("click_updateShippingAddress")
        if (this.shippingAddress.stoId) {
          updateShippingAddress(this.shippingAddress).then((response) => {
          })
        } else {
          insertShippingAddress(this.shippingAddress).then((response) => {
          })
        }
      },
      redirectToBrowse() {
        this.$router.push({
          name: "Browse"
        })
      },
      redirectToMyStore() {
        this.$router.push({
          name: "Store"
        })
      },
      handleTabClick(tab, event) {
        console.log(tab)
        console.log(event)
        this.$notify({
          type: 'info',
          message: `You cannot click ${tab.name}`
        })

      }
    }
  }
</script>

<style scoped>
  .clickable {
    cursor: pointer;
  }

  .card {
    padding: 2%;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
  }

  .price-tag {
    margin: 3%;
  }

  .tag {
    display: inline-block;
    width: 15%;
    padding: 2%;
    font: italic 1em Georgia, serif;

  }

  .price {
    font-weight: bold;
    letter-spacing: 2px;
    font-size: large;
    color: #ed5565;
  }


  .detail_info {
    margin: 5%;
    font-size: large;
  }

  .main_content {
    text-decoration: none;
  }

  .el-tabs__item.is-disabled {
    color: #303133;
    cursor: default
  }

  .el-tabs__item.is-active {
    color: #409EFF;
  }

</style>
