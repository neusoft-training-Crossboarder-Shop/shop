<template>
  <div class="main_container">

    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="200px">
      <el-form-item label="Transaction Id" prop="configName">
        <el-input
          v-model="queryParams.stoId"
          placeholder="storeOrderId"
          clearable
          size="small"
          style="width: 240px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label=" Create time">
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


    <el-table @row-click="redirect" class="el-table--enable-row-hover el-table__body" :data="tableData"
              v-loading="loading">
      <el-table-column label="Store Order Id" align="center" prop="stoId"/>
      <el-table-column label="Product Name" align="center" prop="product.title" :show-overflow-tooltip="true">
        <template slot-scope="scope">
          <el-button type="text" @click.stop="redirectToProduct(scope.row.product.proId)">{{scope.row.product.title}}
          </el-button>
        </template>
      </el-table-column>

      <el-table-column label="Price" align="center" :show-overflow-tooltip="true">
        <template slot-scope="scope">
          <span v-if="scope.row.status<=1">{{scope.row.product.retailPrice}}</span>
          <span v-else>{{scope.row.purchasePrice}}</span>
        </template>
      </el-table-column>

      <el-table-column label="Store Name" align="center" prop="store.storeName"/>


      <el-table-column
        label="Store Platform Type"
        align="center"
        prop="product.platformType"
        min-width="100px"
        :filters="platformType"
        :filter-method="filterPlatformType"
      >
        <template slot-scope="scope">
          <el-tag :type=" (scope.row.store.platformType === '1')?'success':'primary'">
            {{platformTypeFormatter(scope.row)}}
          </el-tag>
        </template>

      </el-table-column>


      <el-table-column label="Create Time" align="center" prop="createTime">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>

      <el-table-column label="Pay Time" align="center" prop="paidTime">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.paidTime) }}</span>
        </template>
      </el-table-column>

      <el-table-column label="last update time" align="center" prop="lastUpdateTime">
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
          <el-tag effect="light" :type="getTypeTag(scope.row.orderStatus)">
            {{orderStatusFormatter(scope.row)}}
          </el-tag>
        </template>


      </el-table-column>


      <el-table-column label="Operation" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button v-if="scope.row.orderStatus == 1" type="success">
            付款
          </el-button>

          <el-button v-if="scope.row.orderStatus == 2" type="info">发货</el-button>
          <el-button v-if="scope.row.orderStatus == 3" type="info">送达</el-button>

          <el-button v-if="scope.row.orderStatus == 4" type="primary">
            Wait for Confirm
          </el-button>

          <el-button type="info" v-if="scope.row.orderStatus == 5 ">buttonCont
            Forbidden Operation
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
  import {parseTime} from "../../../utils/shop";
  import {getDicts} from "../../../api/system/dict/data";
  import {listOrders} from "../../../api/bvo/order";

  export default {
    name: "index.vue",

    data: function () {
      return {
        loading: true,
        total: 0,
        queryParams: {
          stoId: '',
          pageNum: 1,
          pageSize: 10
        },
        dateRange: [],
        tableData: [
          {
            stoId: '1',
            qty: '1',
            product: {
              proId: '1',
              title: '汉堡',
              retailPrice: '180',
            },
            //str
            store: {
              strId: '23',
              storeName: 'KFC',
              platformType: 1,
            },

            purchasePrice: 1280,

            paidTime: '',
            createTime: '',
            lastUpdateTime: '',
            orderStatus: 1,

          }
        ],
        platformType: [
          {text: 'Amazon', value: 1}, {text: 'Ebay', value: 2}
        ],
        status: [
          {text: 'Wait for Payment', value: 1}, {text: 'Wait for Pack out', value: 2}, {text: 'Wait for Deliver', value: 3}, {
            text: 'Deliver Success',
            value: 4
          }, {text: 'Alreadycomplete', value: 5}
        ],
      }
    },
    created() {
      // this.getDicts("order_status").then(response => {
      //
      //   let data=response.data;
      //   data.forEach((item,index)=>{
      //     this.status[index]=
      //       {
      //         text:item.dictLabel,
      //         value:item.dictValue
      //       }
      //   })
      // });
      // this.getDicts("store_platform_type").then(response => {
      //   let data=response.data;
      //   data.forEach((item,index)=>{
      //     this.platformType[index]=
      //       {
      //         text:item.dictLabel,
      //         value:item.dictValue
      //       }
      //   })
      // });
      // 1 -申请 , 2 -complete , -3-Fail
      this.getList()

    },

    methods: {
      redirectToProduct(id) {
        this.$router.push({
          path: `/bvo/good/${id}`
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
      getTypeTag(status) {
        let s = parseInt(status);
        if (s == 1) {
          return 'danger'
        } else if (s == 2) {
          return 'primary'
        } else if (s == 3) {
          return 'primary'
        } else if (s == 4) {
          return 'primary'
        } else if (s == 5) {
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

      redirect(row, event, column) {
        this.$router.push({
          path: `/bvo/order/orderDetail/${row.stoId}`
        })
      },

      filterStatus(value, row) {
        return row.orderStatus == value
      },
      filterPlatformType(value, row) {
        return row.store.platformType == value
      },

      platformTypeFormatter(row, column) {
        return this.platformType[parseInt(row.store.platformType)]['text']
      },

      orderStatusFormatter(row, column) {
        return this.status[parseInt(row.orderStatus) - 1]['text']
      },
    }
  }
</script>

<style scoped>
  .main_container {
    margin: 2%;
    padding: 2%;
  }

  .el-table >>> .el-table__body tr:hover > td {
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
    cursor: pointer;
    background-color: #d9d9d9;
  }
</style>

