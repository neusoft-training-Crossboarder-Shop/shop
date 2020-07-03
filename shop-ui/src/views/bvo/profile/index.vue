<template>
  <div class="profile_container" >
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>个人信息</span>
      </div>
        <div>
          <el-form ref="form" :model="buyer" label-width="80px">
            <el-form-item>
              <el-input v-model="buyer.name" placeholder="店铺名称" :disable="isEdit" @change="change"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary"  v-if="isEdit" class="long-button" @click="save">保存</el-button>
              <el-button type="primary" v-else @click="edit" class="long-button">编辑</el-button>
              <el-button type="danger"  @click="redirect">我的店铺</el-button>
            </el-form-item>
          </el-form>
        </div>
    </el-card>




  </div>
</template>

<script>
    export default {
        name: "index.vue",
        data:function () {
          return {
            isEdit:false,
            buyer:{
              id:"",
              name:""
            },
          }
        },
      methods:{
        save(){
          this.$notify({
            type:"success",
            title:"执行",
            message:"保存成功"
          })
        },
        edit(){
          if (isEdit){
            isEdit=false
          } else{
            isEdit = true;
          }
        },
        change(){
          isEdit = true;
        },
        redirect(){
          this.$router.push({
            path:"bvo/store"
          })
        }
      }
    }
</script>

<style scoped>
  .box-card{
    padding: 2%;
    position: absolute;
    left: 30%;
    top: 20%;
    width: 40%;
    height: 30%;
  }
.long-button{

}
</style>
