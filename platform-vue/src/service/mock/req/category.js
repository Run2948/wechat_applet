export default {
  getCategory: req => {
    return {
      success: true,
      code: 200,
      data: [
        {
          id: 0,
          name: '推荐专区',
          banner: 'http://yanxuan.nosdn.127.net/d872a3433859f8b4f35aabcb1b6e5fad.jpg?imageView&thumbnail=0x196&quality=75',
          sub: [
            {
              id: 22,
              name: '母婴特惠',
              pic: 'http://yanxuan.nosdn.127.net/81edf1f5ea3500de14f560f1b3ee78c4.png?imageView&quality=85&thumbnail=144x144'
            },
            {
              id: 23,
              name: '美食低至5.5折',
              pic: 'http://yanxuan.nosdn.127.net/a61e1f12c7bed9882a865c4823844920.jpg?imageView&quality=85&thumbnail=144x144'
            },
            {
              id: 24,
              name: '美食每满150减30',
              pic: 'http://yanxuan.nosdn.127.net/6a84c7006b92ccf5488219dd27059ae3.png?imageView&quality=85&thumbnail=144x144'
            },
            {
              id: 25,
              name: '严选黑标',
              pic: 'http://yanxuan.nosdn.127.net/9c7fa95ceb9a9af2905d25ce5d2ad4fe.png?imageView&quality=85&thumbnail=144x144'
            }
          ]
        },
        {
          id: 1,
          name: '爆品区'
        },
        {
          id: 2,
          name: '夏季专区'
        },
        {
          id: 3,
          name: '居家'
        },
        {
          id: 4,
          name: '鞋包配饰'
        },
        {
          id: 5,
          name: '服装'
        },
        {
          id: 6,
          name: '电器'
        },
        {
          id: 7,
          name: '洗护'
        },
        {
          id: 8,
          name: '餐厨'
        },
        {
          id: 9,
          name: '婴童'
        },
        {
          id: 10,
          name: '文体'
        },
        {
          id: 11,
          name: '特色区'
        }
      ],
      msg: '获取分类数据成功'
    }
  },
  getSubCategoryByPid: req => {
    return {
      success: true,
      code: 200,
      data: {
        id: 0,
        name: '推荐专区',
        banner: 'http://yanxuan.nosdn.127.net/d872a3433859f8b4f35aabcb1b6e5fad.jpg?imageView&thumbnail=0x196&quality=75',
        sub: [
          {
            id: 22,
            name: '母婴特惠',
            pic: 'http://yanxuan.nosdn.127.net/81edf1f5ea3500de14f560f1b3ee78c4.png?imageView&quality=85&thumbnail=144x144'
          },
          {
            id: 23,
            name: '美食低至5.5折',
            pic: 'http://yanxuan.nosdn.127.net/a61e1f12c7bed9882a865c4823844920.jpg?imageView&quality=85&thumbnail=144x144'
          },
          {
            id: 24,
            name: '美食每满150减30',
            pic: 'http://yanxuan.nosdn.127.net/6a84c7006b92ccf5488219dd27059ae3.png?imageView&quality=85&thumbnail=144x144'
          },
          {
            id: 25,
            name: '严选黑标',
            pic: 'http://yanxuan.nosdn.127.net/9c7fa95ceb9a9af2905d25ce5d2ad4fe.png?imageView&quality=85&thumbnail=144x144'
          }
        ]
      },
      msg: '获取分类数据成功'
    }
  },
  getGoodsByCategory: req => {
    return {
      success: true,
      code: 200,
      data: [
        {
          pic: 'https://img.youpin.mi-img.com/800_pic/7887b091dafa47cb751ab9728b9a42f6.png@base@tag=imgScale&h=350&w=350&et=1&eth=480&etw=480&etc=FFFFFF',
          name: '多亲AI功能电话',
          desc: '内置小爱同学，语音红外遥控器，立体环绕，双卡双待',
          price: '299'
        },
        {
          pic: 'https://img.youpin.mi-img.com/800_pic/b7328e3dda65010f5ee5ff86f9c2d021.png@base@tag=imgScale&h=350&w=350&et=1&eth=480&etw=480&etc=FFFFFF',
          name: '小米8 透明探索版',
          desc: '3D结构光技术，更安全的面部解锁 / 压感屏幕指纹 / 双频GPS / 骁龙845处理器 / AI变焦双摄 / 三星 AMOLED 屏',
          price: '3699'
        },
        {
          pic: 'http://img.youpin.mi-img.com/800_pic/6874c0c9ab598a3fbaf7bbae5fe34ede.png@base@tag=imgScale&h=350&w=350&et=1&eth=480&etw=480&etc=FFFFFF',
          name: '红米6A',
          desc: '12nm高性能处理器 / 5.45" 小巧全面屏 / 1300万高清相机 / “小杨柳腰”机身',
          price: '599'
        }
      ],
      msg: '获取商品数据成功'
    }
  }
}
