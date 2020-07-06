<template>
  <div class="app-container">

    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-input v-model="listQuery.id" clearable class="filter-item" style="width: 200px;" placeholder="Enter the id to check "/>
      <el-input v-model="listQuery.name" clearable class="filter-item" style="width: 200px;" placeholder="Input the name  (CHN/EN)"/>
      <el-button v-permission="['GET /admin/brand/list']" class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">Search</el-button>
      <el-button v-permission="['POST /admin/brand/create']" class="filter-item" type="primary" icon="el-icon-edit" @click="handleCreate">Add</el-button>
      <el-button :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">Export to EXCEL</el-button>
    </div>

    <!-- 查询结果 -->
    <el-table v-loading="listLoading" :data="list" element-loading-text="Searching...please wait for a while" border fit highlight-current-row>

      <el-table-column align="center" label="Company_ID" prop="id"/>

      <el-table-column align="center" label="Company_name(CHN)" prop="name_CHN"/>

      <el-table-column align="center" label="Company Name (EN)" prop="name_EN"/>

      <el-table-column align="center" property="picUrl" label="Company_img">
        <template slot-scope="scope">
          <img v-if="scope.row.picUrl" :src="scope.row.picUrl" width="80">
        </template>
      </el-table-column>

      <el-table-column align="center" label="GMC_type" prop="gmc_report_type"/>

      <el-table-column align="center" label="GMC_url" prop="gmc_report_url"/>

      <el-table-column align="center" min-width="100px" label="GMC_Description" prop="desc"/>

      <el-table-column align="center" label="Creater" prop="creater"/>

      <el-table-column align="center" label="Create_date" prop="create_date"/>

      <el-table-column align="center" label="Updater" prop="updater"/>

      <el-table-column align="center" label="Update_date" prop="update_date"/>

      <el-table-column align="center" label="Remark" prop="remark"/>

      <el-table-column align="center" label="State" prop="state"/>

      <!--      <el-table-column align="center" label="底价" prop="floorPrice"/>-->

      <el-table-column align="center" label="OPERATION" width="200" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button v-permission="['POST /admin/brand/update']" type="primary" size="mini" @click="handleUpdate(scope.row)">Edit</el-button>
          <el-button v-permission="['POST /admin/brand/delete']" type="danger" size="mini" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />

    <!-- 添加或修改对话框 -->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :rules="rules" :model="dataForm" status-icon label-position="left" label-width="180px" style="width: 800px; margin-left:50px;">
        <el-form-item label="Name (CHN)" prop="name_CHN">
          <el-input v-model="dataForm.name"/>
        </el-form-item>
        <el-form-item label="Name (EN)" prop="name_EN">
          <el-input v-model="dataForm.name"/>
        </el-form-item>


        <el-form-item label="Description" prop="simpleDesc">

          <el-card style="height: 700px;width:620px">
            <quill-editor v-model="content" ref="myQuillEditor" style="height: 580px;width : 580px" :options="editorOption">
            </quill-editor>
          </el-card>

        </el-form-item>

        <el-form-item label="Company_img" prop="picUrl">
          <el-upload
            :headers="headers"
            :action="uploadPath"
            :show-file-list="false"
            :on-success="uploadPicUrl"
            class="avatar-uploader"
            accept=".jpg,.jpeg,.png,.gif">
            <img v-if="dataForm.picUrl" :src="dataForm.picUrl" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"/>
          </el-upload>
        </el-form-item>

        <el-form-item label="GMC_Report_type" prop="gmc_report_type">
          <el-input v-model="dataForm.desc"/>
        </el-form-item>

        <el-form-item label="GMC_Report_url" prop="gmc_report_url">
          <el-input v-model="dataForm.url"/>
        </el-form-item>



        <!--        <el-form-item label="底价" prop="floorPrice">-->
        <!--          <el-input v-model="dataForm.floorPrice"/>-->
        <!--        </el-form-item>-->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">Cancel</el-button>
        <el-button v-if="dialogStatus=='create'" type="primary" @click="createData">Confirm</el-button>
        <el-button v-else type="primary" @click="updateData">Confirm</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<style>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #20a0ff;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 120px;
    height: 120px;
    line-height: 120px;
    text-align: center;
  }
  .avatar {
    width: 145px;
    height: 145px;
    display: block;
  }
</style>


<script>
  import { listCompany, createCompany, updateCompany, deleteCompany } from '@/api/mvo/company'
  import { uploadPath } from '@/api/mvo/storage'
  import { getToken } from '@/utils/auth'
  import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
  import {
    quillEditor
  } from 'vue-quill-editor'
  import 'quill/dist/quill.core.css'
  import 'quill/dist/quill.snow.css'
  import 'quill/dist/quill.bubble.css'


  export default {
    name: 'company',
    components: { Pagination , quillEditor},
    data() {
      return {

        uploadPath,
        list: [],
        total: 0,
        listLoading: true,
        listQuery: {
          page: 1,
          limit: 20,
          id: undefined,
          name: undefined,
          sort: 'add_time',
          order: 'desc'
        },
        content: null,
        editorOption: {},

        dataForm: {
          id: undefined,
          name: '',
          desc: '',
          floorPrice: undefined,
          picUrl: undefined
        },
        dialogFormVisible: false,
        dialogStatus: '',
        textMap: {
          update: 'Edit',
          create: 'Create'
        },
        rules: {
          name: [
            { required: true, message: 'Company_name不能为空', trigger: 'blur' }
          ]
        },
        downloadLoading: false
      }
    },
    computed: {
      headers() {
        return {
          'X-Litemall-Admin-Token': getToken()
        }
      }
    },
    created() {
      this.getList()
    },
    methods: {
      getList() {
        this.listLoading = true
        listCompany(this.listQuery)
          .then(response => {
            this.list = response.data.data.list
            this.total = response.data.data.total
            this.listLoading = false
          })
          .catch(() => {
            this.list = []
            this.total = 0
            this.listLoading = false
          })
      },
      handleFilter() {
        this.listQuery.page = 1
        this.getList()
      },
      resetForm() {
        this.dataForm = {
          id: undefined,
          name: '',
          desc: '',
          floorPrice: undefined,
          picUrl: undefined
        }
      },
      handleCreate() {
        this.resetForm()
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].clearValidate()
        })
      },
      uploadPicUrl: function(response) {
        this.dataForm.picUrl = response.data.url
      },
      createData() {
        this.$refs['dataForm'].validate(valid => {
          if (valid) {
            createCompany(this.dataForm)
              .then(response => {
                this.list.unshift(response.data.data)
                this.dialogFormVisible = false
                this.$notify.success({
                  title: 'Success',
                  message: '创建成功'
                })
              })
              .catch(response => {
                this.$notify.error({
                  title: 'Fail',
                  message: response.data.errmsg
                })
              })
          }
        })
      },
      handleUpdate(row) {
        this.dataForm = Object.assign({}, row)
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].clearValidate()
        })
      },
      updateData() {
        this.$refs['dataForm'].validate(valid => {
          if (valid) {
            updateCompany(this.dataForm)
              .then(() => {
                for (const v of this.list) {
                  if (v.id === this.dataForm.id) {
                    const index = this.list.indexOf(v)
                    this.list.splice(index, 1, this.dataForm)
                    break
                  }
                }
                this.dialogFormVisible = false
                this.$notify.success({
                  title: 'Success',
                  message: 'Update Success'
                })
              })
              .catch(response => {
                this.$notify.error({
                  title: 'Fail',
                  message: response.data.errmsg
                })
              })
          }
        })
      },
      handleDelete(row) {
        deleteCompany(row)
          .then(response => {
            this.$notify.success({
              title: 'Success',
              message: 'Delete Success'
            })
            const index = this.list.indexOf(row)
            this.list.splice(index, 1)
          })
          .catch(response => {
            this.$notify.error({
              title: 'Fail',
              message: response.data.errmsg
            })
          })
      },
      handleDownload() {
        this.downloadLoading = true
        import('@/vendor/Export2Excel').then(excel => {
          const tHeader = [
            'CompanyID',
            'Company_name',
            'Description',
            '低价',
            'Company_img'
          ]
          const filterVal = ['id', 'name', 'desc', 'floorPrice', 'picUrl']
          excel.export_json_to_excel2(
            tHeader,
            this.list,
            filterVal,
            'Company Info'
          )
          this.downloadLoading = false
        })
      }
    }
  }
</script>
