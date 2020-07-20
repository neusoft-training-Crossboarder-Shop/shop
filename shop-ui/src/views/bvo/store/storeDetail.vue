<template>
  <div class="main_container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="100px">
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

    <el-table class="el-table--enable-row-hover el-table__body" :data="tableData"
              v-loading="loading">
      <el-table-column label="Dil Id" align="center" prop="dilId"/>
      <el-table-column label="Product Name" align="center" prop="title" :show-overflow-tooltip="true">
        <template slot-scope="scope">
          <el-button type="text" @click.stop="redirectToProduct(scope.row.proId)">{{scope.row.title}}
          </el-button>
        </template>
      </el-table-column>

      <el-table-column label="Sale Price" align="center" prop="salPrice" :show-overflow-tooltip="true"/>
      <el-table-column label="Drop Ship Price" align="center" prop="dropshipPrice" :show-overflow-tooltip="true"/>
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
        prop="dropshipStatus"
        label="Status"
        :filters="statusOptions"
        :filter-method="filterStatus"
      >
        <template slot-scope="scope">
          <el-tag effect="dark" :type="getTypeTag(scope.row.dropShipStatus)">
            {{dropShipStatusFormatter(scope.row)}}
          </el-tag>
        </template>
      </el-table-column>

      <el-table-column label="Operation" align="center" class-name="small-padding fixed-width" width="200px">
        <template slot-scope="scope">
          <el-button v-if="scope.row.dropshipStatus == 1" type="success" @click="OnOutShelf(scope.row.dilId,2)">下架</el-button>
          <el-button v-if="scope.row.dropshipStatus == 1" type="success" @click="modifyPage(scope.row)"> Modify  </el-button>
          <el-button v-else type="primary" @click="OnOutShelf(scope.row.dilId,1)">上架</el-button>
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


    <el-dialog title="OnShelf Modify" :visible.sync="dialogFormVisible" width="25%">
      <el-form :model="selectedRow" label-position="top">
        <el-form-item label="Sales Price" label-width="100px">
          <el-input v-model="selectedRow.salPrice" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="Shelf StockAmount" label-width="100px">
          <el-input v-model="selectedRow.shelfStockAmount" autocomplete="off"></el-input>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateOnShelfPriceAmount">确 定</el-button>
      </div>
    </el-dialog>


  </div>
</template>

<script>
  import {listDropshipItem,updateDropshipStatus,updateDropshipPriceAmount} from "../../../api/bvo/storeDetail";

  export default {
    name: "storeDetail",
    data: function () {
      return {
        storeId:undefined,
        loading: false,
        total: 0,
        dialogFormVisible:false,
        queryParams: {
          storeId:undefined,
          proId:undefined,
          pageNum: 1,
          pageSize: 10
        },
        selectedRow:{},
        dateRange: [],
        tableData: [
          {
            dilId:'',
            proId: '1',
            title: '汉堡',
            salPrice: 1280,
            dropshipPrice: 1000,
            storeRetentionAmount: 100,
            shelfStockAmount: 80,
            createTime: '',
            lastUpdateTime: '',
            dropshipStatus: 1,
          }
        ],
        statusOptions: [
          {text: '上架', value: 1}, {text: '下架', value: 2}
        ]
      }
    },
    created() {
      const storeId = this.$route.params && this.$route.params.storeId;
      this.storeId=storeId
      this.queryParams.storeId=storeId
      this.$notify({
        type: "success",
        message: `你当前正在访问的商店ID为${storeId}`
      });
      this.getList()
    },
    methods: {
      getList() {
        this.loading = true;
        listDropshipItem(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
            this.tableData = response.rows;
            this.total = response.total;
            this.loading = false;
          }
        );
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

      modifyPage(row){
        this.selectedRow=row
        this.dialogFormVisible=true
      },
      OnOutShelf(dilId,status){
          updateDropshipStatus(dilId,status).then(res=>{
            this.getList()
          })
      },
      updateOnShelfPriceAmount(){
        let data={
               dilId:this.selectedRow.dilId,
               salPrice:this.selectedRow.salPrice,
               shelfStockAmount:this.selectedRow.shelfStockAmount
            }
          updateDropshipPriceAmount(data).then(res=>{
            this.$notify({
              type:'success',
              title:'update successfully'
            })
            this.dialogFormVisible=false
            this.getList()
          })
      },
      redirectToProduct(id) {
        this.$router.push({
          path: `/bvo/good/${id}`
        })
      },
      filterStatus(value, row) {
        return row.dropshipStatus == value
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
        return this.statusOptions[parseInt(row.dropshipStatus) - 1]['text']
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
