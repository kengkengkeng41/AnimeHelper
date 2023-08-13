<template>
  <div>
    <header class="home-header wrap">
      <router-link to="/">
        <svg class="icon" aria-hidden="true">
          <use xlink:href="#icon-xiangsu_fangzi"></use>
        </svg>
      </router-link>
      <div class="header-search">
        <span class="app-name">涝碧澄大厅</span>
        <svg class="icon" aria-hidden="true">
          <use xlink:href="#icon-xiangsu_wuqi"></use>
        </svg>
        <router-link class="search-title" to="/browser">烟光凝而暮山紫</router-link>
        
      </div>
      
      <router-link class="login" to="./login" v-if="!isLogin">登录</router-link>
      <router-link class="login" to="./user" v-else>
        <van-icon name="manager-o" />
      </router-link>
      <img :src="avatarUrl" class="iconfont">
    </header>
    <div class="container">
    <swiper :list="swiperList" class="swiper"></swiper>
    </div>
    <nav-bar />
  </div>
</template>

<script>
import { reactive, onMounted, toRefs, ref } from 'vue'
import NavBar from '@/components/NavBar.vue'
import Swiper from '@/components/Swiper.vue'
import { getLocal } from '@/utils/help'
import { getHome } from '@/service/home'
import { getUserInfo } from '@/service/user.js'
import { selectCarousel } from '../service/carousel'
import { convertUrl } from '@/utils/help'

  export default {
  components: { NavBar },
    name: 'Home',
    components: {
      NavBar,
      Swiper,
    },
    setup() {
      // getUserInfo().then(res => {
      //   console.log(res)
      // })
      const state = reactive({
        avatarUrl: '/O.jpg',
        isLogin: false,
        verify: '',
        imgCode: '',
        swiperList: [
          {
            carouselUrl:'/w1.jpg',
            redirectUrl:'/w1.jpg'
          },
          {
            carouselUrl:'/w2.jpg',
            redirectUrl:'/w2.jpg'
          },
          {
            carouselUrl:'/w3.jpg',
            redirectUrl:'/w3.jpg'
          },
        ]
      })
      

      onMounted(async () => {
        
        const token = getLocal('token')
        const avatar = getLocal('avatar')
        if (avatar) {
          state.avatarUrl = avatar
        }
        if (token) {
          state.isLogin = true
        }

        const result = await selectCarousel()
        if (result.data!=''){
          state.swiperList = result.data
          state.swiperList.forEach((item) => {
            item.carouselUrl = convertUrl(item.carouselUrl)
          })
        }

        // const {data} = await getHome()
        /*
        {
          carousels: [
            carouselUrl:
            redirectUrl:
          ]
        }
        */
      //  state.swiperList = data.carousels
      })
      return {
        ...toRefs(state),
        
      }
    }
  }

</script>


<style lang="less" scoped >
  @import '../common/style/mixin';
  .home-header {
      position: fixed;
      left: 0;
      top: 0;
      .wh(100%, 50px);
      .fj();
      line-height: 50px;
      padding: 0 15px;
      .boxSizing();
      font-size: 15px;
      color: #bcffb4;
      z-index: 10000;
      .nbmenu2 {
        color: @primary;
      }
      &.active {
        background: @primary;
        .nbmenu2 {
          color: #fff;
        }
        .login {
          color: #fff;
        }
      }

      .header-search {
          display: flex;
          .wh(74%, 20px);
          line-height: 20px;
          margin: 10px 0;
          padding: 5px 0;
          color: #232326;
          background: rgba(255, 255, 255, .7);
          border-radius: 20px;
          .app-name {
              padding: 0 10px;
              color: @primary;
              font-size: 20px;
              font-weight: bold;
              border-right: 1px solid #666;
          }
          .icon-search {
              padding: 0 10px;
              font-size: 17px;
          }
          .search-title {
              font-size: 12px;
              color: #666;
              line-height: 21px;
          }
      }
      .icon-iconyonghu{
        color: #fff;
        font-size: 22px;
      }
      .login {
        color: @primary;
        line-height: 52px;
        .van-icon-manager-o {
          font-size: 20px;
          vertical-align: -3px;
        }
      }
  }
  
  .swiper {
    margin-top: -32px;
    width: 165vh;
    height: 100%;
  }
</style>