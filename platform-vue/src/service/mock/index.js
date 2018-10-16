import Mock from 'mockjs'
import {req} from './req'

Mock.mock(/\/mock/, req)

export default Mock
