<template>
  <div class="login">
    <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form">
      <h3 class="title">CrossBoarderShop</h3>
      <el-form-item prop="username">
        <el-input v-model="loginForm.username" type="text" auto-complete="off" placeholder="账号">
          <svg-icon slot="prefix" icon-class="user" class="el-input__icon input-icon" />
        </el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input
          v-model="loginForm.password"
          type="password"
          auto-complete="off"
          placeholder="密码"
          @keyup.enter.native="handleLogin"
        >
          <svg-icon slot="prefix" icon-class="password" class="el-input__icon input-icon" />
        </el-input>
      </el-form-item>
      <el-form-item prop="code">
        <el-input
          v-model="loginForm.code"
          auto-complete="off"
          placeholder="验证码"
          style="width: 63%"
          @keyup.enter.native="handleLogin"
        >
          <svg-icon slot="prefix" icon-class="validCode" class="el-input__icon input-icon" />
        </el-input>
        <div class="login-code">
          <img :src="codeUrl" @click="getCode" />
        </div>
      </el-form-item>

      <el-form-item>
        <el-checkbox v-model="loginForm.rememberMe" style="margin:0px 0px 25px 0px;">记住密码</el-checkbox>
        <span class="register" @click="handleRegister">注册</span>
      </el-form-item>

      <el-form-item style="width:100%;">
        <el-button
          :loading="loading"
          size="medium"
          type="primary"
          style="width:100%;"
          @click.native.prevent="handleLogin"
        >
          <span v-if="!loading">Log In</span>
          <span v-else>Login ing....</span>
        </el-button>
      </el-form-item>
    </el-form>
    <!--  底部  -->
    <div class="el-login-footer">
      <span>Copyright © 2020 NeuSoft All Rights Reserved.</span>
    </div>

    <!-- 添加或修改参数配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="35%"  append-to-body>
      <el-form ref="registerForm" :model="registerForm" :rules="registerRules" label-width="30%">
        <el-row>
          <el-col :span="24">
            <el-form-item label="账号类型">
              <el-switch
                  v-model="registerForm.roleIds"
                  active-value='3'
                  inactive-value='4'
                  active-text="供应商"
                  inactive-text="借卖商">
              </el-switch>
            </el-form-item>
<!--            <el-form-item label="角色">-->
<!--              <el-select v-model="registerForm.roleIds" multiple placeholder="请选择">-->
<!--                <el-option-->
<!--                  v-for="item in roleOptions"-->
<!--                  :key="item.roleId"-->
<!--                  :label="item.roleName"-->
<!--                  :value="item.roleId"-->
<!--                  :disabled="item.status == 1"-->
<!--                ></el-option>-->
<!--              </el-select>-->
<!--            </el-form-item>-->


          </el-col>
        </el-row>

        <el-row>
          <el-col :span="24">
            <el-form-item  label="用户名称" prop="userName">
              <el-input v-model="registerForm.userName" placeholder="请输入用户名称" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="10">
          <el-col :span="24">
            <el-form-item label="用户密码" prop="password">
              <el-input v-model="registerForm.password" placeholder="请输入用户密码" type="password" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="24">
            <el-form-item label="用户昵称" prop="nickName">
              <el-input v-model="registerForm.nickName" placeholder="请输入用户昵称" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="24">
            <el-form-item label="手机号码" prop="phonenumber">
              <el-input v-model="registerForm.phonenumber" placeholder="请输入手机号码" maxlength="11" />
            </el-form-item>
          </el-col>
        </el-row>




        <el-row :gutter="10">
          <el-col :span="24">
            <el-form-item label="邮箱" prop="email">
              <el-input v-model="registerForm.email" placeholder="请输入邮箱" maxlength="50" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="24">
            <el-form-item label="用户性别">
<!--              <el-select v-model="registerForm.sex" placeholder="请选择">-->
<!--                <el-option-->
<!--                  v-for="dict in sexOptions"-->
<!--                  :key="dict.dictValue"-->
<!--                  :label="dict.dictLabel"-->
<!--                  :value="dict.dictValue"-->
<!--                ></el-option>-->
<!--              </el-select>-->

              <el-radio-group v-model="registerForm.sex">
                <el-radio
                      v-for="item in sexOptions"
                      :label="item.dictValue"
                      :key="item.dictValue"
                    >{{item.dictLabel}}
                </el-radio>
              </el-radio-group>

            </el-form-item>
          </el-col>
        </el-row>


      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import { getCodeImg } from "@/api/login";
import Cookies from "js-cookie";
import { encrypt, decrypt } from '@/utils/jsencrypt'
import {addUser} from "../api/login";

export default {
  name: "Login",
  data() {
    return {
      codeUrl: "",
      cookiePassword: "",
      loginForm: {
        username: "admin",
        password: "admin123",
        rememberMe: false,
        code: "",
        uuid: ""
      },
      loginRules: {
        username: [
          { required: true, trigger: "blur", message: "用户名不能为空" }
        ],
        password: [
          { required: true, trigger: "blur", message: "密码不能为空" }
        ],
        code: [{ required: true, trigger: "change", message: "验证码不能为空" }]
      },
      loading: false,
      redirect: undefined,

      title:'',
      open:false,

      sexOptions: [{dictLabel:'男',dictValue:'1'},{dictLabel:'女',dictValue:'2'}],
      initPassword:'123456',

      registerForm: {
        userId: undefined,
        // deptId: undefined,
        userName: undefined,
        nickName: undefined,
        password: undefined,
        phonenumber: undefined,
        email: undefined,
        sex: undefined,
        status: "0",
        remark: undefined,
        roleIds: []
      },

        registerRules: {
          userName: [
            { required: true, message: "用户名称不能为空", trigger: "blur" }
          ],
          nickName: [
            { required: true, message: "用户昵称不能为空", trigger: "blur" }
          ],

          password: [
            { required: true, message: "用户密码不能为空", trigger: "blur" }
          ],
          email: [
            { required: true, message: "邮箱地址不能为空", trigger: "blur" },
            {
              type: "email",
              message: "'请输入正确的邮箱地址",
              trigger: ["blur", "change"]
            }
          ],
          phonenumber: [
            { required: true, message: "手机号码不能为空", trigger: "blur" },
            {
              pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/,
              message: "请输入正确的手机号码",
              trigger: "blur"
            }
          ]
        }
      };
  },
  watch: {
    $route: {
      //这个会让用户导回原来的路由
      handler: function(route) {
        this.redirect = route.query && route.query.redirect;
      },
      immediate: true
    }
  },
  created() {
    this.getCode();
    this.getCookie();

  },
  methods: {
    //获取一个二维码图片 和 这个图片对应的uuid
    getCode() {
      getCodeImg().then(res => {
        this.codeUrl = "data:image/gif;base64," + res.img;
        this.loginForm.uuid = res.uuid;
      });
    },
    getCookie() {
      const username = Cookies.get("username");
      const password = Cookies.get("password");
      const rememberMe = Cookies.get('rememberMe')
      this.loginForm = {
        username: username === undefined ? this.loginForm.username : username,
        password: password === undefined ? this.loginForm.password : decrypt(password),
        rememberMe: rememberMe === undefined ? false : Boolean(rememberMe)
      };
    },

    reset() {
      this.registerForm = {
        userId: undefined,
        // deptId: undefined,
        userName: undefined,
        nickName: undefined,
        password: undefined,
        phonenumber: undefined,
        email: undefined,
        sex: undefined,
        status: "0",
        remark: undefined,
        roleIds: []
      };
      this.resetForm("registerForm");
    },
    handleRegister() {

      this.reset();

      console.log(this.sexOptions)
      // this.getTreeselect();
      this.open = true;
      this.title = "Register";
      this.registerForm.password = this.initPassword;
    },

    /** 提交按钮 */
    submitForm: function() {
      this.$refs["registerForm"].validate(valid => {
        if (valid) {
            let id=this.registerForm.roleIds;
            this.registerForm.roleIds=[]
            this.registerForm.roleIds.push(id[0])

            console.log(this.registerForm)
            addUser(this.registerForm).then(response=>{
              this.$notify(
                {
                  type:'success',
                  message:'注册成功'
                }
              )
              this.open = false;

            })

          }
      });
    },
    cancel() {
      this.open = false;
      this.reset();
    },
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          //开始加载
          this.loading = true;
          if (this.loginForm.rememberMe) {
            //保存账号密码
            Cookies.set("username", this.loginForm.username, { expires: 30 });
            Cookies.set("password", encrypt(this.loginForm.password), { expires: 30 });
            Cookies.set('rememberMe', this.loginForm.rememberMe, { expires: 30 });
          } else {
            //删除
            Cookies.remove("username");
            Cookies.remove("password");
            Cookies.remove('rememberMe');
          }
          this.$store
            .dispatch("Login", this.loginForm)
            .then(() => {
              //登陆成功 路由跳转
              this.$router.push({ path: this.redirect || "/" });
            })
            .catch(() => {
              this.loading = false;
              //登陆失败,更换验证码
              this.getCode();
            });
        }
      });
    }
  }
};
</script>

<style rel="stylesheet/scss" lang="scss">
.login {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  background-image: url("../assets/image/login-background.jpg");
  background-size: cover;
}


.title {
  margin: 0px auto 30px auto;
  text-align: center;
  color: #707070;
}

.login-form {
  border-radius: 6px;
  background: #ffffff;
  width: 400px;
  padding: 25px 25px 5px 25px;
  .el-input {
    height: 38px;
    input {
      height: 38px;
    }
  }
  .input-icon {
    height: 39px;
    width: 14px;
    margin-left: 2px;
  }
}
.login-tip {
  font-size: 13px;
  text-align: center;
  color: #bfbfbf;
}
.login-code {
  width: 33%;
  height: 38px;
  float: right;
  img {
    cursor: pointer;
    vertical-align: middle;
  }
}
.el-login-footer {
  height: 40px;
  line-height: 40px;
  position: fixed;
  bottom: 0;
  width: 100%;
  text-align: center;
  color: #fff;
  font-family: Arial;
  font-size: 12px;
  letter-spacing: 1px;
}
.register:hover{
   text-decoration: underline;
   cursor: pointer;
   color: #3A71A8;
}
.register{
  float: right;
  font-size: medium;
  color: #ed5565;
}
</style>
