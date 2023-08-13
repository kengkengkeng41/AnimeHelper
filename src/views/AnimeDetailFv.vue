<template>
  <div class="product-detail">
    <custom-header :title="'番剧详情'" :back="'/browser'" :burger="'/login'"></custom-header>
    <div class="detail-content">
      <img :src="p1" class="pic1" alt="">
      <div class="detail-swipe-wrap">
          
      </div>
      <div class="product-info">
        <div class="product-title">
          番剧名：{{ name }}
        </div>
        <div class="product-desc">
          日文名：{{ jName }}
        </div>
        <div class="text1">
          编号：#{{ number }}
        </div>
        <div class="text1">
          首播年月：{{ ym }} ----- 周{{ sDay }}更新
        </div>
        <div class="text1">
          <text class="area">番剧简介：{{ brief }}</text>
        </div>
        <div class="text2">
          条目创建：{{ insertUser }} ----- 创建日期：{{ insertTime }}
        </div>
        <div class="text2">
          条目更新：{{ updateUser }} ----- 更新日期：{{ updateTime }}
        </div>
      </div>
    </div>
  </div>


  <div class="links">
    <van-button class="btn" round block>链接列表</van-button>

    <div class="container">
      <el-table :data="list" border :header-cell-style="{'text-align':'center'}">
        <el-table-column label="序号" type="index" width="80" align="center"></el-table-column>
        <el-table-column prop="linkName" label="名称" align="center"></el-table-column>
        <el-table-column prop="subtitle" label="字幕组" width="150" align="center"></el-table-column>
        <el-table-column prop="insertTime" label="插入时间" width="200" align="center"></el-table-column>
        <el-table-column fixed="right" label="操作" width="200" align="center">
          <template #default="{ row }">
          <el-button type="primary" size="small" @click="handleCopy(row)">复制链接</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <img class="wallpaper" :src="p2">
  </div>
   
</template>

<script>
import CustomHeader from '@/components/CustomHeader.vue'
import { useRoute } from 'vue-router'
import { reactive, toRefs, onMounted, ref } from 'vue'
import { selectAnimefv } from '@/service/anime'
import { dateFormat2 } from '@/utils/filter.js'
import { selectLinks } from '../service/links';
import useClipboard from 'vue-clipboard3';
import { showSuccessToast } from 'vant'
import { convertUrl } from '@/utils/help'

export default {
    components: {
        CustomHeader,
    },

    setup(){
      const { toClipboard } = useClipboard();
      const route = useRoute()
      console.log(route.query.number)
      const state = reactive({
          display:false,

          number:0,
          name:'',
          jName:'',
          sDay:'',
          ym:'',
          brief:'',
          insertTime:'',
          insertUser:'',
          updateTime:'',
          updateUser:'',
          p1:'',
          p2:'',

      })
      const list = ref()

      onMounted(async () => {
          const { data, message, resultCode } = await selectAnimefv(route.query.number)
          console.log(data.number+'\n'+data.name+'\n'+data.jName+'\n'+data.sDay+'\n'+data.ym+'\n'+data.p1+'\n'+data.p2)
          state.number = data.number
          state.name = data.name
          state.jName = data.jName
          state.sDay = data.sDay
          state.ym = data.ym
          state.insertTime = formatterTime(data.insertTime)
          state.insertUser = data.insertUser
          state.updateTime = formatterTime(data.updateTime)
          state.updateUser = data.updateUser
          state.p1=convertUrl(data.p1)
          state.p2=convertUrl(data.p2)
          state.brief = data.brief

          const linkResult = await selectLinks(state.number)
          Object.keys(linkResult.data).forEach(key=>{
            // alert('key:'+key+' value:'+linkResult[key])
            console.log('key:',key,'value:',linkResult.data[key])
          })
          linkResult.data.forEach((item) => {
            item.insertTime = formatterTime(item.insertTime)
          })
          list.value = linkResult.data
          

      })

      const handleCopy = async (row) => {
        try {
          await toClipboard(row.link);
          console.log("已复制："+row.linkName)
          showSuccessToast("复制成功！")
        } catch (e) {
          console.error(e);
        }
      }

      

      const formatterTime = ((time) => {
        return dateFormat2(time);
      })

        return {
            ...toRefs(state),
            formatterTime,
            handleCopy,
            list
        }
    }

    
}
</script>

<style lang="less">
  @import '../common/style/mixin';
  .product-detail {
    width: 150vh;
    .detail-content {
      .pic1{
        width: 300px;
        height: 100%;
      }
      width: 900px;
      display: flex;
      margin-top: 0px;
      .product-info {
        padding: 0 120px;
        .product-title {
          font-size: 18px;
          text-align: left;
          color: #4cff3b;
        }
        .product-desc {
          font-size: 14px;
          text-align: left;
          color: #65fc86;
          padding: 5px 0;
        }
        .text1 {
          .fj();
          font-size: 10px;
          .area {
            padding: 10px 0px;
            color:rgb(75, 203, 253);
            width:fit-content;
            height:fit-content;
          }
        }
        .text2 {
          font-size: 10px;
          color:#999
        }
        
      }
      .product-intro {
        width: 100%;
        ul {
          .fj();
          width: 100%;
          margin: 10px 0;
          li {
            flex: 1;
            padding: 5px 0;
            text-align: center;
            font-size: 15px;
            border-right: 1px solid #999;
            box-sizing: border-box;
            &:last-child {
              border-right: none;
            }
          }
        }
        .product-content {
          padding: 0 20px;
          img {
            width: 100%;
          }
        }
      }
    }
  }
  .links{
    width: 150vh;
    margin-top: 10px;
    .container {
      background: #b4e1ff;
      padding: 10px 16px;
      text-align: center;
      margin:auto;
      
    }
    .btn {
      margin-bottom:10px;
      width: 33%;
      color:#1b42f0;
    }
    .wallpaper {
      margin-top: 5px;
      width: 150vh;
      height: 100%;
    }
  }
  

</style>
