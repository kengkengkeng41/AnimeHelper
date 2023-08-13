<template>
    <div class="nav-bar van-hairlin--top">
        <ul class="nav-list">
            <router-link class="nav-list-item" to="Eco">
                <svg class="icon" aria-hidden="true"><use xlink:href="#icon-xiangsu_fangzi"></use></svg>
                <span>省流</span>
            </router-link>
            <router-link class="nav-list-item" to="browser">
                <svg class="icon" aria-hidden="true"><use xlink:href="#icon-xiangsu_caomei"></use></svg>
                <span>动漫</span>
            </router-link>
            <router-link class="nav-list-item" to="about">
                <svg class="icon" aria-hidden="true"><use xlink:href="#icon-xiangsu_jinbi"></use></svg>
                <span>关于</span>
            </router-link>
            <router-link class="nav-list-item" to="user">
                <svg class="icon" aria-hidden="true"><use xlink:href="#icon-xiangsu_tuzi"></use></svg>
                <span>我的</span>
            </router-link>
        </ul>
    </div>
</template>

<script>
export default {
    name: 'NavBar'
}
</script>

<style lang="less" scoped>
@import '../common/style/mixin.less';
.nav-bar {
    position: fixed;
    left: 0;
    bottom: 0;
    width: 100%;
    padding: 5px 0;
    z-index: 1000;
    background: #014758;
    transform: translateZ(0);
    -webkit-transform: translateZ(0);
    .nav-list {
        .fj();
        width: 100%;
        padding: 0;
        .nav-list-item {
            display: flex;
            flex: 1;
            flex-direction: row;
            text-align: center;
            color: #ffffff;
            &.router-link-active {
                color: @primary;
            }
            svg {
                transform: translateX(150%);
                font-size: 13px;
            }
            span {
                font-size: 15px;
            }
        }
    }
        
}
</style>