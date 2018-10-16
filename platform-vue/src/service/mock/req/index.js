import user from './user'
import cart from './cart'
import common from './common'
import category from './category'
import goods from './goods'
import home from './home'
import order from './order'
import search from './search'
import topic from './topic'

const module = {
  user,
  cart,
  common,
  category,
  goods,
  home,
  order,
  search,
  topic
}

export function req (req) {
  const [m, a] = [req.url.split('/')[2], req.url.split('/')[3]]
  return module[m][a]()
}
