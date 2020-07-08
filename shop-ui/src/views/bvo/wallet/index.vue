<template>
  <div >
    <div v-if="notShowTransaction" class="profile_container" >
    <el-card class="box-card" v-if="showFrom">
      <div slot="header" class="clearfix">
        <span style="font-weight: bold;font-size: large;margin-right: 2%">Wallet Account</span>
        <span v-if="!notRegistered" class="foot">注册日期:{{parseTime(account.createTime)}}  </span>
        <span v-if="!notRegistered" class="foot">注册人员:{{account.createdBy}}</span>
      </div>
      <div>
        <el-form  ref="form" :rules="rules" :model="account" label-width="20%">
            <el-form-item label="Account Name" prop="accountName">
              <el-input v-model="account.accountName" @focus="changeEdit"  placeholder="Please Enter Account Name" ></el-input>
            </el-form-item>
            <el-form-item label="Account Type" prop="currency">
              <el-radio-group v-model="account.currency">
                <el-radio label="USB"  >$ USB</el-radio>
                <el-radio label="RMB">￥ RMB</el-radio>
              </el-radio-group>
            </el-form-item>

            <el-form-item label="Email"  prop="email">
              <el-input v-model="account.email" @focus="changeEdit"  placeholder="Please Enter Account Name" ></el-input>
            </el-form-item>

            <el-form-item label="Password" prop="password">
              <el-input placeholder="Enter Password" v-model="account.password" show-password></el-input>
            </el-form-item>
          </el-form>
        <template v-if="notRegistered">
          <el-button  class="button-style" type="primary"   @click="register('form')">注册</el-button>
        </template>
        <template v-else >
          <el-button class="button-style"  type="primary" :disabled="!isEdit" @click="save">保存</el-button>
          <el-button class="button-style" style="position: relative;left: -10px"   type="danger"  @click="redirect">我的交易</el-button>
        </template>
      </div>
    </el-card>

    <el-card v-else class="box-card-password">
      <div slot="header" class="clearfix">
        <span>Input Password </span>
      </div>
      <div>
        <el-form ref="login" :rules="loginRule"  :model="login" label-width="80px">
          <el-form-item prop="password" label="Input Password " label-width="20%">
            <el-input v-model="login.password" placeholder="Password" show-password></el-input>
          </el-form-item>
        </el-form>
        <el-button  class="button-style" type="primary" @click="walletLogin('login')">LogIn</el-button>
      </div>
    </el-card>
    </div>
    <div v-else>
      <el-card class="account_fount">
        <div slot="header" class="clearfix">
          <span>Account Fund</span>
        </div>
        <div>
            <el-row :gutter="10" >
             <el-col :span="4" style=" font: italic 1em Georgia, serif; vertical-align: center">Avaliable Money</el-col>
              <el-col :span="4" > <span class="price">$1234.123</span></el-col>
             <el-col :span="4" style="text-align: center; font: italic 1em Georgia, serif; vertical-align: center">Depositing Money</el-col>
             <el-col :span="4" > <span class="price">$213.1</span></el-col>
              <el-col :span="8">
                <el-row :gutter="10" style="vertical-align: center;horiz-align: center">
                  <el-button type="danger">deposite</el-button><el-button type="primary">refresh</el-button>
                </el-row>
              </el-col>
            </el-row>
        </div>
      </el-card>


    <div class="Query-From">

      <el-collapse>
        <el-collapse-item title="Filter Condition" style="margin-left: 3%;margin-right:3%">



        <el-form :model="queryParams" ref="queryForm"  label-width="30%">
          <el-row :gutter="10">
           <el-col :span="10">
                <el-form-item label-width="28%" label="Transaction ID" >
                  <el-input
                    v-model="queryParams.transactionId"
                    placeholder="Please Enter Transaction Id"
                    clearable
                    size="small"
                    style="display: inline-block;width: 90%"
                    @keyup.enter.native="handleQuery"
                  />
                </el-form-item>
           </el-col>
            <el-col :span="10">
              <el-form-item label="Bank Card ID" label-width="30%">
                <el-input

                  v-model="queryParams.bankCardId"
                  placeholder="Please Enter Bank Card Id "
                  clearable
                  size="small"
                  style="display: inline-block;width: 90%"
                  @keyup.enter.native="handleQuery"
                />
              </el-form-item>
            </el-col>
        </el-row>

          <el-row :gutter="10">
           <el-col :span="7">
                <el-form-item label-width="40%" label="Transaction Type">
                    <el-select v-model="queryParams.transactionType" clearable placeholder="请选择">
                      <el-option
                        v-for="(item,index) in this.walletTransactionType"
                        :key="index"
                        :label="item"
                        :value="(index+1)">
                      </el-option>
                    </el-select>
                </el-form-item>
           </el-col>
              <el-col :span="8">
                <el-form-item label-width="50%" label="Transaction  Status">
                    <el-select v-model="queryParams.walletTransactionStatus" clearable placeholder="请选择">
                      <el-option
                        v-for="(item,index) in this.walletTransactionStatus"
                        :key="index"
                        :label="item"
                        :value="(index+1)">
                      </el-option>
                    </el-select>
                </el-form-item>
              </el-col>
            <el-col :span="7">
              <el-form-item label-width="40%" label="Finance Type">
                  <el-select v-model="queryParams.financeType" clearable placeholder="请选择">
                    <el-option
                      v-for="(item,index) in this.walletFinanceType"
                      :key="index"
                      :label="item"
                      :value="(index+1)">
                    </el-option>
                  </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row :gutter="10">
            <el-col :span="8">
            <el-form-item label="Time Area" label-width="33%">
              <el-date-picker
                v-model="dateRange"
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
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        </el-collapse-item>
      </el-collapse>
    </div>
    <el-divider><i class="el-icon-s-marketing"></i></el-divider>
      <div class="transaction">
<!--        v-loading="loading"-->
        <el-table  :data="record">

          <el-table-column type="expand">
            <template slot-scope="props">
              <el-form label-position="left" inline class="table-expand">
                <el-form-item label="Balance">
                  <span>{{ props.row.balance }}</span>
                </el-form-item>
                <el-form-item label="Business Id">
                  <span>{{ props.row.shop }}</span>
                </el-form-item>
                <el-form-item label="Create By">
                  <span>{{ props.row.id }}</span>
                </el-form-item>
              </el-form>
            </template>
          </el-table-column>

          <el-table-column label="TransactionId" align="center" prop="transactionId" />

          <el-table-column label="BankCardId" align="center" prop="bankCardId" :show-overflow-tooltip="true" />

          <el-table-column label="TransactionMoney" align="center" prop="transactionMoney" :show-overflow-tooltip="true" />

          <el-table-column label="TransactionType" align="center" prop="transactionType" :formatter="transactionTypeFormatter" />

          <el-table-column label="FinanceType" align="center" prop="financeType" :formatter="financeTypeFormatter" />

          <el-table-column label="Status" align="center" prop="status" :show-overflow-tooltip="true"  :formatter="transactionStatusFormatter" />

          <el-table-column label="CreateTime" align="center" prop="createTime" width="180">
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.createTime) }}</span>
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

  </div>

</template>

<script>
  // import {getBasicInfo,addDropShipper,updateDropShipper} from "../../../api/bvo/profile";
  import {getBasicInfo,addWalletAccount} from "../../../api/bvo/wallet";
  import {getDicts} from "../../../api/system/dict/data";

  export default {
    name: "index.vue",
    data:function () {
      return {
        notRegistered: true,
        notInputPassword:true,
        notShowTransaction:false,
        isEdit: false,




        account: {
          buyerId: null,
          accountName: null,
          email: null,
          password: null,
          currency: 'USB',
          createTime: null,
          createdBy: null,
        },
        rules: {
          password: [
            {required: true, message: 'Password', trigger: 'blur'},
            {min: 6, max: 15, message: 'Length should between 3 to 5 char', trigger: 'blur'}
          ],
          accountName: [
            {required: true, message: 'Account Name Cannot be null', trigger: 'blur'},
          ],
          email: [
            {required: true, message: 'Email Cannot be null', trigger: 'blur'},
          ],
          currency: [
            {required: true, message: 'Currency type cannot be null', trigger: 'blur'},
          ],
        },
        login:{
          password:'',
        },
        loginRule:{
          password: [
            {required: true, message: 'Password cannnot be empty ', trigger: 'blur'}
          ],
        },




        queryParams :{
          pageNum: 1,
          pageSize: 10,
          transactionId:'',
          bankCardId:'',
          status:'',
          transactionType:'',
          financeType: '',
          beginTime: '',
          endTime:'',
        },
        dateRange: [],

        //业务类型 1-充值 2-提现 3-消费 4-退款
        walletTransactionType:[],

        //1-入款 2-出款
        walletFinanceType:[],

        // 1 -申请 , 2 -完成 , -3-失败
        walletTransactionStatus:[],

        total:0,

        record:[
            {
              transactionId:'',
              bankCardId:'',
              transactionMoney:'',
              //
              transactionType:1,

              //
              financeType: 1,

              //
              status:1,

              createTime:'',

              //隐藏余额
              balance:'',
              businessId:'',
              createBy:'',
            }
          ]
      }
    },
    computed:{
      /**
       * @return {boolean}
       */
        showFrom(){

          return this.notRegistered || (!this.notInputPassword && !this.notRegistered)

      }
    },
    created() {
      // 1 -申请 , 2 -完成 , -3-失败
      console.log(1213)
      this.getDicts("wallet_transaction_status").then(response => {
        let data=response.data;
        console.log(response)
        data.forEach(item=>{
          this.walletTransactionStatus.push(item.dictLabel);
        })
      });
      //1-入款 2-出款
      this.getDicts("wallet_finance_type").then(response => {
        let data=response.data;
        data.forEach(item=>{
          this.walletFinanceType.push(item.dictLabel);
        })
      });
      //业务类型 1-充值 2-提现 3-消费 4-退款
      this.getDicts("wallet_transaction_type").then(response => {
        let data=response.data;
        data.forEach(item=>{
          this.walletTransactionType.push(item.dictLabel);
        })
      });

      // this.getList();
    },
    methods:{
      changeEdit(){
        this.isEdit = true;
      },
      getList(){
        // getBasicInfo(0).then(response=>{
        //   console.log(response)
          //注册成功了
          this.notRegistered =  false;
          // if (response.data.waaWalletAccount === null){
          //   this.notRegistered = true;
          // }else{
          //   console.log("有账号了")
          // }
            // this.buyer=response.data
            // this.notRegistered = false;
        // })
      },
      register(form){
           this.$refs[form].validate((valid) => {
             if (valid) {
               addWalletAccount(this.account).then(response=>{
                 //注册成功
                 this.notRegistered = false;
               })
             } else {
               console.log('error submit!!');
               return false;
             }
        });


        // 注册成功
        // addDropShipper(this.buyer).then(response=>{
        //   this.buyer=response.data;
        // });
      },

      walletLogin(form){
        this.$refs[form].validate((valid) => {
          if (valid) {
            // login(this.account).then(response=>{
              //注册成功
              this.notInputPassword = false;
            // })
          } else {
            return false;
          }
        });
      },
      save(){
        updateAccount(this.account).then(response=>{
           //更新成功
        })
      },
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.dateRange = [];
        this.resetForm("queryForm");
        this.handleQuery();
      },

      redirect(){
        // this.notShowTransaction = false;
      },
      transactionStatusFormatter(row,cloumn){
          return this.walletTransactionStatus[row.status]
      },
      financeTypeFormatter(row,cloumn){
        return this.walletFinanceType[row.financeType]
      },
      transactionTypeFormatter(row,cloumn){
        return this.walletTransactionType[row.transactionType]
      },
    }
  }
</script>

<style scoped>
  .box-card{
    padding: 2%;
    position: absolute;
    left: 20%;
    top: 20%;
    width: 50%;
    height: 60%;
  }
  .account_fount{
    margin: 1%;
    padding-left: 5%;
    padding-right: 5%;
  }
  .box-card-password{
    padding: 2%;
    position: absolute;
    left: 30%;
    top: 30%;
    width: 50%;
    height:35%;

  }
  .button-style{
    margin-top: 2%;
    margin-bottom: 1%;
    display: inline-block;
    width: 100%
  }
  .foot{
    font-size: small;
    color: #99a9bf;
    font-weight: lighter;
  }
.price{
  font-weight: bold;
  color: #ed5565;
  letter-spacing: 5px;
  font-size: x-large;
  text-decoration: none;
}

.Query-From{
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
  margin-left: 1%;
  margin-right: 1%;
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
.transaction{
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
  margin: 2%;
}

</style>
