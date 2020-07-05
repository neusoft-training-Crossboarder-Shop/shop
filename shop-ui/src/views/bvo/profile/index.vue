<template>
  <div class="profile_container" >
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span style="font-weight: bold;font-size: large;margin-right: 2%">Profile</span>
        <span v-if="!notRegistered" class="foot">注册日期:{{parseTime(this.buyer.createTime)}}  </span>
        <span v-if="!notRegistered" class="foot">注册日期:{{this.buyer.createdBy}}</span>
      </div>
        <div>
          <el-form ref="form" :model="buyer" label-width="80px">
            <el-form-item label="StoreName">
              <el-input v-model="buyer.name" @focus="changeEdit"  placeholder="Please Enter Store Name" ></el-input>
            </el-form-item>
          </el-form>
          <template v-if="notRegistered">
              <el-button  class="button-style" type="primary"   @click="register">注册</el-button>
          </template>
          <template v-else >
              <el-button class="button-style"  type="primary" :disabled="!isEdit" @click="save">保存</el-button>
              <el-button class="button-style" style="position: relative;left: -10px"   type="danger"  @click="redirect">我的店铺</el-button>
          </template>
        </div>
    </el-card>
  </div>
</template>

<script>
  import {getBasicInfo,addDropShipper,updateDropShipper} from "../../../api/bvo/profile";

  export default {
        name: "index.vue",
        data:function () {
          return {
            notRegistered:true,
            isEdit:false,
            buyer:{
              name:null,
              createTime: null,
              createdBy:null,
            },
          }
        },
      computed:{
        /**
         * @return {boolean}
         */

      },
      created() {
      this.getList();
    },
      methods:{
          changeEdit(){
            this.isEdit = true;
          },
        getList(){
          getBasicInfo().then(response=>{
            this.buyer=response.data
            this.notRegistered = false;
          })
        },
        register(){
          // 注册成功
          this.notRegistered = false;
          addDropShipper(this.buyer).then(response=>{
             this.buyer=response.data;
          });
        },
        save(){
          updateDropShipper({
            dsrId: this.buyer.dsrId,
            name: this.buyer.name
          }).then(response=>{
            //修改成功
          })
        },
        edit(){
          if (isEdit){
            isEdit=false
          } else{
            isEdit = true;
          }
        },
        redirect(){
          this.$router.push({
            name:"Store"
          })
        }
      }
    }
</script>

<style scoped>
  .box-card{
    padding: 2%;
    position: absolute;
    left: 25%;
    top: 20%;
    width: 50%;
    height: 35%;
  }
.button-style{
  margin-top: 2%;
  margin-bottom: 1%;
  display: inline-block;
  width: 100%
}
.foot{
  font-size: small;
  color: #99a9bf;
  font-weight: lighter;
}
</style>
