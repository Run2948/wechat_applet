// var NewApiRootUrl = 'https://shop.51shop.ink/demo/api/'

var NewApiRootUrl = 'http://dev.51shop.ink/multiuser/api/';
//  var NewApiRootUrl = 'http://192.168.2.15:8080/platform-framework/api/';
// var NewApiRootUrl = 'http://dev.51shop.ink/demo/api/'
// var NewApiRootUrl = 'https://fx.51shop.ink/drsshop/api/'
module.exports = {
  IndexUrlNewGoods: NewApiRootUrl + 'index/newGoods', //
  IndexUrlHotGoods: NewApiRootUrl + 'index/hotGoods', //首页数据接口
  IndexUrlTopic: NewApiRootUrl + 'index/topic', //首页数据接口
  IndexUrlBrand: NewApiRootUrl + 'index/brand', //首页数据接口IndexUrlChannel
  IndexUrlCategory: NewApiRootUrl + 'index/category', //首页数据接口IndexUrlChannel
  IndexUrlBanner: NewApiRootUrl + 'index/banner', //首页数据接口IndexUrlChannel
  IndexUrlChannel: NewApiRootUrl + 'index/channel', //首页数据接口IndexUrlChannel
  IndexUrlSkill: NewApiRootUrl + 'index/secKill', //首页秒杀产品
    CatalogList: NewApiRootUrl + 'catalog/index',  //分类目录全部分类数据接口
    CatalogCurrent: NewApiRootUrl + 'catalog/current',  //分类目录当前分类数据接口

    AuthLoginByWeixin: NewApiRootUrl + 'auth/login_by_weixin', //微信登录

    GoodsCount: NewApiRootUrl + 'goods/count',  //统计商品总数
    GoodsList: NewApiRootUrl + 'goods/list',  //获得商品列表
    GoodsCategory: NewApiRootUrl + 'goods/category',  //获得分类数据
    GoodsDetail: NewApiRootUrl + 'goods/detail',  //获得商品的详情
    GoodsNew: NewApiRootUrl + 'goods/new',  //新品
    GoodsHot: NewApiRootUrl + 'goods/hot',  //热门
    GoodsRelated: NewApiRootUrl + 'goods/related',  //商品详情页的关联商品（大家都在看）

    BrandList: NewApiRootUrl + 'brand/list',  //品牌列表
    BrandDetail: NewApiRootUrl + 'brand/detail',  //品牌详情

    CartList: NewApiRootUrl + 'cart/index', //获取购物车的数据
    CartAdd: NewApiRootUrl + 'cart/add', // 添加商品到购物车
    BuyAdd: NewApiRootUrl + 'buy/add', // 直接购买
    CartUpdate: NewApiRootUrl + 'cart/update', // 更新购物车的商品
    CartDelete: NewApiRootUrl + 'cart/delete', // 删除购物车的商品
    CartChecked: NewApiRootUrl + 'cart/checked', // 选择或取消选择商品
    CartGoodsCount: NewApiRootUrl + 'cart/goodscount', // 获取购物车商品件数
    CartCheckout: NewApiRootUrl + 'cart/checkout', // 下单前信息确认

    BuyCheckout: NewApiRootUrl + 'buy/checkout', // 下单前信息确认

    OrderSubmit: NewApiRootUrl + 'order/submit', // 提交订单
    PayPrepayId: NewApiRootUrl + 'pay/prepay', //获取微信统一下单prepay_id

    CollectList: NewApiRootUrl + 'collect/list',  //收藏列表
    CollectAddOrDelete: NewApiRootUrl + 'collect/addordelete',  //添加或取消收藏

    CommentList: NewApiRootUrl + 'comment/list',  //评论列表
    CommentCount: NewApiRootUrl + 'comment/count',  //评论总数
    CommentPost: NewApiRootUrl + 'comment/post',   //发表评论

  TopicList: NewApiRootUrl + 'topic/list',  //专题列表
    TopicDetail: NewApiRootUrl + 'topic/detail',  //专题详情
    TopicRelated: NewApiRootUrl + 'topic/related',  //相关专题

    SearchIndex: NewApiRootUrl + 'search/index',  //搜索页面数据
    SearchResult: NewApiRootUrl + 'search/result',  //搜索数据
    SearchHelper: NewApiRootUrl + 'search/helper',  //搜索帮助
    SearchClearHistory: NewApiRootUrl + 'search/clearhistory',  //搜索帮助

  AddressList: NewApiRootUrl + 'address/addressUserlist',  //收货地址列表
  // AddressList: NewApiRootUrl + 'address/list',  //收货地址列表
    AddressDetail: NewApiRootUrl + 'address/detail',  //收货地址详情
    AddressSave: NewApiRootUrl + 'address/save',  //保存收货地址
    AddressDelete: NewApiRootUrl + 'address/delete',  //保存收货地址

    RegionList: NewApiRootUrl + 'region/list',  //获取区域列表

    OrderList: NewApiRootUrl + 'order/list',  //订单列表
    OrderDetail: NewApiRootUrl + 'order/detail',  //订单详情
    OrderCancel: NewApiRootUrl + 'order/cancelOrder',  //取消订单
    OrderConfirm: NewApiRootUrl + 'order/confirmOrder',  //确认收货

    FootprintList: NewApiRootUrl + 'footprint/list',  //足迹列表
    FootprintDelete: NewApiRootUrl + 'footprint/delete',  //删除足迹
    
    FeedbackAdd: NewApiRootUrl + 'feedback/save', //添加反馈
    SmsCode: NewApiRootUrl + 'sendRegisterCode', //发送短信
    BindMobile: NewApiRootUrl + 'inviteReg', //fx注册
    Login: NewApiRootUrl + 'auth/login', //账号登录
    Register: NewApiRootUrl + 'auth/register', //注册
    CouponList: NewApiRootUrl + 'coupon/list', // 优惠券列表
    GoodsCouponList: NewApiRootUrl + 'coupon/listByGoods', // 商品优惠券列表   
    OrderQuery: NewApiRootUrl + 'pay/query',
    OrderSuccess: NewApiRootUrl + 'order/updateSuccess',
    CustomerSave: NewApiRootUrl + 'customer/save.do', //新增客户
  CustomerEdit: NewApiRootUrl + 'customer/update.do', //修改客户
    CustomerList: NewApiRootUrl +'customer/queryList.do', //客户列表查询分页
  AllCustomerList: NewApiRootUrl + 'customer/queryAllList.do', //客户列表查询分页
  UpkeepList: NewApiRootUrl +'upkeep/queryUpkeepList.do', //客户管理列表
  dgKeepList: NewApiRootUrl + 'upkeep/queryList.do', //单个用户的客户管理列
  ServiceGoods:NewApiRootUrl + 'index/serviceGoods', //首页所有商品
  QueryList: NewApiRootUrl +'upkeep/queryList.do', //单个客户的维护列表
  WhSave: NewApiRootUrl +'upkeep/save.do',//维护历史保存
  WhUpdate: NewApiRootUrl + 'upkeep/update.do',//维护历史修改
  QueryObject: NewApiRootUrl+ 'upkeep/queryObject.do',//
  CustomerObject: NewApiRootUrl +'customer/queryObject.do',
  IsRealValidate: NewApiRootUrl + 'customer/isRealValidate.do',//实名认证
  BirthdayList: NewApiRootUrl + 'user/getBirthdayList',//生日列表
  Holiday: NewApiRootUrl + 'user/getHoliday',//节假日提醒
  CreateCode: NewApiRootUrl + 'auth/createCode',
  UserInfoById: NewApiRootUrl + 'user/getUserInfoById.do',//获取实名认证信息
  DtoLis: NewApiRootUrl +'customer/queryDtoList.do',//获取客户信息
  GetCount: NewApiRootUrl +'customer/getCount.do',//获取客户统计
  UpkeepUpdate: NewApiRootUrl+'upkeep/update.do',//编辑维护历史,
  CouponListByMer: NewApiRootUrl +'coupon/listMer.do',//商户优惠卷
  TakeMerCoupon: NewApiRootUrl + 'coupon/getMerCoupon.do',//商户优惠卷
  ValidCouponList: NewApiRootUrl + 'coupon/getValidCouponList.do',//选择优惠卷列表
  FansList: NewApiRootUrl + 'user/getSonUser',//我的粉丝
  FUser: NewApiRootUrl + 'user/getFUser',//我的推荐人
  Commission: NewApiRootUrl + 'user/getMlsUser',//佣金
  InsShareGoods: NewApiRootUrl + 'user/insShareGoods',//新增分享历史
  GetShareGoods: NewApiRootUrl + 'user/getShareGoods',//获取分享历史
  WithdrawCashes: NewApiRootUrl + 'user/withdrawCashes',//提现
  SetFid: NewApiRootUrl + 'mlsuser/setFid',//mlsuser/setFid
  GroupList: NewApiRootUrl + 'goods/group.do',//团购
  KillList: NewApiRootUrl + 'goods/kill.do',//秒杀
  GroupBuyList: NewApiRootUrl + 'buy/getGroupBuyList.do',//团购列表
  
  
}; 
