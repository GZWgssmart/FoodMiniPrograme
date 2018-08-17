import env from './env'

// 开发环境url地址
const DEV_URL = 'http://127.0.0.1'
// 正式环境url地址
const PRO_URL = 'https://produce.com'

export default env === 'development' ? DEV_URL : PRO_URL
