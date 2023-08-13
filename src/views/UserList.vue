<template>
  <div class="pagination-container">
    <custom-header title="账号管理" :back="'/user'" :burger="'/home'"></custom-header>
    <el-table :data="list" border :header-cell-style="{'text-align':'center'}">
      <el-table-column label="序号" type="index" width="50" align="center"></el-table-column>
      <el-table-column prop="userId" label="标识" width="120" align="center"></el-table-column>
      <el-table-column prop="loginName" label="登录名" align="center"></el-table-column>
      <el-table-column prop="nickName" label="姓名" align="center"></el-table-column>
      <el-table-column prop="role" label="角色" width="80" align="center"></el-table-column>
      <el-table-column prop="insertTime" label="注册时间" width="220" align="center"></el-table-column>
    <el-table-column label="头像" align="center">
        <template v-slot="list">
        <el-avatar :src="convertUrl(list.row.avatar)" :size="50" shape="square"></el-avatar>
        </template>
    </el-table-column>

    </el-table>

    <el-pagination @size-change="sizeChange" @current-change="currentChange"
      :current-page="page" :page-size="size" :page-sizes="pageSizes"
      layout="total, sizes, prev, pager, next, jumper" :total="total">
    </el-pagination>
  </div>
</template>

<script>
import {ref,reactive,onMounted, toRefs} from "vue";
import { getVisitorList } from '../service/user'
import CustomHeader from '@/components/CustomHeader.vue'
import { dateFormat2 } from '@/utils/filter.js'
import { convertUrl } from '@/utils/help'

export default {
  components:{
    CustomHeader
  },

    setup() {
        const state = reactive({
            page: 1, //第几页
            size: 5, //一页多少条
            total: 0, //总条目数
            pageSizes: [5, 10, 20, 50, 100, 200, 300, 400, 500, 1000], //可选择的一页多少条
        })
        const list = ref()

        onMounted( async () => {
            const {data} = await getVisitorList()
            data.forEach((item) => {
              item.insertTime = formatterTime(item.insertTime)
            })
            list.value = data;
        })

        const getTabelData = () => {
            //allData为全部数据
        state.tableData = state.allData.slice(
            (state.page - 1) * state.size,
            state.page * state.size
        );
        state.total=state.allData.length
        }


        //获取表格数据，自行分页（splice）
        const getTabelData2 = () => {
        let data=JSON.parse(JSON.stringify(state.allData))
        state.tableData = data.splice(
            (state.page - 1) * state.size,
            state.size
        );
        state.total=state.allData.length
        }

        //page改变时的回调函数，参数为当前页码
        const currentChange = (val) => {
        console.log("翻页，当前为第几页", val);
        state.page = val;
        state.getTabelData2();
        }

        //size改变时回调的函数，参数为当前的size
        const sizeChange = (val) => {
        console.log("改变每页多少条，当前一页多少条数据", val);
        state.size = val;
        state.page = 1; 
        state.getTabelData2();
        }

      const formatterTime = ((time) => {
          return dateFormat2(time);
      })
        
        
        return {
            ...toRefs(state),
            getTabelData2,
            currentChange,
            sizeChange,
            formatterTime,
            list,
            convertUrl
        }
            
    },

    // },
}
</script>

<style scoped>
.pagination-container {
  width: 720px;
  background: #c9ffb4;
  padding: 10px 16px;
}

</style>