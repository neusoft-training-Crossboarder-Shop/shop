<template>
    <div id="container" class="container">
      <div class="research_container" >

        <el-collapse style="text-align: center;font-size: large;font-weight: bold">
          <el-collapse-item  title="Search Filter" name="1">
        <el-form type="left" ref="searchCondition" :model="searchCondition" label-width="80px">
            <el-row :gutter="10">
             <el-col :span="12">
               <el-form-item label="Item Title">
                 <el-input v-model="searchCondition.title" placeholder="Key Words"></el-input>
               </el-form-item>
             </el-col>
            </el-row>
            <el-row :gutter="10">
              <el-col :span="7">
                <el-form-item label="Category">
                  <el-input v-model="searchCondition.categoryName" placeholder="Input Category Name"></el-input>
                </el-form-item>
              </el-col>
             <el-col :span="7">
               <el-form-item label="">
                  <el-input v-model="searchCondition.manufacturerName" placeholder="Input Manufacturer Name"></el-input>
               </el-form-item>
             </el-col>
               <el-col :span="7">
                 <el-form-item label="Brand">
                   <el-input v-model="searchCondition.brandName" placeholder="Input Brand Name"></el-input>
                 </el-form-item>
               </el-col>
            </el-row>
          <el-row :gutter="10">
           <el-col :span="6">
             <el-form-item label="Minimum Price" label-width="40%">
               <el-input v-model="searchCondition.lowPrice" ></el-input>
             </el-form-item>
           </el-col>
           <el-col :span="6">
             <el-form-item label="Maximum Price" label-width="42%">
                <el-input v-model="searchCondition.topPrice" ></el-input>
             </el-form-item>
           </el-col>
            <el-col :span="2">
              <el-form-item>
                <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">Search</el-button>
              </el-form-item>
            </el-col>
            <el-col :span="2">
              <el-form-item>
                <el-button icon="el-icon-refresh" size="mini" @click="resetForm('searchCondition')">Reset</el-button>
              </el-form-item>
            </el-col>
            <el-col :span="2">
              <el-form-item>
                <el-button
                  type="danger"
                  icon="el-icon-refresh"
                  size="mini"
                  @click="handleClearCache"
                  v-hasPermi="['system:browse:read']"

                >Clean Cache
                </el-button>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
          </el-collapse-item>
        </el-collapse>
      </div>

      <el-divider></el-divider>
    <div class="infinite-list-wrapper" style="height:1000px;overflow:auto">
      <ul   v-infinite-scroll="load"  infinite-scroll-disabled="disabled" infinite-scroll-throttle-delay="500" infinite-scroll-distance="10" >
<!--        <el-backtop target=".page-component__scroll .el-scrollbar__wrap" ></el-backtop>-->
        <li v-for="item in items" class="card"  :key="item.proId" @click="redirect(item.proId)" >
            <el-image
              style="height: 100%;"
              :src="item.imageList[0].uri"
              fit="cover"
             >
              <div slot="error" class="image-slot">
                <i class="el-icon-picture-outline"></i>
              </div>
            </el-image>
            <div style="padding: 14px;">
              <p class="item-title">{{item.title}}</p>
              <div>
                <el-tag
                  v-for="category in item.categories"
                  :key="category.prcId"
                  effect="light">
                  {{ category.categoryName }}
                </el-tag>
                <el-tag
                  effect="dark" >
                  {{item.productBrand.nameEn }}
                </el-tag>
                <el-tag
                  effect="light" class="item-description">
                  <el-icon
                    class="el-icon-s-shop"/>
                  {{item.manufacturer.nameEn}}
                </el-tag>
              </div>
              <p class="item-price" >${{item.retailPrice}}</p>
            </div>
          </li>
            <li v-if="loading" class="backup" style="display: block;width: 100%;text-align: center;font-size: large;font-weight: bold"> Loading...</li>
            <li v-if="noMore"  class="backup" style="display: block;width: 100%;text-align: center;font-size: large;font-weight: bold">No More</li>
        </ul>
      </div>
    </div>
</template>

<script>
  import { search } from "@/api/bvo/browse";
  import { Loading } from 'element-ui';
  export default {
        name: "index.vue",
        data: function () {
          return {
            loadingInstance:{},
            searchCondition : {
               title:"",
                brandName: "" ,
                categoryName:'',
                manufacturerName:'',
                lowPrice: undefined,
                topPrice: undefined,
                pageNum: 0,
                pageSize: 6,
            },

            items:[
              // {
              //   proId:'1',
              //   title:"",
              //   categories:[{categoryName:''}],
              //   imageList:[{uri:''}],
              //   manufacturer:{
              //     nameEn:'',
              //   },
              //   productBrand:{
              //     nameEn:'',
              //   },
              //   retailPrice:"8.9"
              // }
            ],
            loading: false,
            noMore:false,
        }
      },
    computed: {
      disabled () {
        let result =this.loading || this.noMore;
        console.log("Disabled:"+result)
        return result
      }
    },
      created(){
        this.loadingInstance=Loading.service({
          target:"#container",
          body:"true",
          fullscreen:"false",
          lock:"true",
        });
       },
      methods:{
        getList(){
          search(this.searchCondition).then(response=>{
            this.loadingInstance.close();
            let products=response.data.rows
            products.forEach(item=>{
              item.imageList.forEach(image=>{
                image.uri=process.env.VUE_APP_BASE_API+image.uri
              })
            })
            this.items=[...this.items,...products]
            if(this.items.length===response.data.total){
              this.noMore = true;
            }
            this.loading = false;
          })
        },
        load () {
          this.searchCondition.pageNum=this.searchCondition.pageNum+1
          this.loading = true;
          this.getList()
        },
        redirect(id){
          this.$router.push({
            path:`/bvo/good/${id}`
          })
        },
        handleQuery() {
          this.searchCondition.pageNum = 1;
          this.items=[]
          this.loadingInstance=Loading.service({
            target:"#container",
            body:"true",
            fullscreen:"false",
            lock:"true",
          });
          setTimeout(()=>{
          },500),
          this.getList();
        },
        reset() {
          this.searchCondition = {
            title:"",
            brandName: "" ,
            categoryName:'',
            manufacturerName:'',
            lowPrice: undefined,
            topPrice: undefined,
            pageNum: 0,
            pageSize: 6,
          };
        },
        resetForm(formName) {
           this.reset();
        },
        handleClearCache() {
          this.$notify({
            title: 'Carry',
            message: 'Cache Button',
            type: 'success'
          });
        },

      },

    }
</script>

<style scoped>
.container{
  margin: 2%;
}
.research_container{
  position: -webkit-sticky;
  position: sticky;
  padding: 1%;
  height: 10%;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
}

.card:hover{
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.5), 0 0 6px rgba(0, 0, 0, .04) ;
  cursor: pointer;
}


.card{
  display: inline-block;

  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
  width: 22%;
  height: 20%;
  margin: 1%;
  overflow: hidden
}

.item-description{
  margin-top: 3%;
  margin-bottom: 3%;

  width: 90%;
  overflow: hidden;
  -webkit-line-clamp: 2;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-box-orient: vertical;
}

.item-title{
  font-weight: bold;
  font-size: 1.1em;
  white-space: nowrap;
  text-overflow:ellipsis;
  overflow:hidden;
}

.item-price{
  text-align: right;
  margin:  2px;
  color: rgba(221, 69, 69, 1);
  font-weight : 800 ;
  font-size: large;
  letter-spacing: 5px;
}

.infinite-container{
  display: block;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
  padding-top: 1%;
  overflow: hidden;
}



</style>
