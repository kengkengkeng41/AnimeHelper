<template>
    <div class="insert">
        <custom-header :title="'修改番剧条目'" :back="'/edit'" :burger="'/home'"></custom-header>
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
                />
                <van-field
                v-model="jName"
                name="jName"
                label="日文名"
                placeholder="日文名"
                :rules="[{ required: false, message: '填写日文名' }]"
                />
                <van-field
                v-model="ym"
                name="ym"
                label="年月"
                placeholder="年月"
                :rules="[{ required: false, message: '类似202304' }]"
                />

                <van-field
                label="星期"
                center
                autocomplete="off"
                placeholder="周几的番剧"
                name="sDay"
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
                />
                
                <div style="margin: 16px;">
                    <van-button round block color="#1baeae" native-type="submit">保存</van-button>
                </div>
            </van-form>
        </div>
    
    </div>
</template>

<script>
import { reactive,onMounted, toRefs } from "vue";
import PictureCropper from '@/components/PictureCropper.vue'
import CustomHeader from '@/components/CustomHeader.vue'
import { getLocal, setLocal } from '../utils/help'
import { showSuccessToast, DropdownMenu, DropdownItem } from 'vant'
import { useRouter,useRoute } from 'vue-router'
import { updateAnime, selectAnime } from '../service/anime';
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
        if (getLocal('picBlob') != "null"){
          state.p1blob = getLocal('picBlob')
          console.log("updateAnime:"+state.p1blob)
          setLocal('picBlob', null)
        }

        if (getLocal('picUrl') != "null"){
          state.p1Url = getLocal('picUrl')
          console.log("updateAnime:"+state.p1Url)
          setLocal('picUrl', null)
        }

      }
      if (state.dialogVisible==2) {

        if (getLocal('picBlob') != "null"){
          state.p2blob = getLocal('picBlob')
          console.log("updateAnime:"+state.p2blob)
          setLocal('picBlob', null)
        }

        if (getLocal('picUrl') != "null"){
          state.p2Url = getLocal('picUrl')
          console.log("updateAnime:"+state.p2Url)
          setLocal('picUrl', null)
        }
      }
      state.dialogVisible = 0
      
    }
    const state = reactive({
      number:0,
      name:'',
      jName:'',
      ym:'',
      sDay:'',
      brief:'',
      p1blob: '',
      p2blob: '',
      dialogVisible: 0,
      p1Url: '',
      p2Url: '',
      changed: false
    })
    onMounted( async () => {
      const route = useRoute()
      state.number = route.query.number
      console.log("跳转修改番剧条目：#"+state.number)
      const { data, message, resultCode } = await selectAnime(state.number)
      console.log(data.number+'\n'+data.name+'\n'+data.jName+'\n'+data.sDay+'\n'+data.ym+'\n'+data.p1+'\n'+data.p2)
      state.name = data.name
      state.jName = data.jName
      state.sDay = data.sDay
      state.ym = data.ym
      state.p1blob=data.p1
      state.p1Url=convertUrl(data.p1)
      state.p2blob=data.p2
      state.p2Url=convertUrl(data.p2)
      state.brief=data.brief
    }) 
    const onSubmit = async (values) => {
      console.log(values+'\n'+state.p1Url)
      const {data, message, resultCode} = await updateAnime({
          "name": state.name,
          "jName": state.jName,
          "ym":state.ym,
          "sDay":state.sDay,
          "brief":state.brief,
          "p1":state.p1Url,
          "p2":state.p2Url,
          "number":state.number
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
    .dropdown {
      width: 50%;
      margin-right: 5px;
      text-align: center;
      --dropdown-menu-background-color: #2E75B6;
      --dropdown-menu-title-text-color: #ffffff;

    .dropitem {
      text-align: center;
      width: 50%;
    }
  }

  }
  
  
</style>