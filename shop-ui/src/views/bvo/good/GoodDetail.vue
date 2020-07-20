<template>
  <div class="container">
    <div class="main_container">
    <el-row :gutter="10">
     <el-col :span="10" style="position: relative;top:30px" >
       <el-carousel :interval="4000" type="card">
         <el-carousel-item v-for="image in pro.images" :key="image.imgId">
           <el-image
             class="img"
             style="width: 100%; height: 100%"
             :src="image.uri"
             fit="fill">`
             <div slot="error" class="image-slot">
               <i class="el-icon-picture-outline"></i>
             </div>
           </el-image>
         </el-carousel-item>
       </el-carousel>
     </el-col>
     <el-col :span="14">
       <div class="good-detail">
        <p class="title">{{pro.title}}</p>
         <div>
           <el-tag
             v-for="category in pro.categories"
             :key="category.prcId"
             effect="light">
             {{ category.categoryPath }}
           </el-tag>

           <el-tag
           effect="dark">
           {{pro.productBrand.nameEn }}
          </el-tag>

           <el-tag
           effect="dark">
             <el-icon
             class="el-icon-s-shop"/>
           {{pro.manufacturer.nameEn}}
          </el-tag>


         </div>

       <p class="price_container">
         <i class="el-icon-price-tag"></i>
         <span  class="tag"  style="display: inline-block;width: 20%">Retail Price</span>
         <span class="price">${{pro.retailPrice}}</span>
         <span  class="tag" style="display: inline-block;width: 30%">Minimum Retail Price</span>
         <span class="price">${{pro.mininumRetailPrice}}</span>
      </p>

         <p >
           <span  class="tag" style="padding-right: 45%" >Weight×Width×Height×Length</span>

           <span>{{pro.productPackageInfos[0].weight}}</span>
           <span  >×</span>
           <span>{{pro.productPackageInfos[0].width}}</span>
           <span  >×</span>
           <!--           <span  class="tag" >Height</span>-->
           <span>{{pro.productPackageInfos[0].height}}</span>
           <span  >×</span>
           <!--           <span  class="tag" >Length</span>-->
           <span>{{pro.productPackageInfos[0].length}}</span>
         </p>

       <div class="content">
         <p >
           <span  class="tag">Model</span>
           <span ><el-tag type="success" style="display: inline-block;text-align: center;width: 10%"><i class="el-icon-info"></i>{{pro.model}}</el-tag></span>
         </p>

         <div>
           <span class="tag" style=" font: italic 1em Georgia, serif;">warranty</span><span >{{pro.warrantyDay}}{{pro.timeUnit}}</span>
           <div class="warranty">{{pro.warranty}}</div>
         </div>

       </div>

       <el-row :gutter="10">
          <el-input-number size="medium" v-model="amount"   ></el-input-number>
          <el-button-group>
            <el-button icon="el-icon-star-off" @click="addToWishList">Add to wishlist</el-button>
          </el-button-group>
          <el-popover
            placement="top-start"
            trigger="click">
            <div>
              <el-select v-model="selectedStore" placeholder="请 Selection  "  multiple collapse-tags>
                <el-option
                  v-for="item in storeList"
                  :key="item.storeId"
                  :label="item.storeName"
                  :value="item.storeId">
                  <span style="float: left">{{ item.storeName }}</span>
<!--                  <span style="float: right; color: #8492a6; font-size: 13px">{{ platformTypeFormatter(item.platformType)}}</span>-->
                </el-option>
              </el-select>
              <el-button type="primary" @click="pushToStore">PUSH</el-button>
            </div>
            <el-button type="danger"  slot="reference" icon="el-icon-goods">Purchase</el-button>
          </el-popover>
        </el-row>
       </div>
     </el-col>
    </el-row>
    </div>

    <div class="detail_info">
      <el-collapse style="padding: 3%">
        <el-collapse-item title="INFO OF MANUFACTURER" name="1" style="padding: 1%" >
          <div>
          <div><span class="tag">Name En</span> <span>{{pro.manufacturer.nameEn}}</span></div>
          <div><span class="tag">Name Cn</span> <span>{{pro.manufacturer.nameCn}}</span></div>
          <div><span class="tag">gmcReportUrl</span><span style="display: block;position: relative;left: 15%;top: -35px">{{pro.manufacturer.gmcReportUrl}}</span></div>
          <div><span class="tag">description</span> <span style="display: block;position: relative;left: 15%;top: -35px">{{pro.manufacturer.description}}</span></div>
          </div>
        </el-collapse-item>

        <el-collapse-item v-for="description in pro.productDescriptions" :key="description.pdnId" :title="description.platformType.concat(' Description').toUpperCase()" name="2" style="padding: 1%">
          <div>
            <div><span class="tag">Last Update Time</span> <span>{{parseTime(description.lastUpdateTime)}}</span></div>
            <div>
              <span class="tag">Description

              </span>

              <span style="display: block;position: relative;left: 15%;top: -35px" v-html="description.descrition">

              </span>

            </div>
          </div>
        </el-collapse-item>
      </el-collapse>
    </div>
  </div>
</template>

<script>
  import {getGoodDetail} from "../../../api/bvo/browse";
  import {addProIntoWishList} from "../../../api/bvo/wishlist";
  import {getStoreList} from "../../../api/bvo/store";
  import {purchaseProduct} from "../../../api/bvo/order";

  export default {
        name: "GoodDetail",
        data:function() {
          return {

            queryParams :{
              pageNum: 1,
              pageSize: 100,
              storeId:undefined,
              storeName: undefined,
              platformType: undefined
            },

            storeList:[],
            options: [
            {
                  label: 'Amazon',
                  options: [{
                    value: 1,
                    label: 'storeName1'
                  },
                  {
                    value: 2,
                    label: 'storeName2'
                  }
                ]
            },
            {
              label: 'Ebay',
              options: [{
                value: 1,
                label: 'storeName1'
              }, {
                value: 2,
                label: 'storeName1'
              }, {
                value: 3,
                label: 'storeName1'
              }, {
                value: 4,
                label: 'storeName1'
              }]
            }],

           selectedStore:{},
           amount:1,

            pro:{
              proId:'',
              images:[
                {imgId:1,uri:""},
              ],
              createdTime:'',
              //Part 1
              title : "",
              retailPrice:123,
              mininumRetailPrice:1139,
              model:106,
              //分类信息
              categories:[
                {prcId:1,categoryName:"",categoryPath:""},
              ],
              //品牌信息
              productBrand:
                {brdId:1,nameCn:"",nameEn:""},
              productPackageInfos:[
                {weight:'',width:'',height:'',length:''}
              ],
              //制造商相关信息
              manufacturer:{
                nameCn:'',
                nameEn:'',
                description:'',
                gmcReportUrl:'',
              },
              ean: '',
              skuCd:'',
              upc:'',

              // 保质期相关
              warrantyDay:7,
              timeUnit:'',
              warranty:'',

              platformType:[
                {text:'Amazon',value:1}, {text:'Ebay',value:2}
              ],

              //评论
              productDescriptions:[
                {pdnId:1,lastUpdateTime:'',platformType:'',descrition:''}
              ],
              //打包信息
            },
          }
        },
        created() {
          const proID = this.$route.params && this.$route.params.proID;
          getGoodDetail(proID).then(response=>{
            this.pro=response.data;
            this.pro.images.forEach(image=>{
              if(image.uri!=undefined || image.uri!='' ){
                image.uri=process.env.VUE_APP_BASE_API+image.uri
              }
            })
          });

          // this.getDicts("store_platform_type").then(response => {
          //   let data=response.data;
          //   data.forEach((item,index)=>{
          //     this.platformType[index]=
          //       {
          //         text:item.dictLabel,
          //         value:item.dictValue
          //       }
          //   })
          // });


          getStoreList(this.queryParams).then(response => {
              this.storeList = response.rows;
            }
          );

        },
        methods:{
          pushToStore:function () {
            let data = {
                amount:this.amount,
               proId:this.pro.proId,
               storeIds:this.selectedStore,
            }
            console.log(data)

            purchaseProduct(data).then(res=>{
              this.$notify({
                type:"success",
                title:"执行",
                message:"Already经 Add  到商店"
              })
            })

          },
          addToWishList:function () {
            addProIntoWishList({proId:this.pro.proId}).then(response=>{
                //   Add  Success了
            })
          },
          platformTypeFormatter(platformType){
            // console.log(platformType)
            // console.log(this.platformType[parseInt(platformType)+1])
            return this.platformType[parseInt(platformType)+1]['text']
          },

        },

    }
</script>

<style scoped>

.container{
  margin: 2%;
  padding: 1%;
  height: 40%;
}

.tag{
  display: inline-block;
  width: 15%;
}
.content{
  /*background-color: #ffdaf3;*/

}

.main_container{
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.5), 0 0 6px rgba(0, 0, 0, .04) ;
}
.good-detail{
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
  padding-left: 2%;
  padding-top: 1%;
  padding-bottom: 1%;
}

.title{
  font-weight: bold;
  font-size: large;
}

.price{
  font-weight: bold;
  letter-spacing: 5px;
  font-size: x-large;
}

.price_container{
  background-color: #ed5565;
  color: white;
  padding: 2%;
}

.tag{
  padding: 2%;
  font: italic 1em Georgia, serif;
}

.detail_info{
  margin-top: 2%;
  margin-bottom: 2%;
}

.warranty{
  font-size: small;
  color: #a4a4a4;
  margin-left: 4%;
  margin-right: 1%;
  padding: 2%;
}
</style>
