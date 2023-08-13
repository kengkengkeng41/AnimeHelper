<!--About.vue-->
<template>
  <div class="about">
    <custom-header :name="'关于'" :back="'/home'" :burger="'/login'"></custom-header>
    <div class="about-body">
      <van-divider :style="{ color: '#1baeae', borderColor: '#1baeae', fontSize: '25px', fontWeight: 500 }">简介</van-divider>
      <div>AnimeHelper:beta-0.1.0
      </div>
      <br>
      <div>
        是为动漫链接分享网站，网站中的链接一律使用磁力链接，应配合磁力链接下载器使用。
      </div>
      <van-divider :style="{ color: '#1baeae', borderColor: '#1baeae', fontSize: '25px', fontWeight: 500 }">分享地址</van-divider>
      <div>
        <div><a target="_blank" href="https://www.bitcomet.com/en/downloads">比特彗星官方下载</a>（推荐）</div>
        <br/>
      </div>
    </div>
  </div>
</template>

<script>
import CustomHeader from '@/components/CustomHeader.vue'
export default {
  components: {
    CustomHeader
  },
}
</script>

<style lang="less" scoped>
  .about {
    box-sizing: border-box;
    padding: 20px;
    .about-body {
      margin-top: 44px;
      font-size: 16px;
      a {
        color: #007fff;
      }
    }
  }

</style>
