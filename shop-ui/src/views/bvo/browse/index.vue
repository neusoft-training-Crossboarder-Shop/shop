<template>
    <div class="container">
      <div class="research_container" >
        <el-form type="left" ref="searchCondition" :model="searchCondition" label-width="80px">
            <el-row :gutter="10">
             <el-col :span="12">
               <el-form-item label="商品名称">
                 <el-input v-model="searchCondition.title" placeholder="请输入商品名称"></el-input>
               </el-form-item>
             </el-col>
            </el-row>
            <el-row :gutter="10">
              <el-col :span="7">
                <el-form-item label="类别">
                  <el-select v-model="searchCondition.selectedCategory" placeholder="可选择类别">
                    <el-option
                      v-for="item in categoryList"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value">
                    </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
             <el-col :span="7">
               <el-form-item label="供应商">
                 <el-select collapse-tags filterable multiple v-model="searchCondition.selectedSupplierList" placeholder="可选择供应商">
                   <el-option
                     v-for="item in supplierList"
                     :key="item.id"
                     :label="item.value"
                     :value="item.id">
                   </el-option>
                 </el-select>
               </el-form-item>
             </el-col>
               <el-col :span="7">
                 <el-form-item label="品牌">
                   <el-select collapse-tags filterable multiple v-model="searchCondition.selectedBrandList" placeholder="可以选择品牌">
                     <el-option
                       v-for="item in brandList"
                       :key="item.id"
                       :label="item.value"
                       :value="item.id">
                     </el-option>
                   </el-select>
                 </el-form-item>
               </el-col>
            </el-row>
          <el-row :gutter="10">
           <el-col :span="4">
             <el-form-item label="最低价格">
               <el-input v-model="searchCondition.priceDown" placeholder="最低可接受价格"></el-input>
             </el-form-item>
           </el-col>
           <el-col :span="4">
             <el-form-item label="最高价格">
                <el-input v-model="searchCondition.priceUp" placeholder="最高可接受价格"></el-input>
             </el-form-item>
           </el-col>
            <el-col :span="2">
              <el-form-item>
                <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
              </el-form-item>
            </el-col>
            <el-col :span="2">
              <el-form-item>
                <el-button icon="el-icon-refresh" size="mini" @click="resetForm('searchCondition')">重置</el-button>
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
                >清理缓存
                </el-button>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </div>
      <el-divider></el-divider>

      <ul class="infinite-container" v-infinite-scroll="loadMoreItems" infinite-scroll-disabled="disabled"  >
<!--        <el-backtop target=".page-component__scroll .el-scrollbar__wrap" ></el-backtop>-->
        <li v-for="item in items" class="card" key="item.id" @click="redirect(item.id)" >
            <el-image
              style="width: 100%; height: 80%"
              :src="item.url"
              :fit="fill">
              <div slot="error" class="image-slot">
                <i class="el-icon-picture-outline"></i>
              </div>
            </el-image>
            <div style="padding: 14px;">
              <p class="item-title">{{item.title}}</p>
              <p class="item-description">{{item.description}}</p>
              <p class="item-price" >¥{{item.price}}</p>
            </div>
          </li>
            <li v-if="loading" class="backup"> 加载中...</li>
            <li v-if="noMore"  class="backup">没有更多了</li>
        </ul>
    </div>
</template>

<script>
  import { search, clearCache } from "@/api/bvo/browse";
  export default {
        name: "index.vue",
        data: function () {
          return {
            searchCondition : {
               title:"",
               brandName: "" ,
               selectedSupplierList:[],
               selectedCategory:[],
               selectedBrandList:[],
               priceDown: 1,
               priceUp: 1
            },

            items:[
              {
                id:'1',
                title:"这是一个非常非常非常非常长的标题",
                url:"https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png",
                description:"这是一个超级超级超级超级好吃的汉堡",
                price:"8.9"
              },
              {
                id:'2',
                title:"汉堡2",
                url:"https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png",
                description:"这是一个超级超级超级超级好吃的汉堡",
                price:"8.9"
              },
              {
                id:'3',
                url:"https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png",
                title:"汉堡3",
                description:"这是一个超级超级超级超级好吃的汉堡,这是一个超级超级超级超级好吃的汉堡，重要的事情说俩边",
                price:"8.9"
              },
              {
                id:'4',
                title:"汉堡4",
                url:"https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png",
                description:"这是一个超级超级超级超级好吃的汉堡",
                price:"8.9"
              },
              {
                id:'5',
                title:"汉堡5",
                url:"https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png",
                description:"这是一个超级超级超级超级好吃的汉堡",
                price:"8.9"
              },
              {
                id:'6',
                title:"汉堡5",
                url:"https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png",
                description:"这是一个超级超级超级超级好吃的汉堡",
                price:"8.9"
              },
              {
                id:'7',
                title:"汉堡5",
                url:"https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png",
                description:"这是一个超级超级超级超级好吃的汉堡",
                price:"8.9"
              },
              {
                id:'8',
                title:"汉堡5",
                url:"https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png",
                description:"这是一个超级超级超级超级好吃的汉堡",
                price:"8.9"
              },
              {
                id:'9',
                title:"汉堡5",
                url:"https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png",
                description:"这是一个超级超级超级超级好吃的汉堡",
                price:"8.9"
              },
              {
                id:'10',
                title:"汉堡5",
                url:"https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png",
                description:"这是一个超级超级超级超级好吃的汉堡",
                price:"8.9"
              },
              {
                id:'11',
                title:"汉堡5",
                url:"https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png",
                description:"这是一个超级超级超级超级好吃的汉堡",
                price:"8.9"
              },
              {
                id:'12',
                title:"汉堡5",
                url:"https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png",
                description:"这是一个超级超级超级超级好吃的汉堡",
                price:"8.9"
              },
              {
                id:'13',
                title:"汉堡5",
                url:"https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png",
                description:"这是一个超级超级超级超级好吃的汉堡",
                price:"8.9"
              },
              {
                id:'14',
                title:"汉堡5",
                url:"https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png",
                description:"这是一个超级超级超级超级好吃的汉堡",
                price:"8.9"
              },
              {
                id:'15',
                title:"汉堡5",
                url:"https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png",
                description:"这是一个超级超级超级超级好吃的汉堡",
                price:"8.9"
              }


            ],
            categoryList:[
              {id:1,value:"衣服",},
              {id:2,value:"裤子",},
              {id:3,value:"鞋子",},
            ],
            supplierList:[
              {id:1,value:"沃尔玛"},
              {id:2,value:"老干妈"},
              {id:3,value:"华宇时尚"},
              {id:4,value:"奥迪"},
              ],
            brandList:[
              {id:1,value:"LV"},
              {id:2,value:"圣罗兰"},
              {id:3,value:"兰黛"},
              {id:4,value:"保时捷"}
            ],
            loading: false
        }
      },
    computed: {
      noMore () {
        return this.count >= 20
      },
      disabled () {
        return this.loading || this.noMore
      }
    },
      methods:{
        redirect(id){
          this.$router.push({
            path:`/bvo/good/${id}`
          })
        },
        handleQuery() {
          this.$notify({
            title: '执行',
            message: '执行搜索',
            type: 'success'
          });
          // search(this.searchCondition).then(response=>{
          //
          // });
        },
        resetForm(formName) {
          this.$notify({
            title: '执行',
            message: '重置按钮',
            type: 'success'
          });
          this.$refs[formName].resetFields();
        },
        handleClearCache() {
          this.$notify({
            title: '执行',
            message: '缓存按钮',
            type: 'success'
          });
          // clearCache().then(response => {
          //   if (response.code === 200) {
          //     this.msgSuccess("清理成功");
          //   }
          // });
        },
        loadMoreItems() {
          this.$notify({
            title: '执行',
            message: '加载更多选项',
            type: 'success'
          });
          this.loading = true
          setTimeout(() => {
            this.items.push({
              id: '3',
              url: "https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png",
              title: "汉堡3",
              description: "这是一个超级超级超级超级好吃的汉堡,这是一个超级超级超级超级好吃的汉堡，重要的事情说俩边",
              price: "8.9"
            })
            this.items.push({
              id: '3',
              url: "https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png",
              title: "汉堡3",
              description: "这是一个超级超级超级超级好吃的汉堡,这是一个超级超级超级超级好吃的汉堡，重要的事情说俩边",
              price: "8.9"
            })
            this.items.push({
              id: '3',
              url: "https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png",
              title: "汉堡3",
              description: "这是一个超级超级超级超级好吃的汉堡,这是一个超级超级超级超级好吃的汉堡，重要的事情说俩边",
              price: "8.9"
            })
            this.items.push({
              id: '3',
              url: "https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png",
              title: "汉堡3",
              description: "这是一个超级超级超级超级好吃的汉堡,这是一个超级超级超级超级好吃的汉堡，重要的事情说俩边",
              price: "8.9"
            })
            this.loading = false
          }, 2000)

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
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
}

.card:hover{
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.5), 0 0 6px rgba(0, 0, 0, .04) ;
  text-decoration : underline;
  cursor: pointer;
}


.card{
  display: inline-block;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
  width: 22%;
  height: 30%;
  margin: 1%;
}

.item-description{
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
