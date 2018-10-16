<template>
  <page :better-scroll="true">
    <evaluate-outline></evaluate-outline>
    <evaluate-content></evaluate-content>
  </page>
</template>

<script type="text/ecmascript-6">
import evaluateOutline from './evaluate-outline'
import evaluateContent from './evaluate-content'

export default {
  components: {
    evaluateOutline,
    evaluateContent
  }
}
</script>
