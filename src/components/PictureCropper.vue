<template>
  <el-dialog
    title="上传图片"
    v-model="dialogVisible"
    :show-close="false"
    :close-on-click-modal="false"
    :close-on-press-escape="false"
    destroy-on-close
    width="860px"
  >
    <div class="picture-container">
        <!-- {{dialogVisible}} -->
      <!-- 待上传图片 -->
      <div v-show="!options.img" >
        <el-upload
          class="upload"
          ref="elUpload"
          action="#"
          :on-change="upload"
          accept="image/png, image/jpeg, image/jpg"
          :show-file-list="false"
          :auto-upload="false"
        >
          <el-button default="trigger"  type="primary" ref="uploadBtn">
            选择图片
          </el-button>
        </el-upload>
        <div class="picture-text">支持jpg、png格式的图片，大小不超过5M</div>
      </div>
      <!-- <img :src="options.img" alt=""> -->
      <!-- 已上传图片 -->
      <div v-show="dialogVisible!=0">
        <img v-show="options.show" :src="options.img" class="picture-crop"/>
      </div>

    </div>
    <template #footer>
    <span  class="dialog-footer">
      <div class="reupload" @click="reupload">
        <span v-show="options.img">重新上传</span>
      </div>
      <div>
        <el-button @click="closeDialog">取 消</el-button>
        <el-button type="primary" @click="getUrl">确 定</el-button>
      </div>
    </span>
    </template>
  </el-dialog>
</template>

<script lang="ts" >
import axios from 'axios'
import {ref,reactive,defineComponent} from "vue"
import { VueCropper } from 'vue-cropper'
import 'vue-cropper/dist/index.css'
import type { UploadProps, UploadUserFile,} from 'element-plus'
import { showToast } from 'vant'
import { ElMessage, ElUpload, ElButton, ElDialog } from 'element-plus'
import { getLocal, setLocal } from "../utils/help"
import { insertPic } from '../service/anime'

export default defineComponent({
  props: {
    dialogVisible: {
      type: Number,
    }
  },
  components:{
    VueCropper,
    ElButton,
    ElUpload,
    ElDialog,
    axios
  },
  setup(props, { emit }) {
    let imgs = {}
    const uploadBtn=ref()
    const elUpload=ref()
    const options=reactive({
        img: '', // 原图文件
        show: false
      })
      // vueCropper组件 裁剪配置信息
    
    // 读取原图
    const upload: UploadProps['onChange'] = (file, uploadFiles) => {
        console.log(file);
        console.log(props.dialogVisible);
        const isIMAGE = file.raw.type === 'image/jpeg' || file.raw.type === 'image/png'
        const isLt5M = file.raw.size / 1024 / 1024 < 5
        if (!isIMAGE) {
            ElMessage.warning("请选择 jpg、png 格式的图片" )
            return false
        }
        if (!isLt5M) {
            ElMessage.warning("图片大小不能超过 5MB" )
            return false
        }
        let reader = new FileReader()
        reader.readAsDataURL(file.raw)
        reader.onload = async (e) =>  {
            imgs = e.target.result
            const result = await insertPic ({
              'picture':imgs,
              'filename':file.name
            })
            // Object.keys(result).forEach(key=>{
            //   alert('key:'+key+'value:'+result[key])
            //   console.log('key:',key,'value:',result[key])
            // })
            setLocal('picUrl', result.data)
            setLocal('picBlob', URL.createObjectURL(file.raw))
            console.log(getLocal('picBlob')+"\n"+getLocal('picUrl'))
          // if (props.dialogVisible==1){
          //     // setLocal('picture1Name', file.name)
          //     // setLocal('pictureFile', file.raw)
          //     setLocal('p1Url', data)
          //     setLocal('p1Blob', URL.createObjectURL(file.raw))
          //     console.log(getLocal('p1Blob'))
          //     console.log(getLocal('p1Url'))
          //     options.img = getLocal('p1Blob')
          // } else if (props.dialogVisible==2) {
          //     setLocal('p2Url', data)
          //     setLocal('p21Blob', URL.createObjectURL(file.raw))
          //     console.log(getLocal('pi2Blob'))
          //     console.log(getLocal('p2Url'))
          //     options.img = getLocal('p2Blob')
          // }
          options.img=getLocal('picBlob')
          options.show=true
        }

        elUpload.value.clearFiles()
    }


    // 获取截图信息
   const getUrl = ()=> {
      
      closeDialog()
    }
    // 重新上传
    const reupload=()=> {
        
        console.log(uploadBtn.value.ref);
        
     uploadBtn.value.ref.click()
    }
    // 关闭弹框
    const closeDialog=()=> {
      emit('closePictureDialog')
      options.img = ''
      options.show = false
    }

    return {
            options,
            uploadBtn,
            elUpload,
            upload,
            getUrl,
            reupload,
            closeDialog,
        }
    }
})


</script>

<style lang="scss" scoped>
.dialog-footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 14px;
  .reupload {
    color: #409eff;
    cursor: pointer;
  }
}
.picture-container {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 560px;
  height: 350px;
  background-color: #62cccc;
  margin-right: 10px;
  border-radius: 4px;
  .upload {
    text-align: center;
    margin-bottom: 24px;
  }
  .picture-crop {
    width: 100%;
    height: 350px;
    position: relative;
  }
}
.picture-text {
  color:#5336f8;
  font-size: 12px
}
</style>
