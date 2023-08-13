<template>
    <div class="insert">
        <custom-header :title="'新建番剧条目'" :back="'/edit'" :burger="'/home'"></custom-header>
        <div style="display: flex">
            <img :src="p1blob" class="pic1" @click="dialogVisible=1"/><img :src="p2blob" class="pic2" @click="dialogVisible=2"/>
        </div>
        <picture-cropper v-if="dialogVisible==1" v-model="dialogVisible" @closePictureDialog="closePictureDialog"></picture-cropper>
        <picture-cropper v-if="dialogVisible==2" v-model="dialogVisible" @closePictureDialog="closePictureDialog"></picture-cropper>
        <div class="body">
            <van-form @submit="onSubmit">
                <van-field
                v-model="name"
                name="name"
                label="番名"
                placeholder="番名"
                :rules="[{ required: true, message: '请输入番名' }]"
                class="form"
                />
                <van-field
                v-model="jName"
                name="jName"
                label="日文名"
                placeholder="日文名"
                :rules="[{ required: false, message: '填写日文名' }]"
                class="form"
                />
                <van-field
                v-model="ym"
                name="ym"
                label="年月"
                placeholder="年月"
                :rules="[{ required: false, message: '类似202304' }]"
                class="form"
                />

                <van-field
                label="星期"
                center
                autocomplete="off"
                placeholder="周几的番剧"
                name="sDay"
                class="form"
                >
                  <template #input>
                      <!-- 自定义样式label-flex -->
                      <van-dropdown-menu id="rank" active-color="#1989fa" class="dropdown">
                          <van-dropdown-item v-model="sDay" :options ="idList" class="dropitem"/>
                      </van-dropdown-menu>
                  </template>
                </van-field>

                <van-field
                v-model="brief"
                type="textarea"
                name="brief"
                label="番剧简介"
                placeholder="番剧简介"
                :rules="[{ required: false, message: '番剧简介' }]"
                class="form"
                />
                
                <div style="margin: 16px;">
                    <van-button round block color="#1baeae" native-type="submit">保存</van-button>
                </div>
            </van-form>
        </div>
    
    </div>
</template>

<script>
import {ref,reactive,onMounted, toRefs} from "vue";
import PictureCropper from '@/components/PictureCropper.vue'
import CustomHeader from '@/components/CustomHeader.vue'
import { getLocal, setLocal } from '../utils/help'
import { showSuccessToast, DropdownMenu, DropdownItem } from 'vant'
import { useRouter } from 'vue-router'
import { insertAnime } from '../service/anime';
import { convertUrl } from '@/utils/help'

export default {
  components: {
    CustomHeader,
    PictureCropper,
    DropdownItem,
    DropdownMenu,
  },

  setup() {
    const idList = [
        {text:"周一",value:"1"},
        {text:"周二",value:"2"},
        {text:"周三",value:"3"},
        {text:"周四",value:"4"},
        {text:"周五",value:"5"},
        {text:"周六",value:"6"},
        {text:"周日",value:"7"},
        {text:"旧番",value:"-"},
    ]
    const router = useRouter()
    // 关闭弹框所触发的事件
    const closePictureDialog=(data)=> {
      
      if (state.dialogVisible==1) {
        // alert(state.dialogVisible)
        // alert(getLocal('picBlob'))
        state.p1blob = getLocal('picBlob') != "null" ? getLocal('picBlob') : '/default1.png'
        setLocal('picBlob', null)
        console.log("insertAnime:"+state.p1blob)
        state.p1Url = getLocal('picUrl') != "null" ? getLocal('picUrl') : state.p1Url
        setLocal('picUrl', null)
      }
      if (state.dialogVisible==2) {
        state.p2blob = getLocal('picBlob') != "null" ? getLocal('picBlob') : '/default2.png'
        setLocal('picBlob', null)
        state.p2Url = getLocal('picUrl') != "null" ? getLocal('picUrl') : state.p2Url
        setLocal('picUrl', null)
      }
      state.dialogVisible = 0
      
    }
    const state = reactive({
      name:'',
      jName:'',
      ym:'',
      sDay:'',
      brief:'',
      p1blob: '/default1.png',
      p2blob: '/default2.png',
      dialogVisible: 0,
      p1Url: '',
      p2Url: '',
    })
    onMounted( async () => {
      
    }) 
    const onSubmit = async (values) => {
      console.log(values+'\n'+state.p1Url)
      const {data, message, resultCode} = await insertAnime({
          "name": state.name,
          "jName": state.jName,
          "ym":state.ym,
          "sDay":state.sDay,
          "brief":state.brief,
          "p1":convertUrl(state.p1Url),
          "p2":convertUrl(state.p2Url)
      })
      // Object.keys(result).forEach(key=>{
      //   alert('key:'+key+'value:'+result[key])
      //   console.log('key:',key,'value:',result[key])
      // })
      showSuccessToast(message)
      console.log(message+resultCode)
    }

    return {
      ...toRefs(state),
      closePictureDialog,
      idList,
      onSubmit,
    }
  },
    
}
</script>


<style lang="less" scoped>
  @import '../common/style/mixin';

  .pic1 {
    width: 100%;
    height: 140px;
    display: block;
    margin:5px auto
  }
  .pic2 {
    width: 100%;
    height: 140px;
    display: block;
    margin:5px auto
  }

  .body {
    .form{
      background: #b8fce5;
    }
    
    .dropdown {
      width: 50%;
      margin-right: 5px;
      text-align: center;
      --dropdown-menu-background-color: #2E75B6;
      --dropdown-menu-title-text-color: #e8ffb2;

    .dropitem {
      text-align: center;
      width: 50%;
    }
  }

  }
  
  
</style>