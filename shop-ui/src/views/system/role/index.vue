<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true">
      <el-form-item label="RoleName" prop="roleName">
        <el-input
          v-model="queryParams.roleName"
          placeholder="Please Enter RoleName"
          clearable
          size="small"
          style="width: 240px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="Authority Code" prop="roleKey">
        <el-input
          v-model="queryParams.roleKey"
          placeholder="Please Enter Authority Code"
          clearable
          size="small"
          style="width: 240px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="Status" prop="status">
        <el-select
          v-model="queryParams.status"
          placeholder="RoleStatus"
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
          v-hasPermi="['system:role:add']"
        >Add </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:role:edit']"
        > Modify  </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:role:remove']"
        > Delete </el-button>
      </el-col>

<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="warning"-->
<!--          icon="el-icon-download"-->
<!--          size="mini"-->
<!--          @click="handleExport"-->
<!--          v-hasPermi="['system:post:export']"-->
<!--        >Export </el-button>-->
<!--      </el-col>-->
    </el-row>

    <el-table v-loading="loading" :data="roleList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="RoleID" prop="roleId" width="120" />
      <el-table-column label="RoleName" prop="roleName" :show-overflow-tooltip="true" width="150" />
      <el-table-column label="Authority Code" prop="roleKey" :show-overflow-tooltip="true" width="150" />
      <el-table-column label="Sequence" prop="roleSort" width="100" />
      <el-table-column label="Status" align="center" width="100">
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.status"
            active-value="0"
            inactive-value="1"
            @change="handleStatusChange(scope.row)"
          ></el-switch>
        </template>
      </el-table-column>
      <el-table-column label=" Create   time" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Operation" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:role:edit']"
          > Modify  </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-circle-check"
            @click="handleDataScope(scope.row)"
            v-hasPermi="['system:role:edit']"
          > Data Authority</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:role:remove']"
          > Delete </el-button>
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

    <!--  Add  或 Modify  Role配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="RoleName" prop="roleName">
          <el-input v-model="form.roleName" placeholder="Please Enter RoleName" />
        </el-form-item>
        <el-form-item label="Authority Code" prop="roleKey">
          <el-input v-model="form.roleKey" placeholder="Please Enter Authority Code" />
        </el-form-item>
        <el-form-item label="Role顺序" prop="roleSort">
          <el-input-number v-model="form.roleSort" controls-position="right" :min="0" />
        </el-form-item>
        <el-form-item label="Status">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in statusOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="Menu Authority ">
          <el-tree
            :data="menuOptions"
            show-checkbox
            ref="menu"
            node-key="id"
            empty-text="加载中，请稍后"
            :props="defaultProps"
          ></el-tree>
        </el-form-item>
        <el-form-item label="Remark ">
          <el-input v-model="form.remark" type="textarea" placeholder="Please Enter 内容"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 分配Role Data Authority对话框 -->
    <el-dialog :title="title" :visible.sync="openDataScope" width="500px" append-to-body>
      <el-form :model="form" label-width="80px">
        <el-form-item label="RoleName">
          <el-input v-model="form.roleName" :disabled="true" />
        </el-form-item>
        <el-form-item label="Authority Code">
          <el-input v-model="form.roleKey" :disabled="true" />
        </el-form-item>
        <el-form-item label="Authority 范围">
          <el-select v-model="form.dataScope">
            <el-option
              v-for="item in dataScopeOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
<!--        <el-form-item label=" Data Authority" v-show="form.dataScope == 2">-->
<!--          <el-tree-->
<!--            :data="deptOptions"-->
<!--            show-checkbox-->
<!--            default-expand-all-->
<!--            ref="dept"-->
<!--            node-key="id"-->
<!--            empty-text="加载中，请稍后"-->
<!--            :props="defaultProps"-->
<!--          ></el-tree>-->
<!--        </el-form-item>-->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitDataScope">确 定</el-button>
        <el-button @click="cancelDataScope">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listRole, getRole, delRole, addRole, updateRole, dataScope, changeRoleStatus } from "@/api/system/role";
import { treeselect as menuTreeselect, roleMenuTreeselect } from "@/api/system/menu";

export default {
  name: "Role",
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
      // Role表格Data
      roleList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否显示弹出层（ Data Authority）
      openDataScope: false,
      // Time   范围
      dateRange: [],
      // StatusData Dict
      statusOptions: [],
      // Data 范围选项
      dataScopeOptions: [
        {
          value: "1",
          label: "全部 Data Authority"
        },
        {
          value: "2",
          label: "自定 Data Authority"
        },
        {
          value: "3",
          label: "本部门 Data Authority"
        },
        {
          value: "4",
          label: "本部门及以下 Data Authority"
        },
        {
          value: "5",
          label: "仅本人 Data Authority"
        }
      ],
      // Menu 列表
      menuOptions: [],
      // // 部门列表
      // deptOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        roleName: undefined,
        roleKey: undefined,
        status: undefined
      },
      // 表单参数
      form: {},
      defaultProps: {
        children: "children",
        label: "label"
      },
      // 表单校验
      rules: {
        roleName: [
          { required: true, message: "RoleName不能为空", trigger: "blur" }
        ],
        roleKey: [
          { required: true, message: "Authority Code不能为空", trigger: "blur" }
        ],
        roleSort: [
          { required: true, message: "Role顺序不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("sys_normal_disable").then(response => {
      this.statusOptions = response.data;
    });
  },
  methods: {
    /** 查询Role列表 */
    getList() {
      this.loading = true;
      listRole(this.addDateRange(this.queryParams, this.dateRange)).then(
        response => {
          this.roleList = response.rows;
          this.total = response.total;
          this.loading = false;
        }
      );
    },
    /** 查询Menu 树结构 */
    getMenuTreeselect() {
      menuTreeselect().then(response => {
        this.menuOptions = response.data;
      });
    },
    // /** 查询部门树结构 */
    // getDeptTreeselect() {
    //   deptTreeselect().then(response => {
    //     this.deptOptions = response.data;
    //   });
    // },
    // 所有Menu 节点Data
    getMenuAllCheckedKeys() {
      // 目前 be 选中的Menu 节点
      let checkedKeys = this.$refs.menu.getHalfCheckedKeys();
      // 半选中的Menu 节点
      let halfCheckedKeys = this.$refs.menu.getCheckedKeys();
      checkedKeys.unshift.apply(checkedKeys, halfCheckedKeys);
      return checkedKeys;
    },
    // 所有部门节点Data
    getDeptAllCheckedKeys() {
      // 目前 be 选中的部门节点
      // let checkedKeys = this.$refs.dept.getHalfCheckedKeys();
      // 半选中的部门节点
      // let halfCheckedKeys = this.$refs.dept.getCheckedKeys();
      checkedKeys.unshift.apply(checkedKeys, halfCheckedKeys);
      return checkedKeys;
    },
    /** 根据RoleID查询Menu 树结构 */
    getRoleMenuTreeselect(roleId) {
      roleMenuTreeselect(roleId).then(response => {
        this.menuOptions = response.menus;
        this.$refs.menu.setCheckedKeys(response.checkedKeys);
      });
    },
    /** 根据RoleID查询部门树结构 */
    // getRoleDeptTreeselect(roleId) {
    //   roleDeptTreeselect(roleId).then(response => {
    //     this.deptOptions = response.depts;
    //     this.$refs.dept.setCheckedKeys(response.checkedKeys);
    //   });
    // },
    // RoleStatus Modify
    handleStatusChange(row) {
      let text = row.status === "0" ? "启用" : "停用";
      this.$confirm(' Confirm 要"' + text + '""' + row.roleName + '"Role吗?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return changeRoleStatus(row.roleId, row.status);
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
    // 取消按钮（ Data Authority）
    cancelDataScope() {
      this.openDataScope = false;
      this.reset();
    },
    // 表单Reset
    reset() {
      if (this.$refs.menu != undefined) {
        this.$refs.menu.setCheckedKeys([]);
      }
      this.form = {
        roleId: undefined,
        roleName: undefined,
        roleKey: undefined,
        roleSort: 0,
        status: "0",
        menuIds: [],
        // deptIds: [],
        remark: undefined
      };
      this.resetForm("form");
    },
    /** Search 按钮Operation */
    handleQuery() {
      this.queryParams.pageNum = 1;
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
      this.ids = selection.map(item => item.roleId)
      this.single = selection.length!=1
      this.multiple = !selection.length
    },
    /** Add 按钮Operation */
    handleAdd() {
      this.reset();
      this.getMenuTreeselect();
      this.open = true;
      this.title = " Add  Role";
    },
    /**  Modify  按钮Operation */
    handleUpdate(row) {
      this.reset();
      const roleId = row.roleId || this.ids
      this.$nextTick(() => {
        this.getRoleMenuTreeselect(roleId);
      });
      getRole(roleId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = " Modify  Role";
      });
    },
    /** 分配 Data AuthorityOperation */
    handleDataScope(row) {
      this.reset();
      this.$nextTick(() => {
        this.getRoleDeptTreeselect(row.roleId);
      });
      getRole(row.roleId).then(response => {
        this.form = response.data;
        this.openDataScope = true;
        this.title = "分配 Data Authority";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.roleId != undefined) {
            this.form.menuIds = this.getMenuAllCheckedKeys();
            updateRole(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess(" Modify  Success");
                this.open = false;
                this.getList();
              }
            });
          } else {
            this.form.menuIds = this.getMenuAllCheckedKeys();
            addRole(this.form).then(response => {
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
    /** 提交按钮（ Data Authority） */
    submitDataScope: function() {
      if (this.form.roleId != undefined) {
        // this.form.deptIds = this.getDeptAllCheckedKeys();
        dataScope(this.form).then(response => {
          if (response.code === 200) {
            this.msgSuccess(" Modify  Success");
            this.openDataScope = false;
            this.getList();
          }
        });
      }
    },
    /**  Delete 按钮Operation */
    handleDelete(row) {
      const roleIds = row.roleId || this.ids;
      this.$confirm('是否 Confirm  Delete RoleID为"' + roleIds + '"的Data 项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delRole(roleIds);
        }).then(() => {
          this.getList();
          this.msgSuccess(" Delete Success");
        }).catch(function() {});
    },
    // /** Export 按钮Operation */
    // handleExport() {
    //   const queryParams = this.queryParams;
    //   this.$confirm('是否 Confirm Export 所有RoleData 项?', "警告", {
    //       confirmButtonText: "确定",
    //       cancelButtonText: "取消",
    //       type: "warning"
    //     }).then(function() {
    //       return exportRole(queryParams);
    //     }).then(response => {
    //       this.download(response.msg);
    //     }).catch(function() {});
    // }
  }
};
</script>
