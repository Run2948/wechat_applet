<template>
  <div class="top-recommend">
    <m-floor title="为您推荐" styles="padding:0 .37333rem .37333rem .37333rem">
      <topic-recommend-main></topic-recommend-main>
    </m-floor>
  </div>
</template>

<script type="text/ecmascript-6">
import topicRecommendMain from './topic-recommend-main'
export default {
  components: {
    topicRecommendMain
  }
}
</script>
