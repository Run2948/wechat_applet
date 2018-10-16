<template>
  <div class="search-section">
    <div class="section-header">
      <div class="header-left">
        <span>{{title}}</span>
      </div>
      <div class="header-right">
        <slot name="right"></slot>
      </div>
    </div>
    <div class="section-content" :style="styles">
      <slot></slot>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  props: ['title', 'styles']
}
</script>

<style lang="less" scoped>
  .search-section{
    background: #f4f4f4;
    .section-header{
      display: flex;
      justify-content: space-between;
      height: 75px;
      padding:0 28px;
      .header-left {
        display: flex;
        flex-direction: column;
        justify-content: flex-end;
        span{
          padding-bottom:20px;
          color:#666;
        }
      }
      .header-right {
        display: flex;
        flex-direction: column;
        justify-content: flex-end;
        i{
          padding-bottom:20px;
          color:#666;
        }
      }
    }
    .section-content{
      padding:34px 28px 52px 28px;
      background: white;
    }
  }
</style>
