<template>
  <div>
    <template>
      <div v-if="notRegistered || modifyProfile" style="margin: 5%">
        <h1>Manufacturer Info</h1>

        <el-form :model="manufacturer" :rules="manufacturerRule" ref="manufacturerForm" label-width="10%" >
              <el-form-item label="Chinese Name" prop="nameCn">
                <el-input v-model="manufacturer.nameCn " placeholder="Enter Chinese Name"></el-input>
              </el-form-item>
              <el-form-item label="English Name" prop="nameEn">
                <el-input v-model="manufacturer.nameEn" placeholder="Enter English Name"></el-input>
              </el-form-item>
              <el-form-item label="manDesc" prop="manDesc" height = 200px wodth = 200px>
<!--                <el-input-->
<!--                  type="textarea"-->
<!--                  :rows="2"-->
<!--                  placeholder="Please Enter 内容"-->
<!--                  v-model="manufacturer.manDesc">-->
<!--                </el-input>-->
<!--                <editor v-model="description.descrition" :maxSize="maxSize" ></editor>-->

                <div class="ql-editor" >
<!--                <div class="ql-editor" style="margin-top: 50%;margin-bottom: 50%;padding-top: 10%;padding-bottom: 10%">-->
<!--                  <p class="ql-editor" style="display: block;text-align: center;font-size: small">{{manufacturer.manDesc}}</p>-->
                  <editor class="ql-editor" v-model="manufacturer.manDesc" :maxSize="maxSize" ></editor>
                </div>


              </el-form-item>


              <el-form-item label="GMC Report Type" prop="gmcReportType">
                <el-select v-model="manufacturer.gmcReportType" placeholder="placeholder">
                  <el-option
                    v-for="item in gmcReportTypeOptions"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>


              <el-form-item label="GMC Report Url" prop="gmcReportUrl">
                <el-input v-model="manufacturer.gmcReportUrl" placeholder="placeholder"></el-input>
              </el-form-item>

              <el-form-item>
                <el-button v-if="manufacturer.manId == undefined " type="primary" @click="submitManufacturerForm('manufacturerForm')">Save</el-button>
                <el-button v-if="manufacturer.manId != undefined " type="primary" @click="submitManufacturerForm('manufacturerForm')">Update</el-button>
                <el-button v-if="manufacturer.manId == undefined" @click="resetManufacturerForm('manufacturerForm')">Reset</el-button>
                <el-button v-if="manufacturer.manId != undefined" type="primary" @click="modifyProfile=false">返回</el-button>
              </el-form-item>
            </el-form>
          </div>

          <div v-else>
          <div class="app-container">

            <el-table  class="el-table--enable-row-hover el-table__body" v-loading="manufacturerLoading" :data="manufacturers" >
              <el-table-column type="expand">
                <template slot-scope="props">
                  <el-form label-position="left" class="table-expand">

                    <el-form-item label="manId">
                      <span>{{ props.row.manId }}</span>
                    </el-form-item>
                    <el-form-item label="nameEn">
                      <span>{{ props.row.nameEn }}</span>
                    </el-form-item>
                    <el-form-item label="nameCn">
                      <span>{{ props.row.nameCn }}</span>
                    </el-form-item>
<!--                    <el-form-item label="imgId">-->
<!--                      <span>{{ props.row.imgId }}</span>-->
<!--                    </el-form-item>-->

<!--                    <el-form-item label="picUrl">-->
<!--              <span>-->
<!--                <template>-->
<!--                  <img v-if="props.row.picUrl" :src="props.row.picUrl" width="220"  alt="">-->
<!--                </template>-->
<!--              </span>-->
<!--                    </el-form-item>-->
                    <el-form-item label="createdBy">
                      <span>{{ props.row.createdBy }}</span>
                    </el-form-item>
                    <el-form-item label="createTime">
                      <span>{{ props.row.createTime }}</span>
                    </el-form-item>
                    <el-form-item label="lastUpdateBy">
                      <span>{{ props.row.lastUpdateBy }}</span>
                    </el-form-item>
                    <el-form-item label="lastUpdateTime">
                      <span>{{ props.row.lastUpdateTime }}</span>
                    </el-form-item>
                    <el-form-item label="manDesc">
                      <p  v-html="props.row.manDesc">{{props.row.manDesc}}</p>
<!--                      <span>{{ props.row.manDesc }}</span>-->
<!--                      <span>{{ props.row.manDesc }}</span>-->
                    </el-form-item>
                    <el-form-item label="deleted">
                      <span>{{ props.row.deleted }}</span>
                    </el-form-item>

                  </el-form>
                </template>
              </el-table-column>
              <el-table-column label="Chinese Name" align="center" prop="nameCn" />
              <el-table-column label="English Name" align="center" prop="nameEn" />


              <el-table-column label="GMC Report Type" align="center"  :show-overflow-tooltip="true" >
                <template slot-scope="scope">
                  {{gmcReportTypeOptions[scope.row.gmcReportType-1].label}}
                </template>
              </el-table-column>

              <el-table-column label="GMC Report Url" align="center" prop="gmcReportUrl" width="140" :show-overflow-tooltip="true" />
              <el-table-column label="CreateTime" align="center" prop="createTime" width="170">
              </el-table-column>

              <el-table-column label="Last Update Time" align="center" prop="lastUpdateTime" width="170">
              </el-table-column>


              <el-table-column label="Operation" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                  <!--         el-button Authority 里面v-hasPermi="['system:config:edit']"-->
                  <el-button
                    size="middle"
                    type="primary"
                    icon="el-icon-edit"
                    @click.stop="modifyProfile = true"
                    v-hasPermi="['mvo:profile:edit']"
                  >Modify</el-button>

                </template>
              </el-table-column>

        </el-table>


          </div>

          <div  class="app-container">
            <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="20%">
                <el-input
                  v-model="queryParams.brdId"
                  placeholder="Please Enter Brand Id"
                  clearable
                  size="middle"
                  style="width: 240px"
                  @keyup.enter.native="handleQuery"
                />
                <el-input
                  v-model="queryParams.nameEn"
                  placeholder="Please Enter Brand Name (English)"
                  clearable
                  size="middle"
                  style="width: 240px"
                  @keyup.enter.native="handleQuery"
                />

                <el-input
                  v-model="queryParams.nameCn"
                  placeholder="Please Enter Brand Name (Chinese)"
                  clearable
                  size="middle"
                  style="width: 240px"
                  @keyup.enter.native="handleQuery"
                />

              <el-form-item>
                <el-button type="primary" icon="el-icon-search" size="middle" @click="handleQuery" v-hasPermi="['mvo:brand:query']">Search</el-button>
                <el-button icon="el-icon-refresh" size="middle" @click="resetQuery">Reset</el-button>
              </el-form-item>
            </el-form>


            <el-row :gutter="10" class="mb8">
              <el-col :span="1.5">
                <el-button
                  type="primary"
                  icon="el-icon-plus"
                  size="middle"
                  @click="handleAdd"

                  v-hasPermi="['mvo:brand:add']"
                >Add</el-button>
              </el-col>
              <el-col :span="1.5">

                <el-button
                  type="success"
                  icon="el-icon-edit"
                  size="middle"
                  :disabled="single"
                  @click="handleUpdate"
                  v-hasPermi="['mvo:brand:edit']"
                >Modify</el-button>
              </el-col>
              <el-col :span="1.5">
                <el-button
                  type="danger"
                  icon="el-icon-delete"
                  size="middle"
                  :disabled="multiple"
                  @click="handleDelete"
                  v-hasPermi="['mvo:brand:remove']"
                >Delete
                </el-button>
              </el-col>

            </el-row>

            <el-table   class="el-table--enable-row-hover el-table__body" v-loading="loading" :data="brandList"  @selection-change="handleSelectionChange">

              <el-table-column type="expand">
                <template slot-scope="props">
                  <el-form label-position="left" class="table-expand">
                    <el-form-item label="brdId">
                      <span>{{ props.row.brdId }}</span>
                    </el-form-item>
                    <el-form-item label="manId">
                      <span>{{ props.row.manId }}</span>
                    </el-form-item>
                    <el-form-item label="nameEn">
                      <span>{{ props.row.nameEn }}</span>
                    </el-form-item>
                    <el-form-item label="nameCn">
                      <span>{{ props.row.nameCn }}</span>
                    </el-form-item>
                    <el-form-item label="imgId">
                      <span>{{ props.row.imgId }}</span>
                    </el-form-item>

                    <el-form-item label="picUrl">
              <span>
                <template>
                  <img v-if="props.row.picUrl" :src="props.row.picUrl" width="220"  alt="">
                </template>
              </span>
                    </el-form-item>
                    <el-form-item label="createdBy">
                      <span>{{ props.row.createdBy }}</span>
                    </el-form-item>
                    <el-form-item label="createTime">
                      <span>{{ props.row.createTime }}</span>
                    </el-form-item>
                    <el-form-item label="lastUpdateBy">
                      <span>{{ props.row.lastUpdateBy }}</span>
                    </el-form-item>
                    <el-form-item label="lastUpdateTime">
                      <span>{{ props.row.lastUpdateTime }}</span>
                    </el-form-item>
                    <el-form-item label="brdDesc">
                      <span>{{ props.row.brdDesc }}</span>
                    </el-form-item>
                    <el-form-item label="deleted">
                      <span>{{ props.row.deleted }}</span>
                    </el-form-item>

                  </el-form>
                </template>
              </el-table-column>

              <el-table-column type="selection" width="55" align="center" />
              <el-table-column label="Brand Id" align="center" prop="brdId" />
              <el-table-column label="Manufacturer Id" align="center" prop="manId" />
              <el-table-column label="En_Name" align="center" prop="nameEn" />
              <el-table-column label="Ch_Name" align="center" prop="nameCn" />


              <el-table-column label="Brand Image" align="center" prop="picUrl" width="200">
                <template slot-scope="scope">
                  <img v-bind:src="scope.row.picUrl" @click="editCropper(scope.row)" title="点击Upload " class="img-list" />
                </template>
              </el-table-column>
              <el-table-column label="CreateTime" align="center" prop="createTime" width="170">

              </el-table-column>

              <el-table-column label="Creater" align="center" prop="createdBy"></el-table-column>

              <el-table-column label="Last Update Time" align="center" prop="lastUpdateTime" width="170">
              </el-table-column>

              <el-table-column label="Updater" align="center" prop="lastUpdateBy" ></el-table-column>

              <el-table-column label="Operation" width = 280px align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">


                  <el-button
                    size="small"
                    align="center"
                    type="primary"
                    icon="el-icon-edit"
                    @click.stop="handleUpdate(scope.row)"
                    v-hasPermi="['mvo:brand:edit']"
                  >Modify</el-button>

                  <el-button
                    size="small"
                    align="center"
                    type="danger"
                    icon="el-icon-delete"
                    @click.stop="handleDelete(scope.row)"
                    v-hasPermi="['mov:brand:remove']"
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

            <!--  Add  或 Modify  参数配置对话框 -->
            <el-dialog :title="title" :visible.sync="open" width="80%" append-to-body>
              <el-form ref="form" :model="form" :rules="rules" label-width="30%">
                <el-form-item label="English Name" prop="nameEn">
                  <el-input v-model="form.nameEn" placeholder="Please Enter Store　Name" />
                </el-form-item>
                <el-form-item label="Chinese Name" prop="nameCn">
                  <el-input v-model="form.nameCn" placeholder="Please enter Authentication Code" />
                </el-form-item>


                <el-form-item label="Brand Image"  prop="picUrl">
                  <el-upload action="#" :http-request="requestUpload" :show-file-list="false" :before-upload="beforeUpload">
                    <el-button size="middle">
                      Upload
                      <i class="el-icon-upload el-icon--right"></i>
                    </el-button>
                  </el-upload>

                </el-form-item>

<!--                  <el-button v-bind:src="scope.row.picUrl" @click="editCropper(scope.row)" title="点击Upload " class="img-list" />-->



              </el-form>
              <div slot="footer" class="dialog-footer">
<!--                <el-button type="success"  @click="editCropper(scope.row)"-->
<!--                           title="点击Upload " class="img-list">Upload Picture</el-button>-->
                <el-button type="primary" @click="submitForm">确 定</el-button>
                <el-button type="danger" @click="cancel">取 消</el-button>
              </div>
            </el-dialog>

            <el-dialog :title="title" :visible.sync="imageOpen" width="800px" append-to-body @opened="modalOpened">
              <el-row>
                <el-col :xs="24" :md="12" :style="{height: '350px'}">

                  <vue-cropper
                    ref="cropper"
                    :img="options.img"
                    :info="true"
                    :autoCrop="options.autoCrop"
                    :autoCropWidth="options.autoCropWidth"
                    :autoCropHeight="options.autoCropHeight"
                    :fixedBox="options.fixedBox"
                    @realTime="realTime" 
                    v-if="visible"
                  />
                </el-col>
                <el-col :xs="24" :md="12" :style="{height: '350px'}">
                  <div class="productBrand-upload-preview">
                    <img :src="previews.url" :style="previews.img" />
                  </div>
                </el-col>
              </el-row>
              <br />
              <el-row>
                <el-col :lg="2" :md="2">
                  <el-upload action="#" :http-request="requestUpload" :show-file-list="false" :before-upload="beforeUpload">
                    <el-button size="middle">
                      Upload
                      <i class="el-icon-upload el-icon--right"></i>
                    </el-button>
                  </el-upload>
                </el-col>
                <el-col :lg="{span: 1, offset: 2}" :md="2">
                  <el-button icon="el-icon-plus" size="middle" @click="changeScale(1)"></el-button>
                </el-col>
                <el-col :lg="{span: 1, offset: 1}" :md="2">
                  <el-button icon="el-icon-minus" size="middle" @click="changeScale(-1)"></el-button>
                </el-col>
                <el-col :lg="{span: 1, offset: 1}" :md="2">
                  <el-button icon="el-icon-refresh-left" size="middle" @click="rotateLeft()"></el-button>
                </el-col>
                <el-col :lg="{span: 1, offset: 1}" :md="2">
                  <el-button icon="el-icon-refresh-right" size="middle" @click="rotateRight()"></el-button>
                </el-col>
                <el-col :lg="{span: 2, offset: 6}" :md="2">
                  <el-button type="primary" size="middle" @click="uploadImg()">提 交</el-button>
                </el-col>
              </el-row>
            </el-dialog>
          </div>
        </div>

    </template>
      </div>
</template>

<script>
  import { VueCropper } from "vue-cropper";
  import {api_uploadBrand} from "../../../api/mvo/brand";
  import {api_getBrand,api_getBrandList,api_updateBrand,api_addBrand,api_delBrand} from "../../../api/mvo/brand";
  import {api_getManufacturer,api_insertManufacturer,api_updateManufacturer} from "../../../api/mvo/manufacturer";
  import editor from "../../../components/Editor/index"


  export default {
    name: "index.vue",
    components: { editor,VueCropper },
    data:  function () {
      return{


        notRegistered:true,
        modifyProfile:false,

        manufacturer:{
          manId:undefined,
          sysUserId:undefined,
          nameCn:undefined,
          nameEn:'',
          manDesc:undefined,
          gmcReportType:undefined,
          gmcReportUrl:undefined,
        },

        manufacturers:[
          // {
          //   manId:1,
          //   sysUserId:1,
          //   nameCn:'123',
          //   nameEn:'123',
          //   manDesc:'1',
          //   gmcReportType:'1',
          //   gmcReportUrl:'1',
          //   createdBy:'1',
          //   createTime:'1',
          //   lastUpdateBy:'1',
          //   lastUpdateTime:'1'
          // }
        ],

        gmcReportTypeOptions:[{label:'TUV',value:1},{label:'UL',value:2}],

        manufacturerRule: {
          nameCn: [
            { required: true, message: 'Cannot be null', trigger: 'blur' },
          ],
          nameEn: [
            { required: true, message: 'Cannot be null', trigger: 'change' }
          ],
          gmcReportType: [
            { required: true, message: 'Cannot be null', trigger: 'change' }
          ],
          gmcReportUrl: [
            { required: true, message: '请填写活动形式', trigger: 'blur' }
          ]
        },
        queryParams :{
          nameEn:undefined,
          nameCn:undefined,
          brdId:undefined,
          pageNum: 1,
          pageSize: 10,
        },
        rules: {
          nameEn: [
            { required: true, message: "English name can't be empty", trigger: "blur" }
          ],
          nameCn: [
            { required: true, message: "Chinese name can't be empty", trigger: "blur" }
          ],
        },
        manufacturerLoading:false,
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
        // 参数表格Data
        typeOptions:[
          // "Amazon",
          // "Ebay"
        ],
        brandList:[
          {

            brdId:123,
            manId:'',
            nameEn:"好宝宝网店",
            nameCn:1,
            createTime:"2020-07-01 07:27:04",
            lastUpdateTime:"2020-07-01 07:27:04",
            brdDesc:'',
            picUrl:'',
          },
        ],
        // 弹出层标题
        title: "",

        form: {
          brdId:undefined,
          nameEn:undefined,
          nameCn:undefined,
        },
        // 是否显示弹出层
        imageOpen: false,
        // 是否显示cropper
        visible: false,
        // 弹出层标题
        title: " Modify  头像",
        options: {
          img: '', //裁剪图片的地址
          autoCrop: true, // 是否默认生成截图框
          autoCropWidth: 200, // 默认生成截图框宽度
          autoCropHeight: 150, // 默认生成截图框高度
          fixedBox: true // 固定截图框大小 不允许改变
        },
        previews: {}

      }
    },
    created() {
      api_getManufacturer().then(res=>{
         if (res.data.manId == undefined) {
             this.notRegistered=true
         }else{
           this.manufacturers.push(res.data)
           this.manufacturer=res.data
           this.notRegistered=false
           this.getList();
         }
      })

    },
    methods:{


      modify(row, event, column){
         this.manufacturer=row
         this.modifyProfile=true
      },
      redirect(row, event, column){
        this.$router.push({
          path:`/bvo/storeDetail/${row.storeId}`
        })
      },


      resetManufacturerForm(formName) {

        this.$refs[formName].resetFields();

      },

      submitManufacturerForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {

            if(this.manufacturer.manId == undefined){
              api_insertManufacturer(this.manufacturer).then(res=>{
                  api_getManufacturer().then(res=>{
                    this.manufacturer=res.data
                    this.notRegistered = false
                    this.manufacturers[0]=res.data
                  })
              })

            }else{
              api_updateManufacturer(this.manufacturer).then(res=>{
                api_getManufacturer().then(res=>{
                    this.manufacturers[0]=res.data
                })
              })
            }
          }
        })
      },

      /** 查询参数列表 */
      getList() {
        this.loading = true;
        api_getBrandList(this.queryParams).then(response => {
            this.brandList = response.rows;
            this.brandList.forEach(item=>{
              if(item.picUrl!=undefined || item.picUrl!='' ){
                item.picUrl=process.env.VUE_APP_BASE_API+item.picUrl
              }
            })
            this.total = response.total;
            this.loading = false;
            console.log(this.brandList)
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
        this.title = " Add  品牌";
      },
      reset() {
        this.form = {
            brdId:undefined,
            nameEn:"",
            nameCn:"",
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
        this.handleQuery();

      },
      resetForm(formName) {
        // this.$notify({
        //   title: '执行',
        //   message: 'Reset  按钮',
        //   type: 'success'
        // });
        if (this.$refs[formName]!==undefined) {
          this.$refs[formName].resetFields();
        }
      },
      /** Add 按钮Operation */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = " Add  参数";
      },
      // 多选框选中Data
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.brdId)
        this.single = selection.length!=1
        this.multiple = !selection.length
      },
      /**  Modify  按钮Operation */
      handleUpdate(row) {
        this.reset();
        const brdId = row.brdId || this.ids;
        api_getBrand(brdId).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "Modify Page";
        });
      },
      /** 提交按钮 */
      submitForm: function() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.brdId != undefined) {
              api_updateBrand(this.form).then(response => {
                this.getList();
                this.open = false;
              });
            } else {
              api_addBrand(this.form).then(response => {
                // Add  Success
                this.getList();
                this.open = false;

              });
            }
          }
        });
      },
      /**  Delete 按钮Operation */
      handleDelete(row) {
        const brdId = row.brdId || this.ids;
        this.$confirm('Are you sure to delete No."' + brdId + '"?', "Warning", {
          confirmButtonText: "Yes",
          cancelButtonText: "No",
          type: "warning"
        }).then(function() {
          return api_delBrand(brdId);
        }).then(() => {
          this.getList();
        }).catch(function() {});
      },
      // 编辑头像
      editCropper(row) {
        this.imageOpen = true;
        this.options.img=row.picUrl;
        this.options.brdId=row.brdId;
      },
      // 打开弹出层End   时的回调
      modalOpened() {
        this.visible = true;
      },
      // 覆盖默认的Upload 行为
      requestUpload() {

      },
      // 向左旋转
      rotateLeft() {
        this.$refs.cropper.rotateLeft();
      },
      // 向右旋转
      rotateRight() {
        this.$refs.cropper.rotateRight();
      },
      // 图片缩放
      changeScale(num) {
        num = num || 1;
        this.$refs.cropper.changeScale(num);
      },
      // Upload 预处理
      beforeUpload(file) {
        if (file.type.indexOf("image/") == -1) {
          this.msgError("文件格式错误，请Upload 图片Type ,如：JPG，PNG后缀的文件。");
        } else {
          const reader = new FileReader();
          reader.readAsDataURL(file);
          reader.onload = () => {
            this.options.img = reader.result;
          };
        }
      },
      // Upload 图片
      uploadImg() {
        this.$refs.cropper.getCropBlob(data => {
          let formData = new FormData();
          formData.append("brandfile", data);
          formData.append("brdId", this.options.brdId);
          api_uploadBrand(formData).then(response => {
            if (response.code === 200) {
              this.open = false;
              this.getList();
            }
            this.visible = false;
          });
        });
      },
      // 实时预览
      realTime(data) {
        this.previews = data;
      }
    }
  }
</script>

<style scoped>
  /*表格每一行 be hover时的样式设置*/
  .el-table >>> .el-table__body tr:hover>td {
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
    cursor: pointer;
    background-color: #d9d9d9;
  }

</style>
