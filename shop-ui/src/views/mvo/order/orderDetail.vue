<template xmlns="http://www.w3.org/1999/html">

  <div style="margin: 2%">
    <div class="card">
      <el-row :gutter="10">
        <el-col :span="4">
          <div style="position: relative;left: 14%">
            <el-progress type="circle" :percentage="20" ></el-progress>
          </div>
        </el-col>
        <el-col :span="20">
          <el-steps  :active="active" process-status="wait"  finish-status="success" >
            <el-step class="clickable"  @click.native="on_click(1)"  title="Waiting For Pay"></el-step>
            <el-step class="clickable"  @click.native="on_click(2)" title="Waiting For Dispatching"></el-step>
            <el-step class="clickable" @click.native="on_click(3)" title="Waiting For Arriving"></el-step>
            <el-step class="clickable" @click.native="on_click(4)" title="Waiting For Accepting"></el-step>
            <el-step class="clickable" @click.native="on_click(5)" title="Eventually Completed"></el-step>
          </el-steps >
        </el-col>
      </el-row>
      <el-row :gutter="10">
        <el-tabs  tab-position="left" v-model="active"  stretch style="width:100%;height:680px; overflow: auto" @tab-click="handleTabClick" >
          <el-tab-pane  label="Waiting For Pay" name="1" >
            <div>
              <div class="price-tag">
                <div v-if>
                  <el-form ref="form" :model="stoOrder">
                    <el-row :gutter="10">
                      <el-col :span="2">
                        <span>Quantity</span>
                      </el-col>
                      <el-col :span="4">
                        <el-input-number v-model="stoOrder.qty" :disabled="true" :min="1" :max="10" size="mini" label="Quantity"></el-input-number>
                      </el-col>
                      <el-col :span="4">
                        <span>Store Name : {{stoOrder.store.storeName}}</span>
                      </el-col>
                      <el-col :span="4">
                        <span>Single Price :{{stoOrder.purchasePrice}}</span>
                      </el-col>
                      <el-col :span="2">
                        <span>Total Price </span>
                      </el-col>
                      <el-col :span="4">
                        <span>{{salInfo.price}}</span>
                      </el-col>


                      <el-col :span="2">
                        <el-button  type="info" :disabled="true">Already Pay</el-button>
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
                      <el-input v-model="shippingAddress.familyName" :disabled="true"></el-input>
                    </el-form-item>

                  </el-col>
                  <el-col :span="8">
                    <el-form-item label="Given Name">
                      <el-input v-model="shippingAddress.givenName" :disabled="true"></el-input>
                    </el-form-item>
                  </el-col>
                </el-row>

                <el-row :gutter="10">
                  <el-col :span="8">
                    <el-form-item label="Country Name">
                      <el-input v-model="shippingAddress.countryName" :disabled="true"></el-input>
                    </el-form-item>

                  </el-col>
                  <el-col :span="8">
                    <el-form-item label="State/Province Name">
                      <el-input v-model="shippingAddress.stateOrProvinceName" :disabled="true"></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8">
                    <el-form-item label="State/Province CD">
                      <el-input v-model="shippingAddress.stateOrProvinceCd" :disabled="true"></el-input>
                    </el-form-item>
                  </el-col>
                </el-row>

                <el-row :gutter="10">
                  <el-col :span="8">
                    <el-form-item label="City Name">
                      <el-input v-model="shippingAddress.cityName" placeholder="input" :disabled="true"></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8">
                    <el-form-item label="Postal Cd">
                      <el-input v-model="shippingAddress.postalCd" placeholder="input" :disabled="true"></el-input>
                    </el-form-item>
                  </el-col>
                </el-row>

                <el-row :gutter="10">
                  <el-col :span="8">
                    <el-form-item label="Email">
                      <el-input v-model="shippingAddress.email" placeholder="input" :disabled="true"> </el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8">
                    <el-form-item label="Phone Number">
                      <el-input v-model="shippingAddress.contactPhoneNo" placeholder="input" :disabled="true"></el-input>
                    </el-form-item>
                  </el-col>
                </el-row>

                <el-row :gutter="10">
                  <el-col :span="18">
                    <el-form-item label="Detail Address">
                      <el-input type="textarea" v-model="shippingAddress.addressLine1" :disabled="true"></el-input>
                    </el-form-item>
                  </el-col>
                </el-row>

                <el-row :gutter="10">
                  <el-col :span="9">
                    <el-form-item label="Carrier Name">
                      <el-input type="input" v-model="shippingAddress.carrierName" :disabled="true"></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="9">
                    <el-form-item label="Freight Cost">
                      <el-input type="input" v-model="shippingAddress.freightCost" :disabled="true"></el-input>
                    </el-form-item>
                  </el-col>
                </el-row>
              </el-form>
            </div>
          </el-tab-pane>
          <el-tab-pane label="Waiting For Dispatching" name="2" :disabled="stoOrder.orderStatus<2">
            <el-button type="primary" @click="update_status(stoOrder.stoId,3)" :disabled="stoOrder.orderStatus>2" style="display: inline-block;width: 20%;margin-left: 5%">发货</el-button>
            <div class="main_content detail_info">
              <div>
                <h3 style=" font: italic 1em Georgia, serif">Commodity</h3>
                <div><span class="tag">Name </span> <span>{{salInfo.product.title}}</span></div>
                <div><span class="tag">Quantity</span> <span>{{salInfo.qty}}</span></div>
                <div><span class="tag">Purchase Price</span> <span class="price">${{salInfo.purchasePrice}}</span></div>
                <div><span class="tag">Total Price</span> <span class="price">${{salInfo.price}}</span></div>
              </div>
            </div>

          </el-tab-pane>
          <el-tab-pane  label="Waiting For Arriving" name="3" :disabled="stoOrder.orderStatus<3">
            <div>
              <el-button type="primary" @click="update_status(stoOrder.stoId,3)" :disabled="stoOrder.orderStatus>3" style="display: inline-block;width: 20%;margin-left: 5%">送达</el-button>
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
                      <p>Start from Nan Jing， Jiang Su  City </p>
                    </el-card>
                  </el-timeline-item>
                </el-timeline>
              </div>

            </div>
          </el-tab-pane>
          <el-tab-pane  label="Waiting For Accepting" name="4" :disabled="stoOrder.orderStatus<4">
            <div>
              <el-card class="box-card" style="position: relative;left: 30%;top: 50%; width: 40%">
                <div slot="header" class="clearfix">
                  <span>Accepting</span>
                </div>
                <el-form ref="form" :model="form" style="padding: 3%">
                  <el-button style="display: inline-block;width: 100%;margin-top: 3%;margin-bottom: 3%" type="info">Wait for Confirm </el-button>
                </el-form>
              </el-card>
            </div>
          </el-tab-pane>
          <el-tab-pane  label="Eventually Completed" name="5" :disabled="stoOrder.orderStatus<5">

            <el-card class="box-card" style="position: relative;left: 30%;top: 50%; width: 40%">
              <div slot="header" class="clearfix">
                <span>Purchasing Completed </span>
              </div>
              <el-form ref="form" :model="form" style="padding: 3%">
                <div class="block" style="position: relative ;text-align: center;margin-top: 3%;margin-bottom: 3%" >
                  <span class="demonstration" style="text-align: center">Rate</span>
                  <el-rate
                    v-model="rate"
                    :colors="colors">
                  </el-rate>
                </div>
                <el-button style="display: inline-block;width: 100%;margin-top: 3%;margin-bottom: 3%;position: relative;left: 3%" type="primary">订单complete</el-button>
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
    updateShippingAddress, getSalByStoId
  } from "../../../api/bvo/order";

  import {updateOrderStatus} from "../../../api/mvo/order";

  export default {
    name: "orderDetail.vue",
    data:function () {
      return{
        active:2,
        status:2,
        stoOrder:{
          stoId:'',
          qty:'1',
          product:{
            proId:'1',
            title:'汉堡',
            retailPrice:'180',
          },
          store:{
            strId:'23',
            storeName:'KFC',
            platformType:1,
          },
          purchasePrice:1280,
          paidTime:'',
          createTime:'',
          lastUpdateTime:'',
          orderStatus:5 ,
        },
        purchaseVisible:false,
        shippingAddress:{
          stoId:undefined,
          familyName:'',
          givenName:'',
          countryName:'',
          stateOrProvinceName:'',
          stateOrProvinceCd:'',
          cityName:'',
          postalCd:'',
          email:'',
          contactPhoneNo:'',
          addressLine1:'',
          freightCost:5,
          carrierName:'顺丰快递',
        },
        salInfo:{
          qty:13,
          product:{
            proId:'1',
            title:'汉堡',
            retailPrice:180,
          },
          manufacturer:{
            nameEn:'江南皮革厂',
            description:'江南皮革厂好鞋造江南皮革厂造好鞋江南皮革厂造好鞋江南皮革厂造好鞋江南皮革厂造好鞋',
          },
          //总价格
          price:12.12,
          purchasePrice:12.12,
          trackingNo:'BE661664243JP',
          rate:3,
          colors: ['#99A9BF', '#F7BA2A', '#FF9900']  // 等同于 { 2: '#99A9BF', 4: { value: '#F7BA2A', excluded: true }, 5: '#FF9900' }
        }
      }
    },
    created() {
      this.$notify({
        type : "success",
        message:`你当前正在访问的订单详情为${stoId}`
      })
      const stoId = this.$route.params && this.$route.params.orderId;

      getStoByStoId(stoId).then(response=>{
        this.stoOrder = response.data;
        if (this.stoOrder.orderStatus >= 2) {
          getSalByStoId(this.stoOrder.stoId).then(response => {
            this.salInfo = response.data;
          });
        }
      })

      getShippingAddressByStoId(stoId).then(response=>{
        this.shippingAddress=response.data;
      })

      on_click(this.stoOrder.orderStatus)
    },
    methods:{
      update_status(stoId,status){
        let data={
          mvoId:undefined,
          stoId:stoId,
          status:status
        }
        updateOrderStatus(data).then(res=>{
          getStoByStoId(stoId).then(response=>{
            this.stoOrder = response.data;
          })
        })
      },
      on_click(e){
        if (e > this.stoOrder.orderStatus) {

        }else{
          this.active=''+e
        }
      },
      dispatch(){
          //发货
      },
      arrive(){
          //送达
      },

      handleTabClick(tab,event){

      }
    }
  }
</script>

<style scoped>
  .clickable{
    cursor: pointer;
  }
  .card{
    padding: 2%;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
  }

  .price-tag{
    margin: 3%;
  }
  .tag{
    display: inline-block;
    width: 15%;
    padding: 2%;
    font: italic 1em Georgia, serif;

  }

  .price{
    font-weight: bold;
    letter-spacing: 2px;
    font-size: large;
    color: #ed5565;
  }


  .detail_info{
    margin: 5%;
    font-size: large;
  }
  .main_content{
    text-decoration: none;
  }
  .el-tabs__item.is-disabled {
    color: #303133;
    cursor:default
  }
  .el-tabs__item.is-active {
    color: #409EFF;
  }

</style>
