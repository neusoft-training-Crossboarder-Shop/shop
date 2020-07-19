<template>
  <div id="container" class="item_container">
    <ul class="infinite-container"   >
<!--      <el-backtop target=".page-component__scroll .el-scrollbar__wrap" ></el-backtop>-->
      <li v-for="item in items" class="card" :key="item.proId" @click="redirect(item.proId)" >
        <el-row :gutter="20">
         <el-col :span="6">
           <el-image
             style="width: 100%; height: 100%"
             :src="item.imageUrl"
             >
             <div slot="error" class="image-slot">
               <i class="el-icon-picture-outline"></i>
             </div>
           </el-image>
         </el-col>
         <el-col :span="18">
         <div style="padding: 14px;">

         <p style="font-weight: bold;font-size: 1.2em;white-space: nowrap;text-overflow:ellipsis;  overflow:hidden;">
           {{item.title}}</p>

         <p class="item-description">{{item.categoryName}}</p>
         <p class="item-description">{{item.brandName}}</p>
         <p class="item-description">{{item.manufacturerName}}</p>

         <p class="item-price" >${{item.price}}</p>
<!--                          v-hasPermi="['bvo:wishlist:remove']"-->
         <el-button
               style="float: right;margin: 1%"
               type="danger"
               icon="el-icon-refresh"
               size="big"
               @click.stop="remove(item.proId)"
             >移除
         </el-button>
         </div>
         </el-col>
        </el-row>

      </li>
    </ul>

  </div>

</template>

<script>
    import {getWishList,removeFromList} from "../../../api/bvo/wishlist";
    import { Loading } from 'element-ui';

    export default {

        name: "index.vue",
        data: function () {
          return {
            loadingInstance:'',
            items:[
              {
                proId:'1',
                title:"这是一个非常非常非常非常长的标题",
                imageUrl:'',
                brandName:'',
                categoryName:'',
                manufacturerName:'',
                price:"8.9"
              },
            ],
          }
        },
        created(){
            this.loadingInstance=Loading.service({
              target:"#container",
              body:"true",
              fullscreen:"false",
              lock:"true",
            });
            this.getList();
        },
        methods:{
          getList(){
              getWishList().then(response=>{
                this.items = response.data;

                setTimeout(() => {
                  this.loadingInstance.close();
                }, 500);
              })
          },
          redirect(id){
            this.$router.push({
              path:`/bvo/good/${id}`
            })
          },
          remove(id){
            console.log("12312312id"+id)
            removeFromList(id).then(res=>{
              this.getList();
            })
          }
        }

    }
</script>

<style scoped>
.item_container{
  display: block;
  width: 100%;
  height: 20%;
  margin-right: 2%;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
}
.card:hover{
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.5), 0 0 6px rgba(0, 0, 0, .04) ;
  text-decoration : underline;
  cursor: pointer;
}


.item-description{
  display: block;
  height: 15%;
  font-size: 1em;
  color: #1f2d3d;
  margin-top: 2%;
}


.item-price{
  margin:  2px;
  color: rgba(221, 69, 69, 1);
  font-weight : 800 ;
  font-size: large;
  letter-spacing: 5px;
}

.card{
  display: inline-block;
  float: left;
  clear: both;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
  width: 100%;
  margin: 1%;
  height: 20%;
}

</style>
