<template>
  <div class="app-container">

    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-input v-model="listQuery.id" clearable class="filter-item" style="width: 200px;" placeholder="Enter the ID of brand"/>
      <el-input v-model="listQuery.name" clearable class="filter-item" style="width: 200px;" placeholder="Enter the name of brand"/>
      <el-button v-permission="['GET /admin/brand/list']" class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">Search</el-button>
      <el-button v-permission="['POST /admin/brand/create']" class="filter-item" type="success" icon="el-icon-edit" @click="handleCreate">Add</el-button>
      <el-button :loading="downloadLoading" class="filter-item" type="info" icon="el-icon-download" @click="handleDownload">Export</el-button>
    </div>

    <!-- 查询结果 -->
    <el-table v-loading="listLoading" :data="list" element-loading-text="Searching now..." border fit highlight-current-row>

      <el-table-column align="center" label="Brand ID" prop="brd_id"/>
      <el-table-column align="center" label="Company ID" prop="man_id"/>

      <el-table-column align="center" label="Name_CHN" prop="name_en"/>
      <el-table-column align="center" label="Name_EN" prop="name_chn"/>

      <el-table-column align="center" property="img_id" label="img_id">
        <template slot-scope="scope">
          <img v-if="scope.row.img_id" :src="scope.row.img_id" width="80">
        </template>
      </el-table-column>

<!--      <el-table-column align="center" min-width="400px" label="Desc" prop="desc"/>-->

      <el-table-column align="center" label="Creater" prop="created_by"/>


      <el-table-column align="center" label="Create_date" prop="create_time"/>

      <el-table-column align="center" label="Updater" prop="last_update_by"/>

      <el-table-column align="center" label="Update_date" prop="last_update_time"/>
      <el-table-column align="center" label="Remark" prop="call_cnt"/>

      <el-table-column align="center" label="State" prop="sts_cd"/>
      <el-table-column align="center" label="Deleted" prop="deleted"/>


      <el-table-column align="center" label="Operation" width="200" class-name="small-padding fixed-width">
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
        <el-form-item label="Name (CHN)" prop="name_en">
          <el-input v-model="dataForm.name"/>
        </el-form-item>
        <el-form-item label="Name (EN)" prop="name_chn">
          <el-input v-model="dataForm.name"/>
        </el-form-item>

        <el-form-item label="Description" prop="simpleDesc">

          <el-card style="height: 700px;width:620px">
            <quill-editor v-model="content" ref="myQuillEditor" style="height: 580px;width : 580px" :options="editorOption">
            </quill-editor>
          </el-card>

        </el-form-item>


        <el-form-item label="Company_img" prop="img_id">
          <el-upload
            :headers="headers"
            :action="uploadPath"
            :show-file-list="false"
            :on-success="uploadPicUrl"
            class="avatar-uploader"
            accept=".jpg,.jpeg,.png,.gif">
            <img v-if="dataForm.img_id" :src="dataForm.img_id" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"/>
          </el-upload>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false" type="danger">Cancel</el-button>
        <el-button v-if="dialogStatus=='create'" type="primary" @click="createData">Confirm</el-button>
        <el-button v-else  type="success" @click="updateData">Confirm</el-button>
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
  import { listBrand, createBrand, updateBrand, deleteBrand } from '@/api/mvo/brand'
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
    name: 'brand',
    components: { Pagination, quillEditor },
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
          sort: 'create_time',
          order: 'brd_id'
        },
        dataForm: {
          id: undefined,
          name: '',
          desc: '',
          floorPrice: undefined,
          img_id: undefined
        },
        dialogFormVisible: false,
        dialogStatus: '',
        textMap: {
          update: 'Edit',
          create: 'Create'
        },
        rules: {
          name: [
            { required: true, message: 'Name cannot be empty!', trigger: 'blur' }
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
        listBrand(this.listQuery)
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
          img_id: undefined
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
        this.dataForm.img_id = response.data.url
      },
      createData() {
        this.$refs['dataForm'].validate(valid => {
          if (valid) {
            createBrand(this.dataForm)
              .then(response => {
                this.list.unshift(response.data.data)
                this.dialogFormVisible = false
                this.$notify.success({
                  title: 'Success',
                  message: 'create successfully!'
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
            updateBrand(this.dataForm)
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
                  message: 'Update Successfully!'
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
        deleteBrand(row)
          .then(response => {
            this.$notify.success({
              title: 'Success',
              message: 'Delete Successfully'
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
            'brand_ID',
            'company_ID',
            'name_en',
            'name_chn',
            'Desc',
            'img_id'
          ]
          const filterVal = ['brd_id','man_id', 'name_en','name_chn', 'desc', 'img_id']
          excel.export_json_to_excel2(
            tHeader,
            this.list,
            filterVal,
            'bran'
          )
          this.downloadLoading = false
        })
      }
    }
  }
</script>
