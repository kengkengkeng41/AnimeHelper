<template>
    <van-swipe class="my-swipe" :autoplay="3000" indicatior-color="#1baeae">
        <van-swipe-item v-for="(item, index) in list" :key="index">
            <img :src="item.carouselUrl" alt="" @click="goTo(item.redirectUrl)">
        </van-swipe-item>
    </van-swipe>
</template>

<script>
export default {
    props: {
        list: {
            type: Array,
            default: []
        }
    },
    setup() {
        const goTo = (url) => {
            window.open(url)
        }
        return {
            goTo
        }
    }
}
</script>

<style lang='less' scoped>
    .my-swipe {
        img {
            width:165vh;
            height:100%;
        }
    }
</style>


