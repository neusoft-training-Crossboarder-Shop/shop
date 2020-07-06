<template>
  <div class="container">
    <div class="main_container">
    <el-row :gutter="10">
     <el-col :span="10" style="position: relative;top:30px" >
       <el-carousel :interval="4000" type="card">
         <el-carousel-item v-for="image in pro.images" :key="image.id">
           <el-image
             class="img"
             style="width: 100%; height: 100%"
             :src="image.src"
             :fit="fill">
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
         <p class="price_container">
         <span  class="tag" >PRICE</span>
         <span class="price">${{pro.price}}</span>
       </p>
       <div class="content">
         <p :gutter="8">
           <span  class="tag">SKU_ID</span>
           <span >{{pro.sku}}</span>
         </p>
         <p >
           <span   class="tag" >WEIGHT</span>
           <span  >{{pro.weight}}</span>
         </p>
         <p >
           <span  class="tag" >Brand</span>
           <span>{{pro.brand}}</span>
         </p>
       </div>

         <p >
         <span  class="tag" >AMOUNT</span>
         <span>{{pro.amount}}</span>
       </p>
        <el-row :gutter="10">
          <el-input-number size="medium" v-model="num" @change="handleChange" :min="1" :max="pro.amount" ></el-input-number>
          <el-button-group>
            <el-button icon="el-icon-star-off" @click="addToWishList">Add to wishlist</el-button>
          </el-button-group>
          <el-popover
            placement="top-start"
            trigger="click">
            <div>
              <el-select v-model="value" placeholder="请选择">
                <el-option-group
                  v-for="group in options"
                  :key="group.label"
                  :label="group.label">
                  <el-option
                    v-for="item in group.options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
                </el-option-group>
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
  </div>
</template>

<script>
    export default {
        name: "GoodDetail",
        data:function() {
          return {
            options: [{
              label: 'Amazon',
              options: [{
                value: 1,
                label: 'storeName1'
              }, {
                value: 2,
                label: 'storeName2'
              }]
            }, {
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
            value: '',
              pro:{
                price: 188 ,
                sku : 123141235434214,
                title : "这是一个超级好吃的汉堡包",
                weight: 10,
                brand : "KFC",
                amount : 24,
                ebayDescription: "",
                AmazonDescription: "",
                images:[
                  {id:1,src:"https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"},
                  {id:2,src:"https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"},
                  {id:3,src:"https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"},
                  {id:4,src:"https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"},
                ]
              },
              num:1,
          }
        },
        methods:{
          pushToStore:function () {
            this.$notify({
              type:"success",
              title:"执行中",
              message:"添加到商店"
            })
          },
          addToWishList:function () {
            this.$notify({
              type:"success",
              title:"执行中",
              message:"添加到心愿单"
            })
          }
        },
        created() {
          const proID = this.$route.params && this.$route.params.proID;
          // this.getType(dictId);
          // this.getTypeList();
          // this.getDicts("sys_normal_disable").then(response => {
          //   this.statusOptions = response.data;
          // });
          this.$notify({
            type : "success",
            message:`你当前正在访问的商品ID为${proID}`
          })
        }
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
  background-color: #1ab394;

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
  letter-spacing: 10px;
  font-size: large;
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
</style>
