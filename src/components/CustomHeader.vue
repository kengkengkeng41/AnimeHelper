<template>
    <header class="custom-header van-hairline--bottom">
        <svg v-if="!isback" class="icon" aria-hidden="true" @click="goBack"><use xlink:href="#icon-ChiliPepper"></use></svg>
        <i v-else>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</i>
        <div class="custom-header-name">{{ title }}</div>
        <svg class="icon" aria-hidden="true" @click="goNext"><use xlink:href="#icon-Burger"></use></svg>
    </header>
    <!-- <div class="block" /> -->
</template>

<script>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
export default {
    name: 'CustomHeader',
    props: {
        title: {
            type: String,
            default: ''
        },
        back: {
            type: String,
            default: ''
        },
        noback: {
            type: Boolean,
            default: false
        },
        burger: {
            type: String,
            default: ''
        }
    },
    emits: ['callback'],
    setup(props, ctx) {
        const isback = ref(props.noback)
        const router = useRouter()
        const goBack = () => {
            if (!props.back) {
                router.go(-1)
            } else {
                router.push( { path: props.back} )
            }
            ctx.emit('callback')
        }

        const goNext = () => {
            router.push( { path: props.burger}) 
            ctx.emit('callback')
        }

        return {
            goBack,
            goNext,
            isback
        }

        
    }
}

</script>

<style lang="less" scoped>
@import '../common/style/mixin.less';
.custom-header {
    position: fixed;
    top: 0;
    left: 0;
    z-index: 10000;
    .fj();
    .wh(100%, 35px);
    line-height: 35px;
    padding: 0 10px;
    .boxSizing();
    color: #252525;
    background: #fff;
    .custom-header-name {
        font-size: 14px;
    }
}
.block {
    height: 22px;
}
      .icon{
        color: #ffffff;
        font-size: 25px;
      }
</style>