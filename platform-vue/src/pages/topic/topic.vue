<template>
  <page :better-scroll="true">
    <m-nav-header slot="top"></m-nav-header>
    <topic-banner :bannerList="bannerList"></topic-banner>
    <topic-channels></topic-channels>
    <topic-recommend></topic-recommend>
  </page>
</template>

<script type="text/ecmascript-6">
import mNavHeader from '../common/nav-header'
import topicBanner from './topic-banner'
import topicChannels from './topic-channels'
import topicRecommend from './topic-recommend'
export default {
  data () {
    return {
      bannerList: [
        {
          image: '//yanxuan.nosdn.127.net/9456ac67abbffb2256380ab66b355711.jpg?imageView&quality=75'
        },
        {
          image: '//yanxuan.nosdn.127.net/64b862a9d0306b2b6a6a90ea9dc2a6db.jpg?imageView&quality=75'
        }
      ]
    }
  },
  components: {
    mNavHeader,
    topicBanner,
    topicChannels,
    topicRecommend
  }
}
</script>
