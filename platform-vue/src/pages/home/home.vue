<template>
  <page
    :better-scroll="true"
    ref="scroll">
    <home-header
      slot="top"
      :currentNav="0"
      :navList="navList"
      @change-nav="changeNav"></home-header>
    <home-banner :list="recommend.swipper"></home-banner>
    <home-direct-supply :list="recommend.directSupply"></home-direct-supply>
    <home-release :list="recommend.release"></home-release>
    <home-popular :list="recommend.popular"></home-popular>
    <home-flash></home-flash>
    <home-sale></home-sale>
    <home-section
    :key="key"
    :title="item.title"
    :tag="item.tag"
    :list="item.items"
    v-for="(item, key) in section"></home-section>
    <nav-footer></nav-footer>
  </page>
</template>

<script type="text/ecmascript-6">
import {mapState, mapGetters, mapActions} from 'vuex'
import homeHeader from './home-header'
import homeBanner from './home-banner'
import homeDirectSupply from './home-direct-supply'
import homeRelease from './home-release'
import homePopular from './home-popular'
import homeFlash from './home-flash'
import homeSale from './home-sale'
import homeSection from './home-section'
import navFooter from '../common/nav-footer'

export default {
  computed: {
    ...mapState('home', ['recommend', 'section']),
    ...mapGetters('home', ['navList'])
  },
  components: {
    homeHeader,
    homeBanner,
    homeDirectSupply,
    homeRelease,
    homePopular,
    homeFlash,
    homeSale,
    homeSection,
    navFooter
  },
  methods: {
    ...mapActions('home', ['getHomeInfo']),
    changeNav (o) {
      this.$refs.scroll.scrollTo(`[data-tag=${o.tag}]`)
    }
  },
  created () {
    this.getHomeInfo()
  }
}
</script>
