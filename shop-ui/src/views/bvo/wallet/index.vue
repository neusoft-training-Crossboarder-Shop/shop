<template>
  <div >
    <div v-if="notShowTransaction" class="profile_container" >
      <el-card class="box-card" v-if="showFrom">
        <div slot="header" class="clearfix">
          <span style="font-weight: bold;font-size: large;margin-right: 2%">Wallet Account</span>
          <span v-if="!notRegistered" class="foot">Register Time   :{{parseTime(account.createTime)}}  </span>
          <span v-if="!notRegistered" class="foot">Register 人员:{{account.createdBy}}</span>
        </div>

        <div>
          <el-form  ref="form" :rules="rules" :model="account" label-width="30%">
              <el-form-item label="Account Name" prop="accountName">
                <el-input v-model="account.accountName" @focus="changeEdit"  placeholder="Please Enter Account Name" ></el-input>
              </el-form-item>
              <el-form-item label="Account Type" prop="currency" :disabled="!notRegistered">
                <el-radio-group v-model="account.currency">
                  <el-radio :label="2"  >$ USD</el-radio>
                  <el-radio :label="1" >￥ RMB</el-radio>
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
            <el-button  class="button-style" type="primary"   @click="register('form')">Register </el-button>
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
        <el-form ref="login" :rules="loginRule"  :model="loginAccount" label-width="80px">
          <el-form-item prop="accountName" label="Account Name" label-width="20%">
            <el-input v-model="loginAccount.accountName" placeholder="Account Name" show-password></el-input>
          </el-form-item>
          <el-form-item prop="password" label="Input Password " label-width="20%">
            <el-input v-model="loginAccount.password" placeholder="Password" show-password></el-input>
          </el-form-item>
        </el-form>
        <el-button  class="button-style" type="primary" @click="walletLogin('login')">LogIn</el-button>
      </div>
    </el-card>
    </div>
    <div v-else>
      <el-card class="account_fount">
        <div slot="header" class="clearfix">
          <span>Account Fund</span><span style="margin-left: 10%;font-size: small;color: #d9d9d9">Last Update Time :{{parseTime(walletFund.lastUpdateTime)}}</span>
        </div>
        <div>
            <el-row :gutter="10" >
             <el-col :span="4" style=" font: italic 1em Georgia, serif; vertical-align: center">Avaliable Money</el-col>
              <el-col :span="4" > <span class="price">{{currencyType}}{{walletFund.availableMoney}}</span></el-col>
             <el-col :span="4" style="text-align: center; font: italic 1em Georgia, serif; vertical-align: center">Depositing Money</el-col>
             <el-col :span="4" > <span class="price">{{currencyType}}{{walletFund.depositingMoney}}</span></el-col>
              <el-col :span="8">
                <el-row :gutter="10" style="vertical-align: center;horiz-align: center">
                  <el-button type="danger" @click="cardFormVisible=true">deposit</el-button><el-button type="primary" @click="refresh">refresh</el-button>
                </el-row>
              </el-col>
            </el-row>
        </div>
      </el-card>

      <el-card v-if="cardFormVisible" style="z-index: 10" class="box-card">
        <div slot="header" class="clearfix">
          <span>Depositing</span>
        </div>
        <div>
          <el-form ref="depositForm"  :rules="depositeWithdrawFormRule" :model="depositeWithdrawForm" label-width="15%">
              <el-form-item label="Amount" prop="operateMoney">
                 <el-input v-model="depositeWithdrawForm.operateMoney"  placeholder="Amount"></el-input>
              </el-form-item>
              <el-form-item label="Bank Card Id" prop="bankCardId">
                <el-input v-model="depositeWithdrawForm.bankCardId" placeholder="Bank Card Id"></el-input>
              </el-form-item>
            <el-row :gutter="10">
              <el-col :span="24">
                <el-button type="primary" style="display:block;width: 80%;margin-left: 10%" @click="cardFormVisible = false">Cancel</el-button>
              </el-col>
            </el-row>

            <el-row :gutter="10">
              <el-col :span="24">
                <el-button style="display: block;width: 80%;margin-left: 10%;margin-top: 2%" type="danger" @click="deposite('depositForm')">Deposite</el-button>
              </el-col>
            </el-row>
          </el-form>



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
                    <el-select v-model="queryParams.transactionType" clearable placeholder="Please Select  ">
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
                    <el-select v-model="queryParams.walletTransactionStatus" clearable placeholder="Please Select  ">
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
                  <el-select v-model="queryParams.financeType" clearable placeholder="Please Select  ">
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

        <el-table  :data="record" v-loading="loading">

          <el-table-column type="expand">
            <template slot-scope="props">
              <el-form label-position="left" inline class="table-expand">
                <el-form-item label="Balance">
                  <span>{{ props.row.balance }}</span>
                </el-form-item>
                <el-form-item label="BankCardId">
                  <span>{{ props.row.bankCardId }}</span>
                </el-form-item>
                <el-form-item label="Business Id">
                  <span>{{ props.row.businessId }}</span>
                </el-form-item>


              </el-form>
            </template>
          </el-table-column>

          <el-table-column label="TransactionId" align="center" prop="transactionId" />

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
  import {
    getWalletAccount,
    getWalletAccountFund,
    addWalletAccount,
    walletAccountLogin,
    updateWalletAccount,
    getWalletTransaction,
    depositAccount
  } from "../../../api/bvo/wallet";
  import {getDicts} from "../../../api/system/dict/data";

  export default {
    name: "index.vue",
    data:function () {
      return {
        notRegistered: true,
        notInputPassword:true,
        notShowTransaction:true,
        isEdit: false,
        account: {
          buyerId: null,
          accountName: null,
          email: null,
          password: null,
          currency: 2,
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
        loginAccount:{
          accountName:'',
          password:'',
        },
        loginRule:{
          password: [
            {required: true, message: 'Password cannnot be empty ', trigger: 'blur'}
          ],

        },



        loading:false,

        queryParams :{
          pageNum: 1,
          pageSize: 10,
          transactionId:undefined,
          bankCardId:undefined,
          status:undefined,
          transactionType:undefined,
          financeType: undefined,
          beginTime: undefined,
          endTime:undefined,
        },
        dateRange: [],
        walletFund:{
          availableMoney:'',
          depositingMoney:'',
          withdrawingMoney:'',
          lastUpdateTime:'',
          currency:1,
        },
        cardFormVisible:false,
        depositeWithdrawForm:{
            operateMoney: '',
            bankCardId:'',
        },
        depositeWithdrawFormRule:{
          operateMoney: [
            {required: true, message: 'Ammount cannot be empty ', trigger: 'blur'}
          ],
          bankCardId:[
            {required: true, message: 'Bank Card cannot be empty ', trigger: 'blur'},
            {min: 16, max: 20, message: 'invalid Bank Card Length', trigger: 'blur'}
          ]

        },

        //业务Type  1-充值 2-提现 3-消费 4-退款
        walletTransactionType:[],

        //1-入款 2-出款
        walletFinanceType:[],

        // 1 -申请 , 2 -complete , -3-Fail
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
              // createBy:'',
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
        },
        currencyType(){
          return (this.walletFund.currency == 1)?'$':'￥'
        }
    },

    created() {
      // 1 -申请 , 2 -complete , -3-Fail
      this.getDicts("wallet_transaction_status").then(response => {
        let data=response.data;
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
      //业务Type  1-充值 2-提现 3-消费 4-退款
      this.getDicts("wallet_transaction_type").then(response => {
        let data=response.data;
        data.forEach(item=>{
          this.walletTransactionType.push(item.dictLabel);
        })
      });

      this.getAccount();
    },
    methods:{
      changeEdit(){
        this.isEdit = true;
      },
      getAccount(){
          getWalletAccount().then((response)=>{

            if (eval(response.data)){
              this.notRegistered =  false;
            } else{
              this.notRegistered = true;
            }
          })
      },
      register(form){
           this.$refs[form].validate((valid) => {
             if (valid) {
               console.log(this.account)
               addWalletAccount(this.account).then(response=>{
                 //Register Success
                 this.notRegistered = false;
               })
             } else {
               console.log('error submit!!');
               return false;
             }
        });

      },
      refresh(){
        getWalletAccountFund().then(response=>{
          this.account=response.data.waaWalletAccount
          this.account.currency=response.data.wafWalletAccountFund.currency
          this.walletFund=response.data.wafWalletAccountFund
        })
      },

      walletLogin(form){
        this.$refs[form].validate((valid) => {
          if (valid) {
            // login(this.account).then(response=>{
              //Register Success

                  walletAccountLogin(this.loginAccount).then(response=>{
                    console.log(response)
                  if (eval(response.data)){
                    this.notInputPassword = false;
                    getWalletAccountFund().then(response=>{
                      this.account=response.data.waaWalletAccount
                      this.account.currency=response.data.wafWalletAccountFund.currency
                      this.walletFund = response.data.wafWalletAccountFund
                    })
                  }else{
                    this.$notify({
                      type:"error",
                      message:"Wrong Password"
                    })
                  }
              })
            // })
          } else {
            return false;
          }
        });
      },
      save(){
        updateWalletAccount(this.account).then(response=>{
        })
      },
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      getList(){
        getWalletTransaction(this.addDateRange(this.queryParams, this.dateRange)).then(response=>{
          this.loading=false
          this.record=response.rows;
          this.total=response.total;
        })
      },
      /** Reset  按钮Operation */
      resetQuery() {
        this.dateRange = [];
        this.resetForm("queryForm");
        this.handleQuery();
      },
      deposite(form){
        this.$refs[form].validate((valid) => {
          if (valid) {
            depositAccount(this.depositeWithdrawForm).then(response=>{
              this.refresh()
              this.getList()
              this.cardFormVisible = false;
            })
          } else {
            console.log('error submit!!');
            return false;
          }
        });

      },
      redirect(){
        this.notShowTransaction = false;
        this.getList()
      },
      transactionStatusFormatter(row,cloumn){
          return this.walletTransactionStatus[row.status-1]
      },
      financeTypeFormatter(row,cloumn){
        return this.walletFinanceType[row.financeType-1]
      },
      transactionTypeFormatter(row,cloumn){
        return this.walletTransactionType[row.transactionType-1]
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
    height: 100%;
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
