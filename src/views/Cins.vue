<template>
    <div class="insert">
        <custom-header :title="'轮播图编辑'" :back="'/user'" :burger="'/home'"></custom-header>
        <div style="display: flex">
            <img :src="p2blob" class="pic2" @click="dialogVisible=2"/>
        </div>
        <picture-cropper v-if="dialogVisible==2" v-model="dialogVisible" @closePictureDialog="closePictureDialog"></picture-cropper>
        <div class="body">
            <van-form @submit="onSubmit">
              <van-field
                v-model="number"
                name="number"
                label="番剧编号"
                placeholder="番剧编号"
                :rules="[{ required: false, message: '请输入番剧编号' }]"
                />
                <div style="margin: 16px;">
                    <van-button round block color="#1baeae" native-type="submit">保存</van-button>
                </div>
            </van-form>
        </div>
    </div>
    
    <div class="container">
      <el-table :data="list" border :header-cell-style="{'text-align':'center'}">
        <el-table-column label="序号" type="index" width="80" align="center"></el-table-column>
        <el-table-column fixed="right" label="图片" align="center">
          <template #default="{ row }">
            <img :src="convertUrl(row.carouselUrl)" class="wallpaper" >
          </template>
        </el-table-column>
        <el-table-column prop="insertTime" label="插入时间" width="200" align="center"></el-table-column>
        <el-table-column fixed="right" label="操作" width="150" align="center">
          <template #default="{ row }">
          <el-button type="danger" size="small" @click="handleDel(row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
</template>

<script>
import {ref,reactive,onMounted, toRefs} from "vue";
import PictureCropper from '@/components/PictureCropper.vue'
import CustomHeader from '@/components/CustomHeader.vue'
import { getLocal, setLocal } from '../utils/help'
import { showSuccessToast, DropdownMenu, DropdownItem } from 'vant'
import { useRouter } from 'vue-router'
import { deleteCarousel, insertCarousel, selectCarousel } from '../service/carousel'
import { dateFormat2 } from '@/utils/filter.js'
import { convertUrl } from '@/utils/help'

export default {
  components: {
    CustomHeader,
    PictureCropper,
    DropdownItem,
    DropdownMenu,
  },

  setup() {
    // 关闭弹框所触发的事件
    const closePictureDialog=()=> {
      
      if (state.dialogVisible==2) {
        state.p2blob = getLocal('picBlob') != "null" ? getLocal('picBlob') : '/default2.png'
        setLocal('picBlob', null)
        state.p2Url = getLocal('picUrl') != "null" ? getLocal('picUrl') : state.p2Url
        setLocal('picUrl', null)
      }
      state.dialogVisible = 0
      
    }
    const state = reactive({
      number:'',
      dialogVisible: 0,
      p2blob: '/default2.png',
      p2Url: '',
    })
    const list = ref()
    onMounted( async () => {
      const {data, message, resultCode} = await selectCarousel()
      data.forEach((item) => {
        item.insertTime = formatterTime(item.insertTime)
      })
      list.value = data
    }) 
    const onSubmit = async () => {
      console.log('carouselUrl'+state.p2Url)
      const {data, message, resultCode} = await insertCarousel({
          "carouselUrl": state.p2Url,
          "redirectUrl": state.number=='' ? '#/browser': '#/animeFv?number='+state.number,
          "isDeleted": '0',
      })
      // Object.keys(result).forEach(key=>{
      //   alert('key:'+key+'value:'+result[key])
      //   console.log('key:',key,'value:',result[key])
      // })
      showSuccessToast(message)
      console.log(message+resultCode)
    }

    const handleDel = async (row) => {
        // async await 可直接解析出对象
        const { message } = await deleteCarousel(row.carouselId)
        console.log(message)
        alert(message+'\n'+"F5刷新以检视效果")
    }

    const formatterTime = ((time) => {
      return dateFormat2(time);
    })

    return {
      ...toRefs(state),
      closePictureDialog,
      list,
      handleDel,
      onSubmit,
      convertUrl
    }
  },
    
}
</script>


<style lang="less" scoped>
  @import '../common/style/mixin';

  .pic2 {
    width: 360px;
    height: 100%;
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
  .container {
    width: 960px;
    background: #b4e1ff;
    padding: 10px 16px;
    text-align: center;
    margin:auto;
    .wallpaper {
      width: 720px;
      height: 100%;
    }
  }
  
  
</style>