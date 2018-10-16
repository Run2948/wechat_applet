export const getters = {
  navList: state => {
    let arr = [
      {
        id: 0,
        tag: 'recommend',
        name: '推荐'
      }
    ]
    for (let i = 0; i < state.section.length; i++) {
      let item = {
        id: state.section[i].id,
        tag: state.section[i].tag,
        name: state.section[i].title
      }
      arr.push(item)
    }
    return arr
  }
}
