export default {
  getHomeInfo: req => {
    return {
      success: true,
      code: 200,
      data: {
        recommend: {
          swipper: [
            {
              image: 'https://yanxuan.nosdn.127.net/b8dc2f69673852d93f04ffec0ca522e3.jpg?imageView&thumbnail=750x0&quality=75'
            },
            {
              image: 'https://yanxuan.nosdn.127.net/0e78165e335c1307c79027a745eb3143.jpg?imageView&thumbnail=750x0&quality=75'
            },
            {
              image: 'https://yanxuan.nosdn.127.net/15ffdaa798eb00ca122efcd72292cfa1.jpg?imageView&thumbnail=750x0&quality=75'
            }
          ],
          directSupply: [
            {
              name: 'CK制造商',
              price: '25',
              img: 'http://yanxuan.nosdn.127.net/2cb9d45516776f5fee3fd958b174c23a.png?imageView&thumbnail=355x0&quality=65'
            },
            {
              name: 'Kenneth Cole制造商',
              price: '55',
              img: 'http://yanxuan.nosdn.127.net/f21dd124093bf7ff49ef9b8a9c813d1f.png?imageView&thumbnail=355x0&quality=65'
            },
            {
              name: 'Ralph Lauren制造商',
              price: '19',
              img: 'http://yanxuan.nosdn.127.net/7f55f337f98a198ac3d629847171d5d2.png?imageView&thumbnail=355x0&quality=65'
            }
          ],
          release: [
            {
              name: '网易智造X3 Plus蓝牙HiFi耳机',
              desc: '新一代X3蓝牙耳机 全新升级',
              price: '99',
              img: 'http://yanxuan.nosdn.127.net/ccdbdc82bdc8929723e4941a93f85550.png?imageView&quality=65&thumbnail=330x330'
            }
          ],
          popular: [
            {
              name: '网易智造减压眼部按摩仪',
              desc: '让眼睛享受全方位按摩',
              price: '381.7',
              img: 'http://yanxuan.nosdn.127.net/73af63aa5c47c369fe7e2f30b3815aac.png?imageView&quality=65&thumbnail=330x330'
            }
          ]
        },
        section: [
          {
            id: 22,
            tag: 'live',
            title: '居家',
            items: [
              {
                name: '自然光多功能触控阅读灯',
                desc: '12档拟自然光源，轻薄冷峻线条',
                price: '99',
                img: 'http://yanxuan.nosdn.127.net/2ab7b86be448341df0247b4e44fc2e3b.png?imageView&quality=65&thumbnail=330x330'
              },
              {
                name: '模块懒人随席垫',
                desc: '自由变化，贴心承托可拆洗',
                price: '459',
                img: 'http://yanxuan.nosdn.127.net/a72122fe6d88938b14557660a08ce231.png?imageView&quality=65&thumbnail=330x330'
              }
            ]
          },
          {
            id: 23,
            tag: 'bag',
            title: '鞋包配饰',
            items: [
              {
                name: '牛皮拉链单肩包',
                desc: '精简商务风，实用为先',
                price: '399',
                img: 'http://yanxuan.nosdn.127.net/c510a46d45d0201643b794a789d3a88d.png?imageView&quality=65&thumbnail=330x330'
              }
            ]
          }
        ]
      },
      msg: '获取首页信息成功'
    }
  }
}
