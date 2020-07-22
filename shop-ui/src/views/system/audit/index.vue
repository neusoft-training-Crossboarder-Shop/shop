<template>

 <div class="container">
      <el-form :model="queryParams" ref="queryForm"  label-width="30%">
        <el-row :gutter="10">
          <el-col :span="10">
            <el-form-item  label="Buyer Id" >
              <el-input
                v-model="queryParams.buyerId"
                placeholder="Enter Buyer Id"
                clearable
                size="small"
                style="display: inline-block;width: 90%"
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="Transaction Id" >
              <el-input

                v-model="queryParams.transactionId"
                placeholder="Enter Transaction Id"
                clearable
                size="small"
                style="display: inline-block;width: 90%"
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="10">
          <el-col :span="10">
            <el-form-item  label="Transaction Type">
              <el-select v-model="queryParams.operateType" clearable placeholder="Please Select  ">
                <el-option
                  v-for="(item,index) in this.operateType"
                  :key="index"
                  :label="item"
                  :value="(index+1)">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item  label="Transaction  Status">
              <el-select v-model="queryParams.status" clearable placeholder="Please Select  ">
                <el-option
                  v-for="(item,index) in this.status"
                  :key="index"
                  :label="item"
                  :value="(index+1)">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="10">
          <el-col :span="10">
            <el-form-item label="Time Area" >
              <el-date-picker
                v-model="dataRange"
                size="small"
                style="width: 240px"
                value-format="yyyy-MM-dd"
                type="daterange"
                range-separator="-"
                start-placeholder="Begin Time"
                end-placeholder="End Time"
              ></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="14">
            <el-form-item>
              <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">Search</el-button>
              <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">Reset</el-button>
<!--              v-hasPermi="['system:config:edit']"-->
              <el-button style="display: inline-block;width: 20%;float: right"
                  type="success"
                  icon="el-icon-edit"
                  size="big"
                  :disabled="multiple"
                  @click="handleAccept"
                >多项同意</el-button>

              <el-button style="display: inline-block;width: 20%;float: right"
                         type="success"
                         icon="el-icon-edit"
                         size="big"
                         :disabled="multiple"
                         @click="handleRefuse"
              >多项拒绝</el-button>
            </el-form-item>

          </el-col>
        </el-row>
      </el-form>
   <el-divider><i class="el-icon-s-marketing"></i></el-divider>
   <div class="transaction">
         <!--        v-loading="loading"-->
         <el-table  :data="record" v-loading="loading"  @selection-change="handleSelectionChange">
           <el-table-column type="selection" width="55" align="center" />

           <el-table-column type="expand">
             <template slot-scope="props">
               <el-form label-position="left" inline class="table-expand">
                 <el-form-item label="Operate By">
                   <span>{{ props.row.operateBy }}</span>
                 </el-form-item>
                 <el-form-item label="Create By">
                   <span>{{ props.row.createBy}}</span>
                 </el-form-item>
                 <el-form-item label="Create Time">
                   <span>{{ props.row.createTime }}</span>
                 </el-form-item>
                 <el-form-item label="Last Update By">
                   <span>{{ props.row.lastUpdateBy }}</span>
                 </el-form-item>
                 <el-form-item label="Available Money Before">
                   <span>{{ props.row.availableMoneyBefore }}</span>
                 </el-form-item>
                 <el-form-item label="Depositing Money Before">
                   <span>{{ props.row.depositingMoneyBefore }}</span>
                 </el-form-item>
                 <el-form-item label="Withdrawing Money Before">
                   <span>{{ props.row.withdrawingMoneyBefore }}</span>
                 </el-form-item>
                 <el-form-item label="Available Money After">
                   <span>{{ props.row.withdrawingMoneyBefore }}</span>
                 </el-form-item>
                 <el-form-item label="Depositing Money After">
                   <span>{{ props.row.withdrawingMoneyBefore }}</span>
                 </el-form-item>
                 <el-form-item label="Withdrawing MoneyAfter">
                   <span>{{ props.row.withdrawingMoneyAfter }}</span>
                 </el-form-item>
               </el-form>
             </template>
           </el-table-column>

           <el-table-column label="Transaction Audit Id" align="center" prop="transactionAuditId" />

           <el-table-column label="Buyer Id" align="center" prop="buyerId" :show-overflow-tooltip="true" />

           <el-table-column label="Transaction Id" align="center" prop="transactionId" :show-overflow-tooltip="true" />

           <el-table-column label="Operate Money" align="center" prop="operateMoney"  />

           <el-table-column label="Operate Type" align="center" prop="operateType" :formatter="operateTypeFormatter" />

           <el-table-column label="Status" align="center" prop="status" :show-overflow-tooltip="true"  :formatter="transactionStatusFormatter" />

           <el-table-column label="Last Update Time" align="center" prop="lastUpdateTime" width="180">
             <template slot-scope="scope">
               <span>{{ parseTime(scope.row.lastUpdateTime)}}</span>
             </template>
           </el-table-column>

           <el-table-column label="Operation" align="center" width="200" class-name="small-padding fixed-width" >

             <template slot-scope="scope">
<!--               v-hasPermi="['system:config:edit']"-->
             <div v-if="scope.row.status == 1">
               <el-row :gutter="10" class="mb8">
                 <el-col :span="1.5">
                   <el-button
                     type="success"
                     :disabled="scope.row.status!=1"
                     @click="handleAccept(scope.row)"
                   >同意</el-button>
                 </el-col>
                   <el-col :span="1.5">
                     <el-button
                       type="success"
                       :disabled="scope.row.status!=1"
                       @click="handleRefuse(scope.row)"
                     >拒绝</el-button>
                   </el-col>
               </el-row>
             </div>
               <div v-else>
                    <el-button :disabled="true" type="info">Forbidden Operation</el-button>
               </div>
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



 </div>
</template>

<script>
  import {getDicts} from "../../../api/system/dict/data";
  import {acceptAuditRecord,refuseAuditRecord,listAuditRecord} from "../../../api/system/audit";

  export default {
        name: "index.vue",
      data:function () {
        return{
          total:0,
          queryParams: {
            pageNum: 1,
            pageSize: 10,
            buyerId: undefined,
            transactionId: undefined,
            operateType: undefined,
            status:'',
            beginTime:'',
            endTime:'',
          },
          ids:[],
          loading:false,
          multiple:true,
          record:[
            {
              transactionAuditId:'',
              buyerId:'',
              transactionId:'',
              operateMoney:'',
              operateType:'',
              status:'',
              lastUpdateTime:'',
              //折叠面板中的字段
              operateBy:'',
              createBy:'',
              createTime:'',
              lastUpdateBy:'',
              availableMoneyBefore:'',
              depositingMoneyBefore:'',
              withdrawingMoneyBefore:'',
              availableMoneyAfter:'',
              depositingMoneyAfter:'',
              withdrawingMoneyAfter:'',
            }
          ],
          dataRange:[],
          //业务Type  1-充值 2-提现 3-消费 4-退款
          operateType:[],
          status:[],

        }
      },
      created(){
        // 1 -申请 , 2 -complete , -3-Fail
          this.getDicts("wallet_transaction_status").then(response => {
            let data=response.data;
            data.forEach(item=>{
              this.status.push(item.dictLabel);
            })
           this.getList();
        })

        //业务Type  1-充值 2-提现 3-消费 4-退款
        this.getDicts("wallet_transaction_type").then(response => {
          let data=response.data;
          data.forEach(item=>{
            this.operateType.push(item.dictLabel);
          })
        });
      },
      methods:{
        getList() {
          this.loading = true;
          listAuditRecord(this.addDateRange(this.queryParams,this.dateRange)).then(response => {
              this.record = response.rows;
              this.total = response.total;
              this.loading = false;
            }
          );
        },

        handleQuery() {
          this.queryParams.pageNum = 1;
          this.getList();
        },

        handleRefuse(row) {
          const auditIds = row.transactionAuditId || this.ids;
          console.log(auditIds)
          let fatherThis=this;

          this.$confirm('是否 Confirm 拒绝"' + auditIds + '"的Data 项?', "警告", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          }).then(function() {
            refuseAuditRecord(auditIds).then(response=>{
              console.log("拒绝Success")
              fatherThis.getList()
            });
          }).then(() => {

          }).catch(function() {});

        },
        handleAccept(row) {
          const auditIds = row.transactionAuditId || this.ids;
          console.log(auditIds)
          let fatherThis=this;
          this.$confirm('是否 Confirm 同意"' + auditIds + '"的Data 项?', "警告", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          }).then(function() {
            acceptAuditRecord(auditIds).then(response=>{
              console.log("同意Success")
              fatherThis.getList()
            });


          }).then(() => {


          }).catch(function() {});

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
        handleSelectionChange(selection) {

          this.ids = selection.map(item => item.transactionAuditId)
          this.multiple = !selection.length
        },
        operateTypeFormatter(row,cloumn){
          return this.operateType[row.operateType-1]
        },
        transactionStatusFormatter(row,cloumn){
          return this.status[row.status-1]
        },
      },
    }
</script>

<style scoped>
.container{
  margin: 2%;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
  padding: 2%;
}
.table-expand {
  font-size: 0;
}
.table-expand label {
  width: 90px;
  color: #99a9bf;
}
.table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
</style>
