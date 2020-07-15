<template>
  <div>
    <template>
      <div class="app-container">
        <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="20%">
          <el-form-item label="StoreId" prop="storeID">
            <el-input
              v-model="queryParams.storeId"
              placeholder="Please Enter Store Id"
              clearable
              size="small"
              style="width: 240px"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="StoreName" prop="storeName" label-width="25%">
            <el-input
              v-model="queryParams.storeName"
              placeholder="Please Enter Store Name"
              clearable
              size="small"
              style="width: 240px"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label-width="0">
            <el-select v-model="queryParams.platformType" clearable placeholder="Select Platform Type">
              <el-option
                v-for="(item,index) in typeOptions"
                :key="index"
                :label="item"
                :value="index">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">Search</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">Reset</el-button>
          </el-form-item>
        </el-form>


        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
<!--            v-hasPermi="['bvo:store:add']"-->
            <el-button
              type="primary"
              icon="el-icon-plus"
              size="mini"
              @click="handleAdd"

            >新增</el-button>
          </el-col>
          <el-col :span="1.5">
<!--            v-hasPermi="['bvo:store:edit']" 这是权限控制里加载 el-button里的参数-->
            <el-button
              type="success"
              icon="el-icon-edit"
              size="mini"
              :disabled="single"
              @click="handleUpdate"
            >修改</el-button>
          </el-col>
<!--                        v-hasPermi="['bvo:store:remove']"
-->
          <el-col :span="1.5">
            <el-button
              type="danger"
              icon="el-icon-delete"
              size="mini"
              :disabled="multiple"
              @click="handleDelete"
            >删除
            </el-button>
          </el-col>

          <el-col :span="1.5">
            <el-button
              type="danger"
              icon="el-icon-refresh"
              size="mini"
              @click="handleClearCache"
              v-hasPermi="['system:config:remove']"
            >清理缓存</el-button>
          </el-col>
        </el-row>

        <el-table  @row-click="redirect" class="el-table--enable-row-hover el-table__body" v-loading="loading" :data="storeList"  @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" align="center" />
          <el-table-column label="Store ID" align="center" prop="storeId" />
          <el-table-column label="Platform Type" align="center"  :show-overflow-tooltip="true" >
            <template slot-scope="scope">
              {{typeOptions[scope.row.platformType]}}
            </template>
          </el-table-column>
          <el-table-column label="Store Name" align="center" prop="storeName" />
          <el-table-column label="VerifyCation Token" align="center" prop="authencationCode" width="140" :show-overflow-tooltip="true" />
          <el-table-column label="Creator" align="center" prop="createdBy"  />
          <el-table-column label="CreateTime" align="center" prop="createTime" width="150">
            <template slot-scope="scope">
              <span>{{ scope.row.createTime }}</span>
            </template>
          </el-table-column>

          <el-table-column label="Last Update By" align="center" prop="lastUpdateBy" />

          <el-table-column label="Last Update Time" align="center" prop="lastUpdateTime" width="150">
            <template slot-scope="scope">
              <span>{{ scope.row.lastUpdateTime }}</span>
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
<!--         el-button 权限里面v-hasPermi="['system:config:edit']"-->
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click.stop="handleUpdate(scope.row)"
              >修改</el-button>

              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click.stop="handleDelete(scope.row)"
                v-hasPermi="['system:config:remove']"
              >删除</el-button>
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

        <!-- 添加或修改参数配置对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="80%" append-to-body>
          <el-form ref="form" :model="form" :rules="rules" label-width="30%">
            <el-form-item label="Store Name" prop="storeName">
              <el-input v-model="form.storeName" placeholder="Please Enter Store　Name" />
            </el-form-item>
            <el-form-item label="PlatformType" prop="platformType">
              <el-radio-group v-model="form.platformType">
                <el-radio
                  v-for="(type,index) in typeOptions"
                  :key="index"
                  :value="index"
                  :label="index"
                >{{type}}
                </el-radio>
              </el-radio-group>
            </el-form-item>

            <el-form-item label="Verifycation Code" prop="authencationCode">
              <el-input v-model="form.authencationCode" placeholder="Please enter Authentication Code" />
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="submitForm">确 定</el-button>
            <el-button @click="cancel">取 消</el-button>
          </div>
        </el-dialog>
      </div>
    </template>
  </div>
</template>

<script>
  import {getStoreList,getStore,updateStore,addStore,delStore} from "../../../api/bvo/store";

  export default {
        name: "index.vue",
        data:  function () {
          return{
            queryParams :{
              pageNum: 1,
              pageSize: 10,
              storeId:undefined,
              storeName: undefined,
              platformType: undefined
            },
            rules: {
              storeName: [
                { required: true, message: "Store name can't be empty", trigger: "blur" }
              ],
              authencationCode: [
                { required: true, message: "Authencation Code cann't be empty", trigger: "blur" }
              ],
              platformType: [
                { required: true, message: "Platform Type can't be empty", trigger: "blur" }
              ]
            },
            // 遮罩层
            loading: false,
            // 选中数组
            ids: [],
            // 非单个禁用
            single: true,
            // 非多个禁用
            multiple: true,
            // 总条数
            total: 0,
            // 弹出层标题
            title: "",
            // 是否显示弹出层
            open: false,
            // 参数表格数据
            typeOptions:[
              // "Amazon",
              // "Ebay"
            ],
            storeList:[
              {
                storeId:123,
                storeName:"好宝宝网店",
                platformType:1,
                authencationCode:1,
                createdBy:"好人",
                createTime:"2020-07-01 07:27:04",
                lastUpdateBy:"好人",
                lastUpdateTime:"2020-07-01 07:27:04",
              },
            ],
            // 弹出层标题
            title: "",
            // 是否显示弹出层
            open: false,
            form: {
              storeId:undefined,
              storeName:"",
              platformType:"",
              remark:"",
            },

          }
        },
      created() {
        this.getList();
        this.getDicts("store_platform_type").then(response => {
           let data=response.data;
           data.forEach(item=>{
             this.typeOptions.push(item.dictLabel);
           })
        });
      },
        methods:{
          redirect(row, event, column){
            this.$router.push({
              path:`/bvo/storeDetail/${row.storeId}`
            })
          },
            /** 查询参数列表 */
            getList() {
              this.loading = true;
              getStoreList(this.queryParams).then(response => {
                  this.storeList = response.rows;
                  this.total = response.total;
                  this.loading = false;
                }
              );
            },
            // 取消按钮
            cancel() {
              this.open = false;
              this.reset();
            },
          handleAdd() {
            this.reset();
            this.open = true;
            this.title = "添加参数";
          },
          reset() {
            this.form = {
              storeName:"",
              platformType:"",
              authencationCode:"",
            };
            this.resetForm("form");
          },
          /** 搜索按钮操作 */
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
          /** 新增按钮操作 */
          handleAdd() {
            this.reset();
            this.open = true;
            this.title = "添加参数";
          },
          // 多选框选中数据
          handleSelectionChange(selection) {
            this.ids = selection.map(item => item.storeId)
            this.single = selection.length!=1
            this.multiple = !selection.length
          },
          /** 修改按钮操作 */
          handleUpdate(row) {
            this.reset();
            const storeId = row.storeId || this.ids;
            getStore(storeId).then(response => {
              this.form = response.data;
              this.open = true;
              this.title = "Modify Page";
            });
          },
          /** 提交按钮 */
          submitForm: function() {
            this.$refs["form"].validate(valid => {
              if (valid) {
                if (this.form.storeId != undefined) {
                  updateStore(this.form).then(response => {
                    this.getList();
                    this.open = false;
                  });
                } else {
                  addStore(this.form).then(response => {
                    //添加成功

                    this.getList();
                    this.open = false;

                  });
                }
              }
            });
          },
          /** 删除按钮操作 */
          handleDelete(row) {
            const storeId = row.storeId || this.ids;
            this.$confirm('Are you sure to delete No."' + storeId + '"?', "Warning", {
              confirmButtonText: "Yes",
              cancelButtonText: "No",
              type: "warning"
            }).then(function() {
              return delStore(storeId);
            }).then(() => {
              this.getList();
            }).catch(function() {});
          },
          /** 清理缓存按钮操作 */
          handleClearCache() {
            clearCache().then(response => {
              if (response.code === 200) {
                this.msgSuccess("清理成功");
              }
            });
          }
      }
    }
</script>

<style scoped>
  /*表格每一行被hover时的样式设置*/
  .el-table >>> .el-table__body tr:hover>td {
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
    cursor: pointer;
    background-color: #d9d9d9;
  }

</style>
