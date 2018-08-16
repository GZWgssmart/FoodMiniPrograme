import env from './env'

// 开发环境url地址
const DEV_URL = 'https://www.easy-mock.com/mock/5add9213ce4d0e69998a6f51/iview-admin/'
// 正式环境url地址
const PRO_URL = 'https://produce.com'

export default env === 'development' ? DEV_URL : PRO_URL
