<template>
    <h1>这里是商店详情界面</h1>
</template>

<script>
    export default {
        name: "storeDetail",
        data:function () {
          return {

          }
        },
      created() {
        const storeId = this.$route.params && this.$route.params.storeId;
        // this.getType(dictId);
        // this.getTypeList();
        // this.getDicts("sys_normal_disable").then(response => {
        //   this.statusOptions = response.data;
        // });
        this.$notify({
          type : "success",
          message:`你当前正在访问的商店ID为${storeId}`
        })
      }
    }
</script>

<style scoped>

</style>
