<template xmlns="http://www.w3.org/1999/html">
  <div>
  <div>
    <div class="app-container">
      <el-form type="left" ref="queryForm"  :model="queryParams" label-width="80px">
        <el-row :gutter="10">
          <el-col :span="8">
            <el-form-item label="Item Title">
              <el-input v-model="queryParams.title" placeholder="Key Words"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="Category">
              <el-input v-model="queryParams.categoryName" placeholder="Input Category Name"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="Brand">
              <el-input v-model="queryParams.brandName" placeholder="Input Brand Name"></el-input>
            </el-form-item>
          </el-col>
        </el-row>


        <el-row :gutter="10">
          <el-col :span="6">
            <el-form-item label="Minimum Price" label-width="40%">
              <el-input v-model="queryParams.lowPrice" ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="Maximum Price" label-width="42%">
              <el-input v-model="queryParams.topPrice" ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
          <el-form-item label=" Create   time">
            <el-date-picker
              v-model="dateRange"
              size="middle"
              style="width: 240px"
              value-format="yyyy-MM-dd"
              type="daterange"
              range-separator="-"
              start-placeholder="Start  Time   "
              end-placeholder="End   Time   "
            ></el-date-picker>
          </el-form-item>
          </el-col>
        </el-row>


        <el-row :gutter="10">
          <div style="margin-left: 2%;margin-bottom: 2%">
              <el-button
                type="primary"
                icon="el-icon-plus"
                size="middle"
                @click="handleAdd"
                v-hasPermi="['mvo:pro:add']"
              >Add</el-button>



              <el-button
                type="success"
                icon="el-icon-edit"
                size="middle"
                :disabled="single"
                @click="handleUpdate"

                v-hasPermi="['mvo:pro:edit']"
              >Modify</el-button>

                <el-button
                  type="danger"
                  icon="el-icon-delete"
                  size="middle"
                  :disabled="multiple"
                  @click="handleDelete"
                  v-hasPermi="['mvo:pro:remove']"
                >delete</el-button>

                <el-button icon="el-icon-refresh"  style="float: right;margin-right: 10%" size="middle" @click="resetForm('queryForm')">Reset</el-button>
                <el-button type="primary" style="float: right" icon="el-icon-search" size="middle" @click="handleQuery">Search</el-button>

          </div>
        </el-row>
      </el-form>

      <el-table :data="productList" @row-click="redirect" v-loading="loading"  @selection-change="handleSelectionChange">
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" inline class="demo-table-expand">
              <el-row :gutter="10">
               <el-col :span="7">
                 <el-form-item label="SKU CD">
                   <span>{{ props.row.skuCd }}</span>
                 </el-form-item>
               </el-col>
                <el-col :span="7">
                  <el-form-item label="Ean">
                    <span>{{ props.row.ean }}</span>
                  </el-form-item>
                </el-col>
                <el-col :span="7">
                  <el-form-item label="Model">
                    <span>{{ props.row.model }}</span>
                  </el-form-item>
                </el-col>
              </el-row>

          <el-row :gutter="10">
                <el-col :span="7">
                    <el-form-item label="warranty Day">
                      <span>{{ props.row.warrantyDay  }} {{props.row.timeUnit}}</span>
                    </el-form-item>
                </el-col>

                 <el-col :span="12">
                   <el-form-item label="Package">
                     <span> Weight×Width×Height×Length </span>
                     <span>{{props.row.productPackageInfos[0].weight}}</span>
                     <span  >×</span>
                     <span>{{props.row.productPackageInfos[0].width}}</span>
                     <span  >×</span>
                     <span>{{props.row.productPackageInfos[0].height}}</span>
                     <span  >×</span>
                     <span>{{props.row.productPackageInfos[0].length}}</span>
                   </el-form-item>
               </el-col>
          </el-row>





              <el-form-item label="Warranty">
                <span>{{ props.row.warranty }}</span>
              </el-form-item>


            </el-form>
          </template>
        </el-table-column>

        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="Pro Id" align="center" prop="proId" />
        <el-table-column label="Brand Image" align="center" prop="picUrl" width="200">
          <template slot-scope="scope">
            <img :src="scope.row.images[0].uri" @click.stop="editCropper(scope.row)" title="点击Upload " class="img-list" />
          </template>
        </el-table-column>
        <el-table-column label="Brand Name" align="center" prop="productBrand.nameEn" :show-overflow-tooltip="true"/>
        <el-table-column label="category" align="center" prop="categories[0].categoryPath" :show-overflow-tooltip="true"/>
        <el-table-column label="title" align="center" prop="title" :show-overflow-tooltip="true" />
        <el-table-column label="retailPrice" align="center" prop="retailPrice" :show-overflow-tooltip="true" />
        <el-table-column label="mininumRetailPrice" align="center" prop="mininumRetailPrice" :show-overflow-tooltip="true" />

        <el-table-column label="Create Time" align="center" prop="createTime" width="170" :show-overflow-tooltip="true">

        </el-table-column>

        <el-table-column label="Last Update Time" align="center" prop="lastUpdateTime" width="170" :show-overflow-tooltip="true">

        </el-table-column>

        <el-table-column label="Operation" align="center" class-name="small-padding fixed-width" width="100"  v-hasPermi="['mvo:pro:state:edit']">
          <template slot-scope="scope">
            <el-button v-if="scope.row.prostate == 'In Shelf'" size="middle" type="danger" @click.stop="OnOutShelf(scope.row.proId,'Off Shelf')">Off Shelf</el-button>
            <el-button v-else type="success" size="middle" @click.stop="OnOutShelf(scope.row.proId,'In Shelf')">Put Shelf</el-button>
          </template>
        </el-table-column>

        <el-table-column label="Operation" v-hasPermi="['mvo:pro:edit']" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button
              size="middle"
              type="text"
              icon="el-icon-edit"
              @click.stop="handleUpdate(scope.row)"
              v-hasPermi="['mvo:pro:edit']"
            >Modify</el-button>

            <el-button
              size="middle"
              type="text"
              icon="el-icon-delete"
              @click.stop="handleDelete(scope.row)"
              v-hasPermi="['mvo:pro:remove']"
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
      <el-dialog :title="title" :visible.sync="open" width="800px"  append-to-body style="overflow: auto;height: 80%">
        <el-tabs type="border-card">
          <el-tab-pane label="Product Info">
            <el-form ref="productForm" :model="product" :rules="productRule"  label-width="25%">
              <!--          商品-->
              <div class="section">
                <p style="display: block;text-align: center;font-size: large">Product Info</p>
                <el-form-item label="Title"  prop="title">
                  <el-input v-model="product.title" placeholder="placeholder"></el-input>
                </el-form-item>

                <el-form-item label="Model" prop="model">
                  <el-input v-model="product.model" placeholder="placeholder"></el-input>
                </el-form-item>
                <el-form-item label="Sku Cd" prop="skuCd">
                  <el-input v-model="product.skuCd" placeholder="placeholder"></el-input>
                </el-form-item>
                <el-form-item label="Upc" prop="upc">
                  <el-input v-model="product.upc" placeholder="placeholder"></el-input>
                </el-form-item>
                <el-form-item label="Ean" prop="ean">
                  <el-input v-model="product.ean" placeholder="placeholder"></el-input>
                </el-form-item>
                <el-form-item label="RetailPrice" prop="retailPrice">
                  <el-input v-model="product.retailPrice" placeholder="placeholder"></el-input>
                </el-form-item>
                <el-form-item label="Mininum RetailPrice" prop="mininumRetailPrice">
                  <el-input v-model="product.mininumRetailPrice" placeholder="placeholder"></el-input>
                </el-form-item>
                <el-form-item label="Warranty Unit" >
                  <el-input v-model="product.time_unit" placeholder="placeholder"></el-input>
                </el-form-item>
                <el-form-item label="Warranty Day" >
                  <el-input v-model="product.warrantyDay" placeholder="placeholder"></el-input>
                </el-form-item>
                <el-form-item label="Warranty" >
                  <el-input v-model="product.warranty" type="textarea" placeholder="placeholder"></el-input>
                </el-form-item>



              </div>
              <el-divider></el-divider>

              <!--          包裹信息-->
              <div class="section">
                <p style="display: block;text-align: center;font-size: large">Package Info</p>

                <div v-for="(item,index) in product.productPackageInfos">
                  <div :key="index">
                    <el-form-item label="width">
                      <el-input v-model="item.width" placeholder="placeholder"></el-input>
                    </el-form-item>

                    <el-form-item label="height">
                      <el-input v-model="item.height" placeholder="placeholder"></el-input>

                    </el-form-item>

                    <el-form-item label="length">
                      <el-input v-model="item.length" placeholder="placeholder"></el-input>

                    </el-form-item>

                    <el-form-item label="weight">
                      <el-input v-model="item.weight" placeholder="placeholder"></el-input>
                    </el-form-item>
                  </div>
                </div>

              </div>

              <!--          品牌-->
              <div class="section">
                <p style="display: block;text-align: center;font-size: large">Brand Info</p>
                <el-row :gutter="10">
                  <el-form-item label="Brand Selection">
                    <el-select v-model="product.brdId" filterable clearable placeholder="placeholder" style="display: inline-block;width: 100%">
                      <el-option
                        v-for="item in brandList"
                        :key="item.brdId"
                        :label="item.nameEn"
                        :value="item.brdId">
                        <span style="float: left">{{ item.nameEn }}</span>
                        <span style="float: right; color: #8492a6; font-size: 13px">{{ item.nameCn }}</span>
                      </el-option>
                    </el-select>
                  </el-form-item>

                </el-row>
              </div>
              <el-divider></el-divider>
              <!--          种类-->
              <div class="section">
                <p style="display: block;text-align: center;font-size: large">Category</p>
                <div v-for="(item,index) in product.categories">
                  <div :key="index">
                    <el-form-item label="Category Name">
                      <el-input v-model="item.categoryName" placeholder="placeholder"></el-input>
                    </el-form-item>
                    <el-form-item label="Category Path">
                      <el-input v-model="item.categoryPath" placeholder="placeholder"></el-input>
                    </el-form-item>
                  </div>
                </div>
              </div>

                <el-button type="primary" @click="submitForm" v-hasPermi="['mvo:pro:edit']" >Submit</el-button>
                <el-button @click="cancel">Cancel</el-button>

            </el-form>


            <!--          -->
          </el-tab-pane>
          <el-tab-pane label="Description Editor" @click="openDescription">
            <div v-for="(description) in this.productDescriptions">
              <div :key="description.pdnId" style="margin-top: 5%;margin-bottom: 5%;padding-top: 10%;padding-bottom: 10%">
                <p style="display: block;text-align: center;font-size: large">{{description.platformType}} Description</p>
                <editor v-model="description.descrition" :maxSize="maxSize" ></editor>
              </div>
            </div>
            <el-button type="primary" @click="updateDescription">Submit</el-button>
          </el-tab-pane>
        </el-tabs>


      </el-dialog>


      <el-dialog :title="title" :visible.sync="imageOpen" width="800px" append-to-body @opened="modalOpened">
        <el-row>
          <el-col :xs="24" :md="12" :style="{height: '350px'}">

            <vue-cropper
              ref="cropper"
              :img="currentImage.uri"
              :info="true"
              :autoCrop="cropperOptions[currentOptions].autoCrop"
              :autoCropWidth="cropperOptions[currentOptions].autoCropWidth"
              :autoCropHeight="cropperOptions[currentOptions].autoCropHeight"
              :fixedBox="cropperOptions[currentOptions].fixedBox"
              @realTime="realTime"
              v-if="visible"
            />
          </el-col>
          <el-col :xs="24" :md="12" :style="{height: '350px'}">
            <div v-if="currentOptions==0" class="small-product-upload-preview">
              <img :src="previews.url" :style="previews.img" />
            </div>
            <div v-else class="big-product-upload-preview" >
              <img :src="previews.url" :style="previews.img" />
            </div>
          </el-col>
        </el-row>
        <br />
        <el-row>
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
          <el-col :lg="{span: 2,offset: 3}" :md="2">
            <el-button v-if="currentOptions==1" @click="currentOptions=0" type="primary">Adding Big Image...</el-button>
            <el-button v-if="currentOptions==0" @click="currentOptions=1" type="primary">Adding Small Image...</el-button>
          </el-col>
          <el-col :lg="{span: 2, offset: 6}" :md="2">
            <el-button type="primary" size="middle" v-hasPermi="['mvo:pro:image:edit']"  @click="uploadImg()">提 交</el-button>
          </el-col>
        </el-row>
        <el-upload
          action="#"
          class="upload-demo"
          :http-request="requestUpload"
          :show-file-list="true"
          :file-list="fileList"
          :before-upload="beforeUpload"
          :on-preview="handlePreview"
          :on-remove="handleRemove"
          list-type="picture"
        >
          <el-button size="middle" v-hasPermi="['mvo:pro:image:edit']">
            Upload
            <i class="el-icon-upload el-icon--right"></i>
          </el-button>
        </el-upload>
      </el-dialog>

    </div>
  </div>
</div>

</template>

<script>
  import {
    api_deleteProduct, api_deleteProductImageByImageId, api_getAllBrands,
    api_getProductByProId, api_getProductDescriptionByProId, api_getProductImageByProId,
    api_getProductList,
    api_insertProduct,
    api_updateProduct, api_updateProductDescription, api_updateProductProState, api_uploadProductImage
  } from "../../../api/mvo/product";
  import { VueCropper } from "vue-cropper";
  import editor from "../../../components/Editor/index"


  export default {
        name: "index.vue",
      components:{
        editor,
        VueCropper
      },
      data: function () {
        return{
          modifyProfile:false,
          //商品基本信息
          fileList: [{name: 'food.jpeg', url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'}, {name: 'food2.jpeg', url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'}],
          product:{
            proId:'',
            title:'',
            model:'',
            skuCd:'',
            upc:'',
            ean:'',
            retailPrice:'',
            mininumRetailPrice:'',
            time_unit:'',
            warrantyDay: '',
            warranty:'',
        // 品牌
            productBrand:{
              brdId:'',
              nameEn:'',
            },

        // 尺寸
            productPackageInfos:[
              {
                pckId:'',
                proId:'',
                width:'',
                length:'',
                height:'',
                weight:'',
              }
          ],
        // category
            categories:[
              {
              prcId:'',
              proId:'',
              categoryName:'',
              categoryPath:'',
              }
            ]
          },
          brandList:[
            {brdId:1,nameEn:'AJ1',nameCn:'乔丹1'},
            {brdId:2,nameEn:'AJ2',nameCn:'乔丹2'},
          ],

          productRule: {
            title: [
              { required: true, message: 'Cannot be null', trigger: 'blur' },
            ],
            model: [
              { required: true, message: 'Cannot be null', trigger: 'change' }
            ],
            skuCd: [
              {min: 3, max: 10, message: 'Length should between 3 to 10 char', trigger: 'blur'},
              { required: true, message: 'Cannot be null', trigger: 'change' }
            ],
            upc: [
              { required: true, message: 'Cannot be null', trigger: 'blur' }
            ],
            ean: [
              { required: true, message: 'Cannot be null', trigger: 'blur' }
            ],
            retailPrice: [
              { required: true, message: 'Cannot be null', trigger: 'blur' }
            ],
            mininumRetailPrice: [
              { required: true, message: 'Cannot be null', trigger: 'blur' }
            ],
          },
          dateRange:[],
          queryParams :{
            brdId:undefined,
            title:'',
            brandName:'',
            categoryName:'',
            lowPrice:'',
            topPrice:'',
            pageNum: 1,
            pageSize: 10,
          },
          // manufacturerLoading:false,
          // 遮罩层
          loading: false,
          // 选中数组
          ids: [],
          // 非单个禁用
          single: true,
          // 非多个禁用
          multiple: true,
          // 总条数
          total: 10,

          // 是否显示弹出层
          open: false,

          productList:[
            {
              proId:'',
              // brdId:123,
              title:'',
              retailPrice:'',
              miniumRetailPrice:1139,

              productBrand:{
                brdId:'',
                nameEn:'',
                nameCn:'',
              },

              categories:[{
                prcId:'1',
                categoryPath:'12',
                categoryName:'12',
              }],

              prostate:"In Shelf",

              createTime:"2020-07-01 07:27:04",
              lastUpdateTime:"2020-07-01 07:27:04",

              skuCd:'',
              ean:'',
              model:'',

              timeUnit:'',
              warranty:'',
              warrantyDay:'',
              productPackageInfos:[
                {
                  pckId:'',
                  proId:'',
                  width:'11',
                  height:'12',
                  length:'123',
                  weight:'1234',
               }
              ],
              images:[{
                    imgId:'',
                    proId:'',
                    uri:'',
                    typeCd:'',
              }],
            },
          ],
          productDescriptions:[
            {
               platformType:'',
               pdnId:'',
               proId:'',
               descrition:''
            }
          ],
          // 弹出层标题
          title: "",

          // 是否显示弹出层
          imageOpen: false,
          // 是否显示cropper
          visible: false,
          // 弹出层标题
          // title: " Modify  头像",

          currentImage:{
            uri:undefined,
            proId:undefined,
            imgId:undefined,
            typeCd:undefined,
          },
          currentOptions:0,
          cropperOptions:[
            {
              autoCrop: true, // 是否默认生成截图框
              autoCropWidth: 200, // 默认生成截图框宽度
              autoCropHeight: 150, // 默认生成截图框高度
              fixedBox: true // 固定截图框大小 不允许改变
            },
            {
              autoCrop: true, // 是否默认生成截图框
              autoCropWidth: 380, // 默认生成截图框宽度
              autoCropHeight: 250, // 默认生成截图框高度
              fixedBox: true // 固定截图框大小 不允许改变
            }
          ],
          previews: {},

          //富文本图片
          maxSize:4000

        }
      },
      created() {
         this.getList();
         api_getAllBrands().then(res=>{
           this.brandList=res.data
         })
      },
      methods:{

        redirect(row, event, column){
          this.$router.push({
            path:`/bvo/good/${row.proId}`
          })
        },


        /** 查询参数列表 */
        getList() {
          this.loading = true;
          api_getProductList(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
              this.productList = response.data.rows;
              this.productList.forEach(product=>{
                 product.images.forEach(image=>{
                   if(image.uri!=undefined || image.uri!='' ){
                     image.uri=process.env.VUE_APP_BASE_API+image.uri
                   }
                 })
              })
              this.total = response.data.total;
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
          this.title = "Add Product";
        },
        reset() {
          this.product = {
            proId:undefined,
            title:'',
            model:'',
            skuCd:'',
            upc:'',
            ean:'',
            retailPrice:'',
            mininumRetailPrice:'',
            time_unit:'',
            warrantyDay: '',
            warranty:'',

            brdId:'',
            // 品牌
            productBrand:{
              brdId:undefined,
              nameEn:'',
            },
            // 尺寸
            productPackageInfos:[
              {
                width:'',
                length:'',
                height:'',
                weight:'',
              }
            ],
            // category
            categories:[
              {
                categoryName:'',
                categoryPath:'',
              }
            ],
            images:[{
              imgId:undefined,
              uri:'',
              typeCd:0,
            }],
          };
          this.resetForm("productForm");
        },
        /** Search 按钮Operation */
        handleQuery() {
          this.queryParams.pageNum = 1;
          this.getList();
        },
        /** Reset  按钮Operation */
        resetQuery() {
          this.dateRange=[]
          this.resetForm("queryForm");
          this.handleQuery();

        },
        resetForm(formName) {
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
          this.ids = selection.map(item => item.proId)
          this.single = selection.length!=1
          this.multiple = !selection.length
        },
        /**  Modify  按钮Operation */
        handleUpdate(row) {
          // this.reset();
          const proId = row.proId || this.ids;
          api_getProductByProId(proId).then(response => {
            this.product = response.data;
            this.open = true;
            this.title = 'Modify Page';

            api_getProductDescriptionByProId(proId).then(res=>{
                    this.productDescriptions = res.data;
                    console.log(this.productDescriptions)

            })

          });
        },
        updateDescription(){
          api_updateProductDescription(this.productDescriptions).then(res=>{

          })
        },
        openDescription(){
            this.$notify(
              {
                type:'success',
                message:'打开'
              }
            )
        },

        OnOutShelf(proId,status){
          console.log(proId)
          console.log(status)
          api_updateProductProState(proId,status).then(res=>{
            this.getList()
          })
        },

        /** 提交按钮 */
        submitForm: function() {
          this.$refs["productForm"].validate(valid => {
            if (valid) {
              if (this.product.proId != undefined) {
                api_updateProduct(this.product).then(response => {
                  this.getList();
                  this.open = false;
                });
              } else {
                api_insertProduct(this.product).then(response => {
                  //  Add  Success
                  this.getList();
                  this.open = false;
                });
              }
            }
          });
        },
        /**  Delete 按钮Operation */
        handleDelete(row) {
          const proId = row.proId || this.ids;
          this.$confirm('Are you sure to delete No."' + proId + '"?', "Warning", {
            confirmButtonText: "Yes",
            cancelButtonText: "No",
            type: "warning"
          }).then(function() {
            return api_deleteProduct(proId);
          }).then(() => {
            this.getList();
          }).catch(function() {});
        },
        //


        editCropper(row) {
          this.imageOpen = true;
          this.currentOptions=0;
          this.product=row
          this.currentImage =row.images[0]
          this.getProductImages(row.proId);
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
                  //切换当前图片
                this.currentImage={
                      uri:reader.result,
                      typeCd:this.currentOptions,
                      proId:this.product.proId,
                      imgId:0
                    }
                    // Add  到fileList中
                    let image1 = {
                      url:reader.result,
                      proId:this.product.proId,
                      typeCd:this.currentOptions,
                      name:(this.currentOptions==0)?'small':'big',
                      status:''
                    }
                    this.fileList.push(image1);
            };
          }


        },
        // Upload 图片
        uploadImg() {
          this.$refs.cropper.getCropBlob(data => {
            let formData = new FormData();
            formData.append("productfile", data);
            formData.append("proId", this.currentImage.proId);
            formData.append("imgId", this.currentImage.imgId || 0);
            formData.append("typeCd", this.currentOptions);
              // api_uploadProductImage(formData).then(response => {
            api_uploadProductImage(formData).then(response => {
                this.getProductImages(this.product.proId);
            });
          });
        },
        // 实时预览
        realTime(data) {
          this.previews = data;
        },

        handlePreview(file){
          let image={
            name:(file.typeCd==0)?'small':'big',
            uri:file.url,
            proId:file.proId,
            typeCd:file.typeCd,
            imgId: file.imgId
          }
          this.currentOptions = file.typeCd;
          this.currentImage = image;
        },
        handleRemove(file,fileList){
           let imgId=file.imgId
          if (imgId != undefined && imgId != null && imgId!= 0 ) {
            api_deleteProductImageByImageId(imgId).then(res=>{
              this.getProductImages(this.product.proId);
            })

            if (file.url != undefined && url != ''){
              this.$notify({
                type:'error',
                message:'small picture can\'t be deleted'
              })
              this.getProductImages(this.product.proId);
            }
          }
        },
        getProductImages(proId) {
          api_getProductImageByProId(proId).then(res=>{
            this.fileList=[]
            res.data.forEach(item=>{
              let image2 = {
                url:process.env.VUE_APP_BASE_API+item.uri,
                proId:item.proId,
                typeCd:item.typeCd,
                imgId: item.imgId,
                name:(item.typeCd==0)?'small':'big'
              }
              this.fileList.push(image2)
            })
          })
        }
      }
    // }

    }
</script>

<style scoped>
  /*表格每一行 be hover时的样式设置*/
  .el-table >>> .el-table__body tr:hover>td {
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
    cursor: pointer;
    background-color: #d9d9d9;
  }

  .demo-table-expand {
    font-size: 0;
  }
  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
  }
  .section{
    box-shadow: 0 1px 3px rgba(0, 0, 0, 0.2);
    margin: 2%;
    padding: 2%;
  }


</style>
