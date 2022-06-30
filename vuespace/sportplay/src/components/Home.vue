<template>
  <el-container class="home-container">
    <!--头部-->
    <el-header>
      <div>
        <img src="../assets/logo.jpeg" alt class="logo_img" />
        <span>运动管理平台</span>
      </div>
      <el-button type="info" @click="logout">安全退出</el-button>
    </el-header>
    <!--主体-->
    <el-container>
      <!--侧边栏-->
      <el-aside :width="iscollapse?'64px':'200px'">
        <div class="toggle-button" @click="toggleCollapase">|||</div>
        <el-menu
          background-color="#545c64" text-color="#fff" active-text-color="#409eff" unique-opened 
          :collapse="iscollapse" :collapse-transition="false" :router="true" :default-active="activePath" >
          <!--一级菜单-->
          <el-submenu :index="item.id+''" v-for="item in menuList" :key="item.id">
            <template slot="title">
              <i :class="iconsObject[item.id]"></i>
              <span>{{item.title}}</span>
            </template>
            <!--二级菜单-->
            <el-menu-item :index="it.path" v-for="it in item.slist" :key="it.id" @click="savaNavState(it.path)">
              <template slot="title">
                <i :class="iconsObject[it.id]"></i>
                <span>{{it.title}}</span>
              </template>
            </el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>
      <!--主体内容-->
      <el-main>
        <router-view></router-view><!--路由占位符-->
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  data() {
    return {
      //菜单列表
      menuList:[],
      iconsObject: {
              '100':'iconfont iconguanliyuan',
              '200':'iconfont iconsport',
              '101':'iconfont icondenglu',
              '102':'iconfont iconmima',
              '103':'iconfont iconsport',
              '104':'iconfont iconshangpin',
              '201':'iconfont iconshu',
              '202':'iconfont iconkaluli',
              '203':'iconfont iconshiwu',
              '204':'iconfont icondenglu',
            },
      activePath:'/welcome',  //默认路径    
      iscollapse:false,
    };
  },
  //页面加载触发
  created() {
    //查询menuList
    this.getMenuList();
    this.activePath = window.sessionStorage.getItem('activePath');
  },
  methods: {
    //安全退出
    logout() {
      window.sessionStorage.clear(); //清除session
      this.$router.push("/login"); //回到首页
    },
    //获取导航菜单方法
    async getMenuList(){
      const {data:res} = await this.$http.get("menus");
      console.log(res);
      if(res.flag!=200) return this.$message.error("获取列表失败！");//访问失败的错误信息

      this.menuList=res.menus;//访问成功后数据回填
    },
    //控制伸缩
    toggleCollapase(){
      this.iscollapse = !this.iscollapse;
    },
    //保存路径
    savaNavState(activePath){
      window.sessionStorage.setItem('activePath',activePath)//放在session里
      this.activePath = activePath;
    }
  }
};
</script>

<style lang='less' scoped>
//布局器样式
.home-container {
  height: 100%;
}
//头样式
.el-header {
  background-color: #373d41;
  display: flex;
  justify-content: space-between; //左右贴边
  padding-left: 0%; //左边界
  align-items: center;
  color: #fff;
  font-size: 20px;

  div {
    display: flex;
    align-items: center;
  }
  span {
    margin-left: 15px;
  }
}
//侧边样式
.el-aside {
  background-color: #333744;
  .el-menu{
    border-right: none;
  }
}
//主体样式
.el-main {
  background-color: #eaedf1;
}

img {
  height: 55px;
  width: 55px;
}
.toggle-button{
  background-color:#4A5064;
  font-size: 10px;
  line-height: 24px;
  color:#fff;
  text-align: center;
  letter-spacing: 0.2em;
  cursor: pointer;// 显示鼠标指针为：小手
}
</style>
