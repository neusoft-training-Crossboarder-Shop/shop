<template>
  <div>
    <template>
      <div class="app-container">
        <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
          <el-form-item label="网店ID" prop="storeID">
            <el-input
              v-model="queryParams.storeID"
              placeholder="请输入网店名称"
              clearable
              size="small"
              style="width: 240px"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="网店名称" prop="storeName">
            <el-input
              v-model="queryParams.storeName"
              placeholder="请输入参数键名"
              clearable
              size="small"
              style="width: 240px"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          </el-form-item>
        </el-form>


        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button
              type="primary"
              icon="el-icon-plus"
              size="mini"
              @click="handleAdd"
              v-hasPermi="['bvo:store:add']"
            >新增</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="success"
              icon="el-icon-edit"
              size="mini"
              :disabled="single"
              @click="handleUpdate"
              v-hasPermi="['bvo:store:edit']"
            >修改</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="danger"
              icon="el-icon-delete"
              size="mini"
              :disabled="multiple"
              @click="handleDelete"
              v-hasPermi="['bvo:store:remove']"
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

        <el-table v-loading="loading" :data="storeList" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" align="center" />
          <el-table-column label="网点名称" align="center" prop="storeName" />
          <el-table-column label="平台类别" align="center" prop="storeType" :show-overflow-tooltip="true" />
          <el-table-column label="API Status" align="center" prop="storeStatus" width="130" :show-overflow-tooltip="true" />
          <el-table-column label="备注" align="center" prop="remark" width="130" :show-overflow-tooltip="true" />
          <el-table-column label="创建人" align="center" prop="createdBy"  />
          <el-table-column label="创建时间" align="center" prop="createdTime" width="150">
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.createdTime) }}</span>
            </template>
          </el-table-column>

          <el-table-column label="修改人" align="center" prop="updatedBy" />

          <el-table-column label="修改时间" align="center" prop="updatedTime" width="150">
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.updatedTime) }}</span>
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
                v-hasPermi="['system:config:edit']"
              >修改</el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
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
        <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
          <el-form ref="form" :model="form" :rules="rules" label-width="80px">
            <el-form-item label="网店名称" prop="storeName">
              <el-input v-model="form.storeName" placeholder="请输入参数名称" />
            </el-form-item>
            <el-form-item label="网店ID" prop="storeID">
              <el-input v-model="form.storeID" placeholder="请输入参数键名" />
            </el-form-item>
            <el-form-item label="网店类别" prop="storeType">
              <el-radio-group v-model="form.storeType">
                <el-radio
                    v-for="type in typeOptions"
                    :key="type.id"
                    :label="type.label"
                  >{{type.label}}
                </el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="备注" prop="remark" >
              <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
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
    export default {
        name: "index.vue",
        data:  function () {
          return{
            queryParams :{
              storeId:"",
              storeName:"",
            },
            rules: {
              storeName: [
                { required: true, message: "商店名称不能为空", trigger: "blur" }
              ],
              storeID: [
                { required: true, message: "商店ID不能为空", trigger: "blur" }
              ],
              storeType: [
                { required: true, message: "商店类别不能为空", trigger: "blur" }
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
              {id:1,label:"Amazon"},
              {id:2,label:"Ebay"}
            ],
            storeList:[
              {
                storeId:123,
                storeName:"好宝宝网店",
                storeType:1,
                storeStatus:1,
                createdBy:"好人",
                createdTime:"2020-07-01 07:27:04",
                updatedBy:"好人",
                updatedTime:"2020-07-01 07:27:04",
                remark:"我的第一家网店"
              },
              {
                storeId:1234,
                storeName:"好宝宝网店",
                storeType:1,
                storeStatus:1,
                createdBy:"好人",
                createdTime:"2020-07-01 07:27:04",
                updatedBy:"好人",
                updatedTime:"2020-07-01 07:27:04",
                remark:"我的第一家网店"
              }
            ],
            // 弹出层标题
            title: "",
            // 是否显示弹出层
            open: false,
            form: {
              storeId:"",
              storeName:"",
              storeType:"",
              remark:"",
            },
          }
        },
        methods:{
          created() {
            // this.getList();
            // this.getDicts("sys_yes_no").then(response => {
            //   this.typeOptions = response.data;
            // });
          },
            /** 查询参数列表 */
            getList() {
              // this.loading = true;
              // listConfig(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
              //     this.configList = response.rows;
              //     this.total = response.total;
              //     this.loading = false;
              //   }
              // );
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
              storeId:"",
              storeName:"",
              storeType:"",
              remark:"",
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
            this.ids = selection.map(item => item.configId)
            this.single = selection.length!=1
            this.multiple = !selection.length
          },
          /** 修改按钮操作 */
          handleUpdate(row) {
            this.reset();
            const configId = row.configId || this.ids
            getConfig(configId).then(response => {
              this.form = response.data;
              this.open = true;
              this.title = "修改参数";
            });
          },
          /** 提交按钮 */
          submitForm: function() {
            this.$refs["form"].validate(valid => {
              if (valid) {
                if (this.form.configId != undefined) {
                  updateConfig(this.form).then(response => {
                    if (response.code === 200) {
                      this.msgSuccess("修改成功");
                      this.open = false;
                      this.getList();
                    }
                  });
                } else {
                  addConfig(this.form).then(response => {
                    if (response.code === 200) {
                      this.msgSuccess("新增成功");
                      this.open = false;
                      this.getList();
                    }
                  });
                }
              }
            });
          },
          /** 删除按钮操作 */
          handleDelete(row) {
            const configIds = row.configId || this.ids;
            this.$confirm('是否确认删除参数编号为"' + configIds + '"的数据项?', "警告", {
              confirmButtonText: "确定",
              cancelButtonText: "取消",
              type: "warning"
            }).then(function() {
              return delConfig(configIds);
            }).then(() => {
              this.getList();
              this.msgSuccess("删除成功");
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




</style>
