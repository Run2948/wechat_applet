export default {
  getSearchHot: req => {
    return {
      success: true,
      code: 200,
      data: [
        {
          id: 1,
          name: '8H超品日'
        },
        {
          id: 2,
          name: '烘洗一体机'
        },
        {
          id: 3,
          name: '游戏本'
        },
        {
          id: 4,
          name: '旅行箱'
        },
        {
          id: 5,
          name: '按摩靠枕'
        }
      ],
      msg: '获取热门搜索成功'
    }
  },
  getSearchListBykeyword: req => {
    return {
      success: true,
      code: 200,
      data: {
        recommend: {
          pic: 'https://img.youpin.mi-img.com/800_pic/b7328e3dda65010f5ee5ff86f9c2d021.png@base@tag=imgScale&h=350&w=350&et=1&eth=480&etw=480&etc=FFFFFF',
          name: '小米8 透明探索版',
          desc: '3D结构光技术，更安全的面部解锁 / 压感屏幕指纹 / 双频GPS / 骁龙845处理器 / AI变焦双摄 / 三星 AMOLED 屏',
          price: '3699'
        },
        hot: [
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
        ]
      },
      msg: '获取搜索结果成功'
    }
  }
}
