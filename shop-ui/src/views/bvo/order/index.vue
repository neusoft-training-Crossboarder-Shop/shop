<template>
    <div class="main_container">

      <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
        <el-form-item label="Transaction Id" prop="configName">
          <el-input
            v-model="queryParams.storeOrderId"
            placeholder="storeOrderId"
            clearable
            size="small"
            style="width: 240px"
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="创建时间">
          <el-date-picker
            v-model="dateRange"
            size="small"
            style="width: 240px"
            value-format="yyyy-MM-dd"
            type="daterange"
            range-separator="-"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
          ></el-date-picker>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>


      <el-table @row-click="redirect" class="el-table--enable-row-hover el-table__body"  :data="tableData" v-loading="loading" >
        <el-table-column label="Store Order Id" align="center" prop="stoId"   />
        <el-table-column label="Product Name" align="center" prop="product.title" :show-overflow-tooltip="true">
          <template slot-scope="scope">
             <el-button type="text" @click="redirect(scope.row.product.proId)">{{scope.row.product.proId}}</el-button>
          </template>
        </el-table-column>

        <el-table-column label="Price" align="center"   :show-overflow-tooltip="true">
          <template slot-scope="scope">
            <span v-if="scope.row.status<=1">{{scope.row.product.retailPrice}}</span>
            <span v-else>{{scope.row.purchasePrice}}</span>
          </template>
        </el-table-column>

        <el-table-column label="Store Name" align="center" prop="store.storeName" />


        <el-table-column
          label="Store Platform Type"
          align="center"
          prop="product.platformType"
          min-width="100px"
          :filters="platformType"
          :filter-method="filterPlatformType"
          >
          <template slot-scope="scope">
            <el-tag  :type=" (scope.row.store.platformType === '1')?'success':'primary'">
              {{platformTypeFormatter(scope.row)}}
            </el-tag>
          </template>

        </el-table-column>


        <el-table-column label="Pay Time" align="center" prop="paidTime" >
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.paidTime) }}</span>
          </template>
        </el-table-column>

        <el-table-column label="Create Time" align="center" prop="createTime" >
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.createTime) }}</span>
          </template>
        </el-table-column>

        <el-table-column label="last update time" align="center" prop="lastUpdateTime" >
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.lastUpdateTime) }}</span>
          </template>
        </el-table-column>

        <el-table-column
          align="center"
          prop="orderStatus"
          label="Status"
          :filters="status"
          :filter-method="filterStatus"
        >
          <template slot-scope="scope">
            <el-tag effect="dark" :type="getTypeTag(scope.row.orderStatus)">
              {{orderStatusFormatter(scope.row.orderStatus)}}
            </el-tag>
          </template>


        </el-table-column>


        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
              <el-button v-if="scope.row.orderStatus == 1" type="success" @click="setCardVisible">
                付款
              </el-button>

              <el-button v-if="scope.row.orderStatus == 4" type="success">
                确认送达
              </el-button>

             <el-button type="info" v-if="scope.row.orderStatus != 1 && scope.row.orderStatus != 4 ">buttonCont
                不可操作
             </el-button>

          </template>
        </el-table-column>
      </el-table>

      <pagination
        v-show="total>0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getList"
      />


    </div>
</template>

<script>
  import {getDicts} from "../../../api/system/dict/data";
  import {listOrders} from "../../../api/bvo/order";

  export default {
        name: "index.vue",

        data:function () {
          return{
            loading: true,
            total:0,
            queryParams:{
              storeOrderId:'',
              pageNum:1,
              pageSize:10
            },
            dateRange: [],
            tableData:[
              {
                stoId:'1',
                qty:'1',
                product:{
                  proId:'1',
                  title:'汉堡',
                  retailPrice:'180',
                },
                //str
                store:{
                  strId:'23',
                  storeName:'KFC',
                  platformType:1,
                },

                purchasePrice:1280,

                paidTime:'',
                createTime:'',
                lastUpdateTime:'',
                orderStatus:1,

              }
            ],
            platformType:[
              // {text:'Amazon',value:1}, {text:'Ebay',value:2}
            ],
            status:[
              // {text:'待支付',value:1}, {text:'待发货',value:2}, {text:'待送达',value:3}, {text:'已送达',value:4}, {text:'已完成',value:5}
            ],
          }
        },
      created(){
        this.getList()

        // 1 -申请 , 2 -完成 , -3-失败
        this.getDicts("order_status").then(response => {
          let data=response.data;
          data.forEach((item,index)=>{
            this.status[index]=
              {
                text:item.dictLabel,
                value:item.dictValue
              }
          })
        });
        this.getDicts("store_platform_type").then(response => {
          let data=response.data;
          data.forEach((item,index)=>{
            this.platformType[index]=
              {
                text:item.dictLabel,
                value:item.dictValue
              }
          })
        });

        // setTimeout(()=>{
        //   console.log(this.platformType)
        //   console.log(this.status)
        // },500)

      },

      methods:{
        redirect(id){
          this.$router.push({
            path:`/bvo/good/${id}`
          })
        },
        getList(){
          this.loading = true;

          // this.loading = true;
          // listOrders(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
          //     this.configList = response.rows;
          //     this.total = response.total;
          //     this.loading = false;
          //   }
          // );

          setTimeout(()=>{
            this.tableData=[
              {
                stoId:'1',
                qty:'1',
                product:{
                  proId:'1',
                  title:'汉堡',
                  retailPrice:'180',
                },
                //str
                store:{
                  strId:'23',
                  storeName:'KFC',
                  platformType:1,
                },

                purchasePrice:1280,

                paidTime:'',
                createTime:'',
                lastUpdateTime:'',
                orderStatus:1,

              }
            ],
            this.loading=false

          },500)
        },
        getTypeTag(status){
          let s = parseInt(status);
          if (s == 1){
            return  'danger'
          }else if (s == 2){
            return  'info'
          }else if (s == 3){
            return  'info'
          }else if (s == 4){
            return  'info'
          } else if (s == 5){
            return 'danger'
          }
        },

        redirect(row, event, column){
          this.$router.push({
            path:`/bvo/order/orderDetail/${row.stoId}`
          })
        },

        filterStatus(value,row){
            return row.orderStatus == value
        },
        filterPlatformType(value,row){
          return row.store.platformType == value
        },

        platformTypeFormatter(row,column){
          return this.platformType[parseInt(row.store.platformType)-1].text
        },

        orderStatusFormatter(status){
          return this.status[parseInt(status)-1].text
        },
      }
    }
</script>

<style scoped>
.main_container{
  margin: 2%;
  padding: 2%;
}
.el-table >>> .el-table__body tr:hover>td {
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
  cursor: pointer;
  background-color: #d9d9d9;
}
</style>

