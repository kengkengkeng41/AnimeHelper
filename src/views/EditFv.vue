<template>
    <div class="pagination-container">
        <custom-header :title="'动漫列表'" :back="'/home'" :burger="'/login'"></custom-header>
        <el-table :data="list" border :header-cell-style="{'text-align':'center'}">
        <el-table-column label="视觉图" width="120px" align="center">
            <template v-slot="list">
            <el-image :src="convertUrl(list.row.p1)" sytle="width: 200px; height: 100%" alt=""></el-image>
            </template>
        </el-table-column>
        <el-table-column prop="number" label="标识" width="120" align="center"></el-table-column>
        <el-table-column prop="name" label="番名" align="center"></el-table-column>
        <el-table-column prop="jName" label="日文名" align="center"></el-table-column>
        <el-table-column prop="sDay" label="星期" width="100" align="center"></el-table-column>
        <el-table-column prop="ym" label="开播" width="120" align="center"></el-table-column>
        <!-- <el-table-column prop="insertTime" label="插入时间" width="220" :formatter="formatterTime"></el-table-column> -->
        <el-table-column fixed="right" label="操作" width="120" align="center">
            <template #default="{ row }" >
            <el-button type="text" size="small" @click="handleDetail(row)">详情</el-button>
            </template>
        </el-table-column>
        </el-table>

        <!-- <el-pagination @size-change="sizeChange" @current-change="currentChange"
        :current-page="page" :page-size="size" :page-sizes="pageSizes"
        layout="total, sizes, prev, pager, next, jumper" :total="total">
        </el-pagination> -->
        
    </div>
</template>

<script>
import {ref,reactive,onMounted, toRefs} from "vue";
import { findAllAnime } from '../service/anime'
import CustomHeader from '@/components/CustomHeader.vue'
import { useRouter } from 'vue-router'
import { showSuccessToast } from 'vant';
import { convertUrl } from '@/utils/help'

export default {
  components:{
    CustomHeader
  },

    setup() {
        const router = useRouter();
        const state = reactive({
            page: 1, //第几页
            size: 5, //一页多少条
            total: 0, //总条目数
            pageSizes: [5, 10, 20, 50, 100, 200, 300, 400, 500, 1000], //可选择的一页多少条
        })
        const list = ref()

        onMounted( async () => {
            const {data} = await findAllAnime()
            list.value = data;
        })


        const handleDetail = (row) => {
            // console.log(row)
            console.log("番剧详情查看跳转：#"+row.number)
            router.push({ path: '/animeFv', query:{number:row.number} })
        }



        return {
            ...toRefs(state),
            handleDetail,
            list,
            convertUrl
        }
            
    }

}
</script>

<style lang="less">
    .pagination-container {
        text-align: center;
        width: 165vh;
        margin:auto;
        background: #c9ffb4;
        padding: 10px 16px;
    }
</style>
