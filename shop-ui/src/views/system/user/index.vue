<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :span="24" :xs="24">
        <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
          <el-form-item label="User Name" prop="userName">
            <el-input
              v-model="queryParams.userName"
              placeholder="Please Enter User Name"
              clearable
              size="small"
              style="width: 240px"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="Tele-Number" prop="phonenumber">
            <el-input
              v-model="queryParams.phonenumber"
              placeholder="Please Enter Tele-Number"
              clearable
              size="small"
              style="width: 240px"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="Status" prop="status">
            <el-select
              v-model="queryParams.status"
              placeholder="User Status"
              clearable
              size="small"
              style="width: 240px"
            >
              <el-option
                v-for="dict in statusOptions"
                :key="dict.dictValue"
                :label="dict.dictLabel"
                :value="dict.dictValue"
              />
            </el-select>
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

        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button
              type="primary"
              icon="el-icon-plus"
              size="mini"
              @click="handleAdd"
              v-hasPermi="['system:user:add']"
            >Add </el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="success"
              icon="el-icon-edit"
              size="mini"
              :disabled="single"
              @click="handleUpdate"
              v-hasPermi="['system:user:edit']"
            > Modify  </el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="danger"
              icon="el-icon-delete"
              size="mini"
              :disabled="multiple"
              @click="handleDelete"
              v-hasPermi="['system:user:remove']"
            > Delete </el-button>
          </el-col>
<!--          <el-col :span="1.5">-->
<!--            <el-button-->
<!--              type="info"-->
<!--              icon="el-icon-upload2"-->
<!--              size="mini"-->
<!--              @click="handleImport"-->
<!--              v-hasPermi="['system:user:import']"-->
<!--            >导入</el-button>-->
<!--          </el-col>-->
<!--          <el-col :span="1.5">-->
<!--            <el-button-->
<!--              type="warning"-->
<!--              icon="el-icon-download"-->
<!--              size="mini"-->
<!--              @click="handleExport"-->
<!--              v-hasPermi="['system:user:export']"-->
<!--            >Export </el-button>-->
<!--          </el-col>-->
        </el-row>

        <el-table v-loading="loading" :data="userList" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="User ID" align="center" prop="userId" />
          <el-table-column label="User Name" align="center" prop="userName" :show-overflow-tooltip="true" />
          <el-table-column label="User Name" align="center" prop="nickName" :show-overflow-tooltip="true" />
<!--          <el-table-column label="部门" align="center" prop="dept.deptName" :show-overflow-tooltip="true" />-->
          <el-table-column label="Tele-Number" align="center" prop="phonenumber" width="120" />
          <el-table-column label="Status" align="center">
            <template slot-scope="scope">
              <el-switch
                v-model="scope.row.status"
                active-value="0"
                inactive-value="1"
                @change="handleStatusChange(scope.row)"
              ></el-switch>
            </template>
          </el-table-column>
          <el-table-column label=" Create   time" align="center" prop="createTime" width="160">
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.createTime) }}</span>
            </template>
          </el-table-column>
          <el-table-column
            label="Operation"
            align="center"
            width="180"
            class-name="small-padding fixed-width"
          >
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
                v-hasPermi="['system:user:edit']"
              > Modify  </el-button>
              <el-button
                v-if="scope.row.userId !== 1"
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
                v-hasPermi="['system:user:remove']"
              > Delete </el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-key"
                @click="handleResetPwd(scope.row)"
                v-hasPermi="['system:user:resetPwd']"
              >Reset  </el-button>
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
      </el-col>
    </el-row>

    <!--  Add  或 Modify  参数配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="User Name" prop="nickName">
              <el-input v-model="form.nickName" placeholder="Please Enter User Name" />
            </el-form-item>
          </el-col>
<!--          <el-col :span="12">-->
<!--            <el-form-item label="归属部门" prop="deptId">-->
<!--              <treeselect v-model="form.deptId" :options="deptOptions" :disable-branch-nodes="true" :show-count="true" placeholder="请 Selection  归属部门" />-->
<!--            </el-form-item>-->
<!--          </el-col>-->
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="Tele-Number" prop="phonenumber">
              <el-input v-model="form.phonenumber" placeholder="Please Enter Tele-Number" maxlength="11" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="邮箱" prop="email">
              <el-input v-model="form.email" placeholder="Please Enter 邮箱" maxlength="50" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item v-if="form.userId == undefined" label="User Name" prop="userName">
              <el-input v-model="form.userName" placeholder="Please Enter User Name" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item v-if="form.userId == undefined" label="User 密码" prop="password">
              <el-input v-model="form.password" placeholder="Please Enter User 密码" type="password" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="User 性别">
              <el-select v-model="form.sex" placeholder="请 Selection  ">
                <el-option
                  v-for="dict in sexOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="Status">
              <el-radio-group v-model="form.status">
                <el-radio
                  v-for="dict in statusOptions"
                  :key="dict.dictValue"
                  :label="dict.dictValue"
                >{{dict.dictLabel}}</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
<!--            <el-form-item label="岗位">-->
<!--              <el-select v-model="form.postIds" multiple placeholder="请 Selection  ">-->
<!--                <el-option-->
<!--                  v-for="item in postOptions"-->
<!--                  :key="item.postId"-->
<!--                  :label="item.postName"-->
<!--                  :value="item.postId"-->
<!--                  :disabled="item.status == 1"-->
<!--                ></el-option>-->
<!--              </el-select>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--          <el-col :span="12">-->
            <el-form-item label="Role">
              <el-select v-model="form.roleIds" multiple placeholder="请 Selection  ">
                <el-option
                  v-for="item in roleOptions"
                  :key="item.roleId"
                  :label="item.roleName"
                  :value="item.roleId"
                  :disabled="item.status == 1"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="Remark ">
              <el-input v-model="form.remark" type="textarea" placeholder="Please Enter 内容"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

<!--    &lt;!&ndash; User 导入对话框 &ndash;&gt;-->
<!--    <el-dialog :title="upload.title" :visible.sync="upload.open" width="400px" append-to-body>-->
<!--      <el-upload-->
<!--        ref="upload"-->
<!--        :limit="1"-->
<!--        accept=".xlsx, .xls"-->
<!--        :headers="upload.headers"-->
<!--        :action="upload.url + '?updateSupport=' + upload.updateSupport"-->
<!--        :disabled="upload.isUploading"-->
<!--        :on-progress="handleFileUploadProgress"-->
<!--        :on-success="handleFileSuccess"-->
<!--        :auto-upload="false"-->
<!--        drag-->
<!--      >-->
<!--        <i class="el-icon-upload"></i>-->
<!--        <div class="el-upload__text">-->
<!--          将文件拖到此处，或-->
<!--          <em>点击Upload </em>-->
<!--        </div>-->
<!--        <div class="el-upload__tip" slot="tip">-->
<!--          <el-checkbox v-model="upload.updateSupport" />是否更新Already经存在的User Data -->
<!--          <el-link type="info" style="font-size:12px" @click="importTemplate">下载模板</el-link>-->
<!--        </div>-->
<!--        <div class="el-upload__tip" style="color:red" slot="tip">提示：仅允许导入“xls”或“xlsx”格式文件！</div>-->
<!--      </el-upload>-->
<!--      <div slot="footer" class="dialog-footer">-->
<!--        <el-button type="primary" @click="submitFileForm">确 定</el-button>-->
<!--        <el-button @click="upload.open = false">取 消</el-button>-->
<!--      </div>-->
<!--    </el-dialog>-->
  </div>
</template>

<script>
import { listUser, getUser, delUser, addUser, updateUser, resetUserPwd, changeUserStatus, importTemplate } from "@/api/system/user";
import { getToken } from "@/utils/auth";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "User",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 总条数
      total: 0,
      // User 表格Data
      userList: null,
      // 弹出层标题
      title: "",
      // 部门树选项
      // deptOptions: undefined,
      // 是否显示弹出层
      open: false,
      // 部门Name
      // deptName: undefined,
      // 默认密码
      initPassword: undefined,
      // Time   范围
      dateRange: [],
      // StatusData Dict
      statusOptions: [],
      // 性别StatusDict
      sexOptions: [],
      // 岗位选项
      // postOptions: [],
      // Role选项
      roleOptions: [],
      // 表单参数
      form: {},
      defaultProps: {
        children: "children",
        label: "label"
      },
      // User 导入参数
      upload: {
        // 是否显示弹出层（User 导入）
        open: false,
        // 弹出层标题（User 导入）
        title: "",
        // 是否禁用Upload
        isUploading: false,
        // 是否更新Already经存在的User Data
        updateSupport: 0,
        // 设置Upload 的请求头部
        headers: { Authorization: "Bearer " + getToken() },
        // Upload 的地址
        url: process.env.VUE_APP_BASE_API + "/system/user/importData"
      },
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userName: undefined,
        phonenumber: undefined,
        status: undefined,
        // deptId: undefined
      },
      // 表单校验
      rules: {
        userName: [
          { required: true, message: "User Name不能为空", trigger: "blur" }
        ],
        nickName: [
          { required: true, message: "User Name不能为空", trigger: "blur" }
        ],
        // deptId: [
        //   { required: true, message: "归属部门不能为空", trigger: "blur" }
        // ],
        password: [
          { required: true, message: "User 密码不能为空", trigger: "blur" }
        ],
        email: [
          { required: true, message: "邮箱地址不能为空", trigger: "blur" },
          {
            type: "email",
            message: "'Please Enter 正确的邮箱地址",
            trigger: ["blur", "change"]
          }
        ],
        phonenumber: [
          { required: true, message: "Tele-Number不能为空", trigger: "blur" },
          {
            pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/,
            message: "Please Enter 正确的Tele-Number",
            trigger: "blur"
          }
        ]
      }


    };
  },
  created() {
    this.getList();
    // this.getTreeselect();
    this.getDicts("sys_normal_disable").then(response => {
      this.statusOptions = response.data;
    });
    this.getDicts("sys_user_sex").then(response => {
      this.sexOptions = response.data;
    });
    this.getConfigKey("sys.user.initPassword").then(response => {
      this.initPassword = response.msg;
    });
  },
  methods: {
    /** 查询User 列表 */
    getList() {
      this.loading = true;
      listUser(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
          this.userList = response.rows;
          this.total = response.total;
          this.loading = false;
        }
      );
    },
    /** 查询部门Pull 树结构 */
    // getTreeselect() {
    //   treeselect().then(response => {
    //     this.deptOptions = response.data;
    //   });
    // },
    // 筛选节点
    filterNode(value, data) {
      if (!value) return true;
      return data.label.indexOf(value) !== -1;
    },
    // 节点单击事件
    // handleNodeClick(data) {
    //   this.queryParams.deptId = data.id;
    //   this.getList();
    // },
    // User Status Modify
    handleStatusChange(row) {
      let text = row.status === "0" ? "启用" : "停用";
      this.$confirm(' Confirm 要"' + text + '""' + row.userName + '"User 吗?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return changeUserStatus(row.userId, row.status);
        }).then(() => {
          this.msgSuccess(text + "Success");
        }).catch(function() {
          row.status = row.status === "0" ? "1" : "0";
        });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单Reset
    reset() {
      this.form = {
        userId: undefined,
        // deptId: undefined,
        userName: undefined,
        nickName: undefined,
        password: undefined,
        phonenumber: undefined,
        email: undefined,
        sex: undefined,
        status: "0",
        remark: undefined,
        // postIds: [],
        roleIds: []
      };
      this.resetForm("form");
    },
    /** Search 按钮Operation */
    handleQuery() {
      this.queryParams.page = 1;
      this.getList();
    },
    /** Reset  按钮Operation */
    resetQuery() {
      this.dateRange = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中Data
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.userId);
      this.single = selection.length != 1;
      this.multiple = !selection.length;
    },
    /** Add 按钮Operation */
    handleAdd() {
      this.reset();
      // this.getTreeselect();
      getUser().then(response => {
        // this.postOptions = response.posts;
        this.roleOptions = response.roles;
        this.open = true;
        this.title = " Add  User ";
        this.form.password = this.initPassword;
      });
    },
    /**  Modify  按钮Operation */
    handleUpdate(row) {
      this.reset();
      // this.getTreeselect();
      const userId = row.userId || this.ids;
      getUser(userId).then(response => {
        this.form = response.data;
        // this.postOptions = response.posts;
        this.roleOptions = response.roles;
        // this.form.postIds = response.postIds;
        this.form.roleIds = response.roleIds;
        this.open = true;
        this.title = " Modify  User ";
        this.form.password = "";
      });
    },
    /** Reset  密码按钮Operation */
    handleResetPwd(row) {
      this.$prompt('Please Enter "' + row.userName + '"的新密码', "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消"
      }).then(({ value }) => {
          resetUserPwd(row.userId, value).then(response => {
            if (response.code === 200) {
              this.msgSuccess(" Modify  Success，新密码是：" + value);
            }
          });
        }).catch(() => {});
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.userId != undefined) {
            updateUser(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess(" Modify  Success");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addUser(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("Add Success");
                this.open = false;
                this.getList();
              }
            });
          }

        }
      });
    },
    /**  Delete 按钮Operation */
    handleDelete(row) {
      const userIds = row.userId || this.ids;
      this.$confirm('是否 Confirm  Delete User ID为"' + userIds + '"的Data 项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delUser(userIds);
        }).then(() => {
          this.getList();
          this.msgSuccess(" Delete Success");
        }).catch(function() {});
    },
    /** Export 按钮Operation */
    // handleExport() {
    //   const queryParams = this.queryParams;
    //   this.$confirm('是否 Confirm Export 所有User Data 项?', "警告", {
    //       confirmButtonText: "确定",
    //       cancelButtonText: "取消",
    //       type: "warning"
    //     }).then(function() {
    //       return exportUser(queryParams);
    //     }).then(response => {
    //       this.download(response.msg);
    //     }).catch(function() {});
    // },
    // /** 导入按钮Operation */
    // handleImport() {
    //   this.upload.title = "User 导入";
    //   this.upload.open = true;
    // },
    /** 下载模板Operation */
    importTemplate() {
      importTemplate().then(response => {
        this.download(response.msg);
      });
    },
    // 文件Upload 中处理
    handleFileUploadProgress(event, file, fileList) {
      this.upload.isUploading = true;
    },
    // 文件Upload Success处理
    handleFileSuccess(response, file, fileList) {
      this.upload.open = false;
      this.upload.isUploading = false;
      this.$refs.upload.clearFiles();
      this.$alert(response.msg, "导入结果", { dangerouslyUseHTMLString: true });
      this.getList();
    },
    // 提交Upload 文件
    submitFileForm() {
      this.$refs.upload.submit();
    }
  }
};
</script>
