<template>
  <div class="setting-box">
    <custom-header title="账号管理" :back="'/user'" :burger="'/visitor'"></custom-header>
    <div style="text-align: center" @click="dialogVisible=true">
      <img :src="avatarUrl" class="iconfont" @click="dialogVisible=true"/>
    </div>
    <avatar-cropper v-if="dialogVisible" v-model="dialogVisible" @closeAvatarDialog="closeAvatarDialog"></avatar-cropper>
    
    <div class="input-item">
      <van-field v-model="loginName" label="登录名"/>
      <van-field v-model="nickName" label="昵称"/>
      <van-field v-model="password" type='password' label="修改密码" />
      <van-radio-group v-model="role" direction="horizontal" label="身份">
        <van-radio name="0" shape="square" checked-color="#ee0a24"><p class='radio-text'>游客</p></van-radio>
        <van-radio name="1" shape="square" checked-color="#ee0a24"><p class='radio-text'>管理</p></van-radio>
      </van-radio-group>
    </div>

    <van-button class="save-btn" color="#1baeae" type="primary" @click="save" block>保存</van-button>
    <van-button class="save-btn" color="#1baeae" type="primary" @click="out" block>退出登录</van-button>
  </div>
</template>

<script>
import {ref,reactive,onMounted, toRefs} from "vue";
import AvatarCropper from '@/components/AvatarCropper.vue'
import CustomHeader from '@/components/CustomHeader.vue'
import { getUserInfo, EditUserInfo, logout } from '../service/user'
import { getLocal, setLocal } from '../utils/help'
import { showSuccessToast, Toast } from 'vant'
import { useRouter } from 'vue-router'
import { convertUrl } from '@/utils/help'

export default {
  components: {
    CustomHeader,
    AvatarCropper
  },

  // async watch() {
  //   if (uploaded) {
  //     const { data } = await getUserInfo()
  //     this.nickName = data.nickName
  //     this.avatarUrl = data.avatarUrl
  //     this.role = data.role
  //     uploaded = false
  //   }
  // },
  setup() {
    const router = useRouter()
    const dialogVisible=ref(false)
    // 关闭弹框所触发的事件
    const closeAvatarDialog=(data)=> {
      dialogVisible.value = false
    }
    const state = reactive({
      loginName: '',
      nickName: '',
      avatarUrl: '/default.png',
      password: '',
      role: '0'
    })
    onMounted( async () => {
      const {data} = await getUserInfo()
      state.loginName = data.loginName
      state.nickName = data.nickName
      state.avatarUrl = convertUrl(data.avatar)
      state.role = data.role
    }) 

    const save = async () => {
      const params = {
        role: state.role,
        loginName: state.loginName,
        nickName: state.nickName,
        password: state.password
      }
      const { data } = await EditUserInfo(params)
      showSuccessToast('保存成功')
      router.push({ path: '/user' })
    }

    const out = async () => {
      const { resultCode } = await logout()
      if (resultCode == 200) {
        setLocal('token', '')
        setLocal('user','')
        window.location.href = '/'
      }
    }

    return {
      ...toRefs(state),
      save,
      out,
      dialogVisible,
      closeAvatarDialog
    }
  },
    
}
</script>

<style lang="less" scoped>
  @import '../common/style/mixin';
  .seting-box {
    .input-item {
      margin-top: 44px;
    }
  }
  .save-btn {
      width: 100%;
      margin: 10px auto ;
    }
  .iconfont {
    width: 120px;
    height: 120px;
    display: block;
    margin:5px auto
  }
  

  .radio-text {
    color:#b4ffbc;
    font-size: 12px
  }
  
</style>
