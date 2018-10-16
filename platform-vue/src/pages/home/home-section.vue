<template>
  <div :data-tag="tag" class="home-section">
    <m-floor :title="`${title}好物`">
      <div class="section-wrap">
        <div class="wrap-item" :key="key" v-for="(item, key) in list">
          <m-goods :info="item"></m-goods>
        </div>
        <div class="wrap-item">
          <div class="link" data-ratio="1:1">
            <div class="link-wrap">
              <h4>更多{{title}}好物</h4>
              <i class="iconfont">&#xe617;</i>
            </div>
          </div>
        </div>
      </div>
    </m-floor>
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  props: ['title', 'tag', 'list']
}
</script>

<style lang="less" scoped>
  .home-section {
    margin-bottom: 20px;
    .section-wrap {
      display: flex;
      flex-wrap: wrap;
      padding-bottom: 28px;
      .wrap-item {
        position: relative;
        flex-basis: calc(50% - 3.5px);
        flex-grow: 0;
        width: 0;
        .link {
          position: relative;
          height: 0;
          width: 100%;
          background: #f4f4f4;
          &[data-ratio="1:1"] {
            padding-top: calc(100% * 1 / 1);
          }
          .link-wrap {
            position: absolute;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            h4 {
              font-size: 34px;
              color: #666;
            }
            i {
              margin-top: 28px;
              font-size: 70px;
              color: #999;
            }
          }
        }
        &:nth-child(2n + 1) {
          margin: 0 7px 7px 0;
        }
        &:nth-child(2n) {
          margin-bottom: 7px;
        }
      }
    }
  }
</style>
