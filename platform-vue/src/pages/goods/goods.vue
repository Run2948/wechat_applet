<template>
  <page
      ref="scroll"
      :better-scroll="true"
      :header-immerse="true"
      @scroll="scroll">
    <goods-header
        slot="top"
        :scroll-y="scrollY"
        @clickTab="clickTab"></goods-header>
    <goods-swipper :swipperList="swipperList"></goods-swipper>
    <goods-info></goods-info>
    <goods-number
      @change="goodsNumberChange"></goods-number>
    <goods-spec></goods-spec>
    <goods-instructions></goods-instructions>
    <goods-evaluate></goods-evaluate>
    <goods-shop></goods-shop>
    <goods-details :detailsSet="detailsSet"></goods-details>
    <goods-action slot="bottom"></goods-action>
  </page>
</template>

<script type="text/ecmascript-6">
import goodsHeader from './goods-header'
import goodsSwipper from './goods-swipper'
import goodsInfo from './goods-info'
import goodsNumber from './goods-number'
import goodsSpec from './goods-spec'
import goodsInstructions from './goods-instructions'
import goodsEvaluate from './goods-evaluate'
import goodsShop from './goods-shop'
import goodsDetails from './goods-details'
import goodsAction from './goods-action'

export default {
  data () {
    return {
      swipperList: [
        {
          image: 'https://shop.io.mi-img.com/app/shop/img?id=shop_bb047e7522a92fb11fd12cd96f8180fb.jpeg&w=1080&h=1270&crop=a_0_95_1080_1080&t=webp'
        },
        {
          image: 'https://shop.io.mi-img.com/app/shop/img?id=shop_1da3b5d694c610fc6b97b0ff01e4c29b.jpeg&w=1080&h=1270&crop=a_0_95_1080_1080&t=webp'
        },
        {
          image: 'https://shop.io.mi-img.com/app/shop/img?id=shop_1da3b5d694c610fc6b97b0ff01e4c29b.jpeg&w=1080&h=1270&crop=a_0_95_1080_1080&t=webp'
        }
      ],
      detailsSet: {
        summary: [
          {
            img: 'https://shop.io.mi-img.com/app/shop/img?id=shop_3020bee0ff8c8d7179187f329e682e74.jpeg&w=1080&h=595&t=webp'
          },
          {
            img: 'https://shop.io.mi-img.com/app/shop/img?id=shop_767a057d8e3ecbde11965f2a37befc53.jpeg&w=1080&h=595&t=webp'
          },
          {
            img: 'https://shop.io.mi-img.com/app/shop/img?id=shop_23ada34802c731ddf43e2581f9b2edac.jpeg&w=1080&h=596&t=webp'
          },
          {
            img: 'https://shop.io.mi-img.com/app/shop/img?id=shop_0d53718b702596b52ec52b20576c4755.jpeg&w=1080&h=595&t=webp'
          },
          {
            img: 'https://shop.io.mi-img.com/app/shop/img?id=shop_83f5e8d8764e9f19fee7c07eede250b5.jpeg&w=1080&h=595&t=webp'
          },
          {
            img: 'src="https://shop.io.mi-img.com/app/shop/img?id=shop_4068b055b2ebafe86652db622915f59e.jpeg&w=1080&h=595&t=webp"'
          },
          {
            img: 'https://shop.io.mi-img.com/app/shop/img?id=shop_f3917f388407cd9383891c57955b05ac.jpeg&w=1080&h=595&t=webp'
          },
          {
            img: 'https://shop.io.mi-img.com/app/shop/img?id=shop_d5e8914600a46d3b9fc3cda27dcadbe1.jpeg&w=1080&h=596&t=webp'
          },
          {
            img: 'https://shop.io.mi-img.com/app/shop/img?id=shop_43553f4f19ace000c4929ce37fd3ce24.jpeg&w=1080&h=595&t=webp'
          },
          {
            img: 'https://shop.io.mi-img.com/app/shop/img?id=shop_e1db0a81f37dc7ce9def43b716279033.jpeg&w=1080&h=595&t=webp'
          },
          {
            img: 'https://shop.io.mi-img.com/app/shop/img?id=shop_45e08eeba2ac6b42e2c0a801718ab4f1.jpeg&w=1080&h=595&t=webp'
          },
          {
            img: 'https://shop.io.mi-img.com/app/shop/img?id=shop_0cd89132ea13dfd376a2c70d74ade06f.jpeg&w=1080&h=595&t=webp'
          },
          {
            img: 'https://shop.io.mi-img.com/app/shop/img?id=shop_f5dd768f60b6270188f7602389937ce5.jpeg&w=1080&h=596&t=webp'
          },
          {
            img: 'https://shop.io.mi-img.com/app/shop/img?id=shop_09378fe6de18dbd10966a933bdb9228d.jpeg&w=1080&h=595&t=webp'
          },
          {
            img: 'https://shop.io.mi-img.com/app/shop/img?id=shop_bcd5beca3fb9f65a0b970c3cfd7e1be4.jpeg&w=1080&h=595&t=webp'
          },
          {
            img: 'https://shop.io.mi-img.com/app/shop/img?id=shop_8cf785a9add4fba2ab6d90fc42961655.jpeg&w=1080&h=595&t=webp'
          },
          {
            img: 'https://shop.io.mi-img.com/app/shop/img?id=shop_7ca79c37e84ce425d38870c44aa92b13.jpeg&w=1080&h=595&t=webp'
          },
          {
            img: 'https://shop.io.mi-img.com/app/shop/img?id=shop_b5e634ec3a32263946e4d1068d7c0be3.jpeg&w=1080&h=596&t=webp'
          },
          {
            img: 'https://shop.io.mi-img.com/app/shop/img?id=shop_fd84e636891c2d90d7d7bf148908895d.jpeg&w=1080&h=595&t=webp'
          },
          {
            img: 'https://shop.io.mi-img.com/app/shop/img?id=shop_82c3c8ebd47fb2f25c1a0efba7987563.jpeg&w=1080&h=595&t=webp'
          },
          {
            img: 'https://shop.io.mi-img.com/app/shop/img?id=shop_a8348c4ac33d24bc2186cb7a53f20427.jpeg&w=1080&h=595&t=webp'
          },
          {
            img: 'https://shop.io.mi-img.com/app/shop/img?id=shop_d1d71423b16648e95350a82b5f41607b.jpeg&w=1080&h=595&t=webp'
          },
          {
            img: 'https://shop.io.mi-img.com/app/shop/img?id=shop_124b9b00b5e64160fab1678210c0561f.jpeg&w=1080&h=596&t=webp'
          },
          {
            img: 'https://shop.io.mi-img.com/app/shop/img?id=shop_2de10b2ad67fabf8affe8a2944e2a5a5.jpeg&w=1080&h=595&t=webp'
          },
          {
            img: 'https://shop.io.mi-img.com/app/shop/img?id=shop_049ce8c2339157a9b82cc204b310463b.jpeg&w=1080&h=595&t=webp'
          },
          {
            img: 'https://shop.io.mi-img.com/app/shop/img?id=shop_dc439e30cffd08010a148b0de92965dc.jpeg&w=1080&h=595&t=webp'
          },
          {
            img: 'https://shop.io.mi-img.com/app/shop/img?id=shop_c89817ed64095ef1a3901d7b1ac401be.jpeg&w=1080&h=595&t=webp'
          },
          {
            img: 'https://shop.io.mi-img.com/app/shop/img?id=shop_dd987502a8a0d6507e0602b4d4675fae.jpeg&w=1080&h=596&t=webp'
          },
          {
            img: 'https://shop.io.mi-img.com/app/shop/img?id=shop_937c1c22261cb5a88f15db0ce0ab5553.jpeg&w=1080&h=595&t=webp'
          },
          {
            img: 'https://shop.io.mi-img.com/app/shop/img?id=shop_bb34aa6e9fab71b83ee924c81342f111.jpeg&w=1080&h=595&t=webp'
          },
          {
            img: 'https://shop.io.mi-img.com/app/shop/img?id=shop_ea79a77d4d423ca340d84643370a84ed.jpeg&w=1080&h=444&t=webp'
          }
        ]
      },
      scrollY: 0
    }
  },
  components: {
    goodsHeader,
    goodsSwipper,
    goodsInfo,
    goodsNumber,
    goodsSpec,
    goodsInstructions,
    goodsEvaluate,
    goodsShop,
    goodsDetails,
    goodsAction
  },
  methods: {
    scroll (e) {
      this.scrollY = e.y
    },
    clickTab (n) {
      if (n === 0) {
        this.$refs.scroll.scrollTo([0, 0, 800])
      }
      if (n === 1) {
        this.$refs.scroll.scrollTo('#goods-details')
      }
      if (n === 2) {
        this.$go('/evaluate')
      }
    },
    goodsNumberChange (o) {
      // @TODO
    }
  }
}
</script>
