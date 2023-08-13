<template>
      <div class="links">
    <custom-header :title="'链接列表'" :back="'/home'" :burger="'/login'"></custom-header>

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
  </div>
   
</template>

<script>
import CustomHeader from '@/components/CustomHeader.vue'
import { onMounted, ref } from 'vue'
import { selectAll } from '../service/links';
import useClipboard from 'vue-clipboard3';
import { showSuccessToast } from 'vant'
import { dateFormat2 } from '@/utils/filter.js'

export default {
    components: {
        CustomHeader,
    },

    setup(){
      const { toClipboard } = useClipboard();
      const list = ref()
      onMounted(async () => {
          const linkResult = await selectAll()
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
            handleCopy,
            list
        }
    
    }
}

</script>

<style lang="less">
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
}
</style>