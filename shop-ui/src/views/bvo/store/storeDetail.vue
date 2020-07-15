<template>
  <div class="main_container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="Product ID" prop="configName">
        <el-input
          v-model="queryParams.proId"
          placeholder="Product ID"
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

    <el-table class="el-table--enable-row-hover el-table__body" :data="tableData"
              v-loading="loading">
      <el-table-column label="Product Id" align="center" prop="product.proId"/>
      <el-table-column label="Product Name" align="center" prop="product.title" :show-overflow-tooltip="true">
        <template slot-scope="scope">
          <el-button type="text" @click.stop="redirectToProduct(scope.row.product.proId)">{{scope.row.product.title}}
          </el-button>
        </template>
      </el-table-column>

      <el-table-column label="Sale Price" align="center" prop="salePrice" :show-overflow-tooltip="true"/>
      <el-table-column label="Drop Ship Price" align="center" prop="dropShipPrice" :show-overflow-tooltip="true"/>
      <el-table-column label="Store Retention Amount" align="center" prop="storeRetentionAmount" :show-overflow-tooltip="true"/>
      <el-table-column label="Shelf Stock Amount" align="center" prop="shelfStockAmount" :show-overflow-tooltip="true"/>

      <el-table-column label="Create Time" align="center" prop="createTime">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>

      <el-table-column label="last update time" align="center" prop="lastUpdateTime">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.lastUpdateTime) }}</span>
        </template>
      </el-table-column>

      <el-table-column
        align="center"
        prop="dropShipStatus"
        label="Status"
        :filters="status"
        :filter-method="filterStatus"
      >
        <template slot-scope="scope">
          <el-tag effect="dark" :type="getTypeTag(scope.row.dropShipStatus)">
            {{dropShipStatusFormatter(scope.row)}}
          </el-tag>
        </template>
      </el-table-column>

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button v-if="scope.row.dropShipStatus == 1" type="success">下架</el-button>
          <el-button v-if="scope.row.orderStatus == 2" type="primary">上架</el-button>
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
  import {listOrders} from "../../../api/bvo/order";

  export default {
    name: "storeDetail",
    data: function () {
      return {
        loading: false,
        total: 0,
        queryParams: {
          proId:undefined,
          pageNum: 1,
          pageSize: 10
        },
        dateRange: [],
        tableData: [
          {
            product: {
              proId: '1',
              title: '汉堡',
            },

            salePrice: 1280,
            dropShipPrice: 1000,
            storeRetentionAmount: 100,
            shelfStockAmount: 80,
            createTime: '',
            lastUpdateTime: '',
            dropShipStatus: 1,
          }
        ],
        status: [
          {text: '上架', value: 1}, {text: '下架', value: 2}
        ]
      }
    },
    created() {
      const storeId = this.$route.params && this.$route.params.storeId;
      // this.getType(dictId);
      // this.getTypeList();
      // this.getDicts("sys_normal_disable").then(response => {
      //   this.statusOptions = response.data;
      // });
      this.$notify({
        type: "success",
        message: `你当前正在访问的商店ID为${storeId}`
      });
      this.getList()
    },
    methods: {
      getList() {
        // this.loading = true;
        // listOrders(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
        //     this.tableData = response.rows;
        //     this.total = response.total;
        //     this.loading = false;
        //   }
        // );
      },
      /** 搜索按钮操作 */
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
          message: '重置按钮',
          type: 'success'
        });
        this.$refs[formName].resetFields();
      },
      redirectToProduct(id) {
        this.$router.push({
          path: `/bvo/good/${id}`
        })
      },
      filterStatus(value, row) {
        return row.dropShipStatus == value
      },
      getTypeTag(status) {
        let s = parseInt(status);
        if (s == 1) {
          return 'danger'
        } else {
          return 'info'
        }
      },
      dropShipStatusFormatter(row, column) {
        return this.status[parseInt(row.dropShipStatus) - 1]['text']
      }
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
