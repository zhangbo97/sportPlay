<template>
  <div class="login_container"> 
      <!--登录块-->
      <div class="login_box"> 
          <!--头像-->
          <div class="avatar_box">
              <img src="../assets/logo.png" alt/>
          </div>
          <!--表单区域-->
          <el-form ref="loginFormRef" :rules="loginRules" :model="loginForm" class="login_form" label-width="0">
          <!--用户名-->
            <el-form-item prop="username" >
                <el-input v-model="loginForm.username" prefix-icon="el-icon-user"></el-input>
             </el-form-item>
             <!--密码-->
             <el-form-item prop="password" >
                <el-input v-model="loginForm.password" type="password" prefix-icon="el-icon-s-order"></el-input>
             </el-form-item>
             <!--按钮-->
             <el-form-item class="btns">
                <el-button type="primary" @click="login()">提交</el-button>
                <el-button type="info" @click="restLoginForm()">重置</el-button>
             </el-form-item>
          </el-form>   
      </div>
  </div>
</template>

<script>
export default {
    data() {
        return{
            //表单数据对象
            loginForm:{
                username:"admin",
                password:"123456"
            },
            //验证对象
            loginRules:{
                //校验用户名
                username:[
                    { required: true, message: '请输入用户名', trigger: 'blur' },//必填项验证
                    { min: 5, max: 10, message: '长度在 5 到 12 个字符', trigger: 'blur' }//长度验证
                ],
                //校验密码
                password:[
                    { required: true, message: '请输入密码', trigger: 'blur' },//必填项验证
                    { min: 6, max: 10, message: '长度在 6 到 10 个字符', trigger: 'blur' }//长度验证
                ]
            }
        };
    },
    methods:{
        //重置表单内容
        restLoginForm(){
            this.$refs.loginFormRef.resetFields();
        },
        login(){
            //验证校验规则
             this.$refs.loginFormRef.validate(async valid => {
                if(!valid) return;//验证失败
                const {data:res} =await this.$http.post("login",this.loginForm);//访问后台
                if(res.flag == "ok"){
                    this.$message.success("操作成功");
                    window.sessionStorage.setItem("user",res.user);//存储user对象
                    this.$router.push({path:"/home"});//页面路由跳转
                    
                }else{
                    this.$message.error("操作失败");
                }
            })
        },
    },
}
</script>

<style lang="less" scoped>

 // 跟节点样式
.login_container {
  background-color: #2b4b6b;
  height: 100%;
}
// 登录框
.login_box {
  width: 450px;
  height: 300px;
  background-color: #fff;
  border-radius: 3px;// 圆角
  position: absolute;// 绝对定位
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);// 根据自己位置 以自己为长度位移
  // 头像框
  .avatar_box {
    width: 130px;
    height: 130px;
    border: 1px solid #eee;
    border-radius: 50%; // 加圆角
    padding: 10px;
    box-shadow: 0 0 10px #ddd;// 盒子阴影
    position: absolute;
    left: 50%;
    transform: translate(-50%, -50%);
    background-color: #0ee;
    img {
      width: 100%;
      height: 100%;
      border-radius: 50%; // 加圆角
      background-color: #eee;
    }
  }
  .btns {
    display: flex;// 弹性布局
    justify-content: flex-end; // 尾部对齐
  }
  .login_form {
    position: absolute;
    bottom: 0%;
    width: 100%;
    padding: 0 10px;
    box-sizing: border-box;// 设置边框
  }
}
</style>