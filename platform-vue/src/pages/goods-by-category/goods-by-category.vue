<template>
  <page :better-scroll="true" page-background="white">
    <goods-by-category-header
        slot="top"
        :subCategory="subCategory"
        :sid="sid"
        @change-sub-category="changeSubCategory"></goods-by-category-header>
    <goods-by-category-list :list="goodsByCategory"></goods-by-category-list>
  </page>
</template>

<script type="text/ecmascript-6">
import {mapState, mapActions} from 'vuex'
import goodsByCategoryHeader from './goods-by-category-header'
import goodsByCategoryList from './goods-by-category-list'
export default {
  data () {
    return {
      pid: this.$route.query.pid,
      sid: this.$route.query.sid
    }
  },
  computed: {
    ...mapState('category', ['subCategory', 'goodsByCategory'])
  },
  components: {
    goodsByCategoryHeader,
    goodsByCategoryList
  },
  methods: {
    ...mapActions('category', ['getSubCategoryByPid', 'getGoodsByCategory']),
    changeSubCategory (o) {
      this.getGoodsByCategory(o.id)
    },
    init () {
      this.getSubCategoryByPid(this.pid).then((res) => {
        this.getGoodsByCategory(res.sub[0].id)
      })
    }
  },
  created () {
    this.init()
  }
}
</script>
