<template>
  <el-dialog
    title="上传头像"
    v-model="dialogVisible"
    :show-close="false"
    :close-on-click-modal="false"
    :close-on-press-escape="false"
    destroy-on-close
    width="860px"
  >
    <div class="avatar-container">
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
        <div class="avatar-text">支持jpg、png格式的图片，大小不超过5M</div>
      </div>
      <!-- <img :src="options.img" alt=""> -->
      <!-- 已上传图片 -->
      <div v-show="options.img" class="avatar-crop">
        <vue-cropper
          class="crop-box"
          ref="cropper"
          :img="options.img"
          :autoCrop="options.autoCrop"
          :fixedBox="options.fixedBox"
          :canMoveBox="options.canMoveBox"
          :autoCropWidth="options.autoCropWidth"
          :autoCropHeight="options.autoCropHeight"
          :centerBox="options.centerBox"
          :fixed="options.fixed"
          :fixedNumber="options.fixedNumber"
          :canMove="options.canMove"
          :canScale="options.canScale"
        ></vue-cropper>
      </div>
    </div>
    <template #footer>
    <span  class="dialog-footer">
      <div class="reupload" @click="reupload">
        <span v-show="options.img">重新上传</span>
      </div>
      <div>
        <el-button @click="closeDialog">取 消</el-button>
        <el-button type="primary" @click="getCrop">确 定</el-button>
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
import { EditAvatar } from '../service/user'
import { showToast } from 'vant'
import { ElMessage, ElUpload, ElButton, ElDialog } from 'element-plus'
import { getLocal, setLocal } from "../utils/help"

export default defineComponent({
  props: {
    dialogVisible: {
      type: Boolean,
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
    const cropper=ref()
    const uploadBtn=ref()
    const elUpload=ref()
    const options=reactive({
        img: '', // 原图文件
        autoCrop: true, // 默认生成截图框
        fixedBox: false, // 固定截图框大小
        canMoveBox: true, // 截图框可以拖动
        autoCropWidth: 200, // 截图框宽度
        autoCropHeight: 200, // 截图框高度
        fixed: true, // 截图框宽高固定比例
        fixedNumber: [1, 1], // 截图框的宽高比例
        centerBox: true, // 截图框被限制在图片里面
        canMove: false, // 上传图片不允许拖动
        canScale: false // 上传图片不允许滚轮缩放
      })
      // vueCropper组件 裁剪配置信息
    
    // 读取原图
    const upload: UploadProps['onChange'] = (file, uploadFiles) => {
        console.log(file);
        
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
      reader.onload = e => {
        imgs = e.target.result
      }
      setLocal('AvatarName', file.name)
      // setLocal('AvatarFile', file.raw)
      setLocal('AvatarUrl', URL.createObjectURL(file.raw))
      console.log(getLocal('AvatarUrl'))
      options.img = getLocal('AvatarUrl')
      elUpload.value.clearFiles()
    }
    // 获取截图信息
   const getCrop= ()=> {
      // 获取截图的 base64 数据
      cropper.value.getCropData(async data => {
        try{
          const  message = await EditAvatar({
          'avatar':data,
          'name':getLocal('AvatarName')
        })
        console.log(message)
        } catch(err) {
          showToast('？？？？？？？？？？？？？？？？？？？？！！！！！！！！！！！！！')
        }
      })
      showToast('已更换成功，F5刷新页面看看效果吧！')
      closeDialog()
    }
    // 重新上传
    const reupload=()=> {
        
        console.log(uploadBtn.value.ref);
        
     uploadBtn.value.ref.click()
    }
    // 关闭弹框
   const closeDialog=()=> {
      emit('closeAvatarDialog')
      options.img = ''
    }
 return {
        options,
        cropper,
        uploadBtn,
        elUpload,
        upload,
        getCrop,
        reupload,
        closeDialog
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
.avatar-container {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 560px;
  height: 350px;
  background-color: #8ef3b8;
  margin-right: 55px;
  border-radius: 4px;
  .upload {
    text-align: center;
    margin-bottom: 24px;
  }
  .avatar-crop {
    width: 560px;
    height: 350px;
    position: relative;
    .crop-box {
      width: 100%;
      height: 100%;
      border-radius: 4px;
      overflow: hidden;
    }
  }
}
.avatar-text {
  color:#5336f8;
  font-size: 12px
}
</style>
