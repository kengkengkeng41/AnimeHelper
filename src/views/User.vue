<template>
    <div class="user-box">
        <custom-header :title="'个人中心'" :back="'/home'" :burger="'/visitor'"></custom-header>
        <van-skeleton title :avatar="true" :row="3" :loading="loading">
            <div class="user-info">
                <div class="info">
                    <el-avatar :src="avatar" :size="140" shape="square"></el-avatar>
                    <div class="user-desc">
                        <span>ID： {{ user.userId }}</span>
                        <span>昵称：{{ user.nickName }}</span>
                        <span>登录名：{{ user.loginName }}</span>
                        <span>身份：{{ user.role }}</span>
                    </div>
                </div>
            </div>
        </van-skeleton>
        <ul class="user-list">
            <li class="van-hairline--bottom" @click="goTo('/edit')">
                <span>编辑模式</span>
                <svg class="icon" aria-hidden="true"><use xlink:href="#icon-xiangsu_xianrenzhang"></use></svg>
            </li>
            <li class="van-hairline--bottom" @click="goTo('/cins')">
                <span>轮播图片</span>
                <svg class="icon" aria-hidden="true"><use xlink:href="#icon-xiangsu_zuanshi"></use></svg>
            </li>
            <li class="van-hairline--bottom">
                <span>我的上传</span>
                <svg class="icon" aria-hidden="true"><use xlink:href="#icon-xiangsu_shu"></use></svg>
            </li>
            <li class="van-hairline--bottom" @click="goTo('/setting')">
                <span>账号管理</span>
                <svg class="icon" aria-hidden="true"><use xlink:href="#icon-xiangsu_xigua"></use></svg>
            </li>
        </ul>
        <van-button class="save-btn" color="#fd9292" type="primary" @click="logout" block>退出登录</van-button>
        <nav-bar></nav-bar>
    </div>
</template>

<script>
import { reactive, onMounted, toRefs } from 'vue'
import NavBar from '@/components/NavBar.vue'
import CustomHeader from '../components/CustomHeader.vue'
import { getUserInfo } from '@/service/user.js'
import { useRouter } from 'vue-router'
import { setLocal } from '../utils/help'
import { logout } from '../service/user'
import { showSuccessToast } from 'vant'
import { convertUrl } from '@/utils/help'
export default {
    name: 'visitor',

    components: {
        NavBar,
        CustomHeader
    },
    setup() {
        const router = useRouter()
        const state = reactive({
            user:{},
            avatar: '/default.png',
            loading: true
        })
        onMounted(async () => {
            const { resultCode, message, data } = await getUserInfo()
            // alert(data.avatar+'\n'+data.userId+'\n'+data.nickName+'\n'+data.loginName+'\n'+data.role)
            state.user = data
            state.avatar = convertUrl(data.avatar)
            state.loading = false
            setLocal('avatar', state.avatar)
        })
        const goTo = (path, query) => {
            router.push({ path, query: query || {} })
        }
        return {
            ...toRefs(state),
            
            goTo
        }
    },
    methods: {
    async logout() {
      const { resultCode } = await logout()
      if (resultCode == 200) {
        setLocal('token', '')
        setLocal('avatar','')
        window.location.href = '/'
      }
    }
  }
}
</script>

<style lang="less" scoped>
  @import '../common/style/mixin';
  .user-box {
    .user-header {
      position: fixed;
      top: 0;
      left: 0;
      z-index: 10000;
      .fj();
      .wh(100%, 44px);
      line-height: 44px;
      padding: 0 10px;
      .boxSizing();
      color: #252525;
      background: #fff;
      border-bottom: 1px solid #dcdcdc;
      .user-name {
        font-size: 14px;
      }
    }
    .user-info {
      width: 94%;
      margin: 10px;
      height: 115px;
      background: linear-gradient(90deg, @primary, #47a5a5);
      box-shadow: 0 2px 5px #269090;
      border-radius: 6px;
      margin-top: 50px;
      .info {
        position: relative;
        display: flex;
        width: 100%;
        height: 100%;
        padding: 10px 20px;
        .boxSizing();
        img {
          .wh(60px, 60px);
          border-radius: 50%;
          margin-top: 4px;
        }
        .user-desc {
          display: flex;
          flex-direction: column;
          margin-left: 10px;
          line-height: 20px;
          font-size: 14px;
          color: #fff;
          span {
            color: #fff;
            font-size: 14px;
            padding: 2px 0;
          }
        }
        .account-setting {
          position: absolute;
          top: 10px;
          right: 20px;
          font-size: 13px;
          color: #ffffff;
          .van-icon-setting-o {
            font-size: 16px;
            vertical-align: -3px;
            margin-right: 4px;
          }
        }
      }
    }
    .user-list {
      padding: 0 20px;
      margin-top: 5px;
      margin-bottom: 15px;
      li {
        height: 40px;
        line-height: 40px;
        border-bottom: 1px solid #e9e9e9;
        display: flex;
        justify-content: space-between;
        font-size: 14px;
        .van-icon-arrow {
          margin-top: 13px;
        }
      }
      .icon{
        color: #ffffff;
        font-size: 25px;
      }
    }


    // .logincontent {
    //   display: flex;
    //   flex-direction: column;
    //   background-image: url("/background.jpg");
    //   background-size: 100% 100%;
    //   background-attachment: fixed;
    
    //   width: 100%;
    //   height: 100%;
    //   min-width: 900px;
    //   min-height: 1000px;
    
    //   justify-content: center;
    //   align-items: center;
    // }

  }
</style>