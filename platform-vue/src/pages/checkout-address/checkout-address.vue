<template>
  <page page-background="white">
    <address-item @update="update"></address-item>
  </page>
</template>

<script type="text/ecmascript-6">
import addressItem from './common/address-item'
export default {
  components: {
    addressItem
  },
  methods: {
    update () {
      this.$go('/address/update')
    }
  }
}
</script>
