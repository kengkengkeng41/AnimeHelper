<template>
    <div class="pagination-container">
        <custom-header :title="'编辑'" :back="'/user'" :burger="'/home'"></custom-header>
        <el-button class="btn" @click="goInsert">新建条目</el-button>
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
            <el-button type="text" size="small" @click="handleDetail(row)"
                >查看</el-button
            >
            <el-button type="text" size="small" @click="handleEdit(row)"
                >编辑</el-button
            >
            <el-button type="text" size="small" @click="handleDel(row)"
                >删除</el-button
            >
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
import { deleteAnime, findAllAnime } from '../service/anime'
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


        //获取表格数据，自行分页（splice）
    //     const getTabelData2 = () => {
    //     let data=JSON.parse(JSON.stringify(list))
    //     list = data.splice(
    //         (state.page - 1) * state.size,
    //         state.size
    //     );
    //     state.total=list.length
    //     }

    //     //page改变时的回调函数，参数为当前页码
    //     const currentChange = (val) => {
    //     console.log("翻页，当前为第几页", val);
    //     state.page = val;
    //     state.getTabelData2();
    //     }

    //     //size改变时回调的函数，参数为当前的size
    //     const sizeChange = (val) => {
    //     console.log("改变每页多少条，当前一页多少条数据", val);
    //     state.size = val;
    //     state.page = 1; 
    //     state.getTabelData2();
    //     }

    //   const formatterTime = computed(() => {
    //     return (time) => {
    //       return dateFormat1(time.insertTime);
    //     };
    //   });
        
        const goInsert = () => {
            router.push( { path:'/insertA' })
        }

        const handleDetail = (row) => {
            // console.log(row)
            console.log("番剧详情查看跳转：#"+row.number)
            router.push({ path: '/anime-detail', query:{number:row.number} })
        }

        const handleEdit = (row) => {
            console.log("番剧编辑跳转：#"+row.number)
            router.push( { path: '/updateA', query:{number:row.number} })
            
        }

        const handleDel = async (row) => {
            // async await 可直接解析出对象
            const { message } = await deleteAnime(row.number)
            console.log(message)
            alert(message+'\n'+"F5刷新以检视效果")
        }

        return {
            ...toRefs(state),
            // getTabelData2,
            // currentChange,
            // sizeChange,
            // formatterTime,
            goInsert,
            handleDetail,
            handleEdit,
            handleDel,
            list,
            convertUrl
        }
            
    },

    // },
}
</script>

<style lang="less">

    .pagination-container {
        text-align: center;
        width: 960px;
        margin:auto;
        background: #c9ffb4;
        padding: 10px 16px;
        .btn {
            margin-bottom:10px;
            width: 33%;
            color:#054291;
        }
    }
</style>
