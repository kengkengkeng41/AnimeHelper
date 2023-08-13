<template>
        
        <van-button class="save-btn" color="#fd9292" type="primary" @click="logout" block>退出登录</van-button>

</template>

<script>
import { reactive, onMounted, toRefs } from 'vue'
import NavBar from '@/components/NavBar.vue'
import CustomHeader from '../components/CustomHeader.vue'
import { getUserInfo } from '@/service/user.js'
import { useRouter } from 'vue-router'
import { setLocal } from '../utils/help'
import { showSuccessToast } from 'vant'
export default {
  name: 'User',

  methods: {
  async logout() {
      showSuccessToast('开始清空localStorage')
      setLocal('token', '')
      setLocal('user','')
      window.location.href = '/'
    }
  }
}
</script>

<style>
    .save-btn {
      width: 80%;
      margin: 20px auto ;
    }
</style>