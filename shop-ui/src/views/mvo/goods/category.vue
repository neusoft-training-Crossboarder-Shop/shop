
<!-- @Author: 高歌-->
<!-- @Id: 20175045-->
<!-- @Github : EvilicLufas-->

<template>
  <div class="app-container">

    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-input v-model="listQuery.id" clearable class="filter-item" style="width: 200px;" placeholder="Enter the ID of category"></el-input>
      <el-input v-model="listQuery.name" clearable class="filter-item" style="width: 200px;" placeholder="Enter the name of category"></el-input>
      <el-button  class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">Search</el-button>
      <el-button  class="filter-item" type="success" icon="el-icon-edit" @click="handleCreate">Add</el-button>
      <!--      <el-button v-permission="['GET /mvo/category/list']" class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">Search</el-button>-->
      <!--      <el-button v-permission="['POST /mvo/category/create']" class="filter-item" type="success" icon="el-icon-edit" @click="handleCreate">Add</el-button>-->
      <el-button :loading="downloadLoading" class="filter-item" type="info" icon="el-icon-download" @click="handleDownload">Export</el-button>
    </div>

    <!-- 查询结果 -->
    <el-table v-loading="listLoading" :data="list" element-loading-text="Searching now..." border fit highlight-current-row>

      <el-table-column align="center" label="prcId" prop="prcId"></el-table-column>
      <el-table-column align="center" label="proId" prop="proId"></el-table-column>

      <el-table-column align="center" label="categoryName" prop="categoryName"></el-table-column>
      <el-table-column align="center" label="CategoryPath" prop="CategoryPath"></el-table-column>

      <el-table-column align="center" property="picUrl" label="picUrl">
        <template slot-scope="scope">
          <img v-if="scope.row.picUrl" :src="scope.row.picUrl" width="80"  alt="">
        </template>
      </el-table-column>



      <el-table-column align="center" label="Creater" prop="createdBy"></el-table-column>

      <el-table-column align="center" label="Create_date" prop="createTime"></el-table-column>

      <el-table-column align="center" label="Updater" prop="lastUpdateBy"></el-table-column>

      <el-table-column align="center" label="Update_date" prop="lastUpdateTime"></el-table-column>
      <el-table-column align="center" label="Remark" prop="callCnt"></el-table-column>

      <el-table-column align="center" label="State" prop="stsCd"></el-table-column>
      <el-table-column align="center" label="Deleted" prop="deleted"></el-table-column>

      <el-table-column align="center" label="Operation" width="200" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button  type="primary" size="middle" @click="handleUpdate(scope.row)">Edit</el-button>
          <el-button  type="danger" size="middle" @click="handleDelete(scope.row)">Delete</el-button>
          <!--          <el-button v-permission="['POST /mvo/category/update']" type="primary" size="mini" @click="handleUpdate(scope.row)">Edit</el-button>-->
          <!--          <el-button v-permission="['POST /mvo/category/delete']" type="danger" size="mini" @click="handleDelete(scope.row)">Delete</el-button>-->
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />



    <!-- 添加或修改对话框 -->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :rules="rules" :model="dataForm" status-icon label-position="left" label-width="180px" style="width: 800px; margin-left:50px;">
        <el-form-item label="categoryName" prop="categoryName">
          <el-input v-model="dataForm.CategoryPath"></el-input>
        </el-form-item>
        <el-form-item label="CategoryPath" prop="CategoryPath">
          <el-input v-model="dataForm.categoryName"></el-input>
        </el-form-item>

<!--        <el-form-item label="Description" prop="brdDesc">-->

<!--          <el-card style="height: 700px;width:620px">-->
<!--            &lt;!&ndash;            <quill-editor v-model="dataForm.desc" ref="myQuillEditor" style="height: 580px;width : 580px" :options="editorOption">&ndash;&gt;-->
<!--            <quill-editor v-model="dataForm.brdDesc" ref="myQuillEditor" style="height: 580px;width : 580px" >-->
<!--            </quill-editor>-->
<!--          </el-card>-->

<!--        </el-form-item>-->


        <el-form-item label="category_img" prop="imgId">
          <el-upload
            :headers="headers"
            :action="uploadPath"
            :show-file-list="false"
            :on-success="uploadPicUrl"
            class="avatar-uploader"
            accept=".jpg,.jpeg,.png,.gif">
            <img v-if="dataForm.imgId" :src="dataForm.imgId" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false" type="danger">Cancel</el-button>
        <el-button v-if="dialogStatus ==='create'" type="primary" @click="createData">Confirm</el-button>
        <el-button v-else  type="success" @click="updateData">Confirm</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import { listCategory, createCategory, updateCategory, deleteCategory } from '@/api/mvo/category'
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
    name: "GoodsCategory",
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
          prcId: 'brd_id',
          name: '',
          sort: 'create_time',
          order: 'Desc' //order by create_time desc  mysql 语句
        },
        dataForm: {
          prcId: '',
          proId: '',
          name: '',
          categoryName: '',
          CategoryPath: '',
          imgId: '',
          createdBy: '',
          createTime: '',
          lastUpdateBy: '',
          lastUpdateTime: '',
          callCnt: '',
          deleted: '',
          brdDesc: ''
          // name: '',
          // desc: '',
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
        listCategory(this.listQuery)
          .then(response => {
            this.list = response.data.list;
            this.total = response.data.total;
            this.listLoading = false
          })
          .catch(() => {
            this.list = [];
            this.total = 0;
            this.listLoading = false
          })
      },
      handleFilter() {
        this.listQuery.page = 1
        this.getList()
      },
      resetForm() {
        this.dataForm = {
          prcId: undefined,
          proId: undefined,
          categoryName: undefined,
          CategoryPath: undefined,
          imgId: undefined,
          createdBy: undefined,
          createTime: undefined,
          lastUpdateBy: undefined,
          lastUpdateTime: undefined,
          callCnt: undefined,
          deleted: undefined,
          brdDesc: undefined
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
        this.dataForm.imgId = response.data.url
      },
      createData() {
        this.$refs['dataForm'].validate(valid => {
          if (valid) {
            createCategory(this.dataForm)
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
            updateCategory(this.dataForm)
              .then(() => {
                for (const v of this.list) {
                  if (v.id === this.dataForm.prcId) {
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
        deleteCategory(row)
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
            'category_ID',
            'manufacturer_ID',
            'categoryName',
            'CategoryPath',
            'Desc',
            'imgId'
          ]
          const filterVal = ['prcId','proId', 'categoryName','CategoryPath', 'brdDesc', 'imgId']
          excel.export_json_to_excel2(
            tHeader,
            this.list,
            filterVal,
            'category'
          )
          this.downloadLoading = false
        })
      }
    }
  }
</script>

<style scoped>
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
