<template>
  <div class="main_container">

    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="100px">
      <el-form-item label="Transaction Id" prop="configName" label-width="150px">
        <el-input
          v-model="queryParams.salId"
          placeholder="Sales Order Id"
          clearable
          size="small"
          style="width: 240px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label=" Create   time">
        <el-date-picker
          v-model="dateRange"
          size="small"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="Start  Time   "
          end-placeholder="End   Time   "
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">Search </el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">Reset  </el-button>
      </el-form-item>
    </el-form>


    <el-table @row-click="redirect" class="el-table--enable-row-hover el-table__body"  :data="tableData" v-loading="loading" >
      <el-table-column label="Sales Order Id" align="center" prop="salesOderId"   />
      <el-table-column label="Product Name" align="center" prop="product.title" :show-overflow-tooltip="true">
        <template slot-scope="scope">
          <el-button type="text" @click="redirect(scope.row.product.proId)">{{scope.row.product.title}}</el-button>
        </template>
      </el-table-column>

      <el-table-column label="Purchase Price" align="center"  prop="purchasePrice"  :show-overflow-tooltip="true">

      </el-table-column>

      <el-table-column label="Total Price" align="center"  prop="price"  :show-overflow-tooltip="true">

      </el-table-column>



      <el-table-column label="Store Name" align="center" prop="store.storeName" />


      <el-table-column
        label="Store Platform Type"
        align="center"
        prop="store.platformType"
        min-width="100px"
        :filters="platformType"
        :filter-method="filterPlatformType"
      >
        <template slot-scope="scope">
          <el-tag  :type=" (scope.row.store.platformType == '0')?'success':'primary'">
            {{platformTypeFormatter(scope.row)}}
          </el-tag>
        </template>

      </el-table-column>


      <el-table-column label="Pay Time" align="center" prop="paidTime" >
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
            {{orderStatusFormatter(scope.row)}}
          </el-tag>
        </template>


      </el-table-column>


      <el-table-column label="Operation" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">

          <el-button v-if="scope.row.orderStatus == 2" type="primary" @click.stop="update_status(scope.row.stoId,3)">发货</el-button>
          <el-button v-if="scope.row.orderStatus == 3"  type="primary" @click.stop="update_status(scope.row.stoId,4)">送达</el-button>

          <el-button v-if="scope.row.orderStatus == 4" type="info">
            Wait for Confirm
          </el-button>

          <el-button type="info" v-if="scope.row.orderStatus == 5 ">buttonCont
            不可Operation
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
  import {listOrders,updateOrderStatus} from "../../../api/mvo/order";

  export default {
    name: "index.vue",

    data:function () {
      return{
        loading: true,
        total:0,
        queryParams:{
          mvoId:undefined,
          salId:'',
          pageNum:1,
          pageSize:10,
          startTime:undefined,
          endTime:undefined
        },
        dateRange: [],

        tableData:[
          {
            salesOderId:'',
            stoId:'',
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
            createTime:'',
            lastUpdateTime:'',
            orderStatus:'',
            trackingNo:'BE661664243JP',
          }
        ],
        platformType:[
          {text:'Amazon',value:1}, {text:'Ebay',value:2}
        ],
        status:[
          {text:'待支付',value:1}, {text:'待发货',value:2}, {text:'待送达',value:3}, {text:'Already送达',value:4}, {text:'Alreadycomplete',value:5}
        ],
      }
    },
    created(){
      this.getDicts("order_status").then(response => {

      // 1 -申请 , 2 -complete , -3-Fail
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

      this.getList()


    },

    methods:{
      redirect(id){
        this.$router.push({
          path:`/bvo/good/${id}`
        })
      },
      getList(){
        this.loading = true;

        listOrders(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
            this.tableData = response.rows;
            this.total = response.total;
            this.loading = false;
          }
        );
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
      /** Search 按钮Operation */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      resetQuery() {
        this.dateRange = [];
        this.resetForm("queryForm");
        this.handleQuery();
      },
      resetForm(formName) {
        this.$notify({
          title: '执行',
          message: 'Reset  按钮',
          type: 'success'
        });
        this.$refs[formName].resetFields();
      },

      redirect(row, event, column){
        this.$router.push({
          path:`/mvo/order/orderDetail/${row.stoId}`
        })
      },
      update_status(stoId,status){
         let data={
           mvoId:undefined,
           stoId:stoId,
           status:status
         }
         updateOrderStatus(data).then(res=>{
           this.getList();
         })
      },
      filterStatus(value,row){
        return row.orderStatus == value
      },
      filterPlatformType(value,row){
        return (row.store.platformType+1) == value
      },

      platformTypeFormatter(row,column){
        return this.platformType[parseInt(row.store.platformType)]['text']
      },

      orderStatusFormatter(row,column){
        return this.status[parseInt(row.orderStatus)-1]['text']
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

