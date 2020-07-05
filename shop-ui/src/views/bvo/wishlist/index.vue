<template>
  <div class="item_container">
    <ul class="infinite-container"   >
      <el-backtop target=".page-component__scroll .el-scrollbar__wrap" ></el-backtop>
      <li v-for="item in items" class="card" key="item.id" @click="redirect(item.id)" >
        <el-row :gutter="20">
         <el-col :span="4">
           <el-image
             style="width: 100%; height: 100%"
             :src="item.url"
             :fit="fill">
             <div slot="error" class="image-slot">
               <i class="el-icon-picture-outline"></i>
             </div>
           </el-image>
         </el-col>
         <el-col :span="20">
         <div style="padding: 14px;">
             <p class="item-title">{{item.title}}</p>
             <p class="item-description">{{item.description}}</p>
             <p class="item-price" >¥{{item.price}}</p>
             <el-button
               style="float: right;margin: 1%"
               type="danger"
               icon="el-icon-refresh"
               size="big"
               @click="remove(item.id)"
               v-hasPermi="['bvo:wishlist:remove']"
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
    export default {
        name: "index.vue",
        data: function () {
          return {
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
          }
        },
        methods:{
          redirect(id){
            this.$router.push({
              path:`/bvo/good/${id}`
            })
          },
          remove(id){
            this.$notify({
              title: '执行',
              message: '删除这个物品',
              type: 'success'
            });
          }
        }

    }
</script>

<style scoped>
.item_container{
  display: block;
  width: 100%;
  height: 20%;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
}
.card:hover{
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.5), 0 0 6px rgba(0, 0, 0, .04) ;
  text-decoration : underline;
  cursor: pointer;
}


.card{
  display: inline-block;
  float: left;
  clear: both;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
  width: 100%;
  margin: 1%;
}

.item-description{
  display: block;
  height: 15%;
  font-size: 0.8em;
}

.item-title{
  font-weight: bold;
  font-size: 1.1em;
  white-space: nowrap;
  text-overflow:ellipsis;
  overflow:hidden;
}

.item-price{
  margin:  2px;
  color: rgba(221, 69, 69, 1);
  font-weight : 800 ;
  font-size: large;
  letter-spacing: 5px;
}
</style>
