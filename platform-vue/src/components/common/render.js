export default {
  functional: true,
  props: ['render'],
  render: (h, ctx) => {
    return ctx.props.render(h)
  }
}
