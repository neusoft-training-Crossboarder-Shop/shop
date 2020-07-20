<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true">
      <el-form-item label=" Dict Name" prop="dictType">
        <el-select v-model="queryParams.dictType" size="small">
          <el-option
            v-for="item in typeOptions"
            :key="item.dictId"
            :label="item.dictName"
            :value="item.dictType"
          />
        </el-select>
      </el-form-item>
      <el-form-item label=" Dict 标签" prop="dictLabel">
        <el-input
          v-model="queryParams.dictLabel"
          placeholder="Please enter  Dict 标签"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="Status" prop="status">
        <el-select v-model="queryParams.status" placeholder="Data Status" clearable size="small">
          <el-option
            v-for="dict in statusOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
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
          v-hasPermi="['system:dict:add']"
        >Add </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:dict:edit']"
        >Modify</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:dict:remove']"
        >Delete</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:dict:export']"
        >Export </el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="dataList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label=" Dict 编码" align="center" prop="dictCode" />
      <el-table-column label=" Dict 标签" align="center" prop="dictLabel" />
      <el-table-column label=" Dict 键值" align="center" prop="dictValue" />
      <el-table-column label=" Dict Sequence " align="center" prop="dictSort" />
      <el-table-column label="Status" align="center" prop="status" :formatter="statusFormat" />
      <el-table-column label="Remark" align="center" prop="remark" :show-overflow-tooltip="true" />
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
            v-hasPermi="['system:dict:edit']"
          >Modify</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:dict:remove']"
          >Delete</el-button>
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

    <!--  Add  或Modify参数配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="Dict Type">
          <el-input v-model="form.dictType" :disabled="true" />
        </el-form-item>
        <el-form-item label="Data Lable" prop="dictLabel">
          <el-input v-model="form.dictLabel" placeholder="Please enter Data Label " />
        </el-form-item>
        <el-form-item label="Data Value" prop="dictValue">
          <el-input v-model="form.dictValue" placeholder="Please enter data value" />
        </el-form-item>
        <el-form-item label="Sort List" prop="dictSort">
          <el-input-number v-model="form.dictSort" controls-position="right" :min="0" />
        </el-form-item>
        <el-form-item label="Status" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in statusOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="Remark" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="Please Enter..."></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">Confirm</el-button>
        <el-button @click="cancel">Cancel</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listData, getData, delData, addData, updateData, exportData } from "@/api/system/dict/data";
import { listType, getType } from "@/api/system/dict/type";

export default {
  name: "Data",
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
      //  Dict 表格Data
      dataList: [],
      // 默认 Dict  Type 
      defaultDictType: "",
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // StatusData  Dict
      statusOptions: [],
      //  Type Data  Dict
      typeOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        dictName: undefined,
        dictType: undefined,
        status: undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        dictLabel: [
          { required: true, message: "Data Label can't be Empty", trigger: "blur" }
        ],
        dictValue: [
          { required: true, message: "Data Value can't be Empty", trigger: "blur" }
        ],
        dictSort: [
          { required: true, message: "Data sort list can't be Empty", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    const dictId = this.$route.params && this.$route.params.dictId;
    this.getType(dictId);
    this.getTypeList();
    this.getDicts("sys_normal_disable").then(response => {
      this.statusOptions = response.data;
    });
  },
  methods: {
    /** 查询 Dict  Type 详细 */
    getType(dictId) {
      getType(dictId).then(response => {
        this.queryParams.dictType = response.data.dictType;
        this.defaultDictType = response.data.dictType;
        this.getList();
      });
    },
    /** 查询 Dict  Type 列表 */
    getTypeList() {
      listType().then(response => {
        this.typeOptions = response.rows;
      });
    },
    /** 查询 Dict Data列表 */
    getList() {
      this.loading = true;
      listData(this.queryParams).then(response => {
        this.dataList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // Data Status Dict 翻译
    statusFormat(row, column) {
      return this.selectDictLabel(this.statusOptions, row.status);
    },
    // Cancel按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单Reset
    reset() {
      this.form = {
        dictCode: undefined,
        dictLabel: undefined,
        dictValue: undefined,
        dictSort: 0,
        status: "0",
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
      this.resetForm("queryForm");
      this.queryParams.dictType = this.defaultDictType;
      this.handleQuery();
    },
    /** Add 按钮Operation */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "Add dict data";
      this.form.dictType = this.queryParams.dictType;
    },
    // 多选框选中Data
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.dictCode)
      this.single = selection.length!=1
      this.multiple = !selection.length
    },
    /** Modify按钮Operation */
    handleUpdate(row) {
      this.reset();
      const dictCode = row.dictCode || this.ids
      getData(dictCode).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "Modify Dict Data";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.dictCode != undefined) {
            updateData(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("Modify Success");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addData(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("Add  Success");
                this.open = false;
                this.getList();
              }
            });
          }
        }
      });
    },
    /** Delete按钮Operation */
    handleDelete(row) {
      const dictCodes = row.dictCode || this.ids;
      this.$confirm('If confirm Delete Dict code "' + dictCodes + '"data?', "Warning", {
          confirmButtonText: "Confirm",
          cancelButtonText: "Cancel",
          type: "warning"
        }).then(function() {
          return delData(dictCodes);
        }).then(() => {
          this.getList();
          this.msgSuccess("Delete Success");
        }).catch(function() {});
    },
    /** Export 按钮Operation */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('Export All?', "Warning", {
          confirmButtonText: "Confirm",
          cancelButtonText: "Cancel",
          type: "warning"
        }).then(function() {
          return exportData(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
