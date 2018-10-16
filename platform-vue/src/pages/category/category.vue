<template>
  <page>
    <category-header slot="top"></category-header>
    <category-content
        :list="category"
        @selectSubList="selectSubList"></category-content>
  </page>
</template>

<script type="text/ecmascript-6">
import {mapState, mapActions} from 'vuex'
import categoryHeader from './category-header'
import categoryContent from './category-content'
export default{
  computed: {
    ...mapState('category', ['category'])
  },
  components: {
    categoryHeader,
    categoryContent
  },
  methods: {
    ...mapActions('category', ['getCategory']),
    selectSubList (o) {
      this.$go(`/goods-by-category?pid=${o.parent.id}&sid=${o.sub.id}`)
    }
  },
  created () {
    this.getCategory()
  }
}
</script>
