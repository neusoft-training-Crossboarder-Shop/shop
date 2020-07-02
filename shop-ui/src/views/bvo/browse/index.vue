<template>
    <h1>商品浏览界面</h1>
</template>

<script>
    export default {
        name: "index.vue",
        data: function () {
          return {
            searchCondition : {
               title:"",
               brandName: "" ,
               category:["衣服","裤子"],
               priceDown: 1,
               priceUp: 1
            }
        }

    }
    }
</script>

<style scoped>



</style>
